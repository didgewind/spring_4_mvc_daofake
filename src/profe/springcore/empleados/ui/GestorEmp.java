package profe.springcore.empleados.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import profe.springcore.empleados.model.Empleado;
import profe.springcore.empleados.negocio.EmpNegocio;

public class GestorEmp {

	private EmpNegocio negocio;
	
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] {"beans-empleados.xml"});
		GestorEmp gestor = (GestorEmp) context.getBean("gestorEmp");
		gestor.go();
	}

	private void go() {
		this.insertaEmpleados();
		this.imprimeEmpleados(negocio.getAllEmpleados());
	}
	
	private void imprimeEmpleado(Empleado emp) {
		System.out.println(emp);
	}
	
	private void imprimeEmpleados(List<Empleado> empleados) {
		for (Empleado emp : empleados) {
			this.imprimeEmpleado(emp);
		}
	}

	public void setNegocio(EmpNegocio negocio) {
		this.negocio = negocio;
	}

	private void insertaEmpleados() {
		try {
			/*
			 * Borramos primero los empleados que queremos insertar, para partir de 0...
			 */
			negocio.eliminaEmpleado("03957234Y");
			negocio.eliminaEmpleado("57340934Z");
			negocio.eliminaEmpleado("21094387T");
			negocio.eliminaEmpleado("34534545U");
			List<Empleado> empsAInsertar = new ArrayList<Empleado>();
			empsAInsertar.add(new Empleado("03957234Y", "Manuel", "Pedraza", 70));
			empsAInsertar.add(new Empleado("57340934Z", "Inaru", "Escribano", 0));
			empsAInsertar.add(new Empleado("03957234Y", "J", "G", 40));
			empsAInsertar.add(new Empleado("34534545U", "Made", "Mata", 41));
			negocio.insertaEmpleados(empsAInsertar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
