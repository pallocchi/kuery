package kuery.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import static org.springframework.data.repository.core.support.RepositoryFragment.implemented;

public class KueryRepositoryFactory extends JdbcRepositoryFactory {

    private final Class<?> REPOSITORY_TARGET_TYPE = KueryFragment.class;

    private final RelationalMappingContext context;
    private final JdbcConverter converter;
    private final NamedParameterJdbcOperations operations;

    public KueryRepositoryFactory(
        DataAccessStrategy dataAccessStrategy,
        RelationalMappingContext context,
        JdbcConverter converter,
        Dialect dialect,
        ApplicationEventPublisher publisher,
        NamedParameterJdbcOperations operations) {
        super(dataAccessStrategy, context, converter, dialect, publisher, operations);
        this.context = context;
        this.converter = converter;
        this.operations = operations;
    }

    @NotNull
    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(@NotNull RepositoryMetadata metadata) {
        RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        if (REPOSITORY_TARGET_TYPE.isAssignableFrom(repositoryInterface)) {
            Class<?> type = metadata.getDomainType();
            KueryFragment<?> querydslJdbcFragment = createSimpleQuerydslJdbcFragment(type);
            fragments = fragments.append(implemented(querydslJdbcFragment));
        }
        return fragments;
    }

    private SimpleKueryFragment<?> createSimpleQuerydslJdbcFragment(Class<?> type) {
        RelationalPersistentEntity<?> entity = context.getRequiredPersistentEntity(type);
        return instantiateClass(SimpleKueryFragment.class, entity, converter, operations.getJdbcOperations());
    }
}
