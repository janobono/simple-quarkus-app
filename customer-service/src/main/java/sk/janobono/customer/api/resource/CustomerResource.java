package sk.janobono.customer.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.customer.api.CustomersApi;
import sk.janobono.customer.api.mapper.CustomerApiMapper;
import sk.janobono.customer.api.model.CustomerContent;
import sk.janobono.customer.business.model.CustomerData;
import sk.janobono.customer.business.service.CustomerService;

import java.util.List;

@Slf4j
public class CustomerResource implements CustomersApi {

    private final CustomerService customerService;
    private final CustomerApiMapper customerApiMapper;

    @Inject
    public CustomerResource(final CustomerService customerService, final CustomerApiMapper customerApiMapper) {
        this.customerService = customerService;
        this.customerApiMapper = customerApiMapper;
    }

    @Override
    public Response addCustomer(final CustomerContent customerContent) {
        log.debug("addCustomer({})", customerContent);
        final CustomerData customer = customerService.addCustomer(customerApiMapper.mapToData(customerContent));
        return Response.status(Response.Status.CREATED).entity(customerApiMapper.mapToApi(customer)).build();
    }

    @Override
    public Response deleteCustomer(final Long id) {
        log.debug("deleteCustomer({})", id);
        customerService.deleteCustomer(id);
        return Response.ok().build();
    }

    @Override
    public Response getCustomer(final Long id) {
        log.debug("getCustomer({})", id);
        final CustomerData customer = customerService.getCustomer(id);
        return Response.ok(customerApiMapper.mapToApi(customer)).build();
    }

    @Override
    public Response getCustomers() {
        log.debug("getCustomers()");
        final List<CustomerData> customers = customerService.getCustomers();
        return Response.ok(
                customers.stream()
                        .map(customerApiMapper::mapToApi)
                        .toList()
        ).build();
    }

    @Override
    public Response setCustomer(final Long id, final CustomerContent customerContent) {
        log.debug("setCustomer({},{})", id, customerContent);
        final CustomerData customer = customerService.setCustomer(id, customerApiMapper.mapToData(customerContent));
        return Response.ok(customerApiMapper.mapToApi(customer)).build();
    }
}
