package com.ticket.reservation.domain.seat.dto;

import com.ticket.reservation.domain.seat.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatEditInput {

  private Long id;
  private Long roomId;
  private String row;
  private int number;
  private SeatStatus status;

}
