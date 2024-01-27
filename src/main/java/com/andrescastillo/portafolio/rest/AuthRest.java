package com.andrescastillo.portafolio.rest;

import com.andrescastillo.portafolio.dto.request.AuthRequestDTO;
import com.andrescastillo.portafolio.dto.response.JwtResponseDTO;
import com.andrescastillo.portafolio.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthRest {

    @PostMapping
    ResponseEntity<ApiResponse<JwtResponseDTO>> login(@Valid @RequestBody AuthRequestDTO authRequestDTO);

    @GetMapping("/re-new")
    ResponseEntity<ApiResponse<JwtResponseDTO>> renew(HttpServletRequest request);
}
