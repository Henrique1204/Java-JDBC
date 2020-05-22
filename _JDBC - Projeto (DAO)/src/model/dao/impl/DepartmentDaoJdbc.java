package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.EntidadeDao;
import model.entidades.Department;
import model.entidades.Seller;

public class DepartmentDaoJdbc implements EntidadeDao<Department>
{
	private Connection conn;

	//Construtor
	public DepartmentDaoJdbc(Connection conn)
	{
		this.conn = conn;
	}

	//Métodos sobrescrevidos
	@Override
	public void inserir(Department obj)
	{
		PreparedStatement st = null;

		try
		{
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0)
			{
				ResultSet rs = st.getGeneratedKeys();

				if (rs.next())
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				else
				{
					throw new DBException("Erro inesperado, nenhuma linha foi afetada");
				}

				DB.fecharResultSet(rs);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e.getMessage());
		}
		finally
		{
			DB.fecharStatement(st);
		}
	}

	@Override
	public void atualizar(Department obj)
	{
		PreparedStatement st = null;

		try
		{
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.execute();
		}
		catch (SQLException e)
		{
			throw new DBException(e.getMessage());
		}
		finally
		{
			DB.fecharStatement(st);
		}
	}

	@Override
	public void deletarPorId(Integer id)
	{
		PreparedStatement st = null;

		try
		{
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DBException(e.getMessage());
		}
		finally
		{
			DB.fecharStatement(st);
		}
	}

	@Override
	public Department buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> buscarPorDepartment(Department dep) {
		// TODO Auto-generated method stub
		return null;
	}
}