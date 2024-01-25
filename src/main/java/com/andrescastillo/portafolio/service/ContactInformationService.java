package com.andrescastillo.portafolio.service;

import com.andrescastillo.portafolio.entity.ContactInformation;

import java.util.List;

public interface ContactInformationService {
    ContactInformation save(ContactInformation contactInformation);

    List<ContactInformation> findAll();
}
