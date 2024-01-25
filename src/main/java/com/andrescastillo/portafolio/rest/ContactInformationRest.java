package com.andrescastillo.portafolio.rest;

import com.andrescastillo.portafolio.entity.ContactInformation;
import com.andrescastillo.portafolio.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contact-information")
public interface ContactInformationRest {

    @PostMapping
    ResponseEntity<ApiResponse<ContactInformation>> save(@Valid @RequestBody ContactInformation contactInformation);
}
