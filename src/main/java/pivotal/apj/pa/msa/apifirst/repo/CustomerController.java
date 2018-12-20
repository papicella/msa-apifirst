package pivotal.apj.pa.msa.apifirst.repo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pivotal.apj.pa.msa.apifirst.domain.Customer;
import pivotal.apj.pa.msa.apifirst.exceptions.CustomerNotFoundException;

import java.util.List;

@RestController
@RequestMapping ("/v1/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Return all customers", notes = "Retrieving a list of Customers", response = Customer[].class)
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find a Single Customer", notes = "Retrieving a single customer by ID", response = Customer.class)
    Customer findOne(@PathVariable Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a Single Customer", notes = "Delete a single customer by ID")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    @PostMapping()
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

}