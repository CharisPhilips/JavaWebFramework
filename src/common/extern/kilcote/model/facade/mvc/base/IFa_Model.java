package common.extern.kilcote.model.facade.mvc.base;

import common.extern.kilcote.model.dataaccess.daFatherModel;
import common.extern.kilcote.model.facade.facadeFatherModel;
import common.extern.kilcote.model.rule.middleFatherModel;

public interface IFa_Model {
	/**
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public facadeFatherModel getFacadeModel();
	/**
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel();
	/**
	 * @author kil cote
	 * @version ModelWeb 1.0, 2012. 01. 02 PM 1:45:05
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel();
}
