package no.cmarker.backend.entities.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Christian Marker on 14/05/2018 at 18:45.
 */
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({
		ElementType.TYPE,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
	
	String message() default "Invalid email";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
