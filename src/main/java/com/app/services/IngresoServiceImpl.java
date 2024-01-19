package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Ingresos;
import com.app.repository.IngresosRepository;

@Service
public class IngresoServiceImpl implements IngresosService{

	@Autowired
	private IngresosRepository repo;
	
	@Override
	public List<Ingresos> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Ingresos registrarActualizar(Ingresos bean) {
		// TODO Auto-generated method stub
		return repo.save(bean);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Ingresos buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repo.buscarRegistroPorId(id);
	}

	@Override
	public Ingresos buscarPorCheque(String cheque) {
		// TODO Auto-generated method stub
		return repo.buscarIngresoPorCheque(cheque);
	}

	@Override
	public List<Ingresos> consultaDinamica(String nombre, String anio) {
		// TODO Auto-generated method stub
		return repo.consultaDinamicaIngresos(nombre, anio);
	}

}
