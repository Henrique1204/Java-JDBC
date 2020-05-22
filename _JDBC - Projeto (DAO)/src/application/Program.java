package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EntidadeDao;
import model.entidades.Department;
import model.entidades.Seller;

public class Program
{
	public static void main(String[] args)
	{
		EntidadeDao<Department> departmentDao = DaoFactory.criarDepartmentDao();

		System.out.println("=== Teste 1: inserir() -- Department ===");
		Department newDep = new Department(null, "TI");
		departmentDao.inserir(newDep);
	}
}