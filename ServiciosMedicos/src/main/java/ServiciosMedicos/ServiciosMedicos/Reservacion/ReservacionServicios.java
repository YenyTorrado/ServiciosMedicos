package ServiciosMedicos.ServiciosMedicos.Reservacion;

import ServiciosMedicos.ServiciosMedicos.Reportes.ContadorClientes;
import ServiciosMedicos.ServiciosMedicos.Reportes.StatusReservas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Yeny Torrado
 *
 * @author YnyFer
 */
@Service
public class ReservacionServicios {

    @Autowired
    /**
     * Metodos Crud
     *
     */
    private ReservacionRepositorio metodosCrud;

    /**
     * Get all
     *
     */
    public List<Reservacion> getAll() {
        return metodosCrud.getAll();
    }

    /**
     * Get
     *
     */
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    /**
     * Post
     *
     */
    public Reservacion save(Reservacion reservation) {
        if (reservation.getIdReservation() == null) {
            return metodosCrud.save(reservation);
        } else {
            Optional<Reservacion> reservacion1 = metodosCrud.getReservation(reservation.getIdReservation());
            if (reservacion1.isEmpty()) {
                return metodosCrud.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    /**
     * Put
     *
     */
    public Reservacion update(Reservacion reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservacion> reservacion1 = metodosCrud.getReservation(reservation.getIdReservation());
            if (!reservacion1.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    reservacion1.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservacion1.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reservacion1.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(reservacion1.get());
                return reservacion1.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * Delete
     *
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     * status reservas
     *
     */
    public StatusReservas getReservationsStatusReport() {
        List<Reservacion> completed = metodosCrud.getReservationByStatus("completed");
        List<Reservacion> cancelled = metodosCrud.getReservationByStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }

    /**
     * periodo de reservas
     *
     */
    public List<Reservacion> getReservationPeriod(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = new Date();
        Date bDate = new Date();

        try {
            aDate = parser.parse(dateA);
            bDate = parser.parse(dateB);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (aDate.before(bDate)) {
            return metodosCrud.getReservationPeriod(aDate, bDate);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Contador de reservas
     *
     */
    public List<ContadorClientes> getTopClients() {
        return metodosCrud.getTopClients();
    }
}
