package touk.recru.app.mapper.room;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.entity.Screening;
import touk.recru.app.mapper.seat.SeatViewInfoMapper;


@Mapper(
		componentModel = "spring",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = SeatViewInfoMapper.class
)
public interface ScreeningSeatBookingInfoMapper {
	@Mappings(
			{
					@Mapping(
							source = "uuid",
							target = "screeningId"
					),
					@Mapping(
							source = "screeningRoom.location",
							target = "roomLocation"
					),
					@Mapping(
							source = "screeningRoom.rows",
							target = "rows"
					),
					@Mapping(
							source = "screeningRoom.columns",
							target = "columns"
					),
					@Mapping(
							target = "availableSeats",
							ignore = true
					)
			}
	)
	ScreeningBookingInfoDTO toDto(Screening screening);
}
