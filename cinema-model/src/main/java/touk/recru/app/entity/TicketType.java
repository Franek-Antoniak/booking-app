package touk.recru.app.entity;

import java.math.BigDecimal;

public enum TicketType {
	ADULT(BigDecimal.valueOf(25)), STUDENT(BigDecimal.valueOf(18)), CHILD(BigDecimal.valueOf(12.5));

	private final BigDecimal price;

	TicketType(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
