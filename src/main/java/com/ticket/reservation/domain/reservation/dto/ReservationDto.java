package com.ticket.reservation.domain.reservation.dto;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReservationDto {
  private Long reservationId;
  private Long showtimeId;
  private Long seatId;
  private ReservationStatus reservationStatus;

  public static ReservationDto fromEntity(Reservation reservation) {
    return ReservationDto.builder()
        .reservationId(reservation.getId())
        .showtimeId(reservation.getShowtime().getId())
        .seatId(reservation.getSeat().getId())
        .reservationStatus(reservation.getReservationStatus())
        .build();
  }

  public static Reservation toEntity(ReservationDto reservationDto) {
    Showtime showtime = Showtime.builder().id(reservationDto.getShowtimeId()).build();
    Seat seat = Seat.builder().id(reservationDto.getSeatId()).build();

    return Reservation.builder()
        .id(reservationDto.getReservationId())
        .showtime(showtime)
        .seat(seat)
        .reservationStatus(reservationDto.getReservationStatus())
        .build();
  }

  public static List<ReservationDto> toResponseList(List<Reservation> reservations) {
    return reservations.stream()
        .map(ReservationDto::fromEntity)
        .collect(Collectors.toList());
  }
}
