package com.figumatch.controller;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.figumatch.dto.UserCreateRequest;
import com.figumatch.dto.UserResponse;
import com.figumatch.dto.UserUpdateRequest;
import com.figumatch.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserResponse> list(@RequestParam Optional<String> zone,
    @RequestParam Optional<String> email) {
    if (email.isPresent()) {
      return userService.findByEmail(email.get()).map(List::of).orElseGet(List::of);
    }
    return userService.list(zone);
  }

  @GetMapping("/{id}")
  public UserResponse get(@PathVariable Long id) {
    return userService.get(id);
  }

  @PostMapping
  public UserResponse create(@Valid @RequestBody UserCreateRequest request) {
    return userService.create(request);
  }

  @PatchMapping("/{id}")
  public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
    return userService.update(id, request);
  }
}
