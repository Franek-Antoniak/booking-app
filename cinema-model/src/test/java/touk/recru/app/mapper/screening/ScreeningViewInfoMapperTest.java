package touk.recru.app.mapper.screening;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.entity.Movie;
import touk.recru.app.entity.Screening;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ScreeningViewInfoMapperTest {

	@Spy
	private ScreeningViewInfoMapper mapper = Mappers.getMapper(ScreeningViewInfoMapper.class);

	@Mock
	private Screening mockScreening;

	@Mock
	private Movie mockMovie;


	@Test
	void toDto() {
		// when
		when(mockScreening.getUuid()).thenReturn(UUID.randomUUID());
		when(mockScreening.getMovie()).thenReturn(mockMovie);
		when(mockScreening.getScreeningTime()).thenReturn(LocalDateTime.now());
		when(mockMovie.getTitle()).thenReturn("Movie 1");
		when(mockMovie.getDuration()).thenReturn(Duration.ofMinutes(120));

		// given
		MovieScreeningDTO dto = mapper.toDto(mockScreening);

		// then
		assertThat(dto.getScreeningId()).isEqualTo(mockScreening.getUuid());
		assertThat(dto.getMovieTitle()).isEqualTo(mockMovie.getTitle());
		assertThat(dto.getStartTime()).isEqualTo(mockScreening.getScreeningTime());
		assertThat(dto.getDuration()).isEqualTo(mockMovie.getDuration());
	}
}