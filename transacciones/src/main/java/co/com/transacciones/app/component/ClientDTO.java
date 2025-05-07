package co.com.transacciones.app.component;

public record ClientDTO (int idCliente, int idPersona, String nombre, String genero, int edad, long identificacion,
		String direccion, long telefono, String password, boolean estado){

}
