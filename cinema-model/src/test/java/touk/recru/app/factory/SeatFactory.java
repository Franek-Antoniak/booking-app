package touk.recru.app.factory;

import touk.recru.app.entity.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatFactory {
	public List<Seat> create(int i, int j) {
		List<Seat> seats = new ArrayList<>();
		for (int q = 0; q < i; q++) {
			for (int w = 0; w < j; w++) {
				Seat seat = new Seat();
				seat.setSeatRow(q);
				seat.setSeatNumber(w);
				seats.add(seat);
			}
		}
		return seats;
	}
}
