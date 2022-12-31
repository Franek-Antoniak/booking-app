package touk.recru.app.controller.screening;

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
import touk.recru.app.dto.room.ScreeningBookingInfoDTO;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.facade.ScreeningFacade;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScreeningControllerTest {
	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchScreeningHowManyTimesFacadeIsCalled {

		@Mock
		ScreeningFacade screeningFacade;

		@InjectMocks
		ScreeningController screeningController;

		@Mock
		Page<ScreeningViewInfoDTO> mockPage;

		LocalDateTime now = LocalDateTime.now();

		@Test
		void searchScreening_withNulls() {
			// given
			// when
			screeningController.searchScreening(null, null, null, null);
			// then
			verifyNoInteractions(screeningFacade);
		}

		@Test
		void searchScreening_fromAndToNulls() {
			// given
			// when
			screeningController.searchScreening(null, null, 5, 5);
			// then
			verifyNoInteractions(screeningFacade);
		}

		@Test
		void searchScreening_fromAndToNotNulls() {
			// given
			// when
			when(screeningFacade.search(any(), any(), anyInt(), anyInt())).thenReturn(mockPage);
			screeningController.searchScreening(now, now, 5, 5);
			// then
			verify(screeningFacade, times(1)).search(any(), any(), anyInt(), anyInt());
		}

		@Test
		void searchScreening_fromAndToNotNullsButSizeAndPageNulls() {
			// given
			// when
			when(screeningFacade.search(any(), any())).thenReturn(mockPage);
			screeningController.searchScreening(now, now, null, null);
			// then
			verify(screeningFacade, times(1)).search(any(), any());
		}

		@Test
		void searchScreening_fromNotNullToNull() {
			// given
			// when
			when(screeningFacade.search(any(), anyInt(), anyInt())).thenReturn(mockPage);
			screeningController.searchScreening(now, null, 5, 5);
			// then
			verify(screeningFacade, times(1)).search(any(), anyInt(), anyInt());
		}

		@Test
		void searchScreening_fromNull() {
			// given
			// when
			screeningController.searchScreening(null, now, null, null);
			// then
			verifyNoInteractions(screeningFacade);
		}
	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchScreeningResponseStatusTests {
		@Mock
		ScreeningFacade mockScreeningFacade;
		@InjectMocks
		ScreeningController controller;
		@Spy
		Page<ScreeningViewInfoDTO> mockPage;

		@Test
		public void testSearchScreening_withAllParams() {
			// given
			// when
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class), anyInt(),
					anyInt())).thenReturn(mockPage);  // return a page with some test data

			// send a GET request to the controller with all parameters
			ResponseEntity<Page<ScreeningViewInfoDTO>> response = controller.searchScreening(LocalDateTime.now(),
					LocalDateTime.now(), 0, 20);

			// then
			// verify that the response has a 200 (OK) status
			assertEquals(HttpStatus.OK, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), any(LocalDateTime.class), eq(0), eq(20));
		}

		@Test
		public void testSearchScreening_withPageAndSizeParams() {
			// given
			// when
			when(mockScreeningFacade.search(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(mockPage);
			// return a page with some test data

			// send a GET request to the controller with page and size parameters
			ResponseEntity<Page<ScreeningViewInfoDTO>> response = controller.searchScreening(LocalDateTime.now(), null,
					1, 10);

			// verify that the response has a 200 (OK) status
			assertEquals(HttpStatus.OK, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), eq(1), eq(10));
		}

		@Test
		public void testSearchScreening_withFromAndToParams() {
			// given
			// when
			when(mockScreeningFacade.search(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(
					mockPage);  // return a page with some test data
			// send a GET request to the controller with from and to parameters
			ResponseEntity<Page<ScreeningViewInfoDTO>> response = controller.searchScreening(LocalDateTime.now(),
					LocalDateTime.now(), null, null);
			// then
			// verify that the response has a 200 (OK) status
			assertEquals(HttpStatus.OK, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was called with the correct arguments
			verify(mockScreeningFacade).search(any(LocalDateTime.class), any(LocalDateTime.class));
		}

		@Test
		public void testSearchScreening_withoutParams() {
			// given
			// when
			// send a GET request to the controller without any parameters
			ResponseEntity<Page<ScreeningViewInfoDTO>> response = controller.searchScreening(null, null, null, null);
			// then
			// verify that the response has a 400 (BAD_REQUEST) status
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

			// verify that the mock ScreeningFacade's search method was not called
			verifyNoInteractions(mockScreeningFacade);
		}

	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	class SearchScreeningBookingInfoHowManyTimesFacadeIsCalled {

		@Mock
		ScreeningFacade screeningFacade;

		@InjectMocks
		ScreeningController screeningController;

		UUID screeningId = UUID.randomUUID();

		@Spy
		ScreeningBookingInfoDTO mockScreeningBookingInfoDTO;

		@Test
		void searchScreening_withNulls() {
			// given
			Optional<ScreeningBookingInfoDTO> mockedOptional = Optional.of(mockScreeningBookingInfoDTO);
			// when
			when(screeningFacade.searchScreeningBookingInfo(screeningId)).thenReturn(mockedOptional);
			screeningController.searchScreeningBookingInfo(screeningId);
			// then
			verify(screeningFacade, times(1)).searchScreeningBookingInfo(screeningId);
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