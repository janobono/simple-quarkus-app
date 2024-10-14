package sk.janobono.customer;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.janobono.customer.api.model.Customer;
import sk.janobono.customer.api.model.CustomerContent;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CustomerResourceTest {

    @Test
    void fullRepositoryTest() {
        Customer customer = createCustomer();
        final Customer customer2 = getCustomer(customer.getId());

        Assertions.assertEquals(customer.getId(), customer2.getId());
        Assertions.assertEquals(customer.getFirstName(), customer2.getFirstName());
        Assertions.assertEquals(customer.getLastName(), customer2.getLastName());

        customer = updateCustomer(customer.getId());

        List<Customer> customers = getCustomers();

        Assertions.assertEquals(1, customers.size());
        Assertions.assertEquals(customer.getId(), customers.getFirst().getId());
        Assertions.assertEquals(customer.getFirstName(), customers.getFirst().getFirstName());
        Assertions.assertEquals(customer.getLastName(), customers.getFirst().getLastName());

        deleteCustomer(customer.getId());

        customers = getCustomers();
        Assertions.assertEquals(0, customers.size());
    }

    private Customer createCustomer() {
        final CustomerContent customerContent = new CustomerContent();
        customerContent.setFirstName("John");
        customerContent.setLastName("Doe");

        final Response response = given()
                .contentType("application/json")
                .body(customerContent)
                .post("/customers")
                .then()
                .statusCode(201)
                .extract().response();

        final Customer customer = response.getBody().as(Customer.class);

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(customer.getId());
        Assertions.assertEquals(customerContent.getFirstName(), customer.getFirstName());
        Assertions.assertEquals(customerContent.getLastName(), customer.getLastName());

        return customer;
    }

    private Customer getCustomer(final long id) {
        final Response response = given()
                .when().get("/customers/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Customer customer = response.getBody().as(Customer.class);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(id, customer.getId());

        return customer;
    }

    private Customer updateCustomer(final Long id) {
        final CustomerContent customerContent = new CustomerContent();
        customerContent.setFirstName("Jim");
        customerContent.setLastName("Pocket");

        final Response response = given()
                .contentType("application/json")
                .body(customerContent)
                .put("/customers/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Customer customer = response.getBody().as(Customer.class);

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(customer.getId());
        Assertions.assertEquals(customerContent.getFirstName(), customer.getFirstName());
        Assertions.assertEquals(customerContent.getLastName(), customer.getLastName());

        return customer;
    }

    private List<Customer> getCustomers() {
        final Response response = given()
                .when().get("/customers/")
                .then()
                .statusCode(200)
                .extract().response();

        final Customer[] customers = response.getBody().as(Customer[].class);

        Assertions.assertNotNull(customers);

        return Arrays.asList(customers);
    }

    private void deleteCustomer(final Long id) {
        given()
                .when().delete("/customers/" + id)
                .then().statusCode(200);
    }
}
