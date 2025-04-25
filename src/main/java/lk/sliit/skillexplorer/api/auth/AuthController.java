package lk.sliit.skillexplorer.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/google")
    public ResponseEntity<?> receiveGoogleToken(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        System.out.println("Received Google token: " + token);

        // TODO: You could verify token here using Google libraries or decode it manually

        return ResponseEntity.ok().body("{\"message\":\"Token received\"}");

    }
}