package co.com.transacciones.app.controller;

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

import co.com.transacciones.app.component.AccountDTO;
import co.com.transacciones.app.model.Cuenta;
import co.com.transacciones.app.service.AccountServiceImpl;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;
	
	@GetMapping
	@ResponseBody
	 public List<Cuenta> getAllClients() {
	        return accountService.getAllAccounts();
	}
	    
	@PostMapping
	 public Cuenta createClient(@RequestBody AccountDTO accountDTO) {
 
	        return accountService.saveAccount(accountDTO);
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Cuenta> getClientById(@PathVariable int id) {
	        Optional<Cuenta> cliente = accountService.getAccountById(id);
	        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	  }

	  @PutMapping
	  public ResponseEntity<Cuenta> updateClient(@RequestBody Cuenta accountDTO) {
	        try {
	        	Cuenta updatedCliente = accountService.updateAccount(accountDTO);
	            return ResponseEntity.ok(updatedCliente);
	        } catch (Exception e) {
	            return ResponseEntity.notFound().build();
	        }
	   }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteClient(@PathVariable int id) {
		  accountService.deleteAccount(id);
	        return ResponseEntity.noContent().build();
	  }
}


