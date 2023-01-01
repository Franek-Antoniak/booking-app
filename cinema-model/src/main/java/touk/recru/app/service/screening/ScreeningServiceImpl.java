package touk.recru.app.service.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import touk.recru.app.dto.room.ScreeningBookingInfoDTO;
import touk.recru.app.dto.screening.ScreeningViewInfoDTO;
import touk.recru.app.dto.seat.SeatInfoViewDTO;
import touk.recru.app.entity.Booking;
import touk.recru.app.entity.Screening;
import touk.recru.app.entity.Seat;
import touk.recru.app.mapper.room.ScreeningSeatBookingInfoMapper;
import touk.recru.app.mapper.screening.ScreeningViewInfoMapper;
import touk.recru.app.mapper.seat.SeatViewInfoMapper;
import touk.recru.app.repository.booking.BookingRepository;
import touk.recru.app.repository.room.ScreeningRoomRepository;
import touk.recru.app.repository.screening.ScreeningRepository;
import touk.recru.app.service.booking.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ScreeningServiceImpl extends ScreeningService {
	private final ScreeningRepository screeningRepository;
	private final ScreeningViewInfoMapper screeningMapper;
	private final ScreeningSeatBookingInfoMapper screeningSeatBookingInfoMapper;
	private final BookingService bookingService;
	private final SeatViewInfoMapper seatViewInfoMapper;
	private final ScreeningRoomRepository screeningRoomRepository;
	private final BookingRepository bookingRepository;

	@Override
	public Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, LocalDateTime to, Pageable pageable) {
		return screeningRepository.findScreeningByScreeningTimeBetween(from, to, pageable)
				.map(screeningMapper::toDto);

	}

	@Override
	public Page<ScreeningViewInfoDTO> searchByTime(LocalDateTime from, Pageable pageable) {
		return screeningRepository.findScreeningByScreeningTimeAfter(from, pageable)
				.map(screeningMapper::toDto);
	}

	@Override
	public Optional<ScreeningBookingInfoDTO> searchAvailableSeats(UUID screeningId) {
		Optional<Screening> screening = screeningRepository.findScreeningByUuid(screeningId);
		if (screening.isEmpty()) {
			return Optional.empty();
		}
		Screening screeningEntity = screening.get();
		List<Seat> seats = screeningRoomRepository.findById(screeningEntity.getScreeningRoom()
						.getId())
				.orElseThrow()
				.getSeats();
		List<Booking> bookings = bookingRepository.findAllByIdIn(screeningEntity.getBookings()
				.stream()
				.map(Booking::getId)
				.collect(Collectors.toSet()));
		List<SeatInfoViewDTO> availableSeats = bookingService.getAvailableSeats(seats, bookings)
				.stream()
				.map(seatViewInfoMapper::toDto)
				.collect(Collectors.toList());
		ScreeningBookingInfoDTO screeningBookingInfoDTO = screeningSeatBookingInfoMapper.toDto(screeningEntity);
		screeningBookingInfoDTO.setAvailableSeats(availableSeats);
		return Optional.of(screeningBookingInfoDTO);
	}
}
