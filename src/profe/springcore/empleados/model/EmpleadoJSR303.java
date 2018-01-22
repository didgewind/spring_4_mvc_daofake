package profe.springcore.empleados.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase empleado con validación por anotaciones
 * @author made
 *
 */
public class EmpleadoJSR303 implements Serializable, Cloneable {

	@NotEmpty
	private String cif;
	private String nombre;
	private String apellidos;
	private int edad;
	private String[] aficiones;
	
	public EmpleadoJSR303() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpleadoJSR303(String cif, String nombre, String apellidos, int edad) {
		super();
		this.cif = cif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (cif == null) {
			if (other.getCif() != null)
				return false;
		} else if (!cif.equals(other.getCif()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [cif=" + cif + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + "]";
	}

	public String[] getAficiones() {
		return aficiones;
	}

	public void setAficiones(String[] aficiones) {
		this.aficiones = aficiones;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	
}
