package beans;
// Generated Oct 12, 2023 5:20:54 PM by Hibernate Tools 4.3.1

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import DAO.DAOLogin;
import POJO.Login;

@ManagedBean
@RequestScoped
public class LoginBean {

    private String email;
    private String password;
    private String loginResultMessage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginResultMessage() {
        return loginResultMessage;
    }

    public void setLoginResultMessage(String loginResultMessage) {
        this.loginResultMessage = loginResultMessage;
    }

    public String login() {
        DAOLogin daologin = new DAOLogin();
        Login login = daologin.findUserByEmail(email);
        System.out.println(login);

        if (login != null && login.getPassword().equals(password)) {
            loginResultMessage = "Login successful!";
            return "dashboard"; // Redirect to a success page
        } else {
            loginResultMessage = "Login failed. Please check your credentials.";
            return "login"; // Stay on the login page
        }
    }
}
