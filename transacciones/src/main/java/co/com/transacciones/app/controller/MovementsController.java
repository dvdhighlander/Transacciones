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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.transacciones.app.component.ResponseMovements;
import co.com.transacciones.app.model.MovementsDTO;
import co.com.transacciones.app.model.Movimientos;
import co.com.transacciones.app.service.MovementsServiceImpl;

@RestController
@RequestMapping("/movimientos")
public class MovementsController {

	@Autowired
	private MovementsServiceImpl movementService;
	
	@GetMapping
	@ResponseBody
	 public List<MovementsDTO> getAllClients() {
	        return movementService.getAllMovements();
	}
	    
	@PostMapping
	 public ResponseMovements createClient(@RequestBody MovementsDTO movementDTO) {
	        return movementService.saveMovement(movementDTO);
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<MovementsDTO> getClientById(@PathVariable int id) {
	        Optional<MovementsDTO> cliente = movementService.getMovementById(id);
	        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	  }

	  @PutMapping
	  public ResponseMovements updateClient(@RequestBody MovementsDTO movementDTO) {
	        try {
	        	ResponseMovements updatedCliente = movementService.updateMovement(movementDTO);
	            return updatedCliente;
	        } catch (Exception e) {
	            return new ResponseMovements(0, "Error: "+e.getMessage());
	        }
	   }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteMovement(@PathVariable int id) {
		  movementService.deleteMOvement(id);
	        return ResponseEntity.noContent().build();
	  }
	  
	  @GetMapping("/reportes")
	  public void movementsList(@RequestParam(required = false)  String fechas, @RequestParam(required = false)  String cliente) {
		  if(fechas!=null) {
			  System.out.println(fechas); 
		  }
		  System.out.println(cliente);
		  
	  }
}
