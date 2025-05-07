package com.usuarios.app.service;

import java.util.List;
import java.util.Optional;

import com.usuarios.app.model.ClientDTO;

public interface ClientService {

	public abstract List<ClientDTO> getAllClients();
    public abstract Optional<ClientDTO> getClienteById(int id);
    public abstract ClientDTO saveCliente(ClientDTO clienteDTO);
    public abstract ClientDTO updateCliente(ClientDTO clienteDTO);
    public abstract void deleteCliente(int id);
}
