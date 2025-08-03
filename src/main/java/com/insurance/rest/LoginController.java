package com.insurance.rest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.LoginDTO;
import com.insurance.exception.RegisterNotFoundException;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtConfig jwtConfig;
//	@PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
//        try {
//            // Authenticate the user using Spring Security's authentication manager
//            Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
//            );
//            
//            // If authentication is successful, generate a JWT token
//            if (authentication.isAuthenticated()) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
//                String token = Jwts.builder()
//                        .setSubject(userDetails.getUsername())
//                        .setIssuedAt(new Date())
//                        .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                        .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
//                        .compact();
//                
//                // Return the JWT token to the client
//                return ResponseEntity.status(HttpStatus.OK).body(token);
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//            }
//        } catch (RegisterNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed");
//        }
//    }
}