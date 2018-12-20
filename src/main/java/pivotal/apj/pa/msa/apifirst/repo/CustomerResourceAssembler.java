package pivotal.apj.pa.msa.apifirst.repo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import pivotal.apj.pa.msa.apifirst.domain.Customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CustomerResourceAssembler implements ResourceAssembler<Customer, Resource<Customer>> {
    @Override
    public Resource<Customer> toResource(Customer customer) {

        return new Resource<>(customer,
                linkTo(methodOn(CustomerController.class).findOne(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).customers()).withRel("customers"));
    }
}
