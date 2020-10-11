
package mx.com.aea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorInicio {
    
    @GetMapping("/")
    public String inicio(){
        return "Hola circulo de estudio asdasdasd";
    }
}
