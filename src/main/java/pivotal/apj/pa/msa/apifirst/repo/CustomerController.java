package pivotal.apj.pa.msa.apifirst.repo;

import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pivotal.apj.pa.msa.apifirst.domain.Customer;
import pivotal.apj.pa.msa.apifirst.exceptions.CustomerNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping ("/v1/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private CustomerResourceAssembler customerResourceAssembler;

    public CustomerController(CustomerRepository customerRepository, CustomerResourceAssembler customerResourceAssembler) {
        this.customerRepository = customerRepository;
        this.customerResourceAssembler = customerResourceAssembler;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Return all customers", notes = "Retrieving a list of Customers")
    public Resources<Resource<Customer>> customers() {

        List<Resource<Customer>> employees = customerRepository.findAll().stream()
                .map(customerResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(employees,
                linkTo(methodOn(CustomerController.class).customers()).withSelfRel());

    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find a Single Customer", notes = "Retrieving a single customer by ID")
    Resource<Customer> findOne(@PathVariable Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerResourceAssembler.toResource(customer);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a Single Customer", notes = "Delete a single customer by ID")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) throws URISyntaxException {
        Resource<Customer> resource = customerResourceAssembler.toResource(customerRepository.save(newCustomer));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

}