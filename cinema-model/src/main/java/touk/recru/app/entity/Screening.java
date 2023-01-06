package touk.recru.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Screening extends BaseEntity {
	private LocalDateTime screeningTime;
	@ManyToOne
	@JoinColumn(name = "screening_room_id")
	private ScreeningRoom screeningRoom;

	@OneToMany(mappedBy = "screening")
	@ToString.Exclude
	@Builder.Default
	private List<Booking> bookings = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
}
