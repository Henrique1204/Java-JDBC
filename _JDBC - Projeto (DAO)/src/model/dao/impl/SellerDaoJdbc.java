package model.dao.impl;

import java.util.List;

import model.dao.EntidadeDao;
import model.entidades.Seller;

public class SellerDaoJdbc implements EntidadeDao<Seller>
{
	//M�todos sobrescrevidos
	@Override
	public void inserir(Seller obj)
	{
	
	}

	@Override
	public void atualizar(Seller obj)
	{

	}

	@Override
	public void deletarPorId(Integer id)
	{
	
	}

	@Override
	public Seller buscarPorId(Integer id)
	{
		return null;
	}

	@Override
	public List<Seller> buscarTodos()
	{
		return null;
	}
}