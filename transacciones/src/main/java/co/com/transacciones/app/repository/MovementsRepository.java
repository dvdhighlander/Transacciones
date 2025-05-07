package co.com.transacciones.app.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.transacciones.app.model.Movimientos;

@Repository
public interface MovementsRepository extends JpaRepository<Movimientos, Integer>{


    @Query("SELECT mov FROM Movimientos mov WHERE " +
            "(:initialDate IS NULL OR :endDate IS NULL OR (mov.fecha BETWEEN :startTime AND :endTime)) AND"+
           "(:clientId IS NULL OR mov.cuenta.idCliente = :clientId)")
  public  List<Movimientos> getReport(@Param("initialDate") String initialDate, 
                                        @Param("endDate") String endDate,
                                        @Param("clientId") String clientId);
}
