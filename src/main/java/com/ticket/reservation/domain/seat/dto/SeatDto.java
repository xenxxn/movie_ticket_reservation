package com.ticket.reservation.domain.seat.dto;

import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.SeatStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SeatDto {
  private Long id;
  private Long roomId;
  private String row;
  private int number;
  private SeatStatus status;

  public static SeatDto fromEntity(Seat seat) {
    return SeatDto.builder()
        .id(seat.getId())
        .roomId(seat.getRoomId())
        .row(seat.getRow())
        .number(seat.getNumber())
        .build();
  }

  public static Seat toEntity(SeatDto seatDto) {
    return Seat.builder()
        .id(seatDto.getId())
        .row(seatDto.getRow())
        .number(seatDto.getNumber())
        .status(seatDto.getStatus())
        .build();
  }

  public static List<SeatDto> toResponseList(List<Seat> seats) {
    return seats.stream()
        .map(SeatDto::fromEntity)
        .collect(Collectors.toList());
  }
}
