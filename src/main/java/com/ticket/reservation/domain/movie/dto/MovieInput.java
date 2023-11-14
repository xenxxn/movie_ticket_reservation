package com.ticket.reservation.domain.movie.dto;

import com.ticket.reservation.domain.movie.entity.Movie;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieInput {
  private String title;
  private String director;
  private String country;
  private String genre;
  private String information;
  private String grade;
  private String runningTime;
  private LocalDate releaseDate;
  private LocalDate endDate;

  public static Movie toEntity(MovieInput movieInput) {
    return Movie.builder()
        .title(movieInput.getTitle())
        .director(movieInput.getDirector())
        .country(movieInput.getCountry())
        .genre(movieInput.getGenre())
        .information(movieInput.getInformation())
        .grade(movieInput.getGrade())
        .runningTime(movieInput.getRunningTime())
        .releaseDate(movieInput.getReleaseDate())
        .endDate(movieInput.getEndDate())
        .build();
  }
}
