package touk.recru.app.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateFactory {
	public static List<LocalDateTime> create(LocalDateTime from, int days) {
		List<LocalDateTime> list = new ArrayList<>();
		for (int i = 0; i < days; i++) {
			list.add(from.plusDays(i));
		}
		return list;
	}
}
