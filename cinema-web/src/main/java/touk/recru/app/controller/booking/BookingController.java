package touk.recru.app.controller.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.exception.BookingException;
import touk.recru.app.facade.BookingFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
	private final BookingFacade bookingFacade;

	@PostMapping("/book")
	public ResponseEntity<BookingResultDTO> book(
			@RequestBody
			BookingRequestDTO bookingRequest) {
		return ResponseEntity.ok(bookingFacade.book(bookingRequest));
	}


	@ExceptionHandler(BookingException.class)
	public ResponseEntity<String> bookingRequestException(BookingException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getLocalizedMessage());
	}
}
