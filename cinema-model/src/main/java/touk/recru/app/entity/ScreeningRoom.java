package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ScreeningRoom extends BaseEntity {
	private Integer rows;
	private Integer columns;
	private String location;

	@OneToMany(mappedBy = "screeningRoom")
	@ToString.Exclude
	@Builder.Default
	private List<Screening> screenings = new ArrayList<>();

	@OneToMany(mappedBy = "screeningRoom", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Seat> seats = new ArrayList<>();
}
