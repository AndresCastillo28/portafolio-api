package com.andrescastillo.portafolio.restImpl;

import com.andrescastillo.portafolio.entity.ContactInformation;
import com.andrescastillo.portafolio.rest.ContactInformationRest;
import com.andrescastillo.portafolio.service.ContactInformationService;
import com.andrescastillo.portafolio.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContactInformationRestImpl implements ContactInformationRest {

    private final ContactInformationService contactInformationService;
    @Override
    public ResponseEntity<ApiResponse<ContactInformation>> save(ContactInformation contactInformation) {
        try {
            return ResponseEntity.ok(ApiResponse.success(201, contactInformationService.save(contactInformation)));
        } catch (Exception e) {
            log.error("Error saving contact information", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<ContactInformation>>> findAll() {
        try {
            return ResponseEntity.ok(ApiResponse.success(200, contactInformationService.findAll()));
        } catch (Exception e) {
            log.error("Error saving contact information", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error: " + e.getMessage()));
        }
    }

}
