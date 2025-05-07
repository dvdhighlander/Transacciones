package com.usuarios.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios.app.model.ClientDTO;
import com.usuarios.app.model.Cliente;
import com.usuarios.app.model.Persona;
import com.usuarios.app.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;

	
	@Override
	public List<ClientDTO> getAllClients() {
		 return clientRepository.findAll().stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	}
	
	@Override
	public Optional<ClientDTO> getClienteById(int id) {
		return clientRepository.findById(id).map(this::convertToDTO);

	}


	@Override
	public ClientDTO saveCliente(ClientDTO clienteDTO) {
		Cliente client = convertToEntity(clienteDTO);
        Cliente newClient = clientRepository.save(client);
        return convertToDTO(newClient);
	}


	@Override
	public ClientDTO updateCliente(ClientDTO clienteDTO) {
		Cliente cliente = clientRepository.findById(clienteDTO.getIdCliente()).orElseThrow();
    	cliente.getPersona().setNombre(clienteDTO.getNombre());
    	cliente.getPersona().setGenero(clienteDTO.getGenero());
    	cliente.getPersona().setEdad(clienteDTO.getEdad() );
    	cliente.getPersona().setIdentificacion(clienteDTO.getIdentificacion());
    	cliente.getPersona().setDireccion(clienteDTO.getDireccion());
    	cliente.getPersona().setTelefono(clienteDTO.getTelefono());
    	cliente.setPassword(clienteDTO.getPassword());
    	cliente.setEstado(clienteDTO.isEstado());
	        Cliente updatedClient = clientRepository.save(cliente);
	        return convertToDTO(updatedClient);

	}


	@Override
	public void deleteCliente(int id) {
		 clientRepository.deleteById(id);

		
	}
	
	private ClientDTO convertToDTO(Cliente cliente) {

        return new ClientDTO(cliente.getIdCliente(),cliente.getPersona().getIdPersona(), cliente.getPersona().getNombre(), cliente.getPersona().getGenero(), cliente.getPersona().getEdad(), cliente.getPersona().getIdentificacion(),
        		cliente.getPersona().getDireccion(), cliente.getPersona().getTelefono(), cliente.getPassword(), cliente.isEstado());
    }

    private Cliente convertToEntity(ClientDTO clienteDTO) {
    	Cliente cliente = new Cliente();
    	Persona persona= new Persona();
    	cliente.setPersona(persona);
    	cliente.getPersona().setNombre(clienteDTO.getNombre());
    	cliente.getPersona().setGenero(clienteDTO.getGenero());
    	cliente.getPersona().setEdad(clienteDTO.getEdad());
    	cliente.getPersona().setIdentificacion(clienteDTO.getIdentificacion());
    	cliente.getPersona().setDireccion(clienteDTO.getDireccion());
    	cliente.getPersona().setTelefono(clienteDTO.getTelefono());
    	cliente.setPassword(clienteDTO.getPassword());
    	cliente.setEstado(clienteDTO.isEstado());
      	cliente.getPersona().setIdPersona(clienteDTO.getIdPersona());
        return cliente;
    }
}
