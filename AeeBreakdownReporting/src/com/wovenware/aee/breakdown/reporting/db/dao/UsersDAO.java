package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.UsersTO;

/**
 * <i>Users Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class UsersDAO
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( UsersDAO.class.getName() );

	// Constructors...
	public UsersDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public UsersTO find( String pkUserId )
		throws Exception
	{
		UsersTO userTO = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT `u`.`PK_USER_ID`, `u`.`PASSWORD`, `u`.`NAME`, `u`.`PHONE`, `u`.`SMS_IND` " +
				"FROM `aeebk`.`users` u " +
				"where u.PK_USER_ID = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, pkUserId);
			_log.finest( "PK_USER_ID[" + pkUserId + "]" );
					
			rs = pStmt.executeQuery();
			
			if ( rs != null && rs.next() )
			{
				userTO = new UsersTO();
				
				userTO.setPkUserId(	rs.getString(1));
				userTO.setPassword(	rs.getString(2));
				userTO.setName(		rs.getString(3));
				userTO.setPhone(	rs.getString(4));
				userTO.setSmsInd(	rs.getString(5));
			}
		}
		finally
		{
			if ( rs != null )
			{
				rs.close();
			}
			
			if ( pStmt != null && ! pStmt.isClosed() )
			{
				pStmt.close();
			}
		}
		
		return userTO;
	}
	
	public void create( UsersTO userTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"INSERT INTO `aeebk`.`users` (`PK_USER_ID`,`PASSWORD`,`NAME`,`PHONE`,`SMS_IND`) " +
				"VALUES (?,?,?,?,?)";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, userTO.getPkUserId());
			_log.finest( "PK_USER_ID[" + userTO.getPkUserId() + "]" );
			pStmt.setString(i++, userTO.getPassword());
			_log.finest( "PASSWORD[" + userTO.getPassword() + "]" );
			pStmt.setString(i++, userTO.getName());
			_log.finest( "NAME[" + userTO.getName() + "]" );
			pStmt.setString(i++, userTO.getPhone());
			_log.finest( "PHONE[" + userTO.getPhone() + "]" );
			pStmt.setString(i++, userTO.getSmsInd());
			_log.finest( "SMS_IND[" + userTO.getSmsInd() + "]" );
			
//			int cnt = 
					pStmt.executeUpdate();
		}
		finally
		{
			if ( pStmt != null && ! pStmt.isClosed() )
			{
				pStmt.close();
			}
		}
	}
	
	public void update( UsersTO userTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"UPDATE `aeebk`.`users` SET `PASSWORD` = ?, `NAME` = ?, `PHONE` = ?, `SMS_IND` = ? " +
				"WHERE `PK_USER_ID` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Set
			pStmt.setString(i++, userTO.getPassword());
			_log.finest( "PASSWORD[" + userTO.getPassword() + "]" );
			pStmt.setString(i++, userTO.getName());
			_log.finest( "NAME[" + userTO.getName() + "]" );
			pStmt.setString(i++, userTO.getPhone());
			_log.finest( "PHONE[" + userTO.getPhone() + "]" );
			pStmt.setString(i++, userTO.getSmsInd());
			_log.finest( "SMS_IND[" + userTO.getSmsInd() + "]" );
			
			// Where
			pStmt.setString(i++, userTO.getPkUserId());
			_log.finest( "PK_USER_ID[" + userTO.getPkUserId() + "]" );
			
//			int cnt = 
					pStmt.executeUpdate();
		}
		finally
		{
			if ( pStmt != null && ! pStmt.isClosed() )
			{
				pStmt.close();
			}
		}
	}
	
	public void delete( UsersTO userTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"DELETE FROM `aeebk`.`users` " +
				"WHERE `PK_USER_ID` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Where
			pStmt.setString(i++, userTO.getPkUserId());
			_log.finest( "PK_USER_ID[" + userTO.getPkUserId() + "]" );
			
//			int cnt = 
					pStmt.executeUpdate();
		}
		finally
		{
			if ( pStmt != null && ! pStmt.isClosed() )
			{
				pStmt.close();
			}
		}
	}
}
