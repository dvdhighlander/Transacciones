package com.usuarios.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios.app.model.Persona;
import com.usuarios.app.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public List<Persona> getAllPersons() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

}
