package no.cmarker.backend.entities.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Christian Marker on 14/05/2018 at 18:45.
 */
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
	@Override
	public void initialize(ValidEmail validEmail) {
	
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		return (validateEmail(email));
	}
	
	private boolean validateEmail(String email){
		pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
