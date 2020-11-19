package mx.com.gm.dao;

import mx.com.gm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersHasRoles extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
