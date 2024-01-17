package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Gastos;

public interface GastosRepository extends JpaRepository<Gastos, Integer>{
	
	@Query("select g from Gastos g where g.id_gastos= ?1")
	public Gastos buscarGastosPorId(int id);

	@Query("select g from Gastos g where g.numero_boleta= ?1")
	public Gastos buscarGastosPorBoleta(String boleta);
	
}
