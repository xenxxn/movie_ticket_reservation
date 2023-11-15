package com.ticket.reservation.domain.movie.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MovieOutput {
  private String title;
  private String director;
  private String country;
  private String genre;
  private String information;
  private String grade;
  private String runningTime;
  private LocalDate releaseDate;
  private LocalDate endDate;

  public static List<MovieOutput> toResponse(List<MovieDto> movieDtos) {
    return movieDtos.stream()
        .map(MovieOutput::toResponse)
        .collect(Collectors.toList());
  }

  public static MovieOutput toResponse(MovieDto movieDto) {
    return MovieOutput.builder()
        .title(movieDto.getTitle())
        .director(movieDto.getDirector())
        .country(movieDto.getCountry())
        .genre(movieDto.getGenre())
        .information(movieDto.getInformation())
        .grade(movieDto.getGrade())
        .runningTime(movieDto.getRunningTime())
        .releaseDate(movieDto.getReleaseDate())
        .endDate(movieDto.getEndDate())
        .build();
  }
}
