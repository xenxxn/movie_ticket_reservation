package com.ticket.reservation.domain.reservation.dto;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import java.time.LocalDateTime;
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
  private String theaterName;
  private String roomName;
  private ReservationStatus reservationStatus;
  private String row;
  private int number;
  private String movieTitle;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static ReservationOutput toResponse(ReservationDto reservationDto) {
    return ReservationOutput.builder()
        .reservationId(reservationDto.getReservationId())
        .showtimeId(reservationDto.getShowtimeId())
        .seatId(reservationDto.getSeatId())
        .theaterName(reservationDto.getTheaterName())
        .roomName(reservationDto.getRoomName())
        .reservationStatus(reservationDto.getReservationStatus())
        .row(reservationDto.getSeatRow())
        .number(reservationDto.getSeatNumber())
        .movieTitle(reservationDto.getMovieTitle())
        .startTime(reservationDto.getStartTime())
        .endTime(reservationDto.getEndTime())
        .build();
  }

  public static List<ReservationOutput> toResponseList(List<ReservationDto> reservationDtos) {
    return reservationDtos.stream()
        .map(ReservationOutput::toResponse)
        .collect(Collectors.toList());
  }

}
