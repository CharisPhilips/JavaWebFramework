package common.extern.kilcote.model.dataaccess.util.core.connections.poolconnect;

import java.sql.Connection;

import common.extern.kilcote.model.dataaccess.util.core.connections.ConnectSourceModel;

public class poolConnect extends ConnectSourceModel {

	public poolConnect() {
		super();
	}

	/*******abstract override methods*********/
	@Override
	public void InitVariables() {
	}

	@Override
	public Connection getConnection() {
		return null;
	}

	@Override
	public void closeConnection(Connection objConn) throws Throwable {
		
	}
}
