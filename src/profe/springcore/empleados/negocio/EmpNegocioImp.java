package profe.springcore.empleados.negocio;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import profe.springcore.empleados.daos.EmpDAO;
import profe.springcore.empleados.model.Empleado;

@Service
@Transactional
public class EmpNegocioImp implements EmpNegocio {

	@Resource(name="daoJdbcNoTry")
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
	
	@Override // Para probar diferentes tipos de transacciones
	public boolean insertaEmpleados(Collection<Empleado> emps) {
		for (Empleado emp : emps) {
			dao.insertaEmpleado(emp);
		}
		return true;
	}
	
}
