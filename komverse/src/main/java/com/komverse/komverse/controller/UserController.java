package com.komverse.komverse.controller;

import com.komverse.komverse.model.DTO.UserDTO;
import com.komverse.komverse.model.entity.User;
import com.komverse.komverse.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Integer code) {
        if(userService.existsById(code)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(userService.findById(code));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Integer code) {
        if(userService.existsById(code)) {
            userService.deleteById(code);
            return ResponseEntity.status(HttpStatus.FOUND).body("User deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }
}
