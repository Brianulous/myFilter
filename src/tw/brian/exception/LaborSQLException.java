package tw.brian.exception;

import java.sql.SQLException;

public class LaborSQLException extends SQLException{
	  private static final long serialVersionUID = 2135244094396331484L;
	  
	  public LaborSQLException() {}
	  
	  public LaborSQLException(String msg) {
		  super(msg);
	  }
	

}
