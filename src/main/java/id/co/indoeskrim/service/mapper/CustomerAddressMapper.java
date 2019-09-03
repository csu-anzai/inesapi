package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.CustomerAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerAddress and its DTO CustomerAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface CustomerAddressMapper extends EntityMapper<CustomerAddressDTO, CustomerAddress> {

    @Mapping(source = "customer", target = "customer")
    CustomerAddressDTO toDto(CustomerAddress customerAddress);

    @Mapping(source = "customer", target = "customer")
    CustomerAddress toEntity(CustomerAddressDTO customerAddressDTO);

    default CustomerAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(id);
        return customerAddress;
    }
}
