package com.example.product.productlist.service;

import com.example.product.productlist.persistence.Registration;
import com.example.product.productlist.persistence.RegistrationPagingRepository;
import com.example.product.productlist.web.RegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RegistrationService {

    private final RegistrationPagingRepository registrationRepository;

    public RegistrationService(RegistrationPagingRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<RegistrationDTO> list() {
        return StreamSupport.stream(registrationRepository.findAll().spliterator(), false).map(RegistrationService::convertToRegistrationDTO).collect(Collectors.toList());
    }

    public RegistrationDTO save(RegistrationDTO userDto) {
        return convertToRegistrationDTO(registrationRepository.save(convertToRegistration(userDto)));
    }

    public void deleteById(Long id) {
        registrationRepository.deleteById(id);
    }

    public RegistrationDTO saveOrUpdate(RegistrationDTO userDto) {
        return convertToRegistrationDTO(registrationRepository.save(convertToRegistration(userDto)));
    }


    public RegistrationDTO getCustomerById(Long id) {
        return registrationRepository.findById(id).map(RegistrationService::convertToRegistrationDTO).orElse(null);
    }

    public RegistrationDTO update(RegistrationDTO userDto, Long id) {
        Registration registration = new Registration();
        registration.setUserId(id);
        registration.setUserName(userDto.getName());
        registration.setUserMail(userDto.getEmail());
        registration.setUserPassword(userDto.getPassword());
        return convertToRegistrationDTO(registrationRepository.save(registration));
    }

    private Registration convertToRegistration(RegistrationDTO userDto) {
        Registration registration = new Registration();
        registration.setUserId(userDto.getId());
        registration.setUserName(userDto.getName());
        registration.setUserMail(userDto.getEmail());
        registration.setUserPassword(userDto.getPassword());
        return registration;
    }

    private static RegistrationDTO convertToRegistrationDTO(Registration c) {
        RegistrationDTO result = new RegistrationDTO();
        result.setId(c.getId());
        result.setName(c.getUserName());
        result.setEmail(c.getUserMail());
        result.setPassword(c.getUserPassword());
        return result;
    }

    public List<RegistrationDTO> listPaginated(Pageable pageable) {
        Page<Registration> pagedResult = registrationRepository.findAll(pageable);
        return pagedResult.toList().stream().map(RegistrationService::convertToRegistrationDTO).collect(Collectors.toList());
    }
}


