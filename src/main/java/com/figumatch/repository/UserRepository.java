package com.figumatch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.figumatch.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByZone(String zone);
  Optional<User> findByEmailIgnoreCase(String email);
}
