package com.ticket.reservation.domain.room;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
  private final RoomService roomService;

  @PostMapping("/{roomId}")
  public ResponseEntity createRoom(@RequestBody Room room, @PathVariable Long roomId) {
    Room makeRoom = roomService.createRoom(room);
    return ResponseEntity.ok(makeRoom);
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity deleteRoom(@RequestBody Room room, @PathVariable Long roomId) {
    roomService.deleteRoom(room);
    return ResponseEntity.ok("Room deleted successfully");
  }
}
