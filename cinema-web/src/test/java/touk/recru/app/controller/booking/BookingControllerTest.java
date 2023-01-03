package touk.recru.app.controller.booking;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.exception.BookingException;
import touk.recru.app.facade.BookingFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingControllerTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class BookingControllerResponseStatus {
		@InjectMocks
		BookingController bookingController;

		@Mock
		BookingFacade bookingFacade;

		@Test
		void book_noException() {
			// given
			// when
			when(bookingFacade.book(any())).thenReturn(null);
			ResponseEntity<BookingResultDTO> result = bookingController.book(null);
			// then
			assertEquals(HttpStatus.OK, result.getStatusCode());
		}

		@Test
		void book_exception() {
			// given
			BookingException exception = new BookingException("test");
			// when
			when(bookingFacade.book(any())).thenThrow(exception);
			ResponseEntity<String> exceptionResult = bookingController.bookingRequestException(exception);
			try {
				bookingController.book(null);
			} catch (BookingException e) {
				// then
				assertEquals(exception, e);
			}
			// then
			assertEquals(HttpStatus.BAD_REQUEST, exceptionResult.getStatusCode());
		}
	}
}