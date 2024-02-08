package med.vol.api.domain.repositories;

import med.vol.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository  extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

}
