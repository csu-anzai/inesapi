package id.co.indoeskrim.domain.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.domain.generator
//	Class Name		: CustomerIdGenerator
//	File Name		: CustomerIdGenerator.java
// 	Author			: yosakre
// 	Date Creation	: Aug 14, 2019, 5:53:01 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public class CustomerIdGenerator implements IdentifierGenerator {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		
		Connection conn = session.connection();
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT get_customer_id()");
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch(SQLException sqle) {
			log.error("###CustomerIdGenerator.generate error: {} ", sqle.getMessage());
		}
		return null;
	}

}
