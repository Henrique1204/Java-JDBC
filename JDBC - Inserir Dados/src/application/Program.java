package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

import db.DB;

public class Program
{
	public static void main(String[] args)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try
		{
			conn = DB.getConnection();

			/*
			//(?) define um local que será substituido futuramente
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate,BaseSalary, DepartmentId) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			//Para salvar um data no banco a data precisa ser da classe sql
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);
			*/

			st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0)
			{
				ResultSet rs = st.getGeneratedKeys();

				while (rs.next())
				{
					int id = rs.getInt(1);
					System.out.println("Pronto! Id = " + id);
				}

				DB.fecharResultSet(rs);
			}
			else
			{
				System.out.println("Nenhuma linha afetada");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.fecharStatement(st);
			DB.fecharConnection();
		}
	}
}