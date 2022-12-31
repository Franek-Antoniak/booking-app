package touk.recru.app.mapper.seat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.entity.Seat;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeatViewInfoMapperTest {
	@Spy
	private SeatViewInfoMapper screeningRoomMapper = Mappers.getMapper(SeatViewInfoMapper.class);

	@Mock
	private Seat mockSeat;

	@Test
	void toDto() {
		// when
		when(mockSeat.getUuid()).thenReturn(UUID.randomUUID());
		when(mockSeat.getSeatNumber()).thenReturn(1);
		when(mockSeat.getSeatRow()).thenReturn(2);

		// given
		SeatInfoViewDTO dto = screeningRoomMapper.toDto(mockSeat);

		// then
		assertThat(dto.getSeatId()).isEqualTo(mockSeat.getUuid());
		assertThat(dto.getSeatNumber()).isEqualTo(mockSeat.getSeatNumber());
		assertThat(dto.getSeatRow()).isEqualTo(mockSeat.getSeatRow());
	}
}