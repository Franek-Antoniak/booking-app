package touk.recru.app.dto.booking;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResultDTO {
	private BigDecimal totalPrice;
	private LocalDateTime expirationTime;
}
