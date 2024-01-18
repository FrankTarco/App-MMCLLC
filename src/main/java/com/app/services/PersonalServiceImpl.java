package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Personal;
import com.app.repository.PersonalRepository;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	private PersonalRepository repo;
	
	@Override
	public List<Personal> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Personal registrarActualizar(Personal bean) {
		// TODO Auto-generated method stub
		return repo.save(bean);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Personal buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repo.buscarPersonalPorId(id);
	}

	@Override
	public Personal buscarPorCheque(String cheque) {
		// TODO Auto-generated method stub
		return repo.buscarPersonalPorCheque(cheque);
	}

	@Override
	public List<Personal> consultaDinamica(String nombre, String anio) {
		// TODO Auto-generated method stub
		return repo.consultaDinamicaPersonal(nombre, anio);
	}

}
