package touk.recru.app.facade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.usecase.BookingTicketsUseCase;

@RequiredArgsConstructor
@Validated
@Facade
public class BookingFacade {
	private final BookingTicketsUseCase bookingTicketsUseCase;

	public BookingResultDTO book(@Valid @NotNull BookingRequestDTO bookingRequest) {
		return bookingTicketsUseCase.compute(bookingRequest);
	}
}
