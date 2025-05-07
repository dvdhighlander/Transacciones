package com.usuarios.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuarios.app.model.Persona;

@Repository
public interface PersonRepository extends JpaRepository<Persona, Integer>{

}
