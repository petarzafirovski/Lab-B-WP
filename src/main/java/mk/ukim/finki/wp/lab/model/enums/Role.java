package mk.ukim.finki.wp.lab.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
