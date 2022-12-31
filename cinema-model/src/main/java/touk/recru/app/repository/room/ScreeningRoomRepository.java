package touk.recru.app.repository.room;

import touk.recru.app.entity.Movie;
import touk.recru.app.entity.ScreeningRoom;

import java.util.List;
import java.util.Optional;

public interface ScreeningRoomRepository {
	List<ScreeningRoom> findAll();

	<S extends ScreeningRoom> List<S> saveAll(Iterable<S> entities);

	Optional<ScreeningRoom> findById(Long id);
}
