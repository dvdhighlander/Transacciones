package co.com.transacciones.app.model;

import java.util.Arrays;

public enum MovementTypes {
    RETIRO,
    CONSIGNACION, 
    REEMBOLSO,
    PAGO,
    INTERESES;
	
	public static boolean isValidMovement(String movement) {
	    return Arrays.stream(MovementTypes.values())
	            .anyMatch(status -> status.name().equals(movement));
	}
}


