package com.ticket.reservation.domain.showtime.entity;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.showtime.dto.ShowtimeEditInput;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOWTIME")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOWTIME_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public void addShowtime(Movie movie, Theater theater) {
        this.movie = movie;
        this.theater = theater;
    }
    public void updateShowtime(ShowtimeEditInput showtimeEditInput) {
        if (showtimeEditInput.getStartTime() != null) {
            this.startTime = showtimeEditInput.getStartTime();
        }
        if (showtimeEditInput.getEndTime() != null) {
            this.endTime = showtimeEditInput.getEndTime();
        }
    }

    public Long getMovieId() {
        if (movie == null){
            throw new NoResultException("해당 영화가 존재하지 않습니다.");
        }
        return movie.getId();
    }

    public Long getTheaterId() {
        if (theater == null) {
            throw new NoResultException("해당 영화관이 존재하지 않습니다.");
        }
        return theater.getId();
    }
}
