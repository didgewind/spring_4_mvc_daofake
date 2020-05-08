package profe.springcore.empleados.ui;

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
		this.imprimeEmpleados(negocio.getAllEmpleados());
		System.out.println("------");
		System.out.println(negocio.getEmpleado("23948745F"));
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


}
