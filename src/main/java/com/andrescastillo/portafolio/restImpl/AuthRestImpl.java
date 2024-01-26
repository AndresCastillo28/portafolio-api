package com.andrescastillo.portafolio.restImpl;

import com.andrescastillo.portafolio.dto.request.AuthRequestDTO;
import com.andrescastillo.portafolio.dto.response.JwtResponseDTO;
import com.andrescastillo.portafolio.rest.AuthRest;
import com.andrescastillo.portafolio.utils.ApiResponse;
import com.andrescastillo.portafolio.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestImpl implements AuthRest {

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponseDTO>> login(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.GenerateToken(authRequestDTO.getUsername());
            JwtResponseDTO jwt = new JwtResponseDTO();
            jwt.setAccessToken(token);
            return ResponseEntity.ok(ApiResponse.success(200, jwt));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
