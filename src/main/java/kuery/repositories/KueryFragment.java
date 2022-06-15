package kuery.repositories;

import kuery.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface KueryFragment<T> {
    Optional<T> findOne(Predicate predicate);
    List<T> findAll(Predicate predicate);
    Page<T> findAll(Predicate predicate, Pageable pageable);
}
