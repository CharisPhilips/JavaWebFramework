package common.extern.kilcote.model.error;

import common.extern.kilcote.model.infos.universalResult.infoUniversalReturnCode;

@SuppressWarnings("serial")
public class KilcoteException extends Exception{

	public KilcoteException() {
		super();
	}
	public KilcoteException(String message, Throwable cause, long nErrorCode) {
		super(message, cause);
		m_nErrorCode = nErrorCode;
	}
	public KilcoteException(String message, long nErrorCode) {
		super(message);
		m_nErrorCode = nErrorCode;
	}
	public KilcoteException(Throwable cause, long nErrorCode) {
		super(cause);
		m_nErrorCode = nErrorCode;
	}
	
	public KilcoteException(String message, Throwable cause) {
		super(message, cause);
	}
	public KilcoteException(String message) {
		super(message);
	}
	public KilcoteException(Throwable cause) {
		super(cause);
	}
	private long m_nErrorCode = infoUniversalReturnCode.RESULT_ERROR_UNKNOWN;

	public long getErrorCode() { return m_nErrorCode; }
	public void setErrorCode(long errorCode) { m_nErrorCode = errorCode; }
	
}
