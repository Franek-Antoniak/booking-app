package touk.recru.app.service.screening;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;

import java.time.LocalDateTime;

public abstract class ScreeningService {
	public abstract Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, LocalDateTime to, Pageable pageable);

	public abstract Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, Pageable pageable);
}
