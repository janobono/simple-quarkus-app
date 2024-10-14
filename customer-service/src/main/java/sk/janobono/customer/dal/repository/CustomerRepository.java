package sk.janobono.customer.dal.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.janobono.customer.dal.domain.CustomerDo;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerDo> {
}
