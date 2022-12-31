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
import touk.recru.app.usecase.MoviesScreeningsSearchByTimeUseCase;
import touk.recru.app.usecase.SearchAvailableSeatsUseCase;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class ScreeningFacadeTest {
	@Mock
	MoviesScreeningsSearchByTimeUseCase moviesScreeningsSearchByTimeUseCase;

	@Mock
	SearchAvailableSeatsUseCase searchAvailableSeatsUseCase;

	@InjectMocks
	private ScreeningFacade screeningFacade;

	@Test
	void search_validationFrom() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<ScreeningFacade>> search1Violation = validator.forExecutables()
				.validateParameters(screeningFacade, ScreeningFacade.class.getMethod("search", LocalDateTime.class),
						new Object[]{
								null
						});
		// then
		assert search1Violation.size() == 1;
		var violation = search1Violation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("search.from");
		assert violation.getMessage()
				.equals("must not be null");
	}

	@Test
	void search_validationTo() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<ScreeningFacade>> searchViolation = validator.forExecutables()
				.validateParameters(screeningFacade,
						ScreeningFacade.class.getMethod("search", LocalDateTime.class, LocalDateTime.class),
						new Object[]{
								LocalDateTime.now(),
								null
						});
		// then
		assert searchViolation.size() == 1;
		var iterator = searchViolation.iterator();
		var violation1 = iterator.next();
		assert violation1.getPropertyPath()
				.toString()
				.equals("search.to");
		assert violation1.getMessage()
				.equals("must not be null");
	}

	@Test
	void search_validationPage() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<ScreeningFacade>> searchViolation = validator.forExecutables()
				.validateParameters(screeningFacade,
						ScreeningFacade.class.getMethod("search", LocalDateTime.class, LocalDateTime.class, int.class,
								int.class), new Object[]{
								LocalDateTime.now(),
								LocalDateTime.now(),
								-1,
								50
						});
		// then
		assert searchViolation.size() == 1;
		var iterator = searchViolation.iterator();
		var violation1 = iterator.next();
		assert violation1.getPropertyPath()
				.toString()
				.equals("search.page");
		assert violation1.getMessage()
				.equals("must be greater than or equal to 0");
	}

	@Test
	void search_validationSize() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<ScreeningFacade>> searchViolation = validator.forExecutables()
				.validateParameters(screeningFacade,
						ScreeningFacade.class.getMethod("search", LocalDateTime.class, LocalDateTime.class, int.class,
								int.class), new Object[]{
								LocalDateTime.now(),
								LocalDateTime.now(),
								0,
								-1
						});
		// then
		assert searchViolation.size() == 1;
		var iterator = searchViolation.iterator();
		var violation1 = iterator.next();
		assert violation1.getPropertyPath()
				.toString()
				.equals("search.size");
		assert violation1.getMessage()
				.equals("must be greater than 0");
	}

	@Test
	void searchInfoScreeningRoom_validationScreeningId() throws NoSuchMethodException {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		// when
		Set<ConstraintViolation<ScreeningFacade>> searchViolation = validator.forExecutables()
				.validateParameters(screeningFacade,
						ScreeningFacade.class.getMethod("searchScreeningBookingInfo", UUID.class), new Object[]{
								null
						});
		// then
		assert searchViolation.size() == 1;
		var violation = searchViolation.iterator()
				.next();
		assert violation.getPropertyPath()
				.toString()
				.equals("searchScreeningBookingInfo.screeningId");
		assert violation.getMessage()
				.equals("must not be null");
	}
}