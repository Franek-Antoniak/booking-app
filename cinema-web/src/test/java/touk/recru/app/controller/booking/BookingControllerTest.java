package touk.recru.app.controller.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import touk.recru.app.dto.booking.BookingRequestDTO;
import touk.recru.app.dto.booking.BookingResultDTO;
import touk.recru.app.exception.BookingException;
import touk.recru.app.facade.BookingFacade;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingControllerTest {

	@Nested
	@ExtendWith(MockitoExtension.class)
	class BookingControllerMockMvcTests {
		@InjectMocks
		BookingController bookingController;

		@Mock
		BookingFacade bookingFacade;

		@Spy
		ObjectMapper objectMapper;

		BookingRequestDTO request;
		MockMvc mockMvc;

		@BeforeEach
		void setUp() {
			mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
					.build();
			request = new BookingRequestDTO();
			request.setScreeningId(UUID.randomUUID());
		}

		@Test
		void book_noException() throws Exception {
			// given
			BookingResultDTO resultDTO = new BookingResultDTO();
			resultDTO.setTotalPrice(BigDecimal.ONE);
			// when
			when(bookingFacade.book(any())).thenReturn(resultDTO);
			// then
			mockMvc.perform(post("/booking/book").content(objectMapper.writeValueAsString(request))
							.contentType("application/json"))
					.andExpect(result -> assertEquals(HttpStatus.OK.value(), result.getResponse()
							.getStatus()))
					.andExpect(result -> assertEquals(objectMapper.writeValueAsString(resultDTO), result.getResponse()
							.getContentAsString()));
		}

		@Test
		void book_exceptionMessage() throws Exception {
			// given
			BookingRequestDTO request = new BookingRequestDTO();
			request.setScreeningId(UUID.randomUUID());
			BookingException exception = new BookingException("test");
			MockMvc mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
					.build();
			// when
			when(bookingFacade.book(any())).thenThrow(exception);
			// then
			mockMvc.perform(post("/booking/book").content(objectMapper.writeValueAsString(request))
							.contentType("application/json"))
					.andExpect(result -> assertEquals(exception.getMessage(),
							Objects.requireNonNull(result.getResolvedException())
									.getMessage()));
		}
	}
}