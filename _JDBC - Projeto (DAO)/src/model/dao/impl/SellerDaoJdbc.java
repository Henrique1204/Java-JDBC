package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.EntidadeDao;
import model.entidades.Department;
import model.entidades.Seller;

public class SellerDaoJdbc implements EntidadeDao<Seller>
{
	private Connection conn;

	//Construtor
	public SellerDaoJdbc(Connection conn)
	{
		this.setConn(conn);
	}

	//Getter
	public Connection getConn()
	{
		return this.conn;
	}

	//Setter
	public void setConn(Connection conn)
	{
		this.conn = conn;
	}

	//M�todos sobrescrevidos
	@Override
	public void inserir(Seller obj)
	{
	
	}

	@Override
	public void atualizar(Seller obj)
	{

	}

	@Override
	public void deletarPorId(Integer id)
	{
	
	}

	@Override
	public Seller buscarPorId(Integer id)
	{
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if(rs.next())
			{
				Department dep = new Department( rs.getInt("DepartmentId"), rs.getString("DepName") );
				Seller obj = new Seller( rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), dep );

				return obj;
			}

			return null;
		}
		catch (SQLException e)
		{
			throw new DBException(e.getMessage());
		}
		finally
		{
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
	}

	@Override
	public List<Seller> buscarTodos()
	{
		return null;
	}
}