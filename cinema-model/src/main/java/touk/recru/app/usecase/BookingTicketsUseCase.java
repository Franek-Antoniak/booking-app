package touk.recru.app.usecase;

import lombok.RequiredArgsConstructor;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.service.booking.BookingService;

@UseCase
@RequiredArgsConstructor
public class BookingTicketsUseCase {
	private final BookingService bookingService;

	public BookingResultDTO compute(BookingRequestDTO bookingRequest) {
		return bookingService.book(bookingRequest);
	}
}
