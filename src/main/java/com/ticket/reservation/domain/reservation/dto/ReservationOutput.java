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
@AllArgsConstructor
@Builder
public class ReservationOutput {
  private Long reservationId;
  private Long showtimeId;
  private Long seatId;
  private ReservationStatus reservationStatus;

  public static Reservation toEntity(ReservationOutput reservationOutput) {
    Showtime showtime = Showtime.builder().id(reservationOutput.getShowtimeId()).build();
    Seat seat = Seat.builder().id(reservationOutput.getSeatId()).build();

    return Reservation.builder()
        .id(reservationOutput.getReservationId())
        .showtime(showtime)
        .seat(seat)
        .reservationStatus(reservationOutput.getReservationStatus())
        .build();
  }

  public static ReservationOutput toResponse(ReservationDto reservationDto) {
    return ReservationOutput.builder()
        .reservationId(reservationDto.getReservationId())
        .showtimeId(reservationDto.getShowtimeId())
        .seatId(reservationDto.getSeatId())
        .reservationStatus(reservationDto.getReservationStatus())
        .build();
  }

  public static List<ReservationOutput> toResponseList(List<ReservationDto> reservationDtos) {
    return reservationDtos.stream()
        .map(ReservationOutput::toResponse)
        .collect(Collectors.toList());
  }

}
