package common.extern.kilcote.model.rule.mvc.apply;

import common.extern.kilcote.model.dataaccess.daFatherModel;
import common.extern.kilcote.model.dataaccess.mvc.base.IItem_Model;
import common.extern.kilcote.model.rule.middleFatherModel;
import common.extern.kilcote.model.rule.mvc.base.IMiddle_Model;

/**
 */
public class BaseMapping extends mapping_Model implements IMiddle_Model{

	/**ParentmappingLayer*/
	private middleFatherModel m_middleParent = null;
	/**ParentdataaccessLayer*/
	private daFatherModel m_daParent = null;

	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA) throws Exception {
		super();
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	
	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA, IItem_Model itemModel) throws Exception {
		super(itemModel);
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	
	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA, IItem_Model itemModel, String strXmlRoot, String strXmlNode, String strNodeCount, String strXmlException) throws Exception {
		super(itemModel, strXmlRoot, strXmlNode, strNodeCount, strXmlException);
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	public daFatherModel getDAModel() { return m_daParent; }
	public middleFatherModel getMiddleModel() { return m_middleParent; }
}
