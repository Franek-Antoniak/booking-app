package touk.recru.app.service.booking;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.dto.person.PersonDTO;
import touk.recru.app.entity.*;
import touk.recru.app.exception.BookingException;
import touk.recru.app.repository.booking.BookingRepository;
import touk.recru.app.repository.screening.ScreeningRepository;
import touk.recru.app.repository.ticket.TicketRepository;
import touk.recru.app.service.ticket.TicketService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class BookingServiceImpl extends BookingService {
	private final BookingRepository bookingRepository;
	private final ScreeningRepository screeningRepository;
	private final TicketService ticketService;
	private final TicketRepository ticketRepository;


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

	@Override
	@Transactional
	public BookingResultDTO book(BookingRequestDTO bookingRequest) {
		if (bookingRequest.getTicketsType()
				.size() != bookingRequest.getSeats()
				.size()) {
			throw new BookingException("Number of seats and tickets type must be equal");
		}
		Screening screening = screeningRepository.findScreeningByUuid(bookingRequest.getScreeningId())
				.orElseThrow(() -> new BookingException("Booking failed. Screening not found"));
		List<Booking> bookingList = bookingRepository.findAllByScreening(screening);
		Set<Seat> availableSeats = new HashSet<>(getAvailableSeats(screening.getScreeningRoom()
				.getSeats(), bookingList));
		List<Seat> bookingSeats = availableSeats.stream()
				.filter(seat -> bookingRequest.getSeats()
						.contains(seat.getUuid()))
				.collect(Collectors.toList());
		if (bookingSeats.size() != bookingRequest.getSeats()
				.size()) {
			throw new BookingException("Seats are not available");
		}
		List<Ticket> tickets = ticketService.createTickets(bookingRequest.getTicketsType(), bookingSeats);
		Booking booking = createBooking(tickets, bookingRequest.getPerson(), screening);
		ticketRepository.saveAll(tickets);
		bookingRepository.save(booking);
		return new BookingResultDTO(booking.getTotalPrice(), screening.getScreeningTime()
				.minusMinutes(15));
	}

	private Booking createBooking(List<Ticket> tickets, PersonDTO person, Screening screening) {
		Booking booking = new Booking();
		booking.setTickets(tickets);
		booking.setScreening(screening);
		booking.setPerson(new Person(person.getName(), person.getSurname()));
		tickets.forEach(ticket -> ticket.setBooking(booking));
		return booking;
	}
}
