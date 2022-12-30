package touk.recru.app.entity;

import jakarta.persistence.Embeddable;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Embeddable
@ToString
public class Person {
	private String name;
	private String surname;
	private String email;
	private String phone;
}

