package touk.recru.app.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Screening;

import java.util.List;

@Repository
public interface BookingRepositoryImpl extends BookingRepository, JpaRepository<Booking, Long> {
	List<Booking> findAllByScreening(Screening screeningEntity);

	<S extends Booking> S save(S entity);

}
