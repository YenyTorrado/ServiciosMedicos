package ServiciosMedicos.ServiciosMedicos.Especialidad;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author YnyFer
 */
@Repository
public class EspecialidadRepositorio {
    
    @Autowired
    private EspecialidadInterface crud;
    public List<Especialidad> getAll(){
        return (List<Especialidad>) crud.findAll();
    }
    public Optional<Especialidad> getSpecialty(int id){
        return crud.findById(id);
    }

    public Especialidad save(Especialidad specialty){
        return crud.save(specialty);
    }
    public void delete(Especialidad specialty){
       crud.delete(specialty);
    }
    
    
}
