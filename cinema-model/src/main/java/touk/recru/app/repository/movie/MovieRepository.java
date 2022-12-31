package touk.recru.app.repository.movie;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import touk.recru.app.entity.Movie;

import java.util.List;

public interface MovieRepository {
	List<Movie> findAll();

	<S extends Movie> List<S> saveAll(Iterable<S> entities);
}
