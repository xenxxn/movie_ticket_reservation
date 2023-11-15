package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RoomOutput {
  private String name;

  public static Room toEntity(RoomOutput roomOutput) {
    return Room.builder()
        .name(roomOutput.getName())
        .build();
  }

  public static List<RoomOutput> toResponseList(List<RoomDto> roomDtos) {
    return roomDtos.stream()
        .map(RoomOutput::toResponse)
        .collect(Collectors.toList());
  }

  public static RoomOutput toResponse(RoomDto roomDto) {
    return RoomOutput.builder()
        .name(roomDto.getName())
        .build();
  }

}
