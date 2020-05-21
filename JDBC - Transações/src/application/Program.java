 package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DBException;

public class Program
{
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement st = null;

		try
		{
			conn = DB.getConnection();

			//Define as operações como não concluidas
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int linhas1 = st.executeUpdate("UPDATE seller SET BaseSalary = 1090 WHERE DepartmentId = 1");

			/*
			int x = 1;
			if ( x < 2)
			{
				throw new SQLException("Erro falso");
			}
			*/

			int linhas2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			//Define as operações como concluidas
			conn.commit();

			System.out.println("Linha 1 = " + linhas1);
			System.out.println("Linha 2 = " + linhas2);
		}
		catch (SQLException e)
		{
			try
			{
				//Cancela e faz as ações voltarem para o estado inicial
				conn.rollback();
				throw new DBException("Tansacao cancelada! [ERRO] " + e.getMessage());
			}
			catch (SQLException f)
			{
				throw new DBException("Erro ao tentar voltar a transacao! [ERRO] " + f.getMessage());
			}
		}
		finally
		{
			DB.fecharStatement(st);
			DB.fecharConnection();
		}
	}
}