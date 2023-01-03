package touk.recru.app.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.service.booking.BookingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingTicketsUseCaseTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchSeats {
		@Mock
		BookingService bookingService;

		@InjectMocks
		BookingTicketsUseCase useCase;

		@Mock
		BookingRequestDTO bookingRequestDTO;

		@Mock
		BookingResultDTO bookingResultDTO;

		@Test
		public void compute() {
			// given
			// when
			when(bookingService.book(bookingRequestDTO)).thenReturn(bookingResultDTO);
			BookingResultDTO result = useCase.compute(bookingRequestDTO);
			// then
			assertEquals(bookingResultDTO, result);
		}
	}
}