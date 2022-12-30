package touk.recru.app.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.entity.Movie;
import touk.recru.app.entity.Screening;
import touk.recru.app.factory.DateFactory;
import touk.recru.app.factory.MovieFactory;
import touk.recru.app.factory.ScreeningFactory;
import touk.recru.app.factory.ScreeningPageableFactory;
import touk.recru.app.mapper.screening.ScreeningViewInfoMapper;
import touk.recru.app.service.screening.ScreeningService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoviesScreeningsSearchByTimeUseCaseTest {
	@Spy
	ScreeningViewInfoMapper mapper = Mappers.getMapper(ScreeningViewInfoMapper.class);

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
		public void computeWithCustomPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			LocalDateTime to = LocalDateTime.now()
					.plusDays(4);
			List<Movie> movies = MovieFactory.create(5);
			List<LocalDateTime> dates = DateFactory.create(LocalDateTime.now(), 10);
			List<Screening> screenings = ScreeningFactory.create(10, movies, dates);
			List<Screening> expectedScreenings = screenings.stream()
					.filter(screening -> screening.getScreeningTime()
							.isAfter(from) && screening.getScreeningTime()
							.isBefore(to))
					.toList();
			List<ScreeningViewInfoDTO> expectedScreeningsViewInfo = expectedScreenings.stream()
					.map(mapper::toDto)
					.toList();
			Pageable pageable = factory.create(0, 10);
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(expectedScreeningsViewInfo.stream()
					.sorted(Comparator.comparing(ScreeningViewInfoDTO::getMovieTitle)
							.thenComparing(ScreeningViewInfoDTO::getStartTime))
					.limit(pageable.getPageSize())
					.toList());
			// when
			when(screeningService.searchByTime(from, to, pageable)).thenReturn(expectedPage);

			var result = useCase.compute(from, to, pageable.getPageNumber(), pageable.getPageSize());
			// then
			assertEquals(expectedPage, result);
		}

		@Test
		public void computeWithDefaultPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			LocalDateTime to = LocalDateTime.now()
					.plusDays(4);
			List<Movie> movies = MovieFactory.create(5);
			List<LocalDateTime> dates = DateFactory.create(LocalDateTime.now(), 10);
			List<Screening> screenings = ScreeningFactory.create(10, movies, dates);
			List<Screening> expectedScreenings = screenings.stream()
					.filter(screening -> screening.getScreeningTime()
							.isAfter(from) && screening.getScreeningTime()
							.isBefore(to))
					.toList();
			List<ScreeningViewInfoDTO> expectedScreeningsViewInfo = expectedScreenings.stream()
					.map(mapper::toDto)
					.toList();
			Pageable pageable = factory.defaultPageable();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(expectedScreeningsViewInfo.stream()
					.sorted(Comparator.comparing(ScreeningViewInfoDTO::getMovieTitle)
							.thenComparing(ScreeningViewInfoDTO::getStartTime))
					.limit(pageable.getPageSize())
					.toList());
			// when
			when(screeningService.searchByTime(from, to, pageable)).thenReturn(expectedPage);

			var result = useCase.compute(from, to, pageable.getPageNumber(), pageable.getPageSize());
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
		public void computeWithCustomPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			List<Movie> movies = MovieFactory.create(5);
			List<LocalDateTime> dates = DateFactory.create(LocalDateTime.now(), 10);
			List<Screening> screenings = ScreeningFactory.create(10, movies, dates);
			List<Screening> expectedScreenings = screenings.stream()
					.filter(screening -> screening.getScreeningTime()
							.isAfter(from))
					.toList();
			List<ScreeningViewInfoDTO> expectedScreeningsViewInfo = expectedScreenings.stream()
					.map(mapper::toDto)
					.toList();
			Pageable pageable = factory.create(0, 10);
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(expectedScreeningsViewInfo.stream()
					.sorted(Comparator.comparing(ScreeningViewInfoDTO::getMovieTitle)
							.thenComparing(ScreeningViewInfoDTO::getStartTime))
					.limit(pageable.getPageSize())
					.toList());
			// when
			when(screeningService.searchByTime(from, pageable)).thenReturn(expectedPage);

			var result = useCase.compute(from, pageable.getPageNumber(), pageable.getPageSize());
			// then
			assertEquals(expectedPage, result);
		}

		@Test
		public void computeWithDefaultPageable() {
			// given
			LocalDateTime from = LocalDateTime.now();
			List<Movie> movies = MovieFactory.create(5);
			List<LocalDateTime> dates = DateFactory.create(LocalDateTime.now(), 10);
			List<Screening> screenings = ScreeningFactory.create(10, movies, dates);
			List<Screening> expectedScreenings = screenings.stream()
					.filter(screening -> screening.getScreeningTime()
							.isAfter(from))
					.toList();
			List<ScreeningViewInfoDTO> expectedScreeningsViewInfo = expectedScreenings.stream()
					.map(mapper::toDto)
					.toList();
			Pageable pageable = factory.defaultPageable();
			Page<ScreeningViewInfoDTO> expectedPage = new PageImpl<>(expectedScreeningsViewInfo.stream()
					.sorted(Comparator.comparing(ScreeningViewInfoDTO::getMovieTitle)
							.thenComparing(ScreeningViewInfoDTO::getStartTime))
					.limit(pageable.getPageSize())
					.toList());
			// when
			when(screeningService.searchByTime(from, pageable)).thenReturn(expectedPage);

			var result = useCase.compute(from, pageable.getPageNumber(), pageable.getPageSize());
			// then
			assertEquals(expectedPage, result);
		}
	}
}