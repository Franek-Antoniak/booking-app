package touk.recru.app.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.factory.ScreeningPageableFactory;
import touk.recru.app.service.screening.ScreeningService;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class MoviesScreeningsSearchByTimeUseCase {
	private final ScreeningService screeningService;
	private final ScreeningPageableFactory pageFactory;

	public Page<MovieScreeningDTO> compute(LocalDateTime from, int page, int size) {
		return screeningService.searchByTime(from, pageFactory.create(page, size));
	}

	public Page<MovieScreeningDTO> compute(LocalDateTime from, LocalDateTime to, int page, int size) {
		return screeningService.searchByTime(from, to, pageFactory.create(page, size));
	}
}
