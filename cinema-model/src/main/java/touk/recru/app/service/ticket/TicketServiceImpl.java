package touk.recru.app.service.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import touk.recru.app.dto.ticket.type.TicketTypeDTO;
import touk.recru.app.entity.Seat;
import touk.recru.app.entity.Ticket;
import touk.recru.app.entity.TicketType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class TicketServiceImpl extends TicketService {

	@Override
	public List<Ticket> createTickets(List<TicketTypeDTO> ticketsType, List<Seat> seats) {
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < ticketsType.size(); i++) {
			TicketType ticketType = ticketsType.get(i)
					.toEntity();
			Ticket ticket = Ticket.builder()
					.ticketType(ticketType)
					.seat(seats.get(i))
					.build();
			tickets.add(ticket);
		}
		return tickets;
	}
}
