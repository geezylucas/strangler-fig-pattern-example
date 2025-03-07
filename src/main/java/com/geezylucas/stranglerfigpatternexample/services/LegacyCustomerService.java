package com.geezylucas.stranglerfigpatternexample.services;

import com.geezylucas.stranglerfigpatternexample.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LegacyCustomerService {

    private final List<Customer> sampleCustomers = Arrays.asList(
            new Customer("LEGACY_1", "Legacy Customer 1"),
            new Customer("LEGACY_2", "Legacy Customer 2"),
            new Customer("LEGACY_3", "Legacy Customer 3")
    );

    public Mono<Customer> getCustomerById(String id) {
        // Retrieve customer from sample list
        return Mono.just(sampleCustomers.stream()
                        .filter(customer -> customer.getId().equals(id))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")));
    }
}