package py.com.pegasus.test.pets.services;

import org.springframework.security.core.Authentication;

public interface SecurityAuthentication {
    Authentication getAuthentication();
}