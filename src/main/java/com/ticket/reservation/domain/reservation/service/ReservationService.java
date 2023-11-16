package com.ticket.reservation.domain.reservation.service;

import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.reservation.repository.ReservationRepository;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
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

  public Showtime validateShowtime(Long showtimeId) {
    return showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new NoResultException("해당 상영회차는 존재하지 않습니다."));
  }

  public Seat validateSeat(Long seatId) {
    Seat seat =  seatRepository.findById(seatId)
        .orElseThrow(() -> new NoResultException("해당 좌석은 존재하지 않습니다."));

    if (seat.getStatus() == SeatStatus.RESERVED) {
      throw new RuntimeException("이미 예약된 좌석입니다.");
    }
    return seat;
  }
}
