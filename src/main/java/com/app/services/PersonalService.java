package com.app.services;

import java.util.List;

import com.app.entity.Personal;

public interface PersonalService {

	public List<Personal> listar();
	public Personal registrarActualizar(Personal bean);
	public void eliminar(int id);
	public Personal buscarPorId(int id);
	public Personal buscarPorCheque(String cheque);
	public List<Personal>consultaDinamica(String nombre,String anio);
	
}
