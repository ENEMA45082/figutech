package com.figumatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.figumatch.dto.UserCreateRequest;
import com.figumatch.dto.UserResponse;
import com.figumatch.dto.UserUpdateRequest;
import com.figumatch.entity.User;
import com.figumatch.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserResponse> list(Optional<String> zone) {
    List<User> users = zone.map(userRepository::findByZone).orElseGet(userRepository::findAll);
    return users.stream().map(UserService::toResponse).collect(Collectors.toList());
  }

  public Optional<UserResponse> findByEmail(String email) {
    return userRepository.findByEmailIgnoreCase(email).map(UserService::toResponse);
  }

  public UserResponse get(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return toResponse(user);
  }

  public UserResponse create(UserCreateRequest request) {
    User user = new User();
    user.setName(request.getName());
    user.setAlias(request.getAlias());
    user.setEmail(request.getEmail());
    user.setZone(request.getZone());
    user.setWhatsapp(request.getWhatsapp());
    return toResponse(userRepository.save(user));
  }

  public UserResponse update(Long id, UserUpdateRequest request) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    if (request.getName() != null) user.setName(request.getName());
    if (request.getAlias() != null) user.setAlias(request.getAlias());
    if (request.getEmail() != null) user.setEmail(request.getEmail());
    if (request.getZone() != null) user.setZone(request.getZone());
    if (request.getWhatsapp() != null) user.setWhatsapp(request.getWhatsapp());

    return toResponse(userRepository.save(user));
  }

  public User getEntity(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public static UserResponse toResponse(User user) {
    return new UserResponse(
      user.getId(),
      user.getName(),
      user.getAlias(),
      user.getEmail(),
      user.getZone(),
      user.getWhatsapp(),
      user.getCreatedAt()
    );
  }
}
