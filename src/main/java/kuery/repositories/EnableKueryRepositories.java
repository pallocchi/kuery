package kuery.repositories;

import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enable {@link KueryRepository} support.
 *
 * @see EnableJdbcRepositories
 */
@EnableJdbcRepositories(repositoryFactoryBeanClass = KueryRepositoryFactoryBean.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableKueryRepositories {}