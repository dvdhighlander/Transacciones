package com.usuarios.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.app.model.ClientDTO;
import com.usuarios.app.service.ClientServiceImpl;


@RestController
@RequestMapping("/clientes")
public class UserController {

	@Autowired
	private ClientServiceImpl clientService;
	
    @GetMapping
    @ResponseBody
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }
    
    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clienteDTO) {
        return clientService.saveCliente(clienteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable int id) {
        Optional<ClientDTO> cliente = clientService.getClienteById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        try {
        	ClientDTO updatedCliente = clientService.updateCliente(clientDTO);
            return ResponseEntity.ok(updatedCliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
    	clientService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
