package touk.recru.app.facade.movie;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.facade.Facade;
import touk.recru.app.usecase.MoviesScreeningsSearchByTimeUseCase;

import java.time.LocalDateTime;

@Facade
@RequiredArgsConstructor
@Validated
public class ScreeningFacade {
	private final MoviesScreeningsSearchByTimeUseCase screeningSearch;

	public Page<ScreeningViewInfoDTO> search(@NotNull LocalDateTime from) {
		return screeningSearch.compute(from);
	}

	public Page<ScreeningViewInfoDTO> search(@NotNull LocalDateTime from, @NotNull LocalDateTime to) {
		return screeningSearch.compute(from, to);
	}

	public Page<ScreeningViewInfoDTO> search(@NotNull LocalDateTime from, @PositiveOrZero int page,
			@Positive int size) {
		return screeningSearch.compute(from, page, size);
	}

	public Page<ScreeningViewInfoDTO> search(@NotNull LocalDateTime from, @NotNull LocalDateTime to,
			@PositiveOrZero int page, @Positive int size) {
		return screeningSearch.compute(from, to, page, size);
	}
}
