package com.ticket.reservation.domain.room.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RoomOutput {

  private String theaterName;
  private String name;
  private int totalSeat;

  public static List<RoomOutput> toResponseList(List<RoomDto> roomDtos) {
    return roomDtos.stream()
        .map(RoomOutput::toResponse)
        .collect(Collectors.toList());
  }

  public static RoomOutput toResponse(RoomDto roomDto) {
    return RoomOutput.builder()
        .theaterName(roomDto.getTheaterName())
        .name(roomDto.getName())
        .totalSeat(roomDto.getTotalSeat())
        .build();
  }

}
