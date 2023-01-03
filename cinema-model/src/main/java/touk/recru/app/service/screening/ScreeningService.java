package touk.recru.app.service.screening;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public abstract class ScreeningService {
	public abstract Page<MovieScreeningDTO> searchByTime(LocalDateTime from, LocalDateTime to, Pageable pageable);

	public abstract Page<MovieScreeningDTO> searchByTime(LocalDateTime from, Pageable pageable);

	public abstract Optional<ScreeningBookingInfoDTO> searchAvailableSeats(UUID screeningId);
}
