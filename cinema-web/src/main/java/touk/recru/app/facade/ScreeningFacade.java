package touk.recru.app.facade;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.usecase.MoviesScreeningsSearchByTimeUseCase;
import touk.recru.app.usecase.SearchAvailableSeatsUseCase;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Validated
@Facade
public class ScreeningFacade {
	private final MoviesScreeningsSearchByTimeUseCase screeningSearch;
	private final SearchAvailableSeatsUseCase availableSeatsSearch;

	public Page<MovieScreeningDTO> search(@NotNull LocalDateTime from, @PositiveOrZero int page, @Positive int size) {
		return screeningSearch.compute(from, page, size);
	}

	public Page<MovieScreeningDTO> search(@NotNull LocalDateTime from, @NotNull LocalDateTime to,
			@PositiveOrZero int page, @Positive int size) {
		return screeningSearch.compute(from, to, page, size);
	}

	public Optional<ScreeningBookingInfoDTO> searchScreeningBookingInfo(@NotNull UUID screeningId) {
		return availableSeatsSearch.compute(screeningId);
	}
}


