package validation;

import model.User;
import org.springframework.stereotype.Component;

@Component

public class UserValidator {
    public boolean validate(User user) {
        return true;
    }
}
