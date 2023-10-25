package com.ticket.reservation.domain.room;

import com.ticket.reservation.domain.theater.Theater;
import com.ticket.reservation.domain.theater.TheaterRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
  private final RoomRepository roomRepository;
  private final TheaterRepository theaterRepository;
  public Room createRoom(Room room) {
    Optional<Theater> theater = theaterRepository.findById(room.getTheater().getId());
    if (theater.isPresent()) {
      Theater theaterResult = theater.get();
      room.addRoom(theaterResult);
      return roomRepository.save(room);
    }else {
      throw new RuntimeException("존재하지 않는 영화관입니다.");
    }
  }

  public void deleteRoom(Room room) {
    Optional<Theater> theater = theaterRepository.findById(room.getTheater().getId());
    if (theater.isPresent()) {
      Theater theaterResult = theater.get();
      room.removeRoom(theaterResult);
     roomRepository.delete(room);
    } else {
      throw new RuntimeException("존재하지 않는 영화관입니다.");
    }
  }


}
