package Components;

public class Korisnik {
    private String username;
    private String password;
    private Boolean isAdmin;

    public Korisnik(String username, String password){
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }


}
