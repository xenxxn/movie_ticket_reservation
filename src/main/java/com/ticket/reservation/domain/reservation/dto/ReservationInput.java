package com.ticket.reservation.domain.reservation.dto;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationInput {
  private Long reservationId;
  private Long showtimeId;
  private Long seatId;
  private ReservationStatus reservationStatus;

  public static Reservation toEntity(ReservationInput reservationInput) {
    Showtime showtime = Showtime.builder().id(reservationInput.getShowtimeId()).build();
    Seat seat = Seat.builder().id(reservationInput.getSeatId()).build();

    return Reservation.builder()
        .id(reservationInput.getReservationId())
        .showtime(showtime)
        .seat(seat)
        .reservationStatus(ReservationStatus.UNUSED)
        .build();
  }
}
