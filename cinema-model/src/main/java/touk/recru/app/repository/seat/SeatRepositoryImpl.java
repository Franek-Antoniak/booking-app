package touk.recru.app.repository.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import touk.recru.app.entity.Seat;

public interface SeatRepositoryImpl extends SeatRepository, JpaRepository<Seat, Long> {
}
