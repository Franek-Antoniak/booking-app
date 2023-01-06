package touk.recru.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Seat extends BaseEntity {

	private Integer seatRow;
	private Integer seatNumber;

	@ManyToOne
	@JoinColumn(name = "screening_room_id")
	private ScreeningRoom screeningRoom;
}
