package touk.recru.app.dto.ticket.type;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import touk.recru.app.entity.TicketType;

import java.util.Arrays;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDTO {
	@NotNull(message = "Ticket type must be specified")
	private String type;

	@AssertTrue(message = "Ticket type must be one of available types")
	public boolean isTicketTypeValid() {
		if (type == null) {
			return true;
		}
		var checkType = type.toUpperCase();
		return Arrays.stream(TicketType.values())
				.map(TicketType::name)
				.anyMatch(checkType::equals);
	}

	public TicketType toEntity() {
		return TicketType.valueOf(type.toUpperCase());
	}
}
