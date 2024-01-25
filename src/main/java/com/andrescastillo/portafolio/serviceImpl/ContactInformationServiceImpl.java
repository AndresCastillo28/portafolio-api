package com.andrescastillo.portafolio.serviceImpl;

import com.andrescastillo.portafolio.dao.ContactInformationRepository;
import com.andrescastillo.portafolio.entity.ContactInformation;
import com.andrescastillo.portafolio.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInformationServiceImpl implements ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        return contactInformationRepository.save(contactInformation);
    }
}
