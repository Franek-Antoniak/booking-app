package touk.recru.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Booking extends BaseEntity {
	@Embedded
	private Person person;
	@OneToMany(mappedBy = "booking")
	@Builder.Default
	@ToString.Exclude
	private Set<Ticket> tickets = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;

	public BigDecimal getTotalPrice() {
		return tickets.stream()
				.map(Ticket::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public List<Seat> getSeats() {
		return tickets.stream()
				.map(Ticket::getSeat)
				.collect(Collectors.toList());
	}
}
