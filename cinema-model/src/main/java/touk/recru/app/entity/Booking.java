package touk.recru.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class Booking extends BaseEntity {

	private LocalDateTime expirationTime;
	@Embedded
	private Person person;
	@OneToMany(mappedBy = "booking")
	private List<Ticket> tickets = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;

	public BigDecimal getTotalPrice() {
		return tickets.stream()
		              .map(Ticket::getPrice)
		              .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public boolean isExpired() {
		return LocalDateTime.now()
		                    .isAfter(expirationTime);
	}

	public List<Seat> getSeats() {
		return tickets.stream()
		              .map(Ticket::getSeat)
		              .collect(Collectors.toList());
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		ticket.setBooking(this);
	}
}
