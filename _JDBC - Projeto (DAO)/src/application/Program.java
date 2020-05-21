package application;

import model.dao.DaoFactory;
import model.dao.EntidadeDao;
import model.entidades.Seller;

public class Program
{
	public static void main(String[] args)
	{
		EntidadeDao<Seller> sellerDao = DaoFactory.criarSellerDao();

		Seller seller = sellerDao.buscarPorId(3);

		System.out.println(seller);
	}
}