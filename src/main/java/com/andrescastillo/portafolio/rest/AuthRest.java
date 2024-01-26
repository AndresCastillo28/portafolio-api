package com.andrescastillo.portafolio.rest;

import com.andrescastillo.portafolio.dto.request.AuthRequestDTO;
import com.andrescastillo.portafolio.dto.response.JwtResponseDTO;
import com.andrescastillo.portafolio.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthRest {

    @PostMapping
    public ResponseEntity<ApiResponse<JwtResponseDTO>> login(@Valid @RequestBody AuthRequestDTO authRequestDTO);
}
