package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	@Query("select p from Personal p where p.id_personal= ?1")
	public Personal buscarPersonalPorId(int id);
	
	@Query("select p from Personal p where p.numero_cheque= ?1")
	public Personal buscarPersonalPorCheque(String cheque);
	
}
