package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/message")
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @GetMapping("/say-hello")
  public String sayHello() {
    return "hello";
  }

  @GetMapping("/get-first-message")
  public ResponseEntity<?> getFirstMessage() {
    return messageRepository.findFirstMessage()
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/get-message/{id}")
  public ResponseEntity<?> getMessage(@PathVariable Long id) {
    Optional<Message> messageOpt = messageRepository.findById(id);
    if (messageOpt.isPresent()) {
      return ResponseEntity.ok(messageOpt.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/get-next-message/{id}")
  public ResponseEntity<?> getNextMessage(@PathVariable Long id) {
    // Call the repository to find the next message with an ID greater than the
    // provided one
    Optional<Message> nextMessage = messageRepository.findNextMessage(id);

    // If the next message exists, return it
    if (nextMessage.isPresent()) {
      return ResponseEntity.ok(nextMessage.get());
    } else {
      // If no next message exists, return a 404 response
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No next message found");
    }
  }

  @GetMapping("/get-previous-message/{id}")
  public ResponseEntity<?> getPreviousMessage(@PathVariable Long id) {
    // Call the repository to find the next message with an ID greater than the
    // provided one
    Optional<Message> previousMessage = messageRepository.findPreviousMessage(id);

    // If the next message exists, return it
    if (previousMessage.isPresent()) {
      return ResponseEntity.ok(previousMessage.get());
    } else {
      // If no next message exists, return a 404 response
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No next message found");
    }
  }

  @PostMapping("/create-message")
  public ResponseEntity<?> createMessage(@RequestBody Map<String, String> messageBody) {
    String message = messageBody.get("message");
    String description = "";
    if (messageBody.get("detailDescription") != null) {
      description = messageBody.get("detailDescription");
    }
    Message newMessage = Message.builder().message(message).detailDescription(description).build();
    messageRepository.save(newMessage);
    return ResponseEntity.ok(newMessage);
  }

  @GetMapping("/get-all-messages")
  public ResponseEntity<?> getAllMessages() {
    List<Message> messages = messageRepository.findAll();
    return ResponseEntity.ok(messages);
  }

  @DeleteMapping("/delete-message/{id}")
  public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
    Optional<Message> messageOpt = messageRepository.findById(id);
    if (messageOpt.isPresent()) {
      messageRepository.deleteById(id);
      return ResponseEntity.ok("successfully deleted");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("edit-message")
  public ResponseEntity<?> editMessage(@RequestBody Message message) {
    Optional<Message> messageOpt = messageRepository.findById(message.getId());
    if (messageOpt.isPresent()) {
      Message edited = messageRepository.save(message);
      return ResponseEntity.ok(Map.of("result", "success", "message", edited));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
