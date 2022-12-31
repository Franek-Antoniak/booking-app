package touk.recru.app.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.factory.ScreeningPageableFactory;
import touk.recru.app.service.screening.ScreeningService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoviesScreeningsSearchByTimeUseCaseTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class ScreeningsBetweenDates {
		@Mock
		ScreeningService screeningService;

		@Spy
		ScreeningPageableFactory factory = new ScreeningPageableFactory();
		@InjectMocks
		MoviesScreeningsSearchByTimeUseCase useCase;

		@Test
		public void compute_withCustomPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			LocalDateTime to = LocalDateTime.now()
					.plusDays(4);
			Pageable pageable = factory.create(0, 10);
			ScreeningViewInfoDTO expectedValue = ScreeningViewInfoDTO.builder()
					.movieTitle("title")
					.screeningId(UUID.randomUUID())
					.duration(Duration.ofMinutes(120))
					.startTime(from)
					.build();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(List.of(expectedValue));
			// when
			when(screeningService.searchByTime(from, to, pageable)).thenReturn(expectedPage);

			Page<ScreeningViewInfoDTO> result = useCase.compute(from, to, pageable.getPageNumber(), pageable.getPageSize());
			// then
			assertEquals(expectedPage, result);
		}

		@Test
		public void compute_WithDefaultPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			LocalDateTime to = LocalDateTime.now()
					.plusDays(4);
			Pageable pageable = factory.defaultPageable();
			ScreeningViewInfoDTO expectedValue = ScreeningViewInfoDTO.builder()
					.movieTitle("title")
					.screeningId(UUID.randomUUID())
					.duration(Duration.ofMinutes(120))
					.startTime(from)
					.build();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(List.of(expectedValue));
			// when
			when(screeningService.searchByTime(from, to, pageable)).thenReturn(expectedPage);

			Page<ScreeningViewInfoDTO> result = useCase.compute(from, to);
			// then
			assertEquals(expectedPage, result);
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	class ScreeningsAfterDate {
		@Mock
		ScreeningService screeningService;

		@Spy
		ScreeningPageableFactory factory = new ScreeningPageableFactory();
		@InjectMocks
		MoviesScreeningsSearchByTimeUseCase useCase;

		@Test
		public void compute_WithCustomPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			Pageable pageable = factory.create(0, 10);
			ScreeningViewInfoDTO expectedValue = ScreeningViewInfoDTO.builder()
					.movieTitle("title")
					.screeningId(UUID.randomUUID())
					.duration(Duration.ofMinutes(120))
					.startTime(from)
					.build();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(List.of(expectedValue));
			// when
			when(screeningService.searchByTime(from, pageable)).thenReturn(expectedPage);

			Page<ScreeningViewInfoDTO> result = useCase.compute(from, pageable.getPageNumber(), pageable.getPageSize());
			// then
			assertEquals(expectedPage, result);
		}

		@Test
		public void compute_WithDefaultPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			Pageable pageable = factory.defaultPageable();
			ScreeningViewInfoDTO expectedValue = ScreeningViewInfoDTO.builder()
					.movieTitle("title")
					.screeningId(UUID.randomUUID())
					.duration(Duration.ofMinutes(120))
					.startTime(from)
					.build();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(List.of(expectedValue));
			// when
			when(screeningService.searchByTime(from, pageable)).thenReturn(expectedPage);

			Page<ScreeningViewInfoDTO> result = useCase.compute(from);
			// then
			assertEquals(expectedPage, result);
		}
	}
}