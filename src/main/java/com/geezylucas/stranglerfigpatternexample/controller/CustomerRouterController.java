package com.geezylucas.stranglerfigpatternexample.controller;

import com.geezylucas.stranglerfigpatternexample.model.Customer;
import com.geezylucas.stranglerfigpatternexample.service.LegacyCustomerService;
import com.geezylucas.stranglerfigpatternexample.service.ModernCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Ejemplo Práctico
 * <p>
 * Imagina que estás migrando un endpoint "/cliente" de un sistema heredado a una arquitectura de microservicios.
 * Puedes desplegar un router que dirija las peticiones a la implementación antigua o a la nueva basándose en
 * condiciones específicas.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerRouterController {

    private final LegacyCustomerService legacyService;
    private final ModernCustomerService modernService;

    @GetMapping("/customer")
    public Mono<Customer> getCustomer(@RequestParam String id) {
        if (isModernCustomer(id)) {
            return modernService.getCustomerById(id);
        }
        return legacyService.getCustomerById(id);
    }

    private boolean isModernCustomer(String id) {
        // Logic to determine if the request should be routed to the modern service
        return id.startsWith("MODERN_");
    }
}
