package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Movie extends BaseEntity {
	private String title;
	private Duration duration;
	@OneToMany(mappedBy = "movie")
	@Builder.Default()
	@ToString.Exclude
	private Set<Screening> screenings = new HashSet<>();
}
