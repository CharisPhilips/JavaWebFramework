package common.extern.kilcote.model.rule.mvc.base;

import common.extern.kilcote.model.dataaccess.daFatherModel;
import common.extern.kilcote.model.rule.middleFatherModel;

public interface IMiddle_Model {
	/**
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel();
	/**
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:13
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel();
}
