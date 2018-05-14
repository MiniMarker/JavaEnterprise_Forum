package no.cmarker.backend.entities.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Christian Marker on 14/05/2018 at 18:46.
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({
		ElementType.TYPE,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
	
	String message() default "Invalid Password";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
