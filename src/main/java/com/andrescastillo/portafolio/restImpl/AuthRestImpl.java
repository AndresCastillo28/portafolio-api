package com.andrescastillo.portafolio.restImpl;

import com.andrescastillo.portafolio.dto.request.AuthRequestDTO;
import com.andrescastillo.portafolio.dto.response.JwtResponseDTO;
import com.andrescastillo.portafolio.rest.AuthRest;
import com.andrescastillo.portafolio.utils.ApiResponse;
import com.andrescastillo.portafolio.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

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

    @Override
    public ResponseEntity<ApiResponse<JwtResponseDTO>> renew(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        if (token == null) {
            // Return an error response if the token is not present in the request
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(400, "Token is missing"));
        }

        try {
            String user = jwtUtils.extractUsername(token);
            if (jwtUtils.validateToken(token, user)) {
                String newToken = jwtUtils.GenerateToken(user);
                JwtResponseDTO jwt = new JwtResponseDTO();
                jwt.setAccessToken(newToken);
                return ResponseEntity.ok(ApiResponse.success(200, jwt));
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error(401, "Invalid token"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(500, "An error occurred: " + e.getMessage()));
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String tokenHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (tokenHeader != null && tokenHeader.startsWith(BEARER_PREFIX)) {
            return tokenHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

}
