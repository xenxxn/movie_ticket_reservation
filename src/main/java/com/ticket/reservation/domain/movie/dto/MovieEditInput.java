package com.ticket.reservation.domain.movie.dto;

import com.ticket.reservation.domain.movie.Movie;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEditInput {
  private Long id;
  private String title;
  private String director;
  private String country;
  private String genre;
  private String information;
  private String grade;
  private String runningTime;
  private LocalDate releaseDate;
  private LocalDate endDate;

  public static Movie toEntity(MovieEditInput movieEditInput) {
    return Movie.builder()
        .title(movieEditInput.getTitle())
        .director(movieEditInput.getDirector())
        .country(movieEditInput.getCountry())
        .genre(movieEditInput.getGenre())
        .information(movieEditInput.getInformation())
        .grade(movieEditInput.getGrade())
        .runningTime(movieEditInput.getRunningTime())
        .releaseDate(movieEditInput.getReleaseDate())
        .endDate(movieEditInput.getEndDate())
        .build();
  }
}
