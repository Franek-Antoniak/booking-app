package touk.recru.app.controller.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.exception.DataIntegrationException;
import touk.recru.app.exception.ScreeningNotFoundException;
import touk.recru.app.facade.ScreeningFacade;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/screenings")
@RequiredArgsConstructor
public class ScreeningController {
	private final ScreeningFacade screeningFacade;

	@GetMapping(
			value = {
					"/",
					""
			}
	)
	public ResponseEntity<Page<MovieScreeningDTO>> searchScreening(
			@RequestParam
			LocalDateTime from,
			@RequestParam(required = false)
			LocalDateTime to,
			@RequestParam(
					required = false,
					defaultValue = "0"
			)
			Integer page,
			@RequestParam(
					required = false,
					defaultValue = "20"
			)
			Integer size) {
		if (to == null) {
			return ResponseEntity.ok(screeningFacade.search(from, page, size));
		}
		return ResponseEntity.ok(screeningFacade.search(from, to, page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ScreeningBookingInfoDTO> searchScreeningBookingInfo(
			@PathVariable("id")
			UUID screeningId) {
		Optional<ScreeningBookingInfoDTO> screeningRoomViewInfoDTO = screeningFacade.searchScreeningBookingInfo(
				screeningId);
		return screeningRoomViewInfoDTO.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound()
						.build());
	}


	@ResponseStatus(
			value = HttpStatus.INTERNAL_SERVER_ERROR,
			reason = "Data integration violation"
	)
	@ExceptionHandler(DataIntegrationException.class)
	public ResponseEntity<String> dataIntegrationException(DataIntegrationException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
	}

	@ResponseStatus(
			value = HttpStatus.BAD_REQUEST,
			reason = "Screening not found"
	)
	@ExceptionHandler(ScreeningNotFoundException.class)
	public ResponseEntity<String> screeningNotFoundException(ScreeningNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
	}
}