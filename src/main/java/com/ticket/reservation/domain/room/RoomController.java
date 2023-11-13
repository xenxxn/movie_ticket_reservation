package com.ticket.reservation.domain.room;

import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomEditInput;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.room.dto.RoomOutput;
import com.ticket.reservation.domain.theater.Theater;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
  private final RoomService roomService;

  @PostMapping("/{roomId}")
  public RoomDto createRoom(@RequestBody RoomInput roomInput, @PathVariable Long roomId) {
    Room makeRoom = roomService.createRoom(roomInput);
    return RoomDto.fromEntity(makeRoom);
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<String> deleteRoom(@RequestBody Room room, @PathVariable Long roomId) {
    roomService.deleteRoom(room);
    return ResponseEntity.ok("Room deleted successfully");
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
