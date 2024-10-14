package sk.janobono.customer.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.customer.business.mapper.CustomerBusinessMapper;
import sk.janobono.customer.business.model.CustomerContentData;
import sk.janobono.customer.business.model.CustomerData;
import sk.janobono.customer.dal.domain.CustomerDo;
import sk.janobono.customer.dal.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessMapper customerBusinessMapper;

    @Inject
    public CustomerService(final CustomerRepository customerRepository, final CustomerBusinessMapper customerBusinessMapper) {
        this.customerRepository = customerRepository;
        this.customerBusinessMapper = customerBusinessMapper;
    }

    @Transactional
    public CustomerData addCustomer(final CustomerContentData customerContent) {
        log.debug("addCustomer({})", customerContent);

        final CustomerDo customerDo = CustomerDo.builder()
                .firstName(customerContent.firstName())
                .lastName(customerContent.lastName())
                .build();

        customerRepository.persist(customerDo);

        return customerBusinessMapper.mapToData(customerDo);
    }

    @Transactional
    public void deleteCustomer(final Long id) {
        log.debug("deleteCustomer({})", id);

        // Workaround - delete doesn't return correct flag
        if (customerRepository.findByIdOptional(id).isEmpty()) {
            throw new NotFoundException("Customer with id [%d] not found".formatted(id));
        }
        customerRepository.deleteById(id);

//        if (customerRepository.deleteById(id)) {
//            throw new NotFoundException("Customer with id [%d] not found".formatted(id));
//        }
    }

    public CustomerData getCustomer(final Long id) {
        log.debug("getCustomer({})", id);

        return customerRepository.findByIdOptional(id)
                .map(customerBusinessMapper::mapToData)
                .orElseThrow(() -> new NotFoundException("Customer with id [%d] not found".formatted(id)));
    }

    public List<CustomerData> getCustomers() {
        log.debug("getCustomers()");

        return customerRepository.findAll().stream()
                .map(customerBusinessMapper::mapToData)
                .toList();
    }

    @Transactional
    public CustomerData setCustomer(final Long id, final CustomerContentData customerContent) {
        log.debug("setCustomer({},{})", id, customerContent);

        final Optional<CustomerDo> customer = customerRepository.findByIdOptional(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer with id [%d] not found".formatted(id));
        }

        final CustomerDo customerDo = customer.get();
        customerDo.setFirstName(customerContent.firstName());
        customerDo.setLastName(customerContent.lastName());

        customerRepository.persist(customerDo);

        return customerBusinessMapper.mapToData(customerDo);
    }
}
