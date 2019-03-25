package profe.springcore.empleados.negocio;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import profe.springcore.empleados.daos.EmpDAO;
import profe.springcore.empleados.model.Empleado;

@Service
public class EmpNegocioImp implements EmpNegocio {

	@Resource(name="daoJdbc")
	private EmpDAO dao;

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	public Empleado getEmpleado(String cif) {
		return dao.getEmpleado(cif);
	}

	public List<Empleado> getAllEmpleados() {
		return dao.getAllEmpleados();
	}

	public boolean eliminaEmpleado(String cif) {
		return dao.eliminaEmpleado(cif);
	}

	public boolean insertaEmpleado(Empleado emp) {
		return dao.insertaEmpleado(emp);
	}

	public boolean modificaEmpleado(Empleado emp) {
		return dao.modificaEmpleado(emp);
	}

	public String[] getAllHobbies() {
		return dao.getAllHobbies();
	}
	
	
}
