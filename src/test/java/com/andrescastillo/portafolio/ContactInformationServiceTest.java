package com.andrescastillo.portafolio;

import com.andrescastillo.portafolio.dao.ContactInformationRepository;
import com.andrescastillo.portafolio.entity.ContactInformation;
import com.andrescastillo.portafolio.serviceImpl.ContactInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactInformationServiceTest {

    @Mock
    private ContactInformationRepository contactInformationRepository;

    @InjectMocks
    private ContactInformationServiceImpl contactInformationService;

    @Test
    public void testSave() {
        ContactInformation contactInfo = new ContactInformation();
        contactInfo.setName("Andres");
        contactInfo.setEmail("test@example.com");
        contactInfo.setMessage("Hello World");

        when(contactInformationRepository.save(any(ContactInformation.class))).thenReturn(contactInfo);

        ContactInformation savedContactInfo = contactInformationService.save(contactInfo);

        assertNotNull(savedContactInfo);
        assertEquals("Andres", savedContactInfo.getName());
        assertEquals("test@example.com", savedContactInfo.getEmail());
        assertEquals("Hello World", savedContactInfo.getMessage());
        verify(contactInformationRepository).save(any(ContactInformation.class));
    }
}
