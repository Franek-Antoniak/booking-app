package touk.recru.app.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import touk.recru.app.entity.ScreeningRoom;
import touk.recru.app.entity.Seat;
import touk.recru.app.repository.room.ScreeningRoomRepository;
import touk.recru.app.repository.seat.SeatRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatBootstrap {
	private final ScreeningRoomRepository screeningRoomRepository;
	private final SeatRepository seatRepository;
	@Transactional
	public void init() {
		List<ScreeningRoom> screeningRooms = screeningRoomRepository.findAll();
		for (var room: screeningRooms) {
			List<Seat> seats = new ArrayList<>();
			for (int i = 0; i < room.getRows(); i++) {
				for (int j = 0; j < room.getColumns(); j++) {
					seats.add(Seat.builder()
							.seatRow(i)
							.seatNumber(j)
							.screeningRoom(room)
							.build());
				}
			}
			room.setSeats(seats);
			seatRepository.saveAll(seats);
		}
	}
}
