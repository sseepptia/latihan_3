package POJO;
// Generated Oct 12, 2023 5:20:54 PM by Hibernate Tools 4.3.1
import java.util.Date;

public class Reservation {
    private Integer id;
    private String name;
    private String phoneNumber;
    private Date date;
    private Date hours;

    // Getters and setters for all properties
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHours() {
        return hours;
    }

    public void setHours(Date hours) {
        this.hours = hours;
    }
}
