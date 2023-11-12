package com.ticket.reservation.domain.showtime;

import com.ticket.reservation.domain.movie.Movie;
import com.ticket.reservation.domain.theater.Theater;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    //getMovie를 했을 때, 만약 Null 이라면 getId() 호출 시 NPE가 발생하기때문에 NPE방지를 위한 메소드 생성
    public Long getMovieId() {
        return movie.getId();
    }

    public Long getTheaterId() {
        return theater.getId();
    }
}
