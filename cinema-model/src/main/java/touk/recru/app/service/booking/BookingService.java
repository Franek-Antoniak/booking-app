package touk.recru.app.service.booking;

import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Seat;

import java.util.List;

public abstract class BookingService {
	public abstract List<SeatInfoViewDTO> getAvailableSeats(List<Seat> seats, List<Booking> bookingList);

	public abstract BookingResultDTO book(BookingRequestDTO bookingRequest);
}
