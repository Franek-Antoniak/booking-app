package touk.recru.app.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import touk.recru.app.entity.Movie;
import touk.recru.app.repository.movie.MovieRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieBootstrap {
	private final MovieRepository movieRepository;

	public void init() {
		List<Movie> movies = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			movies.add(Movie.builder()
					.title("Movie " + i)
					.duration(Duration.ofMinutes(120))
					.build());
		}
		movieRepository.saveAll(movies);
	}
}
