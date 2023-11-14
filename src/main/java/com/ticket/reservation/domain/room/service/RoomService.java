package com.ticket.reservation.domain.room.service;

import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomEditInput;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.room.dto.RoomOutput;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
  private final RoomRepository roomRepository;
  private final TheaterRepository theaterRepository;

  @Transactional
  public Room createRoom(RoomInput roomInput) {
    Room room = RoomInput.toEntity(roomInput);
    boolean isExistsTheater = theaterRepository.existsById(room.getTheaterId());
    if (!isExistsTheater) {
      throw new NoResultException("존재하지 않는 영화관입니다.");
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

  @Transactional
  public RoomDto editRoom(RoomEditInput roomEditInput) {
    Room room = RoomEditInput.toEntity(roomEditInput);
    boolean isExistsTheater = theaterRepository.existsById(room.getTheaterId());
    if (!isExistsTheater) {
      throw new NoResultException("존재하지 않는 영화관입니다.");
    }
    roomRepository.save(room);
    return RoomDto.fromEntity(room);
  }

  public List<RoomOutput> searchRoomList(Long theaterId){
    Theater theater = theaterRepository.findById(theaterId)
        .orElseThrow(() -> new NoResultException("존재하지 않는 영화관입니다."));
    List<Room> rooms = roomRepository.findRoomByTheater(theater);
    List<RoomDto> roomDtos = RoomDto.toResponseList(rooms);
    return RoomOutput.toResponseList(roomDtos);
  }
}
