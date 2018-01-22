package profe.springmvc.empleados.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import profe.springcore.empleados.model.Empleado;
import profe.springcore.empleados.negocio.EmpNegocio;

@Controller
@RequestMapping("/infoEmpleado")
@SessionAttributes("empleado")
public class InfoEmpleadoController {
	
	@Autowired
	private EmpNegocio negocio;
	
	/*
	 * Es necesario especificar el nombre del atributo porque si no
	 * lo va a insertar en el modelo con el nombre List<String>
	 */
	@ModelAttribute("listaDeAficiones")
	public String[] addListaDeAficiones() {
		return negocio.getAllHobbies();
	}
	
	@RequestMapping(params="!modEdad", method=RequestMethod.GET)
	public String muestraInfo(@ModelAttribute(name="empleado") Empleado emp) {
		return "info-empleado";
	}
	
	@RequestMapping(params="modEdad", method=RequestMethod.GET)
	public String muestraInfo(@RequestParam int modEdad, @ModelAttribute(name="empleado") Empleado emp) {
		emp.setEdad(modEdad);
		return "info-empleado";
	}
	
	@PostMapping
	public String postInfo(@ModelAttribute(name="empleado") Empleado emp) {
		// Modificar en la bd (no es necesario en este caso xq el objeto empleado ya es
		// el registro de la bd mock)
		return "info-empleado";
	}
	
}
