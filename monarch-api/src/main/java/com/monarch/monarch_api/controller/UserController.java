package com.monarch.monarch_api.controller;

import com.monarch.monarch_api.dto.LoginRequest;
import com.monarch.monarch_api.dto.UserDto;
import com.monarch.monarch_api.model.User;
import com.monarch.monarch_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PostMapping("/register")
    public ResponseEntity resgister(@RequestBody @Valid UserDto dto) {
        if(repository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest request) {
        Optional<User> user = repository.findByEmail(request.email());

        if(user.isPresent() && user.get().getPassword() != request.password()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto dto) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else if(repository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        User userModel = user.get();
        BeanUtils.copyProperties(dto, userModel);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        repository.delete(user.get());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
