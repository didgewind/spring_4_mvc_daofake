package profe.springcore.empleados.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import profe.springcore.empleados.model.Empleado;

public class EmpDAOMock implements EmpDAO {

	private Map<String, Empleado> mpEmpleados;
	
	@Override
	public Empleado getEmpleado(String cif) {
		Empleado eReturn = null;
		try {
			 eReturn = (Empleado) mpEmpleados.get(cif).clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eReturn;
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		List<Empleado> lReturn = new ArrayList<Empleado>();
		for (Empleado emp : mpEmpleados.values()) {
			try {
				lReturn.add((Empleado) emp.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lReturn;
	}

	public void setEmpleados(Map<String, Empleado> mpEmpleados) {
		this.mpEmpleados = mpEmpleados;
	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		Empleado eAux = this.mpEmpleados.remove(cif);
		return (eAux != null);
	}

	@Override
	public boolean insertaEmpleado(Empleado emp) {
		if (this.existeEmpleado(emp.getCif())) {
			return false;
		} else {
			try {
				this.mpEmpleados.put(emp.getCif(), (Empleado) emp.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public boolean modificaEmpleado(Empleado emp) {
		if (!this.existeEmpleado(emp.getCif())) {
			return false;
		} else {
			try {
				this.mpEmpleados.put(emp.getCif(), (Empleado) emp.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	private boolean existeEmpleado(String cif) {
		return this.mpEmpleados.containsKey(cif);
	}

	@Override
	public String[] getAllHobbies() {
		// TODO Auto-generated method stub
		String[] aReturn = {"Ir a encuentros Rainbow", "Practicar el no apego", "Bailar el Despacito"};
		return aReturn;
	}
	
}
