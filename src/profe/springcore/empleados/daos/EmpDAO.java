package profe.springcore.empleados.daos;

import java.util.List;

import profe.springcore.empleados.model.Empleado;

public interface EmpDAO {

	public abstract Empleado getEmpleado(String cif);
	
	public abstract List<Empleado> getAllEmpleados();
	
	/**
	 * 
	 * @param cif
	 * @return true si hemos eliminado el empleado, false en caso contrario
	 */
	public abstract boolean eliminaEmpleado(String cif);
	
	public abstract boolean insertaEmpleado(Empleado emp);
	
	public abstract boolean modificaEmpleado(Empleado emp);
	
	public abstract String[] getAllHobbies();
}
