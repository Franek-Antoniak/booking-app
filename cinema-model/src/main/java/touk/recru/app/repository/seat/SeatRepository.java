package touk.recru.app.repository.seat;

import touk.recru.app.entity.Seat;

import java.util.List;

public interface SeatRepository {
	<S extends Seat> List<S> saveAll(Iterable<S> entities);
}