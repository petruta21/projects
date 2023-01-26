package com.example.product.productlist.web;

import com.example.product.productlist.service.RegistrationService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/list")
    public Iterable<RegistrationDTO> list(Pageable pageable) {
        return registrationService.listPaginated(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        registrationService.deleteById(id);
    }

    @PostMapping("/user")
    private Long create(@Valid @RequestBody RegistrationDTO registration) {
        return registrationService.save(registration).getId();
    }

    @PutMapping("/{id}")
    private RegistrationDTO update(@Valid @RequestBody RegistrationDTO registration, @PathVariable("id") Long id) {
        return registrationService.update(registration, id);
    }
}
