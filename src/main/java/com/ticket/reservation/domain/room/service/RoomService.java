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
    Theater theater = validateTheater(room.getTheater().getId());
    if (theater == null) {
      throw new NoResultException("해당 영화관은 존재하지 않습니다.");
    }
      return roomRepository.save(room);
  }

  @Transactional
  public void deleteSpecificRoom(Long theaterId, Long roomId) {
    Theater theater = validateTheater(theaterId);
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new NoResultException("해당 상영관은 존재하지 않습니다."));
    theater.getRooms().remove(room);
    theaterRepository.save(theater);
  }

  @Transactional
  public void deleteAllRooms(Long theaterId) {
    Theater theater = validateTheater(theaterId);
    List<Room> rooms = roomRepository.findRoomByTheaterId(theaterId);

    for (Room room : rooms) {
      room.setRoom(null);
      roomRepository.save(room);
    }

    theater.getRooms().clear();
    theaterRepository.save(theater);
  }


  @Transactional
  public RoomDto editRoom(RoomEditInput roomEditInput) {
    Room room = RoomEditInput.toEntity(roomEditInput);
    Theater theater = validateTheater(room.getTheater().getId());
    if (theater == null) {
      throw new NoResultException("해당 영화관은 존재하지 않습니다.");
    }
    roomRepository.save(room);
    return RoomDto.fromEntity(room);
  }

  public List<RoomOutput> searchRoomList(Long theaterId){
    validateTheater(theaterId);
    List<Room> rooms = roomRepository.findRoomByTheaterId(theaterId);
    List<RoomDto> roomDtos = RoomDto.toResponseList(rooms);
    return RoomOutput.toResponseList(roomDtos);
  }

  public Theater validateTheater(Long theaterId) {
    Theater theater = theaterRepository.findById(theaterId)
        .orElseThrow(() -> new NoResultException("해당 영화관은 존재하지 않습니다."));
    if (theater.getRooms().isEmpty()) {
      throw new NoResultException("해당 상영관은 존재하지 않습니다.");
    }
    return theater;
  }
}
