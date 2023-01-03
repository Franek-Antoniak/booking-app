package touk.recru.app.usecase;

import lombok.RequiredArgsConstructor;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.service.screening.ScreeningService;

import java.util.Optional;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class SearchAvailableSeatsUseCase {
	private final ScreeningService screeningService;

	public Optional<ScreeningBookingInfoDTO> compute(UUID screeningId) {
		return screeningService.searchAvailableSeats(screeningId);
	}
}
