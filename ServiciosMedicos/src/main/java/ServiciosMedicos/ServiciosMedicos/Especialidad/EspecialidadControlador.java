package ServiciosMedicos.ServiciosMedicos.Especialidad;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YnyFer
 */
@RestController
@RequestMapping("/api/Specialty")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class EspecialidadControlador {
    @Autowired
    private EspecialidadServicios servicio;
    @GetMapping("/all")
    public List<Especialidad> getSpecialty(){
        return servicio.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Especialidad> getSpecialty(@PathVariable("id") int specialtyId) {
        return servicio.getSpecialty(specialtyId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad save(@RequestBody Especialidad specialty) {
        return servicio.save(specialty);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad update(@RequestBody Especialidad specialty) {
        return servicio.update(specialty);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int specialtyId) {
        return servicio.deleteSpecialty(specialtyId);
    }
    
}
