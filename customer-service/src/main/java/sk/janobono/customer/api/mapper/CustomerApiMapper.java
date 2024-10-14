package sk.janobono.customer.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.customer.api.model.Customer;
import sk.janobono.customer.api.model.CustomerContent;
import sk.janobono.customer.business.model.CustomerContentData;
import sk.janobono.customer.business.model.CustomerData;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerApiMapper {

    Customer mapToApi(CustomerData customer);

    CustomerContentData mapToData(CustomerContent customerContent);
}
