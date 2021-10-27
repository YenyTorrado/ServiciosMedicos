package ServiciosMedicos.ServiciosMedicos.Especialidad;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YnyFer
 */
@Service
public class EspecialidadServicios {
    @Autowired
    private EspecialidadRepositorio metodosCrud;

    public List<Especialidad> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Especialidad> getSpecialty(int specialtyId) {
        return metodosCrud.getSpecialty(specialtyId);
    }

    public Especialidad save(Especialidad specialty) {
        if (specialty.getId()== null) {
            return metodosCrud.save(specialty);
        } else {
            Optional<Especialidad> especialidad1 = metodosCrud.getSpecialty(specialty.getId());
            if (especialidad1.isEmpty()) {
                return metodosCrud.save(specialty);
            } else {
                return specialty;
            }
        }
    }

    public Especialidad update(Especialidad specialty){
        if(specialty.getId()!=null){
            Optional<Especialidad>g=metodosCrud.getSpecialty(specialty.getId());
            if(!g.isEmpty()){
                if(specialty.getDescription()!=null){
                    g.get().setDescription(specialty.getDescription());
                }
                if(specialty.getName()!=null){
                    g.get().setName(specialty.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return specialty;
    }
    public boolean deleteSpecialty(int specialtyId){
        Boolean d=getSpecialty(specialtyId).map(specialty -> {
            metodosCrud.delete(specialty);
            return true;
        }).orElse(false);
        return d;
    }

   
}
