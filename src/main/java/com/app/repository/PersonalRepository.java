package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	public Personal findByPersonal(String personal);
	
}
