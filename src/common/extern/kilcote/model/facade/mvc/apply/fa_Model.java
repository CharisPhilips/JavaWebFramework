package common.extern.kilcote.model.facade.mvc.apply;

import common.extern.kilcote.model.dataaccess.daFatherModel;
import common.extern.kilcote.model.facade.facadeFatherModel;
import common.extern.kilcote.model.facade.mvc.base.IFa_Model;
import common.extern.kilcote.model.rule.middleFatherModel;

/**
 * facadeMainModel
 * @author kil cote
 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:29
 * @since ModelWeb 1.0
 */
public class fa_Model implements IFa_Model
{
	protected fa_Model(facadeFatherModel parentFacade, middleFatherModel parentMiddle, daFatherModel parentDA){
		m_facadeParent = parentFacade;
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	/**ParentfacadeLayer*/
	private facadeFatherModel m_facadeParent = null;
	/**ParentRuleLayer*/
	private middleFatherModel m_middleParent = null;
	/**ParentdataaccessLayer*/
	private daFatherModel m_daParent = null;
	
	/**
	 * Get ParentFacade
	 * 
	 * @return
	 * 
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:31
	 * @since ModelWeb 1.0
	 */
	public facadeFatherModel getFacadeModel() { return m_facadeParent; }
	/**
	 * Get ParentRule Object
	 * 
	 * @return
	 * 
	 * @author kil cote
	 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel() { return m_middleParent; }
	/**
	 * Get Parent DA
	 * 
	 * @return
	 * 
	 * @author kil cote
	 * @version ModelWeb 1.0, 2012. 01. 02 PM 1:45:05
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel() { return m_daParent; }
}