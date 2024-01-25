package com.andrescastillo.portafolio.dao;

import com.andrescastillo.portafolio.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
