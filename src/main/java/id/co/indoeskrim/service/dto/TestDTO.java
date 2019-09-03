package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.Objects;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.service.dto
//	Class Name		: TestDTO
//	File Name		: TestDTO.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:54:56 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public class TestDTO implements Serializable {
	
	private Long id;

    private String val1;

    private String val2;

    private String val3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}

	public String getVal3() {
		return val3;
	}

	public void setVal3(String val3) {
		this.val3 = val3;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TestDTO testDTO = (TestDTO) o;
		if (testDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), testDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "CustomerAddressDTO{" 
				+ "id=" + getId() 
				+ ", val1='" + getVal1() + "'" 
				+ ", val2='" + getVal2() + "'" 
				+ ", val3='" + getVal3() + "'" 
				+ "}";
	}
    
}

