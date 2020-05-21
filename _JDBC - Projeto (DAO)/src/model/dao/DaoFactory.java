package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJdbc;
import model.entidades.Seller;

public class DaoFactory
{
	public static EntidadeDao<Seller> criarSellerDao()
	{
		return new SellerDaoJdbc(DB.getConnection());
	}
}