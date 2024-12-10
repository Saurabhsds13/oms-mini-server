package in.dmart.oms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
/*
 * The warning about the missing serialVersionUID field is issued because the
 * CustomerNotFoundException class is implementing the serializable interface
 * (indirectly, through RuntimeException or Exception), and it's a good practice
 * to define a serialVersionUID for all Serializable classes. This field is used
 * during deserialization to verify that the sender and receiver of a serialized
 * object maintain compatibility regarding the object's class definition.
 * 
 * Explanation private static final: The field is constant and specific to this
 * class. long serialVersionUID: A unique identifier for the class version.
 * Value of serialVersionUID: You can choose any long value (e.g., 1L). Some
 * IDEs can generate a default one for you. For consistency, it’s common to use
 * 1L for manually created exceptions unless you anticipate significant future
 * changes. Why is serialVersionUID Important? If the class changes (e.g.,
 * adding/removing fields or methods) and the serialVersionUID isn't set, Java
 * generates one automatically at runtime based on the class structure. If a
 * mismatch occurs during deserialization (e.g., a serialized object was created
 * with an older version of the class), an InvalidClassException is thrown. By
 * explicitly defining it, you avoid unexpected issues when working with
 * serialization.
 * 
 */