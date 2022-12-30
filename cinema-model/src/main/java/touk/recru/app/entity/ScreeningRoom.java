package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.SortedSet;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class ScreeningRoom extends BaseEntity {
	private int rows;
	@OneToMany(mappedBy = "screeningRoom")
	private SortedSet<Screening> screenings;

	@OneToMany(mappedBy = "screeningRoom")
	@JoinColumn(name = "booking_id")
	private List<Seat> seats;
}
