package com.reloadly.account.controller;

import com.reloadly.account.config.JwtUtils;
import com.reloadly.account.entity.RoleEntity;
import com.reloadly.account.entity.UserEntity;
import com.reloadly.account.repository.RoleRepository;
import com.reloadly.account.repository.UserRepository;
import com.reloadly.account.request.LoginRequest;
import com.reloadly.account.request.SignupRequest;
import com.reloadly.account.request.UserRequest;
import com.reloadly.account.response.JwtResponse;
import com.reloadly.account.response.MessageResponse;
import com.reloadly.account.service.impl.UserDetailsImpl;
import com.reloadly.account.util.RoleEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
    @RequestMapping("/api/auth")
public class AuthController {
    private final Logger logger = LogManager.getLogger(AuthController.class);
    static final String ERROR_PROCESS = "Error: ";
    static final String ERROR_REQUEST = "Error trying to process the request: ";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/process/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername().toLowerCase(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/process/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername().toLowerCase())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserEntity user = new UserEntity(signUpRequest.getUsername().toLowerCase(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getIdentificationNumber(), signUpRequest.getHolderName(),
                signUpRequest.getHolderLastName(), signUpRequest.getHolderSurName(), signUpRequest.getAddress());

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
                RoleEntity userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRoleEntity = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found 1."));
                        roles.add(adminRoleEntity);

                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found 2."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found 3."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> update(@Validated @RequestBody UserRequest request, @PathVariable Long id) {
        try {
            Optional<UserEntity> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                user.setHolderName(request.getHolderName());
                user.setHolderSurName(request.getHolderSurName());
                user.setHolderLastName(request.getHolderLastName());
                user.setAddress(request.getAddress());
                user = userRepository.save(user);
                return ResponseEntity.ok(user);
            } else {
                return new ResponseEntity<>("This user not exists", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error(ERROR_PROCESS, e);
            return new ResponseEntity<>(ERROR_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }

}
