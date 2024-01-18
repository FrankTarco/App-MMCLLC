package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	@Query("select p from Personal p where p.id_personal= ?1")
	public Personal buscarPersonalPorId(int id);
	
	@Query("select p from Personal p where p.numero_cheque= ?1")
	public Personal buscarPersonalPorCheque(String cheque);
	
	@Query("SELECT p FROM Personal p WHERE (p.nombre_persona LIKE ?1) AND (?2 = '' OR FUNCTION('YEAR', p.fecha) = CAST(?2 AS int))")
	public List<Personal>consultaDinamicaPersonal(String nombre, String anio);
	
}
