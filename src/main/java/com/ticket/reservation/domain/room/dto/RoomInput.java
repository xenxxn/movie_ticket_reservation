package com.ticket.reservation.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RoomInput {

  private Long theaterId;
  private String name;
  private int totalSeat;

}
