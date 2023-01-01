package touk.recru.app.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import touk.recru.app.entity.Movie;
import touk.recru.app.entity.Screening;
import touk.recru.app.entity.ScreeningRoom;
import touk.recru.app.repository.movie.MovieRepository;
import touk.recru.app.repository.room.ScreeningRoomRepository;
import touk.recru.app.repository.screening.ScreeningRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScreeningBootstrap {
	private final ScreeningRepository screeningRepository;
	private final MovieRepository movieRepository;
	private final ScreeningRoomRepository screeningRoomRepository;

	public void init() {
		List<Screening> screenings = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		List<ScreeningRoom> screeningRooms = screeningRoomRepository.findAll();
		LocalDateTime start = LocalDateTime.now();
		for (int i = 0; i < 100; i++) {
			screenings.add(Screening.builder()
					.screeningTime(start)
					.movie(movies.get(i % movies.size()))
					.screeningRoom(screeningRooms.get(i % screeningRooms.size()))
					.build());
			if (i % 5 == 0) {
				start = start.plusHours(12);
			}
		}
		screeningRepository.saveAll(screenings);
	}
}
