package common.extern.kilcote.model.rule.mvc.apply;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;

import org.w3c.dom.Node;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import common.extern.kilcote.model.dataaccess.daFatherModel;
import common.extern.kilcote.model.dataaccess.mvc.apply.BaseDA;
import common.extern.kilcote.model.dataaccess.mvc.base.IItem_Model;
import common.extern.kilcote.model.error.KilcoteException;
import common.extern.kilcote.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.kilcote.model.infos.universalResult.infoUniversalReturnCode;
import common.extern.kilcote.model.rule.middleFatherModel;
import common.extern.kilcote.model.rule.mvc.apply.inter.IBaseRule;
import common.extern.kilcote.model.rule.mvc.apply.inter.IMapping_Model;
import common.extern.kilcote.model.systemframework.Applicationlog;
import common.extern.utils.DateTime;

public abstract class BaseRule extends rule_Model implements IBaseRule {
	
	public BaseRule(middleFatherModel parentRule, daFatherModel parentDA, IItem_Model item) {
		super(parentRule, parentDA, item);
	}
    /**
	 */
	@SuppressWarnings("unused")
	private static String REGEXP_ISVALIDEMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@\\w+((\\.|-)\\w+)*\\.\\w+$";

	public static String m_strInvalidField  = "Invalid Field.";
	public static String m_strInvalidFields  = "Invalid Field.";

