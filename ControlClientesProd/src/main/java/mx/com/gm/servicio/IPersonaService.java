package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface IPersonaService {
 
    public List<Persona> listarPersonas();
    
    public void savePersona(Persona persona);
    
    public void deletePersona(Persona persona);
    
    public Persona findPersona(Persona persona);
    
    
}
