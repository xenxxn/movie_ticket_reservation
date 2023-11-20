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
public class SeatOutput {
  private String row;
  private int number;
  private SeatStatus status;


  public static SeatOutput toResponse(SeatDto seatDto) {
    return SeatOutput.builder()
        .row(seatDto.getRow())
        .number(seatDto.getNumber())
        .status(seatDto.getStatus())
        .build();
  }

  public static List<SeatOutput> toResponseList(List<SeatDto> seatDtos) {
    return seatDtos.stream()
        .map(SeatOutput::toResponse)
        .collect(Collectors.toList());
  }
}
