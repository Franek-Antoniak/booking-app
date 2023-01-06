package touk.recru.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Booking extends BaseEntity {
	@Embedded
	private Person person;
	@OneToMany(
			mappedBy = "booking",
			fetch = FetchType.EAGER
	)
	@Builder.Default
	@ToString.Exclude
	private List<Ticket> tickets = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screening_id")
	@ToString.Exclude
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
