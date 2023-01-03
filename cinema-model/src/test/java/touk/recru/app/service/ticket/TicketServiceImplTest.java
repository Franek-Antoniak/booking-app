package touk.recru.app.service.ticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.ticket.type.TicketTypeDTO;
import touk.recru.app.entity.Seat;
import touk.recru.app.entity.Ticket;
import touk.recru.app.entity.TicketType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
	@Spy
	TicketServiceImpl ticketService;

	@Test
	void createTickets() {
		// given
		List<TicketTypeDTO> ticketsType = new ArrayList<>();
		ticketsType.add(TicketTypeDTO.builder()
				.type(TicketType.ADULT.name())
				.build());
		ticketsType.add(TicketTypeDTO.builder()
				.type(TicketType.CHILD.name())
				.build());
		ticketsType.add(TicketTypeDTO.builder()
				.type(TicketType.STUDENT.name())
				.build());
		List<Seat> seats = new ArrayList<>();
		seats.add(Seat.builder()
				.seatRow(1)
				.seatNumber(1)
				.build());
		seats.add(Seat.builder()
				.seatNumber(1)
				.seatNumber(2)
				.build());
		seats.add(Seat.builder()
				.seatRow(1)
				.seatNumber(3)
				.build());
		// when
		List<Ticket> tickets = ticketService.createTickets(ticketsType, seats);
		// then
		assertEquals(3, tickets.size());
	}
}