	public void setError(String strErrorMsg) {
		Applicationlog.LogKilCoteError(strErrorMsg);
	}
	/**
	 * @author kil cote 
	 * @version  2013-04-02 10:14:11
	 * @throws Throwable 
	 */
	protected abstract BaseDA getDAClient() throws Throwable;
	/**
	 */
	protected abstract IMapping_Model getMappingClient() throws Throwable;
    public long insertData(Object data, Object mappingOption) throws Throwable {
    	if (data == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromData(data, mappingOption);
    	return insertItem(item);
    }
	public long insertXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
	
    	if (strXml == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXml(strXml, dataProtocol, mappingOption);
    	return insertItem(item);
	}
	public long insertXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	if (nodeXml == null) {
    		return 0;
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXmlNode(nodeXml, dataProtocol, mappingOption);
    	return insertItem(item);
	}
	public long insertJSon(String strJSon, Object mappingOption) throws Throwable {
    	if (strJSon == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromJSon(strJSon, mappingOption);
    	return insertItem(item);
	}
	public long insertHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
    	if (hashInfo == null) {
    		return 0;
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromHashMap(hashInfo, mappingOption);
		if (item == null) {
			throw new KilcoteException("Insert data is null. - at BusinessRuleLayer  insertData()...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
		}
		return insertItem(item);
	}
	public long insertItem(IItem_Model item) throws Throwable {
		if (item == null) {
			throw new KilcoteException("Insert data is null. - at BusinessRuleLayer  insertData() ...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
		}
		boolean bIsValid  = CheckValidate(item);
		if (bIsValid) {
			return onInsertItem(item);
		}
		else {
			throw new KilcoteException("Can't put data to database. - at BusinessRuleLayer  CheckValidateItem()...", infoUniversalReturnCode.RESULT_ERROR_INVALID_TABLEITEM);
		}
	}
    public boolean updateData(Object data, Object mappingOption) throws Throwable {
    	if (data == null) {
    		throw new KilcoteException("Update data is null. - at BusinessRuleLayer  insertData()...", infoUniversalReturnCode.RESULT_ERROR_NULL);
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromData(data, mappingOption);
    	return updateItem(item);
    }
    public boolean updateXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXml(strXml, dataProtocol, mappingOption);
    	return updateItem(item);
    }
    public boolean updateXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXmlNode(nodeXml, dataProtocol, mappingOption);
    	return updateItem(item);
    }
    public boolean updateJSon(String strJSon, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromJSon(strJSon, mappingOption);
    	return updateItem(item);
    }
    public boolean updateHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromHashMap(hashInfo, mappingOption);
    	return updateItem(item);
	}
    public boolean updateItem(IItem_Model item) throws Throwable {
    	if (item == null) {
    		throw new KilcoteException("Update data is null. - at BusinessRuleLayer  updateData()...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
    	}
    	boolean bIsValid  = CheckValidate(item);
    	if (bIsValid) {
    		return onUpdateItem(item);
    	}
        return false;
	}
    public boolean deleteData(long nDataNumber) throws Throwable {
    	if (nDataNumber <= 0) {
    		throw new KilcoteException("Primary key to delete must be over 1. - at BusinessRuleLayer  deleteData()...", infoUniversalReturnCode.RESULT_ERROR_INVALID_PRIMARYID);
    	}
		boolean result = onDeleteItem(nDataNumber);
        return result;
    }
    public boolean CheckValidate(IItem_Model item) throws Throwable {
        boolean isValid = onCheckValidate(item);
        if (!isValid) {
    		throw new KilcoteException("Invalid field. - at BusinessRuleLayer  CheckValidate()...", infoUniversalReturnCode.RESULT_ERROR_INVALID_DATA);
        }
        return isValid;
    }
    public boolean onCheckValidate(IItem_Model item) throws Throwable {
        return true;
    }
	public long onInsertItem(IItem_Model item) throws Throwable {
		return (Long)getDAClient().set_Insert(item);
	}
	public boolean onUpdateItem(IItem_Model item) throws Throwable {
		return (getDAClient().set_Update(item) == 1) ? true : false;
	}
	public boolean onDeleteItem(long nNo) throws Throwable {
		return (getDAClient().set_Delete(nNo) == 1) ? true : false;
	}
    /**
	 */
    protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable{
		return IsValidField(item, nFieldIndex, nMaxLen, 0);
	}
    /** 
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen, int nMinLen) throws Throwable{
		if(item.isAvaiable(nFieldIndex)) {
			if(item.getFieldType(nFieldIndex)==Types.VARCHAR || item.getFieldType(nFieldIndex)==Types.LONGVARCHAR ) {
				int nLen = ((String)item.getAvaiable(nFieldIndex)).length();
				if ((nLen < nMinLen) || (nLen > nMaxLen)) {
					Applicationlog.LogKilCote(item.getFieldLabel(nFieldIndex) + "Invalid field length. - at BusinessRuleLayer  CheckValidate()...");
					return false;
				}
			}
			else if(item.getFieldType(nFieldIndex)==Types.BIGINT || item.getFieldType(nFieldIndex)==Types.INTEGER || 
					item.getFieldType(nFieldIndex)==Types.SMALLINT || item.getFieldType(nFieldIndex)==Types.TINYINT) {
				int nLen = String.valueOf(((Long)item.getAvaiable(nFieldIndex))).length();
				if ((nLen < nMinLen) || (nLen > nMaxLen)) {
					Applicationlog.LogKilCote(item.getFieldLabel(nFieldIndex) + "Invalid field length. - at BusinessRuleLayer  CheckValidate()...");
					return false;
				}
			}
		}
		return true;
	}
	/** 
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, boolean bInitHMS) throws Throwable{
		return IsValidField(item, nFieldIndex, dtMaxDate, null, bInitHMS);
	}
	/** 
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, Calendar dtMinDate, boolean bInitHMS) throws Throwable {
		boolean result = true;
		if(item.isAvaiable(nFieldIndex)) {
			switch(item.getFieldType(nFieldIndex)){
				case Types.TIME:{
					Time objResult = (Time)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							if(DateTime.getTimeFromCalendar(dtMaxDate).after(objResult)){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							if(DateTime.getTimeFromCalendar(dtMinDate).before(objResult)){
								result = false;
								break;
							}
						}
					}
					break;
				}
				case Types.DATE:{
					Date objResult = (Date)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							Calendar dtMaxDateInitHMS = (Calendar)dtMaxDate.clone();
							if(bInitHMS) {
								dtMaxDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMaxDateInitHMS.set(Calendar.MINUTE, 0);
								dtMaxDateInitHMS.set(Calendar.SECOND, 0);
								dtMaxDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.after(DateTime.getDateFromCalendar(dtMaxDateInitHMS))){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							Calendar dtMinDateInitHMS = (Calendar)dtMinDate.clone();
							if(bInitHMS) {
								dtMinDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMinDateInitHMS.set(Calendar.MINUTE, 0);
								dtMinDateInitHMS.set(Calendar.SECOND, 0);
								dtMinDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.before(DateTime.getDateFromCalendar(dtMinDateInitHMS))){
								result = false;
								break;
							}
						}
					}
					break;
				}
				case Types.TIMESTAMP:{
					Timestamp objResult = (Timestamp)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							Calendar dtMaxDateInitHMS = (Calendar)dtMaxDate.clone();
							if(bInitHMS) {
								dtMaxDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMaxDateInitHMS.set(Calendar.MINUTE, 0);
								dtMaxDateInitHMS.set(Calendar.SECOND, 0);
								dtMaxDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.after(DateTime.getTimeStampFromCalendar(dtMaxDateInitHMS))){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							Calendar dtMinDateInitHMS = (Calendar)dtMinDate.clone();
							if(bInitHMS) {
								dtMinDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMinDateInitHMS.set(Calendar.MINUTE, 0);
								dtMinDateInitHMS.set(Calendar.SECOND, 0);
								dtMinDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.before(DateTime.getTimeStampFromCalendar(dtMinDateInitHMS))){
								result = false;
								break;
							}
						}
					}
					break;
				}
				default: {
					return false;
				}
			}
		}
		if(!result) {
			Applicationlog.LogKilCote(item.getFieldLabel(nFieldIndex) + "field time invalid. - at BusinessRuleLayer  CheckValidate()...");
		}
		return result;
	}
	/**
	 * Function IsValidEmail:
	 * Returns:
	 * Parameters:
	 */
	protected boolean IsValidEmail(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable {
		boolean isValid = true;
		if(item.isAvaiable(nFieldIndex)) {
			if(item.getFieldType(nFieldIndex)==Types.VARCHAR || item.getFieldType(nFieldIndex)==Types.LONGVARCHAR ) {
				String strEMail = (String)item.getAvaiable(nFieldIndex);
				if (strEMail.length() <= 0) {
					Applicationlog.LogKilCote("Empty email. - at BusinessRuleLayer  IsValidEmail()...");
					return false;
				}
				isValid = IsValidField(item, nFieldIndex, nMaxLen);
				if (isValid) {
					isValid = (new RegularExpression(REGEXP_ISVALIDEMAIL)).matches(strEMail);
					if (!isValid) {
						Applicationlog.LogKilCote("Incorrect email foramt. - at BusinessRuleLayer  IsValidEmail()...");
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
}
