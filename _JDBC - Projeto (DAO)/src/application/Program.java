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

		/*
		System.out.println("=== Teste 1: inserir() -- Department ===");
		Department newDep = new Department(null, "TI");
		departmentDao.inserir(newDep);

		System.out.println("\n=== Teste 2: atualizar() -- Department ===");
		departmentDao.atualizar(new Department(7, "Desenvolvimento"));

		System.out.println("\n=== Teste 3: deletarPorId() -- Department ===");
		departmentDao.deletarPorId(6);

		System.out.println("\n=== Teste 4: buscarPorId() -- Department ===");
		System.out.println(departmentDao.buscarPorId(2));
		*/

		System.out.println("\n=== Teste 5: buscarTodos() -- Department ===");
		List<Department> dep = departmentDao.buscarTodos();
		dep.forEach(System.out::println);
	}
}