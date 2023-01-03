package touk.recru.app.dto.screening;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScreeningDTO {
	private UUID screeningId;
	private String movieTitle;
	private LocalDateTime startTime;
	private Duration duration;
}
