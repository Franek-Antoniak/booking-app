package touk.recru.app.dto.seat;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatInfoViewDTO {
	private UUID seatId;
	private Integer seatRow;
	private Integer seatNumber;
}

