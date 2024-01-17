package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Gastos;
import com.app.repository.GastosRepository;

@Service
public class GastosServiceImpl implements GastosService{

	@Autowired
	private GastosRepository repo;
	
	@Override
	public List<Gastos> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Gastos registrarActualizar(Gastos bean) {
		// TODO Auto-generated method stub
		return repo.save(bean);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Gastos gastosPorId(int id) {
		// TODO Auto-generated method stub
		return repo.buscarGastosPorId(id);
	}

	@Override
	public Gastos gastosPorBoleta(String boleta) {
		// TODO Auto-generated method stub
		return repo.buscarGastosPorBoleta(boleta);
	}

	
	
}
