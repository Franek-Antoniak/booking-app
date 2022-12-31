package touk.recru.app.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import touk.recru.app.entity.Booking;

import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepositoryImpl extends BookingRepository, JpaRepository<Booking, Long> {
	List<Booking> findAllByIdIn(Set<Long> ids);
}
