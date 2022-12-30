package touk.recru.app.repository.screening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import touk.recru.app.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository {
	List<Screening> saveAll(List<Screening> screenings);

	Page<Screening> findScreeningByScreeningTimeAfter(LocalDateTime screeningTimeStart, Pageable pageable);

	Page<Screening> findScreeningByScreeningTimeBetween(LocalDateTime screeningTimeStart,
			LocalDateTime screeningTimeEnd, Pageable pageable);
}
