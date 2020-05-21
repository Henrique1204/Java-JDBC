package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EntidadeDao;
import model.entidades.Department;
import model.entidades.Seller;

public class Program
{
	public static void main(String[] args)
	{
		Department obj = new Department(1, "Books");
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);

		EntidadeDao<Seller> sellerDao = DaoFactory.criarSellerDao();

		System.out.println(obj);
		System.out.println(seller);
	}
}