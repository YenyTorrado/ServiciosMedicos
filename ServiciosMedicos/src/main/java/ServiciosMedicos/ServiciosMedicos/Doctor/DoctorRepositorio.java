package ServiciosMedicos.ServiciosMedicos.Doctor;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author YnyFer
 */

@Repository
public class DoctorRepositorio {
    
    @Autowired
    private DoctorInterface crud;

    public List<Doctor> getAll(){
        return (List<Doctor>) crud.findAll();
    }

    public Optional<Doctor> getDoctor(int id){
        return crud.findById(id);
    }

    public Doctor save(Doctor doctor){
        return crud.save(doctor);
    }
    public void delete(Doctor doctor){
        crud.delete(doctor);
    }
    
}
