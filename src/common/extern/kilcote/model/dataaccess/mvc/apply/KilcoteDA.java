package common.extern.kilcote.model.dataaccess.mvc.apply;

import common.extern.kilcote.model.dataaccess.mvc.base.IItem_Model;
import common.extern.kilcote.model.dataaccess.util.core.connections.dbConn_Model;
/**
 * Baic information definition class for database : KilCote
 * This is used with KilCoteBusiness and KilCoteRule
 * Maybe parent class is useless. 
 * @author kil cote 
 * @version  2013-03-26 08:14:12
 */
public class KilcoteDA extends BaseDA
{
	public KilcoteDA(dbConn_Model conn)
	{
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public KilcoteDA(dbConn_Model conn, IItem_Model itemM) 
	{
		m_ItemModel = itemM;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public KilcoteDA(dbConn_Model conn, IItem_Model itemM, int nKind) {
		m_ItemModel = itemM; m_ItemModel.setKind(nKind);
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
}
