package com.ticket.reservation.domain.room.controller;

import com.ticket.reservation.domain.room.service.RoomService;
import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomEditInput;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.room.dto.RoomOutput;
import com.ticket.reservation.domain.room.entity.Room;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
  private final RoomService roomService;

  @PostMapping("/{roomId}")
  public ResponseEntity<RoomDto> addRoom(@RequestBody RoomInput roomInput, @PathVariable Long roomId) {
    Room makeRoom = roomService.addRoom(roomInput);
    RoomDto roomDto = RoomDto.fromEntity(makeRoom);
    return ResponseEntity.ok(roomDto);
  }

  @DeleteMapping("/{theaterId}/{roomId}")
  public ResponseEntity<String> deleteSpecificRoom(@PathVariable Long theaterId, @PathVariable Long roomId) {
    roomService.deleteSpecificRoom(theaterId, roomId);
    return ResponseEntity.ok("해당 상영관이 삭제되었습니다.");
  }

  @DeleteMapping("/{theaterId}")
  public ResponseEntity<String> deleteAllRooms(@PathVariable Long theaterId) {
    roomService.deleteAllRooms(theaterId);
    return ResponseEntity.ok("모든 상영관이 삭제되었습니다.");
  }

  @PutMapping("/modification/{roomId}")
  public ResponseEntity<RoomDto> editRoom(@PathVariable("roomId") Long roomId,@RequestBody RoomEditInput roomEditInput) {
    return ResponseEntity.ok(roomService.editRoom(roomEditInput));
  }

  @GetMapping("/list/{theaterId}")
  public ResponseEntity<List<RoomOutput>> searchRoomList(@PathVariable Long theaterId) {
    return ResponseEntity.ok(roomService.searchRoomList(theaterId));
  }
}
