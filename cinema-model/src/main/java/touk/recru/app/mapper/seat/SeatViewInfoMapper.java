package touk.recru.app.mapper.seat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.entity.Seat;


@Mapper(
		componentModel = "spring",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface SeatViewInfoMapper {

	@Mappings(
			{
					@Mapping(
							source = "uuid",
							target = "seatId"
					),
					@Mapping(
							source = "seatRow",
							target = "seatRow"
					),
					@Mapping(
							source = "seatNumber",
							target = "seatNumber"
					)
			}
	)
	SeatInfoViewDTO toDto(Seat seat);
}