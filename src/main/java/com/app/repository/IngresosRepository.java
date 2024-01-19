package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Ingresos;

public interface IngresosRepository extends JpaRepository<Ingresos, Integer>{

	@Query("select i from Ingresos i where i.id_ingresos= ?1")
	public Ingresos buscarRegistroPorId(int id);
	
	@Query("select i from Ingresos i where i.numero_cheque= ?1")
	public Ingresos buscarIngresoPorCheque(String cheque);
	
	@Query("SELECT p FROM Ingresos p WHERE (p.nombre_propiedad LIKE ?1) AND (?2 = '' OR FUNCTION('YEAR', p.fecha) = CAST(?2 AS int))")
	public List<Ingresos>consultaDinamicaIngresos(String nombre, String anio);
}
