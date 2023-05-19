package Bll.Validator;

import java.util.regex.Pattern;
import Model.Client;

/**
 * Se verifica daca email-ul clientului din baza de date este corect.
 * @author tania
 */

public class ClientEmailValidator implements Validator<Client> {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    public void validate(Client t) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(t.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
    }

}
