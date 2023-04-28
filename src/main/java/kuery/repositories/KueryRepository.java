package kuery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface KueryRepository<T, ID>
        extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>, KueryFragment<T> {

}