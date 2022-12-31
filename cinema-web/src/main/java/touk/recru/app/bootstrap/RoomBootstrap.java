package touk.recru.app.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import touk.recru.app.entity.ScreeningRoom;
import touk.recru.app.repository.room.ScreeningRoomRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomBootstrap {
	private final ScreeningRoomRepository screeningRoomRepository;

	public void init() {
		List<ScreeningRoom> screeningRooms = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			screeningRooms.add(ScreeningRoom.builder()
					.location("Room " + i)
					.rows(8)
					.columns(10)
					.build());
		}
		screeningRoomRepository.saveAll(screeningRooms);
	}
}
