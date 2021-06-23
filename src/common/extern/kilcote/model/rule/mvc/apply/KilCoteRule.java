package common.extern.kilcote.model.rule.mvc.apply;

import java.lang.reflect.Method;
import java.util.Calendar;

import common.extern.kilcote.model.dataaccess.mvc.apply.BaseDA;
import common.extern.kilcote.model.dataaccess.mvc.apply.KilcoteDA;
import common.extern.kilcote.model.dataaccess.mvc.base.IItem_Model;
import common.extern.kilcote.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;
import common.extern.kilcote.model.facade.mvc.apply.KilcoteBusiness;
import common.extern.kilcote.model.model.page.info.pageModel;
import common.extern.kilcote.model.rule.mvc.apply.inter.IMapping_Model;
import common.extern.kilcote.model.rule.mvc.apply.inter.IKilCoteRule;
import common.extern.utils.ReflectionUtil;

/**
 */
public class KilCoteRule extends BaseRule implements IKilCoteRule{

	public KilCoteRule(KilcoteBusiness friendBusiness, KilcoteDA friendDA, IItem_Model item, pageModel pgModel) {
		super(null, null, item);
		m_pageModel = pgModel;
		m_friendBusiness = friendBusiness;
		m_friendDA = friendDA; 
	}
	/**  PageModel**/
	@SuppressWarnings("unused")
	private pageModel m_pageModel = null;
	private KilcoteBusiness m_friendBusiness = null;
	private KilcoteDA m_friendDA = null;
	@Override
	protected IMapping_Model getMappingClient() throws Throwable { return m_friendBusiness; }
	@Override
	protected BaseDA getDAClient() throws Throwable { return m_friendDA; }
	public pageModel getPageModel() { return m_pageModel; }
	public void setPageModel(pageModel model) { m_pageModel = model; }
	
	public Object getPageInfo(pageExpressionRelation pageInfo) {
		if(pageInfo==null) { return null; }
		pageModel pageSchemaInfo = getPageModel();
		if(pageSchemaInfo==null) return null;
		long nLimit = pageInfo.getLimit();
		long nPageSize = pageInfo.getPageSize();
		long nPageCount = pageInfo.getPageCount();
		long nPageIndex = pageInfo.getPageIndex();
		Object result = null;
		try {
			result = pageSchemaInfo.getPageClass().newInstance();
			//limit
			ReflectionUtil.invokePutLongMethod(pageSchemaInfo.getLimit(), result, nLimit);
			//size
			ReflectionUtil.invokePutLongMethod(pageSchemaInfo.getPageSize(), result, nPageSize);
			//count
			ReflectionUtil.invokePutLongMethod(pageSchemaInfo.getPageCount(), result, nPageCount);
			//index
			ReflectionUtil.invokePutLongMethod(pageSchemaInfo.getPageIndex(), result, nPageIndex);
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return result;
	}
	public pageExpressionRelation getPageInfo(Object pageInfo) {
		if(pageInfo==null) { return null; }
		pageModel pageSchemaInfo = getPageModel();
		if(pageSchemaInfo==null) return null;
		
		pageExpressionRelation result = new pageExpressionRelation();
		//limit
		long nLimit = ReflectionUtil.invokeGetLongMethod(pageSchemaInfo.getLimit(), pageInfo);
		result.setLimit(nLimit);
		//limit
		long nPageSize = ReflectionUtil.invokeGetLongMethod(pageSchemaInfo.getPageSize(), pageInfo);
		result.setPageSize(nPageSize);
		//limit
		long nPageCount = ReflectionUtil.invokeGetLongMethod(pageSchemaInfo.getPageCount(), pageInfo);
		result.setPageCount(nPageCount);
		//limit
		long nPageIndex = ReflectionUtil.invokeGetLongMethod(pageSchemaInfo.getPageIndex(), pageInfo);
		result.setPageIndex(nPageIndex);
		return result;
	}
	public boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable{
		return super.IsValidField(item, nFieldIndex, nMaxLen);
	}
    public boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen, int nMinLen) throws Throwable{
		return super.IsValidField(item, nFieldIndex, nMaxLen, nMinLen);
	}
	public boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, boolean bInitHMS) throws Throwable{
		return super.IsValidField(item, nFieldIndex, dtMaxDate, bInitHMS);
	}
	public boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, Calendar dtMinDate, boolean bInitHMS) throws Throwable {
		return super.IsValidField(item, nFieldIndex, dtMaxDate, dtMinDate, bInitHMS);
	}
	public boolean IsValidEmail(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable {
		return super.IsValidEmail(item, nFieldIndex, nMaxLen);
	}
	public boolean CheckValidate(IItem_Model item) throws Throwable {
		try {
			Method method = m_friendBusiness.getClass().getMethod("onCheckValidate", IItem_Model.class);
			if(method!=null) {
				return (Boolean)method.invoke(m_friendBusiness, item);
			}
		}
		catch(NoSuchMethodException ex){ }
		return true;
	}
}
