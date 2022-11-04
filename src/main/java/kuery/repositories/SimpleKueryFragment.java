package kuery.repositories;

import kuery.types.Predicate;
import kuery.types.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.support.JdbcUtil;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.LongSupplier;
import java.util.stream.Stream;

import static kuery.clauses.ClausesKt.asc;
import static kuery.clauses.ClausesKt.desc;
import static kuery.clauses.ClausesKt.from;
import static kuery.clauses.ClausesKt.limit;
import static kuery.clauses.ClausesKt.offset;
import static kuery.clauses.ClausesKt.orderBy;
import static kuery.clauses.ClausesKt.select;
import static kuery.clauses.ClausesKt.where;

@Transactional(readOnly = true)
public class SimpleKueryFragment<T> implements KueryFragment<T> {

    private final Logger log = LoggerFactory.getLogger(SimpleKueryFragment.class);

    private final RelationalPersistentEntity<T> entity;
    private final JdbcConverter converter;
    private final JdbcTemplate template;

    public SimpleKueryFragment(
        RelationalPersistentEntity<T> entity,
        JdbcConverter converter,
        JdbcTemplate template) {
        this.entity = entity;
        this.converter = converter;
        this.template = template;
    }

    @Override
    public long count(Predicate predicate) {
        Assert.notNull(predicate, "Predicate must not be null!");
        return queryCount(createCountQuery(predicate));
    }

    @Override
    public Optional<T> findOne(Predicate predicate) {
        Assert.notNull(predicate, "Predicate must not be null!");
        return Optional.ofNullable(queryOne(createQuery(predicate, Pageable.unpaged())));
    }

    @Override
    public List<T> findAll(Predicate predicate) {
        Assert.notNull(predicate, "Predicate must not be null!");
        return queryMany(createQuery(predicate, Pageable.unpaged()));
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        Assert.notNull(predicate, "Predicate must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        Query countQuery = createCountQuery(predicate);
        LongSupplier count = () -> queryCount(countQuery);
        Query query = createQuery(predicate, pageable);
        List<T> content = queryMany(query);
        return PageableExecutionUtils.getPage(content, pageable, count);
    }

    protected Query createQuery(Predicate predicate, Pageable pageable) {
        Assert.notNull(predicate, "Predicate must not be null!");
        return doCreateQuery(predicate, pageable);
    }

    protected Query createCountQuery(@Nullable Predicate predicate) {
        Query query = from(select("count(*)"), entity.getTableName().getReference());
        if (predicate != null)
            query = where(query, predicate);
        return query;
    }

    private Query doCreateQuery(@Nullable Predicate predicate, Pageable pageable) {
        Query query = from(select(), entity.getTableName().getReference());
        if (predicate != null)
            query = where(query, predicate);
        if (pageable != null) {
            if (pageable.getSort().isSorted()) {
                query = orderBy(query, getSortingFields(pageable));
            }
            if (pageable.isPaged()) {
                query = limit(query, pageable.getPageSize());
                query = offset(query, pageable.getOffset());
            }
        }
        return query;
    }

    private T queryOne(Query query) {
        List<T> results = queryMany(query);
        return DataAccessUtils.singleResult(results);
    }

    private List<T> queryMany(Query query) {
        List<T> result = query(query);
        if (Objects.isNull(result))
            return Collections.emptyList();
        return result;
    }

    private long queryCount(Query query) {
        Object[] args = convertArgs(query);
        Long result = template.queryForObject(query.getStatement(), Long.class, args);
        return result != null ? result : 0;
    }

    private List<T> query(Query query) {
        Object[] args = convertArgs(query);
        if (log.isDebugEnabled())
            log.debug("Execute query [" + query.getStatement() + "] with args " + Arrays.toString(args));
        EntityRowMapper<T> mapper = new EntityRowMapper<T>(entity, converter);
        RowMapperResultSetExtractor<T> rse = new RowMapperResultSetExtractor<>(mapper);
        return template.query(query.getStatement(), rse, args);
    }

    private Object[] getSortingFields(Pageable pageable) {
        return pageable.getSort().get().map(o -> {
            if (o.getDirection().isDescending())
                return desc(o.getProperty());
            return asc(o.getProperty());
        }).toArray();
    }

    private Object[] convertArgs(Query query) {
        return Stream.of(query.getArgs())
            .map(arg -> converter.writeJdbcValue(arg, arg.getClass(), JdbcUtil.sqlTypeFor(arg.getClass())).getValue())
            .toArray(Object[]::new);
    }
}
