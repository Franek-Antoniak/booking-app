package touk.recru.app.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import touk.recru.app.entity.Movie;

@Repository
public interface MovieRepositoryImpl extends MovieRepository, JpaRepository<Movie, Long> {
}
