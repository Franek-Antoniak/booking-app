package touk.recru.app.repository.screening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import touk.recru.app.entity.Screening;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
interface ScreeningRepositoryImpl extends ScreeningRepository, JpaRepository<Screening, Long> {
	Page<Screening> findScreeningByScreeningTimeAfter(LocalDateTime screeningTimeStart, Pageable pageable);

	Page<Screening> findScreeningByScreeningTimeBetween(LocalDateTime screeningTimeStart,
			LocalDateTime screeningTimeEnd, Pageable pageable);

	@EntityGraph(
			attributePaths = {
					"screeningRoom",
					"bookings",
					"movie"
			},
			type = EntityGraph.EntityGraphType.FETCH
	)
	Optional<Screening> findScreeningByUuid(UUID screeningId);
}
