package ServiciosMedicos.ServiciosMedicos.Reservacion;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YnyFer
 */
public interface ReservacionInterface extends CrudRepository<Reservacion, Integer> {
    public List<Reservacion> findAllByStatus (String status);
    
    public List<Reservacion> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    //select clientid, count(*) as "total" from reservacion group by clientid order by total desc; 
    @Query("SELECT c.client, COUNT(c.client) FROM Reservacion AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationByClient();
}
