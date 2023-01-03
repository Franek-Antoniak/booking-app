package touk.recru.app.dto.person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
	@NotNull
	@Size(
			min = 3,
			message = "Name must be at least 3 characters long"
	)
	@Pattern(
			regexp = "^[A-Z][a-zA-Z]*",
			message = "Name must start with capital letter"
	)
	private String name;

	@NotNull
	@Size(
			min = 3,
			message = "Surname must be at least 3 characters long"
	)
	@Pattern(
			regexp = "^[A-Z][a-zA-Z]*(-[A-Z][a-zA-Z]*)?$",
			message = "Surname must start with capital letter and can contain only letters and one dash"
	)
	private String surname;
}
