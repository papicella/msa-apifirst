package pivotal.apj.pa.msa.apifirst;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pivotal.apj.pa.msa.apifirst.domain.Customer;
import pivotal.apj.pa.msa.apifirst.repo.CustomerRepository;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Customer("pas", "active")));
            log.info("Preloading " + repository.save(new Customer("lucia", "active")));
            log.info("Preloading " + repository.save(new Customer("lucas", "inactive")));
            log.info("Preloading " + repository.save(new Customer("siena", "inactive")));
            log.info("Preloading " + repository.save(new Customer("dbs", "active")));
        };
    }
}
