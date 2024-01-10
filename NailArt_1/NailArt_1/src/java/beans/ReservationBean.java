package beans;

import DAO.DAOReservation;
import POJO.Reservation;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.application.NavigationHandler;

@ManagedBean
@SessionScoped
public class ReservationBean implements Serializable {

    private Reservation reservation = new Reservation();
    private boolean insertionSuccess = false;

    // Inject the DAO
    private DAOReservation reservationDAO = new DAOReservation();
    
    public void init() {
        reservation = new Reservation();
    }

    public void initUpdate(Integer id) {
        if (id != null) {
            reservation = reservationDAO.getReservationById(id);
        }
    }

    public String redirectToUpdate(Integer id) {
        initUpdate(id);
        String redirectUrl = "update.xhtml?id=" + id + "&faces-redirect=true";
        return redirectUrl;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservationDAO.getAllReservations();
    }

    public boolean isInsertionSuccess() {
        return insertionSuccess;
    }

    public String submitReservation() {
        // Save the reservation using the DAO
        insertionSuccess = reservationDAO.insertReservation(reservation);

        // You can handle success or failure as needed here
        if (insertionSuccess) {
            // Success handling, e.g., redirect to a success page
            return "success.xhtml";
        } else {
            // Failure handling, e.g., stay on the same page or redirect to an error page
            return "error.xhtml";
        }
    }

    public String updateReservation() {
        boolean updated = reservationDAO.updateReservation(reservation);
        init();
        if (updated) {
            return "dashboard"; // You can redirect to a success page
        } else {
            // Handle the case where the update failed, e.g., display an error message
            return "error"; // You can redirect to an error page
        }
    }

    public String deleteReservation(Integer id) {
        System.out.println("Deleting reservation: " + id);

        boolean deleted = reservationDAO.deleteReservation(id);
        if (deleted) {
            return "dashboard"; // You can redirect to a success page
        } else {
            return "error"; // You can redirect to an error page
        }
    }
}
