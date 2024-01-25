package com.andrescastillo.portafolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "contact_information")
@Data
public class ContactInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 1, max = 255, message = "The message must be between 10 and 255 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Message cannot be empty")
    @Size(min = 10, max = 500, message = "The message must be between 10 and 500 characters")
    @Column(name = "message")
    private String message;

}
