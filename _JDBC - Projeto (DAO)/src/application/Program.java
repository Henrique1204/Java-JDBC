package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EntidadeDao;
import model.entidades.Department;
import model.entidades.Seller;

public class Program
{
	public static void main(String[] args)
	{
		EntidadeDao<Seller> sellerDao = DaoFactory.criarSellerDao();

		System.out.println("=== Teste 1: buscarPorId() -- Seller ===");
		Seller seller = sellerDao.buscarPorId(3);
		System.out.println(seller);

		System.out.println("\n=== Teste 2: buscarPorDepartment() -- Seller ===");
		Department department = new Department(2, null);
		List<Seller> lista = sellerDao.buscarPorDepartment(department);
		lista.forEach(System.out::println);
	}
}