package touk.recru.app.repository.ticket;

import touk.recru.app.entity.Ticket;

import java.util.List;

public interface TicketRepository {
	<S extends Ticket> List<S> saveAll(Iterable<S> entities);
}
