 package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegridadeException;

public class Program
{
	public static void main(String[] args)
	{
		Connection conn = null;
		PreparedStatement st = null;

		try
		{
			conn = DB.getConnection();

			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			//st.setInt(1,5); Deletando um dado que não é referenciado em outra tabela
			st.setInt(1, 2); //Deletando um dado que é referenciado em outro tabela (gera um erro de integridade)

			int linhasAfetadas = st.executeUpdate();
			System.out.println("Pronto! " + linhasAfetadas);
		}
		catch (SQLException e)
		{
			throw new DBIntegridadeException(e.getMessage()); 
		}
		finally
		{
			DB.fecharStatement(st);
			DB.fecharConnection();
		}
	}
}