package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import id.co.indoeskrim.domain.Customer;
import id.co.indoeskrim.domain.CustomerAddress;
import id.co.indoeskrim.service.dto.CustomerDTO;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {


    @Mapping(target = "customerAddresses", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(String id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerId(id);
        return customer;
    }
    
    default CustomerAddress convertCustomerAddressDTOtoCustomerAddress(CustomerDTO dto) {
//    	return dto.ma;
    	return null;
    	
    }
}
