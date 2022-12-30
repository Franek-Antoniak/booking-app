package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class Seat extends BaseEntity {
	private int row;
	private int seatNumber;
	private boolean occupied;

	@ManyToOne
	@JoinColumn(name = "screening_room_id")
	private ScreeningRoom screeningRoom;
}
