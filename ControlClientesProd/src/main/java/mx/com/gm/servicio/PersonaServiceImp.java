package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.IPersonaDAO;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImp implements IPersonaService {

    @Autowired
    private IPersonaDAO personaDao;
    
    @Override
    @Transactional (readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional 
    public void savePersona(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void deletePersona(Persona persona) {
       personaDao.delete(persona);
    }

    @Override
    @Transactional (readOnly = true)
    public Persona findPersona(Persona persona) {
      return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
