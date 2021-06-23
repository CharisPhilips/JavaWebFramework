package common.extern.kilcote.model.error.dataaccess;

import common.extern.kilcote.model.error.KilcoteException;


/**
 * KilCote Model's Error Object for Exception
 */
@SuppressWarnings("serial")
public class KilcoteDBAcessException extends KilcoteException{

	public KilcoteDBAcessException() {
		super();
	}
	public KilcoteDBAcessException(String message, Throwable cause, long nErrorCode) {
		super(message, cause, nErrorCode);
	}
	public KilcoteDBAcessException(String message, long nErrorCode) {
		super(message, nErrorCode);
	}
	public KilcoteDBAcessException(Throwable cause, long nErrorCode) {
		super(cause, nErrorCode);
	}
	public KilcoteDBAcessException(String message, Throwable cause) {
		super(message, cause);
	}
	public KilcoteDBAcessException(String message) {
		super(message);
	}
	public KilcoteDBAcessException(Throwable cause) {
		super(cause);
	}
	public KilcoteDBAcessException(String message, Throwable cause, long nErrorCode, String strQuery) {
		super(message, cause, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public KilcoteDBAcessException(String message, long nErrorCode, String strQuery) {
		super(message, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public KilcoteDBAcessException(Throwable cause, long nErrorCode, String strQuery) {
		super(cause, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public KilcoteDBAcessException(String message, Throwable cause, String strQuery) {
		super(message, cause);
		m_strSqlQuery = strQuery;
	}
	public KilcoteDBAcessException(String message, String strQuery) {
		super(message);
		m_strSqlQuery = strQuery;
	}
	public KilcoteDBAcessException(Throwable cause, String strQuery) {
		super(cause);
	}
	
	private String m_strSqlQuery;

	public String getSqlQuery() { 	return m_strSqlQuery; }
	public void setSqlQuery(String sqlQuery) { m_strSqlQuery = sqlQuery; }
	
}
