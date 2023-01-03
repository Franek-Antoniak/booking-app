package touk.recru.app.repository.booking;

import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Screening;

import java.util.List;

public interface BookingRepository {
	List<Booking> findAllByScreening(Screening screeningEntity);

	<S extends Booking> S save(S entity);
}
