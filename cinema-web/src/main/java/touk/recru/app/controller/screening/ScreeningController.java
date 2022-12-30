package touk.recru.app.controller.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.facade.movie.ScreeningFacade;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/screenings")
@RequiredArgsConstructor
public class ScreeningController {
	private final ScreeningFacade screeningFacade;

	@GetMapping("/search")
	public ResponseEntity<Page<ScreeningViewInfoDTO>> search(@RequestParam(required = false) LocalDateTime from,
			@RequestParam(required = false) LocalDateTime to, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		if (page != null && size != null && from != null && to != null) {
			return ResponseEntity.ok(screeningFacade.search(from, to, page, size));
		}
		if (page != null && size != null && from != null) {
			return ResponseEntity.ok(screeningFacade.search(from, page, size));
		}
		if (from != null && to != null) {
			return ResponseEntity.ok(screeningFacade.search(from, to));
		}
		return ResponseEntity.ok(screeningFacade.search(from));
	}
}