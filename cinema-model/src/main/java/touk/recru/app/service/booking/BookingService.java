package touk.recru.app.service.booking;

import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Seat;

import java.util.List;

public abstract class BookingService {
	public abstract List<Seat> getAvailableSeats(List<Seat> seats, List<Booking> bookingList);
}
