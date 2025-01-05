package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("SELECT m FROM Message m ORDER BY m.id ASC LIMIT 1")
  Optional<Message> findFirstMessage();

  @Query("SELECT m FROM Message m WHERE m.id > :id ORDER BY m.id ASC LIMIT 1")
  Optional<Message> findNextMessage(@Param("id") Long id);

  @Query("SELECT m FROM Message m WHERE m.id < :id ORDER BY m.id DESC LIMIT 1")
  Optional<Message> findPreviousMessage(@Param("id") Long id);

}
