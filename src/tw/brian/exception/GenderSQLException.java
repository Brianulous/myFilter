package tw.brian.exception;

import java.sql.SQLException;

public class GenderSQLException extends SQLException {
	private static final long serialVersionUID = 2135244094396331484L;

	public GenderSQLException() {
	}

	public GenderSQLException(String msg) {
		super(msg);
	}

}
