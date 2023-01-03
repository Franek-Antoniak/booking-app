package touk.recru.app.facade;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.person.PersonDTO;
import touk.recru.app.dto.ticket.type.TicketTypeDTO;
import touk.recru.app.entity.TicketType;
import touk.recru.app.usecase.BookingTicketsUseCase;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BookingFacadeTest {


	@Mock
	private BookingTicketsUseCase bookingTicketsUseCase;

	@InjectMocks
	private BookingFacade bookingFacade;

	@Test
	void book_validationBookingRequest() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{null});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void book_validationBookingRequest_screeningIdNotNull() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))
				.person(PersonDTO.builder()
						.surname("Surname")
						.name("Name")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.screeningId");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void book_validationBookingRequest_seatsNotEmpty() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))
				.person(PersonDTO.builder()
						.surname("Surname")
						.name("Name")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.seats");
		assert violation.getMessage()
				.equals("must not be empty");
	}

	@Test
	void book_validationBookingRequest_ticketsTypeNotEmpty() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.person(PersonDTO.builder()
						.surname("Surname")
						.name("Name")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.ticketsType");
		assert violation.getMessage()
				.equals("must not be empty");
	}

	@Test
	void book_validationBookingRequest_personNotNull() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void book_validationBookingRequest_person_nameNotNull() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.name");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void book_validationBookingRequest_person_surnameNotNull() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("Name")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.surname");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void book_validationBookingRequest_person_nameMinSize3() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("S")
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.name");
		assert violation.getMessage()
				.equals("Name must be at least 3 characters long");
	}

	@Test
	void book_validationBookingRequest_person_surnameMinSize3() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("Name")
						.surname("S")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.surname");
		assert violation.getMessage()
				.equals("Surname must be at least 3 characters long");
	}

	@Test
	void book_validationBookingRequest_person_nameStartsWithCapitalLetter() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("name")
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.name");
		assert violation.getMessage()
				.equals("Name must start with capital letter");
	}

	@Test
	void book_validationBookingRequest_person_surnameMatchThePattern() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("Name")
						.surname("Surname Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.person.surname");
		assert violation.getMessage()
				.equals("Surname must start with capital letter and can contain only letters and one dash");
	}

	@Test
	void book_validationBookingRequest_person_surnameShouldHandlePolishCharacters() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("Name")
						.surname("Żuręurname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 0;
	}

	@Test
	void book_validationBookingRequest_person_nameShouldHandlePolishCharacters() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(TicketType.ADULT.name())
						.build()))

				.person(PersonDTO.builder()
						.name("Ńąmę")
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 0;
	}


	@Test
	void book_validationBookingRequest_ticketsType_ticketTypeNotNull() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type(null)
						.build()))
				.person(PersonDTO.builder()
						.name("Name")
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.ticketsType[0].type");
		assert violation.getMessage()
				.equals("Ticket type must be specified");
	}

	@Test
	void book_validationBookingRequest_ticketsType_isAdultTrueAndIsChildTrueAndIsStudentTrue() throws NoSuchMethodException {
		// given
		BookingRequestDTO request = BookingRequestDTO.builder()
				.screeningId(UUID.randomUUID())
				.seats(List.of(UUID.randomUUID()))
				.ticketsType(List.of(TicketTypeDTO.builder()
						.type("NOT-VALID")
						.build()))
				.person(PersonDTO.builder()
						.name("Name")
						.surname("Surname")
						.build())
				.build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<BookingFacade>> search1Violation = validator.forExecutables()
				.validateParameters(bookingFacade, BookingFacade.class.getMethod("book", BookingRequestDTO.class),
						new Object[]{request});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("book.bookingRequest.ticketsType[0].ticketTypeValid");
		assert violation.getMessage()
				.equals("Ticket type must be one of available types");
	}
}