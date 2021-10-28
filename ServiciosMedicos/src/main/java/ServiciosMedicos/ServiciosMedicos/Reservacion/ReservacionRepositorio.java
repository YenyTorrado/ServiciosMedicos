package ServiciosMedicos.ServiciosMedicos.Reservacion;


import ServiciosMedicos.ServiciosMedicos.Cliente.Cliente;
import ServiciosMedicos.ServiciosMedicos.Reportes.ContadorClientes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author YnyFer
 */
@Repository
public class ReservacionRepositorio {
      @Autowired
    private ReservacionInterface crud4;

    public List<Reservacion> getAll(){
        return (List<Reservacion>) crud4.findAll();
    }
    public Optional<Reservacion> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservacion save(Reservacion reservation){
        return crud4.save(reservation);
    }
    public void delete(Reservacion reservation){
        crud4.delete(reservation);
    }
    
     public List<Reservacion> getReservationByStatus(String status){
        return crud4.findAllByStatus(status);
    }
    
   public List<Reservacion> getReservationPeriod(Date a, Date b){
       return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
   } 
   
   public List<ContadorClientes> getTopClients(){
       List<ContadorClientes> res= new ArrayList<>();
       List<Object[]> report = crud4.countTotalReservationByClient();
       for(int i=0;i<report.size();i++){
           res.add(new ContadorClientes((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
       }
       return res;
   }
}
