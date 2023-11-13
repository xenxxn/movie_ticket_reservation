package com.ticket.reservation.domain.room;

import com.ticket.reservation.domain.theater.Theater;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  List<Room> findRoomByTheater (Theater theater);
}
