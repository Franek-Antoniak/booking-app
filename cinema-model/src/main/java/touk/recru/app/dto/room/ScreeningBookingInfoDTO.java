package touk.recru.app.dto.room;

import lombok.*;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.entity.Seat;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningBookingInfoDTO {
	private UUID screeningId;
	private String roomLocation;
	private Integer rows;
	private Integer columns;
	private List<SeatInfoViewDTO> availableSeats;
}