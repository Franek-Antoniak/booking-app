package touk.recru.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class Movie extends BaseEntity {
	private String title;
	@OneToMany(mappedBy = "movie")
	private List<Screening> screenings;
}
