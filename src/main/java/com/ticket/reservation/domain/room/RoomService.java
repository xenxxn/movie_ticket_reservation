package com.ticket.reservation.domain.room;

import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.theater.Theater;
import com.ticket.reservation.domain.theater.TheaterRepository;
import java.util.Optional;
import javax.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
  private final RoomRepository roomRepository;
  private final TheaterRepository theaterRepository;
  public Room createRoom(RoomInput roomInput) {
    Room room = RoomInput.toEntity(roomInput);
    boolean isExistsTheater = theaterRepository.existsById(room.getTheaterId());
    if (!isExistsTheater) {
      throw new NoResultException("존재하지 않는 영화관 입니다.");
    }
      return roomRepository.save(room);
  }

  public void deleteRoom(Room room) {
    Optional<Theater> theater = theaterRepository.findById(room.getTheater().getId());
    if (theater.isPresent()) {
      Theater theaterResult = theater.get();
      room.removeRoom(theaterResult);
      roomRepository.delete(room);
    } else {
      throw new NoResultException("존재하지 않는 영화관입니다.");
    }
  }
}
