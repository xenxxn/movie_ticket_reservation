package com.ticket.reservation.domain.reservation.service;

import com.ticket.reservation.domain.reservation.dto.ReservationDto;
import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.reservation.dto.ReservationOutput;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.reservation.repository.ReservationRepository;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ShowtimeRepository showtimeRepository;
  private final SeatRepository seatRepository;

  @Transactional
  public Reservation makeReservation(ReservationInput reservationInput) {
    Reservation reservation = ReservationInput.toEntity(reservationInput);
    Showtime showtime = validateShowtime(reservationInput.getShowtimeId());
    reservation.setShowtime(showtime);
    Seat seat = validateSeat(reservationInput.getSeatId());
    seat.setStatus(SeatStatus.RESERVED);
    reservation.setSeat(seat);
    return reservationRepository.save(reservation);
  }

  @Transactional
  public void deleteReservation(Long seatId, Long showtimeId, Long reservationId) {
    Seat seat = validateSeat(seatId);
    validateShowtime(showtimeId);
    Reservation reservation = validateReservation(reservationId);
    seat.getReservations().remove(reservation);
    seat.setStatus(SeatStatus.UNRESERVED);
    seatRepository.save(seat);
    reservationRepository.delete(reservation);
  }

  public List<ReservationOutput> searchReservationsByShowtime(Long showtimeId) {
    Showtime showtime = validateShowtime(showtimeId);
    List<Reservation> reservations = reservationRepository.findReservationsByShowtime(showtime);
    List<ReservationDto> reservationDtos = ReservationDto.toResponseList(reservations);
    return ReservationOutput.toResponseList(reservationDtos);
  }

  public ReservationOutput searchSpecificReservation(Long reservationId){
    Reservation reservation = validateReservation(reservationId);
    ReservationDto reservationDto = ReservationDto.fromEntity(reservation);
    return ReservationOutput.toResponse(reservationDto);
  }

  public Showtime validateShowtime(Long showtimeId) {
    return showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new NoResultException("해당 상영회차는 존재하지 않습니다."));
  }

  public Seat validateSeat(Long seatId) {
    return seatRepository.findById(seatId)
        .orElseThrow(() -> new NoResultException("해당 좌석은 존재하지 않습니다."));
  }

  public Reservation validateReservation(Long reservationId) {
    return reservationRepository.findById(reservationId)
        .orElseThrow(() -> new NoResultException("해당 예약은 존재하지 않습니다."));
  }
}
