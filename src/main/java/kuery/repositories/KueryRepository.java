package kuery.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface KueryRepository<T, ID>
        extends PagingAndSortingRepository<T, ID>, KueryFragment<T> {

}