package touk.recru.app.controller.screening;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import touk.recru.app.dto.screening.MovieScreeningDTO;
import touk.recru.app.dto.screening.ScreeningBookingInfoDTO;
import touk.recru.app.exception.DataIntegrationException;
import touk.recru.app.exception.ScreeningNotFoundException;
import touk.recru.app.facade.ScreeningFacade;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScreeningControllerTest {
	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchScreeningResponseStatusTests {
		@Mock
		ScreeningFacade mockScreeningFacade;
		@InjectMocks
		ScreeningController controller;
		Page<MovieScreeningDTO> mockPage = Page.empty();

		MockMvc mockMvc;

		@BeforeEach
		void setUp() {
			mockMvc = MockMvcBuilders.standaloneSetup(controller)
					.build();
		}

		@Test
		public void testSearchScreening_withAllParams() throws Exception {
			// given
			// when
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(),
					anyInt())).thenReturn(mockPage);  // return a page with some test data

			// send a GET request to the controller with all parameters
			mockMvc.perform(get("/screenings").param("from", LocalDateTime.now()
									.toString())
							.param("to", LocalDateTime.now()
									.toString())
							.param("page", "0")
							.param("size", "20"))
					.andExpect(status().isOk());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), any(LocalDateTime.class), eq(0), eq(20));
		}

		@Test
		public void testSearchScreening_withPageAndSizeParams() throws Exception {
			// given
			// when
			when(mockScreeningFacade.search(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(mockPage);
			// return a page with some test data

			// send a GET request to the controller with page and size parameters
			mockMvc.perform(get("/screenings").param("from", LocalDateTime.now()
									.toString())
							.param("to", (String) null)
							.param("page", "1")
							.param("size", "10"))
					.andExpect(status().isOk());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), eq(1), eq(10));
		}

		@Test
		public void testSearchScreening_withFromAndToParams() throws Exception {
			// given
			// when
			// return a page with some test data
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(),
					anyInt())).thenReturn(mockPage);
			// send a GET request to the controller with from and to parameters
			mockMvc.perform(get("/screenings").param("from", LocalDateTime.now()
									.toString())
							.param("to", LocalDateTime.now()
									.toString()))
					.andExpect(status().isOk());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), any(LocalDateTime.class), eq(0), eq(20));
		}

		@Test
		public void testSearchScreening_withoutParams() throws Exception {
			// given
			// when
			// send a GET request to the controller without any parameters
			mockMvc.perform(get("/screenings"))
					.andExpect(status().isBadRequest());
			// then
			// verify that the mock ScreeningFacade's search method was not called
			verifyNoInteractions(mockScreeningFacade);
		}

		@Test
		void testSearchScreening_screeningNotFoundException() throws Exception {
			// given
			// when
			// return a page with some test data
			ScreeningNotFoundException exception = new ScreeningNotFoundException("test");
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(),
					anyInt())).thenThrow(exception);
			// send a GET request to the controller with from and to parameters
			mockMvc.perform(get("/screenings").param("from", LocalDateTime.now()
									.toString())
							.param("to", LocalDateTime.now()
									.toString()))
					.andExpect(status().isBadRequest())
					.andExpect(result -> assertEquals(exception.getMessage(),
							Objects.requireNonNull(result.getResolvedException())
									.getMessage()));
		}


		@Test
		void testSearchScreening_dataIntegrationException() throws Exception {
			// given
			// when
			// return a page with some test data
			DataIntegrationException exception = new DataIntegrationException("test");
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(),
					anyInt())).thenThrow(exception);
			// send a GET request to the controller with from and to parameters
			mockMvc.perform(get("/screenings").param("from", LocalDateTime.now()
									.toString())
							.param("to", LocalDateTime.now()
									.toString()))
					.andExpect(status().isInternalServerError())
					.andExpect(result -> assertEquals(exception.getMessage(),
							Objects.requireNonNull(result.getResolvedException())
									.getMessage()));
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchScreeningBookingInfoResponseStatusTests {
		@Mock
		ScreeningFacade mockScreeningFacade;
		@InjectMocks
		ScreeningController controller;
		@Spy
		ScreeningBookingInfoDTO mockScreeningBookingInfoDTO;

		@Test
		public void searchAvailableRoom_NotNullResult() {
			// given
			UUID screeningId = UUID.randomUUID();
			// when
			Optional<ScreeningBookingInfoDTO> mockedOptional = Optional.of(mockScreeningBookingInfoDTO);
			when(mockScreeningFacade.searchScreeningBookingInfo(any(UUID.class))).thenReturn(mockedOptional);

			// send a GET request to the controller with all parameters
			ResponseEntity<ScreeningBookingInfoDTO> response = controller.searchScreeningBookingInfo(screeningId);

			// then
			// verify that the response has a 200 (OK) status
			assertEquals(HttpStatus.OK, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).searchScreeningBookingInfo(screeningId);
		}

		@Test
		public void searchAvailableRoom_NullResult() {
			UUID screeningId = UUID.randomUUID();
			// when
			when(mockScreeningFacade.searchScreeningBookingInfo(any(UUID.class))).thenReturn(Optional.empty());

			// send a GET request to the controller with all parameters
			ResponseEntity<ScreeningBookingInfoDTO> response = controller.searchScreeningBookingInfo(screeningId);

			// then
			// verify that the response has a 404 (NOT FOUND) status
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).searchScreeningBookingInfo(screeningId);
		}
	}
}