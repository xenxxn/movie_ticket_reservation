package com.ticket.reservation.domain.showtime.repository;

import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

  List<Showtime> findAllByMovieIdAndRoomId(Long movieId, Long roomId);

  List<Showtime> findAllByMovieId(Long movieId);

  List<Showtime> findAllByRoomId(Long theaterId);

  List<Showtime> findShowtimeByRoomId(Long roomId);
}
