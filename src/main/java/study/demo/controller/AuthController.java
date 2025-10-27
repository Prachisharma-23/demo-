package study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.User;
import study.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}
	@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
    return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword())
            .<ResponseEntity<?>>map(user -> ResponseEntity.ok(user))
            .orElseGet(() -> ResponseEntity.status(401).body("Invalid email or password"));
}

}


