package touk.recru.app.mapper.screening;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.entity.Screening;

@Mapper(
		componentModel = "spring",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ScreeningViewInfoMapper {

	@Mappings(
			{@Mapping(
					source = "uuid",
					target = "screeningId"
			), @Mapping(
					source = "movie.title",
					target = "movieTitle"
			), @Mapping(
					source = "screeningTime",
					target = "startTime"
			), @Mapping(
					source = "movie.duration",
					target = "duration"
			)}
	)
	ScreeningViewInfoDTO toDto(Screening screening);
}