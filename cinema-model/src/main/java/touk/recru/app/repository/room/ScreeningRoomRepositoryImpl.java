package touk.recru.app.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import touk.recru.app.entity.ScreeningRoom;

import java.util.List;
import java.util.Optional;

@Repository
interface ScreeningRoomRepositoryImpl extends ScreeningRoomRepository, JpaRepository<ScreeningRoom, Long> {
	List<ScreeningRoom> findAll();

	Optional<ScreeningRoom> findById(Long id);
}
