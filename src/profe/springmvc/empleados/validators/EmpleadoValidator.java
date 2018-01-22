package profe.springmvc.empleados.validators;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import profe.springcore.empleados.model.Empleado;

/**
 * Validador de Empleado personalizado spring
 * @author made
 *
 */
@Component
public class EmpleadoValidator implements Validator {

	/* Para i18n cif en el mensaje de error */
	@Autowired
	private ApplicationContext context;
	
	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		// Validamos sólo la clase Empleado
		return Empleado.class.equals(paramClass);
		// Validamos Empleado y sus subclases
		// return Empleado.class.isAssignableFrom(paramClass);
	}

	/**
	 * Valida que el cif esté y que la edad sea mayor que 15
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		String cif = context.getMessage("cif", null, Locale.getDefault());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cif", "campo.obligatorio",
				new Object[]{cif});
		
		Empleado emp = (Empleado) obj;
		if(emp.getEdad() < 16){
			errors.rejectValue("edad", "edad.negativa", 
					"la Edad no puede ser menor que 16");
		}
		
	}
}