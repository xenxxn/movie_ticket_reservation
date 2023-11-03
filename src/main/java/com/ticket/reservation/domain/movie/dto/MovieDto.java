package com.ticket.reservation.domain.movie.dto;

import com.ticket.reservation.domain.movie.Movie;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MovieDto {
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

  public static MovieDto fromEntity(Movie movie) {
    return MovieDto.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .director(movie.getDirector())
        .country(movie.getCountry())
        .genre(movie.getGenre())
        .information(movie.getInformation())
        .grade(movie.getGrade())
        .runningTime(movie.getRunningTime())
        .releaseDate(movie.getReleaseDate())
        .endDate(movie.getEndDate())
        .build();
  }
}
