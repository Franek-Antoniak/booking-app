package touk.recru.app.mapper.room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.room.ScreeningBookingInfoDTO;
import touk.recru.app.entity.Screening;
import touk.recru.app.entity.ScreeningRoom;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScreeningSeatBookingInfoMapperTest {

	@Spy
	private ScreeningSeatBookingInfoMapper mapper = Mappers.getMapper(ScreeningSeatBookingInfoMapper.class);

	@Mock
	private Screening mockScreening;

	@Mock
	private ScreeningRoom mockScreeningRoom;

	@Test
	void toDto() {
		// when
		when(mockScreening.getUuid()).thenReturn(UUID.randomUUID());
		when(mockScreening.getScreeningRoom()).thenReturn(mockScreeningRoom);
		when(mockScreeningRoom.getLocation()).thenReturn("Room 1");
		when(mockScreeningRoom.getRows()).thenReturn(8);
		when(mockScreeningRoom.getColumns()).thenReturn(10);

		// given

		ScreeningBookingInfoDTO dto = mapper.toDto(mockScreening);

		// then
		assertThat(dto.getScreeningId()).isEqualTo(mockScreening.getUuid());
		assertThat(dto.getRoomLocation()).isEqualTo(mockScreeningRoom.getLocation());
		assertThat(dto.getRows()).isEqualTo(mockScreeningRoom.getRows());
		assertThat(dto.getColumns()).isEqualTo(mockScreeningRoom.getColumns());
	}
}