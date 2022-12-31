package touk.recru.app.controller.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import touk.recru.app.dto.room.ScreeningBookingInfoDTO;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.facade.ScreeningFacade;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/screenings")
@RequiredArgsConstructor
public class ScreeningController {
	private final ScreeningFacade screeningFacade;

	@GetMapping("/search")
	public ResponseEntity<Page<ScreeningViewInfoDTO>> searchScreening(
			@RequestParam(required = false)
			LocalDateTime from,
			@RequestParam(required = false)
			LocalDateTime to,
			@RequestParam(required = false)
			Integer page,
			@RequestParam(required = false)
			Integer size) {
		if (page != null && size != null && from != null && to != null) {
			return ResponseEntity.ok(screeningFacade.search(from, to, page, size));
		}
		if (page != null && size != null && from != null) {
			return ResponseEntity.ok(screeningFacade.search(from, page, size));
		}
		if (from != null && to != null) {
			return ResponseEntity.ok(screeningFacade.search(from, to));
		}
		if (from != null) {
			return ResponseEntity.ok(screeningFacade.search(from));
		}
		return ResponseEntity.badRequest()
				.build();
	}

	@GetMapping("/search/available/{id}")
	public ResponseEntity<ScreeningBookingInfoDTO> searchScreeningBookingInfo(
			@PathVariable("id")
			UUID screeningId) {
		Optional<ScreeningBookingInfoDTO> screeningRoomViewInfoDTO = screeningFacade.searchScreeningBookingInfo(
				screeningId);
		return screeningRoomViewInfoDTO.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound()
						.build());
	}
}