package touk.recru.app.service.booking;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.dto.person.PersonDTO;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.dto.ticket.type.TicketTypeDTO;
import touk.recru.app.entity.*;
import touk.recru.app.exception.BookingException;
import touk.recru.app.mapper.seat.SeatViewInfoMapper;
import touk.recru.app.repository.booking.BookingRepository;
import touk.recru.app.repository.screening.ScreeningRepository;
import touk.recru.app.repository.ticket.TicketRepository;
import touk.recru.app.service.ticket.TicketService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingServiceImplTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class AvailableSeatsTest {
		@Spy
		SeatViewInfoMapper seatViewInfoMapper = Mappers.getMapper(SeatViewInfoMapper.class);
		@Spy
		@InjectMocks
		private BookingServiceImpl bookingService;

		@Test
		void getAvailableSeats_caseOne() {
			// given
			List<Seat> seats = getSeats();
			List<Ticket> tickets = getTickets();
			addTicketsToSeats(seats, tickets);
			// reserved space alternately
			List<Booking> bookingList1 = getBookingListAlternately(tickets);
			List<Seat> answer1 = List.of();
			// when
			List<SeatInfoViewDTO> result1 = bookingService.getAvailableSeats(seats, bookingList1);

			// then
			assertTrue(result1.isEmpty());
		}

		private List<Seat> getSeats() {
			return Stream.iterate(0, i -> i + 1)
					.limit(10)
					.map(i -> {
						Seat seat = new Seat();
						seat.setSeatRow(0);
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

		private List<Booking> getBookingListAlternately(List<Ticket> tickets) {
			List<Booking> bookingList = new ArrayList<>();
			for (int i = 0; i < tickets.size(); i += 2) {
				Booking booking = new Booking();
				booking.setTickets(List.of(tickets.get(i)));
				bookingList.add(booking);
			}
			return bookingList;
		}

		@Test
		void getAvailableSeats_caseTwo() {
			// given
			List<Seat> seats = getSeats();
			List<Ticket> tickets = getTickets();
			addTicketsToSeats(seats, tickets);
			// reserved space in the middle
			List<Booking> bookingList2 = getBookingListInMiddle(tickets);
			List<Seat> answer2 = seats.stream()
					.filter(seat -> seat.getSeatNumber() < 4 || seat.getSeatNumber() > 6)
					.toList();
			// when
			List<SeatInfoViewDTO> result2 = bookingService.getAvailableSeats(seats, bookingList2);

			// then
			assertTrue(answer2.stream()
					.map(Seat::getUuid)
					.allMatch(uuid -> result2.stream()
							.map(SeatInfoViewDTO::getSeatId)
							.anyMatch(uuid::equals)));
		}

		private List<Booking> getBookingListInMiddle(List<Ticket> tickets) {
			Booking booking = new Booking();
			booking.setTickets(List.of(tickets.get(5)));
			return List.of(booking);
		}

		@Test
		void getAvailableSeats_caseThree() {
			// given
			List<Seat> seats = getSeats();
			List<Ticket> tickets = getTickets();
			addTicketsToSeats(seats, tickets);
			// non reserved space
			List<Booking> bookingList3 = List.of();
			List<Seat> answer3 = new ArrayList<>(seats);
			// when
			List<SeatInfoViewDTO> result3 = bookingService.getAvailableSeats(seats, bookingList3);

			// then
			assertTrue(answer3.stream()
					.map(Seat::getUuid)
					.allMatch(uuid -> result3.stream()
							.map(SeatInfoViewDTO::getSeatId)
							.anyMatch(uuid::equals)));
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	class BookingTest {
		@Spy
		@InjectMocks
		BookingServiceImpl bookingService;

		@Mock
		BookingRepository bookingRepository;
		@Mock
		ScreeningRepository screeningRepository;

		@Mock
		TicketService ticketService;

		@Mock
		TicketRepository ticketRepository;

		@Mock
		SeatViewInfoMapper seatViewInfoMapper;

		@Test
		void book_shouldThrowBookingExceptionIfNumberOfSeatsAndTicketsTypeNotEqual() {
			// given
			BookingRequestDTO request = new BookingRequestDTO();
			request.setSeats(List.of());
			request.setTicketsType(List.of(new TicketTypeDTO()));
			// when
			// then
			// Invoke the book method and assert that a BookingException is thrown
			assertThrows(BookingException.class, () -> bookingService.book(request),
					"Number of seats and tickets type must be equal");
		}

		@Test
		void book_shouldThrowBookingExceptionIfScreeningNotFound() {
			// given
			BookingRequestDTO request = new BookingRequestDTO();
			request.setSeats(List.of());
			request.setTicketsType(List.of());
			// when
			when(screeningRepository.findScreeningByUuid(any())).thenReturn(Optional.empty());
			// then
			// Invoke the book method and assert that a BookingException is thrown
			assertThrows(BookingException.class, () -> bookingService.book(request),
					"Booking failed. Screening not found");
		}

		@Test
		void book_shouldTrowBookingExceptionIfScreeningIsToClose() {
			// given
			BookingRequestDTO request = new BookingRequestDTO();
			request.setSeats(List.of());
			request.setTicketsType(List.of());
			LocalDateTime screeningTime = LocalDateTime.now();
			// when
			// Set up the mock objects
			when(screeningRepository.findScreeningByUuid(any())).thenReturn(Optional.of(Screening.builder()
					.screeningTime(screeningTime)
					.build()));
			// then
			assertThrows(BookingException.class, () -> bookingService.book(request),
					"Booking failed. Screening time is too close. You can book tickets at latest 15 minutes before " + "screening time");
		}

		@Test
		void book_shouldThrowBookingExceptionIfSeatsNotAvailable() {
			// given
			BookingRequestDTO request = new BookingRequestDTO();
			request.setSeats(List.of(UUID.randomUUID()));
			request.setTicketsType(List.of());
			// when
			// then
			// Invoke the book method and assert that a BookingException is thrown
			assertThrows(BookingException.class, () -> bookingService.book(request), "Seats not available");
		}

		@Test
		void book_shouldBookSeatsAndCreateTickets() {
			// given
			List<UUID> uuids = Stream.iterate(0, i -> i + 1)
					.limit(10)
					.map(i -> UUID.randomUUID())
					.toList();
			LocalDateTime screeningTime = LocalDateTime.now()
					.plusDays(1);
			// when
			// Set up the mock objects
			when(screeningRepository.findScreeningByUuid(any())).thenReturn(Optional.of(Screening.builder()
					.screeningRoom(ScreeningRoom.builder()
							.seats(List.of(Seat.builder()
									.uuid(uuids.get(0))
									.build(), Seat.builder()
									.uuid(uuids.get(1))
									.build()))
							.build())
					.screeningTime(screeningTime)
					.build()));
			when(ticketService.createTickets(anyList(), anyList())).thenReturn(List.of(Ticket.builder()
					.ticketType(TicketType.ADULT)
					.build(), Ticket.builder()
					.ticketType(TicketType.CHILD)
					.build()));
			when(bookingRepository.save(any())).thenReturn(new Booking());
			when(bookingService.getAvailableSeats(anyList(), anyList())).thenReturn(List.of(SeatInfoViewDTO.builder()
					.seatId(uuids.get(0))
					.build(), SeatInfoViewDTO.builder()
					.seatId(uuids.get(1))
					.build()));
			// Create a BookingRequestDTO with valid data
			BookingRequestDTO request = new BookingRequestDTO();
			request.setSeats(List.of(uuids.get(0), uuids.get(1)));
			request.setScreeningId(UUID.randomUUID());
			request.setTicketsType(List.of(TicketTypeDTO.builder()
					.type(TicketType.ADULT.name())
					.build(), TicketTypeDTO.builder()
					.type(TicketType.CHILD.name())
					.build()));
			request.setPerson(new PersonDTO("John", "Doe"));
			// Invoke the book method
			BookingResultDTO result = bookingService.book(request);
			// Verify that the book method was called correctly
			verify(screeningRepository).findScreeningByUuid(any());
			verify(ticketService).createTickets(anyList(), anyList());
			verify(bookingRepository).save(any());
			// Check the values of the BookingResultDTO
			assertNotNull(result);
			assertNotNull(result.getTotalPrice());
			assertNotNull(result.getExpirationTime());
			assertEquals(BigDecimal.valueOf(37.5), result.getTotalPrice());
			assertEquals(screeningTime.minusMinutes(15), result.getExpirationTime());
		}
	}
}