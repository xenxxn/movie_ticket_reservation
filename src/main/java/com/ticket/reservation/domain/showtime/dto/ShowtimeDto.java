package com.ticket.reservation.domain.showtime.dto;

import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ShowtimeDto {
  private Long id;
  private Long movieId;
  private Long theaterId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  //TODO : movie, theater service 와 controller 완료 후에 사용할 메소드
//  public void addShowtime(Movie movie, Theater theater) {
//    this.movie = movie;
//    this.theater = theater;
//  }

  public static ShowtimeDto fromEntity(Showtime showtime){
    return ShowtimeDto.builder()
        .id(showtime.getId())
        .movieId(showtime.getMovieId())
        .theaterId(showtime.getTheaterId())
        .startTime(showtime.getStartTime())
        .endTime(showtime.getEndTime())
        .build();
  }
}
