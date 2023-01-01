package touk.recru.app.repository.screening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import touk.recru.app.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreeningRepository {
	Page<Screening> findScreeningByScreeningTimeAfter(LocalDateTime screeningTimeStart, Pageable pageable);

	Page<Screening> findScreeningByScreeningTimeBetween(LocalDateTime screeningTimeStart,
			LocalDateTime screeningTimeEnd, Pageable pageable);


	Optional<Screening> findScreeningByUuid(UUID screeningId);

	<S extends Screening> List<S> saveAll(Iterable<S> entities);
}
