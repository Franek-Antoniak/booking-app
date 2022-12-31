package touk.recru.app.factory;

import touk.recru.app.entity.Movie;
import touk.recru.app.entity.Screening;
import touk.recru.app.entity.ScreeningRoom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScreeningFactory {
	public static List<Screening> create(int n, List<Movie> movies, List<LocalDateTime> dates) {
		List<Screening> screenings = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int movieIndex = i % movies.size();
			int dateIndex = i % dates.size();
			Screening temp = create(movies.get(movieIndex), dates.get(dateIndex));
			screenings.add(temp);
		}
		return screenings;
	}

	public static Screening create(Movie movie, LocalDateTime date) {
		return Screening.builder()
				.movie(movie)
				.screeningTime(date)
				.build();
	}

	public static List<Screening> create(int n, List<Movie> movies, List<LocalDateTime> dates,
			List<ScreeningRoom> rooms) {
		List<Screening> screenings = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int movieIndex = i % movies.size();
			int dateIndex = i % dates.size();
			int roomIndex = i % rooms.size();
			Screening temp = create(movies.get(movieIndex), dates.get(dateIndex), rooms.get(roomIndex));
			screenings.add(temp);
		}
		return screenings;
	}

	public static Screening create(Movie movie, LocalDateTime date, ScreeningRoom room) {
		return Screening.builder()
		                .movie(movie)
		                .screeningTime(date)
		                .screeningRoom(room)
		                .build();
	}
}
