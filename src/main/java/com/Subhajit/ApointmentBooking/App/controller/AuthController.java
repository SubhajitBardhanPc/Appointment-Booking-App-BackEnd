package com.Subhajit.ApointmentBooking.App.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Subhajit.ApointmentBooking.App.JwtServices.jwtToken;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginPost(
            @RequestBody Map<String,String> creds,
            HttpSession session
    ) {
        String username = creds.get("username");
        String password = creds.get("password");
        if (("Subhajit".equals(username) && "9433601566".equals(password))) {
            session.setAttribute("user", username);
            String token = jwtToken.generateToken(username);
            return ResponseEntity.ok(
                    Map.of(
                            "message","Login Success",
                            "sessionId", session.getId(),
                            "Token",token
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid Login"));
        }
    }
    @GetMapping("/checkToken")
    public ResponseEntity<Map<String,String>>checkToken(
            @RequestHeader(value = "Authorization",required = false) String authHeader
    ){
        if(authHeader == null || !authHeader.startsWith("Token ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("token","null","message","Invalid Login"));
        }
        String token = authHeader.substring(7); // Remove "Bearer "

        // Validate using same username used in token generation
        if (jwtToken.validationToken(token, "Subhajit")) {
            return ResponseEntity.ok(Map.of("message", "Token Valid", "token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("token", "null", "message", "Invalid or Expired Token"));
        }
    }
}

