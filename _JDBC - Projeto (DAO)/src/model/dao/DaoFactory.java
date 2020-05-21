package model.dao;

import model.dao.impl.SellerDaoJdbc;
import model.entidades.Seller;

public class DaoFactory
{
	public static EntidadeDao<Seller> criarSellerDao()
	{
		return new SellerDaoJdbc();
	}
}