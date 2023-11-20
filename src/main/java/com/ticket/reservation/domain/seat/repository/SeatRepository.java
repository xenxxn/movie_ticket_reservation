package com.ticket.reservation.domain.seat.repository;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.entity.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

  List<Seat> findSeatByRoomOrderByRow(Room room);

  boolean existsByRowAndNumber(String row, int number);

  List<Seat> findAllByRoomId(Long roomId);

  List<Seat> findSeatsByShowtimeId(Long showtimeId);
}
