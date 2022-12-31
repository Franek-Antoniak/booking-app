package touk.recru.app.service.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Seat;
import touk.recru.app.entity.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
	@Spy
	private BookingServiceImpl bookingService;


	@Test
	void getAvailableSeats() {
		// given
		List<Seat> seats = getSeats();
		List<Ticket> tickets = getTickets();
		addTicketsToSeats(seats, tickets);
		// reserved space alternately
		List<Booking> bookingList1 = getBookingListAlternately(tickets);
		// reserved space in the middle
		List<Booking> bookingList2 = getBookingListInMiddle(tickets);
		// non reserved space
		List<Booking> bookingList3 = List.of();
		List<Seat> answer1 = List.of();
		List<Seat> answer2 = seats.stream()
				.filter(seat -> seat.getSeatNumber() < 4 || seat.getSeatNumber() > 6)
				.toList();
		List<Seat> answer3 = new ArrayList<>(seats);
		// when
		List<Seat> result1 = bookingService.getAvailableSeats(seats, bookingList1);
		List<Seat> result2 = bookingService.getAvailableSeats(seats, bookingList2);
		List<Seat> result3 = bookingService.getAvailableSeats(seats, bookingList3);

		// then
		assertEquals(answer1, result1);
		assertEquals(answer2, result2);
		assertEquals(answer3, result3);
	}

	private List<Booking> getBookingListInMiddle(List<Ticket> tickets) {
		Booking booking = new Booking();
		booking.setTickets(List.of(tickets.get(5)));
		return List.of(booking);
	}

	private List<Booking> getBookingListAlternately(List<Ticket> tickets) {
		List<Booking> bookingList = new ArrayList<>();
		for (int i = 0; i < tickets.size(); i += 2) {
			Booking booking = new Booking();
			booking.setTickets(List.of(tickets.get(i)));
			bookingList.add(booking);
		}
		return bookingList;
	}

	private List<Seat> getSeats() {
		return Stream.iterate(0, i -> i + 1)
				.limit(10)
				.map(i -> {
					Seat seat = new Seat();
					seat.setSeatNumber(i);
					return seat;
				})
				.toList();
	}

	private List<Ticket> getTickets() {
		return Stream.generate(Ticket::new)
				.limit(10)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}

	private void addTicketsToSeats(List<Seat> seats, List<Ticket> tickets) {
		for (int i = 0; i < tickets.size(); i++) {
			tickets.get(i)
					.setSeat(seats.get(i));
		}
	}
}