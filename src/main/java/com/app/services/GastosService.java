package com.app.services;

import java.util.List;

import com.app.entity.Gastos;

public interface GastosService {

	public List<Gastos>listar();
	public Gastos registrarActualizar(Gastos bean);
	public void eliminar(int id);
	public Gastos gastosPorId(int id);
	public Gastos gastosPorBoleta(String boleta);
	public List<Gastos>consultaDinamica(String nombre,String anio);
	
}
