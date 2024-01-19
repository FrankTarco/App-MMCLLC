package com.app.services;

import java.util.List;

import com.app.entity.Ingresos;

public interface IngresosService {

	public List<Ingresos> listar();
	public Ingresos registrarActualizar(Ingresos bean);
	public void eliminar(int id);
	public Ingresos buscarPorId(int id);
	public Ingresos buscarPorCheque(String cheque);
	public List<Ingresos>consultaDinamica(String nombre,String anio);
}
