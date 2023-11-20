package com.ticket.reservation.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEditInput {

  private Long id;
  private Long theaterId;
  private String name;
  private int totalSeat;

}
