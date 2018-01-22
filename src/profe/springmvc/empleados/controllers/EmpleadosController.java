package profe.springmvc.empleados.controllers;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import profe.springcore.empleados.model.Empleado;
import profe.springcore.empleados.negocio.EmpNegocio;

@Controller
@SessionAttributes("empleado")
@RequestMapping("/gestEmpleados")
public class EmpleadosController {
	
	@Autowired
	private EmpNegocio negocio;
	
	@Resource(name="empleadoValidator")
	private Validator validator;

	/* Registramos el validador con este controlador */
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	/**
	 * Método @ModelAttribute que devuelve el empleado para el formulario
	 * @return
	 */
	@ModelAttribute
	public Empleado addEmpleado() {
		return new Empleado();
	}
		
	/**
	 * Método @ModelAttribute que normaliza el parámetro enviado por el botón
	 * que pulsamos
	 * @return
	 */
	@ModelAttribute
	public void normalizaOpcion(HttpServletRequest request, Model model) {
		String opcion = null;
		Map<String, String[]> paramsMap = request.getParameterMap();
		if (paramsMap.containsKey("muestra")) {
			opcion = "muestra";
		} else if (paramsMap.containsKey("inserta")) {
			opcion = "inserta";
		} else if (paramsMap.containsKey("elimina")) {
			opcion = "elimina";
		} else if (paramsMap.containsKey("muestraTodos")) {
			opcion = "muestraTodos";
		} else if (paramsMap.containsKey("modifica")) {
			opcion = "modifica";
		}
		model.addAttribute("opcion", opcion);
	}
	
	@GetMapping
	public String initForm(){
		return "empleados";
	}
	
	/*@RequestMapping("/muestraEmpleado")
	public String muestraEmp(HttpServletRequest request, Model model) {
		model.addAttribute("empleado", negocio.getEmpleado(request.getParameter("cif")));
		return "muestra-empleado";
	}
	
	// Esta versión utiliza @RequestParam
	@RequestMapping(params={"muestra"}, method=RequestMethod.POST)
	public String muestraEmp(@RequestParam(name="cif", defaultValue="423452435T") String cif, Model model) {
		model.addAttribute("empleado", negocio.getEmpleado(cif));
		model.addAttribute("opcion", "muestra");
		return "empleados";
	}*/

	/*
	 * Controlamos que el empleado no exista pintando sólo el cif.
	 * Cuando controlemos errores bien, mostrar un mensaje.
	 */
	@RequestMapping(params={"muestra"}, method=RequestMethod.POST)
	public String muestraEmp(@Validated @ModelAttribute Empleado emp, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "empleados";
		}
		Empleado empAux = negocio.getEmpleado(emp.getCif());
		if (empAux == null) {
			emp.setApellidos("");
			emp.setNombre("");
			emp.setEdad(0);
		} else {
			model.addAttribute("empleado", empAux);
		}
		return "empleados";
	}

	@RequestMapping(params={"elimina"}, method=RequestMethod.POST)
	public String eliminaEmp(@ModelAttribute Empleado emp, Model model) {
		boolean resultado = negocio.eliminaEmpleado(emp.getCif());
		model.addAttribute("mensaje", resultado ? "Empleado eliminado correctamente" :
			"Error al eliminar el empleado con cif " + emp.getCif());
		return "empleados";
	}

	@RequestMapping(params={"muestraTodos"}, method=RequestMethod.POST)
	public String muestraAllEmp(@ModelAttribute Empleado emp, Model model) {
		model.addAttribute("listaEmpleados", negocio.getAllEmpleados());
		return "empleados";
	}

	@RequestMapping(params={"inserta"}, method=RequestMethod.POST)
	public String insertaEmp(@ModelAttribute Empleado emp, Model model) {
		boolean resultado = negocio.insertaEmpleado(emp);
		model.addAttribute("mensaje", resultado ? "Empleado insertado correctamente" :
			"Error al insertar el empleado con cif " + emp.getCif());
		return "empleados";
	}

	@RequestMapping(params={"modifica"}, method=RequestMethod.POST)
	public String modificaEmp(@ModelAttribute Empleado emp, Model model) {
		boolean resultado = negocio.modificaEmpleado(emp);
		model.addAttribute("mensaje", resultado ? "Empleado modificado correctamente" :
			"Error al modificar el empleado con cif " + emp.getCif());
		return "empleados";
	}
}
