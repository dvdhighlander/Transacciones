package co.com.transacciones.app.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


public record MovementsDTO (int idMovimiento, String fecha, String tipoMovimiento, double valor,double saldoInicial, int idCuenta){

}
