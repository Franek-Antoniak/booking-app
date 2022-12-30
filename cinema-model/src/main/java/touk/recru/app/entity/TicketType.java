package touk.recru.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum TicketType {
	ADULT(BigDecimal.valueOf(25)), STUDENT(BigDecimal.valueOf(18)), CHILD(BigDecimal.valueOf(12.5));

	private final BigDecimal price;
}
