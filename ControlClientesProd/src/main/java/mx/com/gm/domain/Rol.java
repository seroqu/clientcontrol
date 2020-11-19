package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty
    private String role_name;
    
   @ManyToMany 
   @JoinTable(
   name = "user_has_roles", 
   joinColumns = @JoinColumn(name = "id_rol"), 
   inverseJoinColumns = @JoinColumn(name = "id_usuario"))
   private List<User> users;
    
    
}
