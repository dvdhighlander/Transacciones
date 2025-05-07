package co.com.transacciones.app.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.transacciones.app.component.ResponseMovements;
import co.com.transacciones.app.model.Cuenta;
import co.com.transacciones.app.model.MovementTypes;
import co.com.transacciones.app.model.MovementsDTO;
import co.com.transacciones.app.model.Movimientos;
import co.com.transacciones.app.model.ReportDTO;
import co.com.transacciones.app.model.ResponseReport;
import co.com.transacciones.app.repository.AccountRepository;
import co.com.transacciones.app.repository.MovementsRepository;

@Service
public class MovementsServiceImpl implements MovementsService{

	
	@Autowired
	MovementsRepository movRepository;
	@Autowired
	AccountRepository accountRepository;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	
	@Override
	public List<MovementsDTO> getAllMovements() {
		 return movRepository.findAll().stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	}

	@Override
	public Optional<MovementsDTO> getMovementById(int id) {
		return movRepository.findById(id).map(this::convertToDTO);
	}

	@Override
	public ResponseMovements saveMovement(MovementsDTO movement) {
	      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		if (MovementTypes.isValidMovement(movement.tipoMovimiento())) {
			
			try {
			Movimientos mov = convertToEntity(movement);	
			if(checkAvailableMoney(mov)) {
				mov.setSaldoInicial(mov.getCuenta().getSaldoInicial());
				mov.getCuenta().setSaldoInicial(newBalance(mov.getCuenta().getSaldoInicial(), mov.getValor(), mov.getTipoMovimiento()));
			}else {
				return new ResponseMovements(0, "Saldo No Disponible");
			}
			Movimientos newMov = movRepository.save(mov);			
	        return new ResponseMovements(newMov.getIdMovimiento(), "OK");
			}catch(Exception e) {
			return new ResponseMovements(0, "Error: "+e.getMessage());
			}
		}else {
			return new ResponseMovements(0, "Movimiento Invalido");
		}

	}

	@Override
	public ResponseMovements updateMovement(MovementsDTO movement) throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Movimientos mov = movRepository.findById(movement.idMovimiento()).orElseThrow();
		if (MovementTypes.isValidMovement(movement.tipoMovimiento())) {
			mov.setTipoMovimiento(movement.tipoMovimiento());
		}else {
			return new ResponseMovements(0, "Movimiento Invalido");
		}
		try {
	    mov.setFecha(new Timestamp(dateFormat.parse(movement.fecha()).getTime()));
	    if(checkAvailableMoney(mov)) {
	    	mov.setValor(movement.valor());
		}else {
			return new ResponseMovements(0, "Saldo No Disponible");
		}
    	
    	Movimientos updatedMOv = movRepository.save(mov);
    	 return new ResponseMovements(updatedMOv.getIdMovimiento(), "OK");
		}catch(Exception e) {
			return new ResponseMovements(0, "Error: "+e.getMessage());
		}
		
	}

	@Override
	public void deleteMOvement(int id) {
		movRepository.deleteById(id);
		
	}


	private MovementsDTO convertToDTO(Movimientos movement) {
		
        return new MovementsDTO(movement.getIdMovimiento(),dateFormat.format(movement.getFecha()),movement.getTipoMovimiento(),movement.getValor(),movement.getSaldoInicial(),
        		movement.getCuenta().getIdCuenta());
    }

    private Movimientos convertToEntity(MovementsDTO movementDTO) throws ParseException {
    	Movimientos mov = new Movimientos();
    	Cuenta account= accountRepository.findById(movementDTO.idCuenta()).orElseThrow();
       mov.setCuenta(account);
       mov.setFecha(new Timestamp(dateFormat.parse(movementDTO.fecha()).getTime()));
       mov.setTipoMovimiento(movementDTO.tipoMovimiento());
       mov.setValor(movementDTO.valor());
       mov.setSaldoInicial(movementDTO.saldoInicial());
        return mov;
    }

    private boolean checkAvailableMoney(Movimientos mov) {
    	if(mov.getTipoMovimiento().equals(MovementTypes.CONSIGNACION.toString()) || mov.getTipoMovimiento().equals(MovementTypes.REEMBOLSO.toString())|| mov.getTipoMovimiento().equals(MovementTypes.INTERESES.toString())) {
    		return true;
    	}
    	double diff= mov.getCuenta().getSaldoInicial()-mov.getValor();
    	if(diff>=0) {
    		return true;
    	}
    	return false;
    }
    
    private double newBalance(double oldBalance, double value, String MovementType) {
    	double newBalance=0;
    	if(MovementType.equals(MovementTypes.CONSIGNACION.toString()) || MovementType.equals(MovementTypes.REEMBOLSO.toString())|| MovementType.equals(MovementTypes.INTERESES.toString())) {
    		newBalance=oldBalance + value;
    		return newBalance;
    	}
    	if(MovementType.equals(MovementTypes.RETIRO.toString()) || MovementType.equals(MovementTypes.PAGO.toString())) {
    		newBalance=oldBalance - value;
    		return newBalance;
    	}
    	return oldBalance;
    }

	@Override
	public ResponseReport getReport(String dateRange, String  userId) {
		List<ReportDTO> movementList= new ArrayList<ReportDTO>();
		String initialDate=null;
		String endDate=null;
		int clientId=0;;
		if(dateRange==null && userId==null ) {
			return new ResponseReport( "Debe haber al menos un filtro de búsqueda",movementList);
		}
		if(dateRange.isEmpty() && userId.isEmpty() ) {
			return new ResponseReport( "Debe haber al menos un filtro de búsqueda",movementList);
		}
		if(dateRange!=null  && !dateRange.isEmpty()) {
			String[] dates= dateRange.split("-");
			if(dates.length!= 2) {
				return new ResponseReport( "Rango de fechas incorrecto",movementList);
			}
			initialDate=dates[0];
			endDate=dates[1];
		}
		List<Movimientos> rta= movRepository.getReport(initialDate, endDate, userId);
		for(Movimientos line: rta) {
			System.out.println(line.getIdMovimiento());
		}
		return new ResponseReport( "Rango de fechas incorrecto",movementList);
	}
	

}

