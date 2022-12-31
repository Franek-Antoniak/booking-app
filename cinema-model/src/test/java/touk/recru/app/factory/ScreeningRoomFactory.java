package touk.recru.app.factory;

import touk.recru.app.entity.ScreeningRoom;
import touk.recru.app.entity.Seat;

import java.util.ArrayList;
import java.util.List;

public class ScreeningRoomFactory {
	public static List<ScreeningRoom> create(int amount) {
		List<ScreeningRoom> rooms = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			List<Seat> seats = new SeatFactory().create(8, 10);
			for (int j = 0; j < amount; j++) {
				ScreeningRoom screeningRoom = new ScreeningRoom();
				screeningRoom.setLocation("Room" + i);
				screeningRoom.setSeats(seats);
				screeningRoom.setRows(8);
				screeningRoom.setColumns(10);
				rooms.add(screeningRoom);
			}
		}
		return rooms;
	}
}
