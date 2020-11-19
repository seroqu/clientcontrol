package mx.com.gm.servicio;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsersHasRoles;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userDetailService")
@Slf4j
public class UserRolService implements UserDetailsService {

    @Autowired
    private UsersHasRoles userrolDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           
            mx.com.gm.domain.User user = userrolDAO.findByUsername(username);
           
            
            if(user==null){
                throw new UsernameNotFoundException(username);
            }
            
            var roles = new ArrayList<GrantedAuthority>();
            //roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            
            for(Rol rol: user.getUserRoles()){
                roles.add(new SimpleGrantedAuthority(rol.getRole_name()));
            }
            
            
            System.out.println("Debbug here: " + user.getUsername() + " " + user.getPassword());
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}
