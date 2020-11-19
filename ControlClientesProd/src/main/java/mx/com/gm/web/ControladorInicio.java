package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private IPersonaService personaService;
        
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        log.info("ejecutando el controlador Spring MVC");
        log.info("Loggin user: " + user.getUsername());
        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        
        var totalBalance = 0d;
        for(var p: personas){
             totalBalance += p.getSaldo();
        }
        
        model.addAttribute("totalBalance",totalBalance);
        model.addAttribute("totalPeople", personas.size());
        
        return "index";
    }
        
    @GetMapping("/addPerson")
    public String add(Persona persona){
        return "edit";
    }
    
    @PostMapping("/save")
    public String save(@Valid Persona persona, Errors errors){
        if(errors.hasErrors()){
          return "edit";
        }
        
        personaService.savePersona(persona);
        return "redirect:/";
    }
    
    @GetMapping("/edit/{idPersona}")
    public String edit(Persona persona, Model model){
        persona = personaService.findPersona(persona);
        model.addAttribute("persona", persona);
        return "edit";
    }
    
    @GetMapping("/delete/{idPersona}")
    public String delete(Persona persona){
        personaService.deletePersona(persona);
        return "redirect:/";
    }
    
    
    @GetMapping("/delete2")
    public String delete2(Persona persona){
        personaService.deletePersona(persona);
        return "redirect:/";
    }
    
    
    
    
}
