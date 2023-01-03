package touk.recru.app.dto.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import touk.recru.app.dto.person.PersonDTO;
import touk.recru.app.dto.ticket.type.TicketTypeDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

	@NotNull
	private UUID screeningId;
	@NotEmpty
	private List<@NotNull UUID> seats;

	@NotEmpty
	@Valid
	private List<@NotNull TicketTypeDTO> ticketsType;
	@NotNull
	@Valid
	private PersonDTO person;
}