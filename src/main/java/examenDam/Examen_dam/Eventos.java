package examenDam.Examen_dam;

import java.time.LocalDateTime;

public class Eventos {

	String nombre;
	LocalDateTime fecha;
	String ubicacion;
	String descripcion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Eventos(String nombre, LocalDateTime fecha, String ubicacion, String descripcion) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Eventos [nombre=" + nombre + ", fecha=" + fecha + ", ubicacion=" + ubicacion + ", descripcion="
				+ descripcion + "]";
	}

}
