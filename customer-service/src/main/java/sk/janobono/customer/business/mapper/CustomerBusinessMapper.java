package sk.janobono.customer.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.customer.business.model.CustomerData;
import sk.janobono.customer.dal.domain.CustomerDo;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerBusinessMapper {

    CustomerData mapToData(CustomerDo customer);
}
