package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import id.co.indoeskrim.domain.MasterAddress;
import id.co.indoeskrim.service.dto.MasterAddressDTO;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.service.mapper
//	Class Name		: MasterAddressMapper
//	File Name		: MasterAddressMapper.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 7:04:04 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Mapper(componentModel = "spring", uses = {})
public interface MasterAddressMapper extends EntityMapper<MasterAddressDTO, MasterAddress> {

	MasterAddressDTO toDto(MasterAddress masterAddress);
	
	
	@Mapping(source= "masterZipcodes", ignore = true, target = "masterZipcodes")
	MasterAddress toEntity(MasterAddressDTO masterAddressDTO);
	
	default MasterAddress fromId(Long id) {
		if(id == null) {
			return null;
		}
		MasterAddress masterAddress = new MasterAddress();
		masterAddress.setId(id);
		return masterAddress;
	}
	
	/*
	 * 
	CashCollectMasterDTO toDto(CashCollectMaster cashCollectMaster);
	
	@Mapping(source = "cashCollectDetails", ignore = true, target = "cashCollectDetails")
	CashCollectMaster toEntity(CashCollectMasterDTO cashCollectMasterDTO);

	default List<CashCollectDetail> convertCashCollectDetailDtoToCashCollectDetail(List<CashCollectDetailDTO> dto) {
		return dto.stream().map(obj -> {
			CashCollectDetail ccd = new CashCollectDetail();
			ccd.setWarehouseCode(obj.getWarehouseCode());
			ccd.setWarehouseName(obj.getWarehouseName());
			ccd.setCustomerCode(obj.getCustomerCode());
			ccd.setCustomerName(obj.getCustomerName());
			ccd.setCashOnHand(obj.getCashOnHand());
			ccd.setCollectionAmount(obj.getCollectionAmount());
			return ccd;
		}).collect(Collectors.toList());
	}
 
 
	 * 
	 * 
	 */
	
}
