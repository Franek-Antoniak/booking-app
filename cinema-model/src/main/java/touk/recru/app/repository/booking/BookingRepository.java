package touk.recru.app.repository.booking;

import touk.recru.app.entity.Booking;

import java.util.List;
import java.util.Set;

public interface BookingRepository {
	List<Booking> findAllByIdIn(Set<Long> ids);
}
