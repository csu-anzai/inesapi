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

public class OrderIdGenerator implements IdentifierGenerator {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		
		Connection conn = session.connection();
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT get_order_id()");
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch(SQLException sqle) {
			log.error("###OrderIdGenerator.generate error: {} ", sqle.getMessage());
		}
		return null;
	}

}
