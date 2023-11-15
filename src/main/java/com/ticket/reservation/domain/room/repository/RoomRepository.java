package com.ticket.reservation.domain.room.repository;

import com.ticket.reservation.domain.room.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  List<Room> findRoomByTheaterId (Long theaterId);
}
