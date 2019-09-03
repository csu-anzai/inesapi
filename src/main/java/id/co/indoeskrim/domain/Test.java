package id.co.indoeskrim.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.domain
//	Class Name		: Test
//	File Name		: Test.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:40:41 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Entity
@Table(name = "test")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Test extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;    
    @Id
    @SequenceGenerator(name = "test_pk_seq", sequenceName = "test_pk_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_pk_seq")
    private Long id;

    @Size(max = 40)
    @Column(name = "val1", length = 40)
    private String val1;

    @Size(max = 150)
    @Column(name = "val2", length = 150)
    private String val2;

    @Size(max = 150)
    @Column(name = "val3", length = 150)
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
        Test test = (Test) o;
        if (test.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), test.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Test{" +
            "id=" + getId() +
            ", address='" + getVal1() + "'" +
            ", receiverName='" + getVal2() + "'" +
            ", receiverPhone='" + getVal3() + "'" +
            "}";
    }
}
