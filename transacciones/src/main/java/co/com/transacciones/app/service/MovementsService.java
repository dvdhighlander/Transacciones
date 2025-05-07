package co.com.transacciones.app.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import co.com.transacciones.app.component.ResponseMovements;
import co.com.transacciones.app.model.MovementsDTO;
import co.com.transacciones.app.model.ResponseReport;

public interface MovementsService {

	public abstract List<MovementsDTO> getAllMovements();
    public abstract Optional<MovementsDTO> getMovementById(int id);
    public abstract ResponseMovements saveMovement(MovementsDTO movement);
    public abstract ResponseMovements updateMovement(MovementsDTO movement) throws ParseException;
    public abstract void deleteMOvement(int id);
    public abstract ResponseReport getReport(String dateRange, String userId);
}
