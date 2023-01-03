package touk.recru.app.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.service.screening.ScreeningService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchAvailableSeatsUseCaseTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchSeats {
		@Mock
		ScreeningService screeningService;

		@InjectMocks
		SearchAvailableSeatsUseCase useCase;

		@Test
		public void compute() {
			// given
			UUID screeningId = UUID.randomUUID();
			ScreeningBookingInfoDTO expectedDTO = ScreeningBookingInfoDTO.builder()
					.screeningId(screeningId)
					.build();
			// when
			when(screeningService.searchAvailableSeats(screeningId)).thenReturn(Optional.of(expectedDTO));
			Optional<ScreeningBookingInfoDTO> seats = useCase.compute(screeningId);
			// then
			assertTrue(seats.isPresent());
			assertEquals(expectedDTO, seats.get());
		}
	}
}