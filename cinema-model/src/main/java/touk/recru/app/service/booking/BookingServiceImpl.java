package touk.recru.app.service.booking;

import org.springframework.stereotype.Service;
import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Seat;
import touk.recru.app.entity.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class BookingServiceImpl extends BookingService {
	@Override
	public List<Seat> getAvailableSeats(List<Seat> seats, List<Booking> bookingList) {
		Set<Seat> bookedSeats = bookingList.stream()
				.map(Booking::getTickets)
				.flatMap(List::stream)
				.map(Ticket::getSeat)
				.collect(Collectors.toSet());
		List<Seat> availableSeats = new ArrayList<>();
		for (int i = 0; i < seats.size(); i++) {
			Seat seat = seats.get(i);
			if (bookedSeats.contains(seat)) {
				continue;
			}
			if (i > 0 && bookedSeats.contains(seats.get(i - 1))) {
				continue;
			}
			if (i < seats.size() - 1 && bookedSeats.contains(seats.get(i + 1))) {
				continue;
			}
			availableSeats.add(seat);
		}
		return availableSeats;
	}
}
