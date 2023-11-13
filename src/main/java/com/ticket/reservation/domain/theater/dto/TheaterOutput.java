package com.ticket.reservation.domain.theater.dto;

import com.ticket.reservation.domain.theater.Theater;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TheaterOutput {
  private String name;
  private String location;
  private LocalTime openTime;
  private LocalTime closeTime;

  public static List<TheaterOutput> toResponse(List<TheaterDto> theaterDtos) {
    return theaterDtos.stream()
        .map(TheaterOutput::toResponse)
        .collect(Collectors.toList());
  }

  public static TheaterOutput toResponse(TheaterDto theaterDto) {
    return TheaterOutput.builder()
        .name(theaterDto.getName())
        .location(theaterDto.getLocation())
        .openTime(theaterDto.getOpenTime())
        .closeTime(theaterDto.getCloseTime())
        .build();
  }
}
