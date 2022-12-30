package touk.recru.app.service.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.mapper.screening.ScreeningViewInfoMapper;
import touk.recru.app.repository.screening.ScreeningRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class ScreeningServiceImpl extends ScreeningService {
	private final ScreeningRepository screeningRepository;
	private final ScreeningViewInfoMapper mapper;

	@Override
	public Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, LocalDateTime to, Pageable pageable) {
		return screeningRepository.findScreeningByScreeningTimeBetween(from, to, pageable)
				.map(mapper::toDto);

	}

	@Override
	public Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, Pageable pageable) {
		return screeningRepository.findScreeningByScreeningTimeAfter(from, pageable)
				.map(mapper::toDto);
	}
}
