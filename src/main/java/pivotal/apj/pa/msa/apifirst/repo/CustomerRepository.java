package pivotal.apj.pa.msa.apifirst.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pivotal.apj.pa.msa.apifirst.domain.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
}
