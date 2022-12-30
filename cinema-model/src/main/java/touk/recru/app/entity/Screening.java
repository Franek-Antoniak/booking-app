package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Screening extends BaseEntity {
	private LocalDateTime screeningTime;
	@ManyToOne
	@JoinColumn(name = "screening_room_id")
	private ScreeningRoom screeningRoom;

	@OneToMany(mappedBy = "screening")
	@ToString.Exclude
	private List<Booking> bookings;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
}
