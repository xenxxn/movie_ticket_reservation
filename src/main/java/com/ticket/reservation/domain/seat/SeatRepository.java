package com.ticket.reservation.domain.seat;

import com.ticket.reservation.domain.room.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
  List<Seat> findSeatByRoomOrderByRow (Room room);

  boolean existsByRowAndNumber(String row, int number);

  Seat findSeatByRoom (Room room);
}
