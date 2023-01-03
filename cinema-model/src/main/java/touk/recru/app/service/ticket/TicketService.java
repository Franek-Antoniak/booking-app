package touk.recru.app.service.ticket;

import touk.recru.app.dto.ticket.type.TicketTypeDTO;
import touk.recru.app.entity.Seat;
import touk.recru.app.entity.Ticket;

import java.util.List;

public abstract class TicketService {

	public abstract List<Ticket> createTickets(List<TicketTypeDTO> ticketsType, List<Seat> seats);
}
