package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Department dep = instanciarDepartment(rs);
				Seller obj = instanciarSeller(rs, dep);

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

	@Override
	public List<Seller> buscarPorDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ORDER BY Name");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> lista = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next())
			{
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null)
				{
					dep = instanciarDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller obj = instanciarSeller(rs, dep);
				lista.add(obj);
			}

			return lista;
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

	//M�todos implementados
	private Department instanciarDepartment(ResultSet rs) throws SQLException
	{
		Department department = new Department( rs.getInt("DepartmentId"), rs.getString("DepName") );
		return department;
	}

	private Seller instanciarSeller(ResultSet rs, Department department) throws SQLException
	{
		Seller seller = new Seller( rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), department );
		return seller;
	}
}