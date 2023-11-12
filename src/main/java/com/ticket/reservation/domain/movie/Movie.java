package com.ticket.reservation.domain.movie;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIE")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "RUNNING_TIME")
    private String runningTime;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;


}
