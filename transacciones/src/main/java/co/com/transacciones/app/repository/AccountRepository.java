package co.com.transacciones.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.transacciones.app.model.Cuenta;

@Repository
public interface AccountRepository extends JpaRepository<Cuenta, Integer>{

}
