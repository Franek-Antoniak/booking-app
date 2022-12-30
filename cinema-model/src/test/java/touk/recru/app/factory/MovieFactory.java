package touk.recru.app.factory;

import touk.recru.app.entity.Movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieFactory {
	public static List<Movie> create(int n) {
		List<Movie> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(Movie.builder()
					.title("Movie " + i)
					.duration(Duration.ofMinutes(120))
					.build());
		}
		return list;
	}
}
