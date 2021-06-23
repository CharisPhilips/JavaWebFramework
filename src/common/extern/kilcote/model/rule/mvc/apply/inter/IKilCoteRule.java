package common.extern.kilcote.model.rule.mvc.apply.inter;

import common.extern.kilcote.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;

/**
 */
public interface IKilCoteRule extends IBaseRule
{
	public Object getPageInfo(pageExpressionRelation pageInfo);	
	public pageExpressionRelation getPageInfo(Object pageInfo);
}
