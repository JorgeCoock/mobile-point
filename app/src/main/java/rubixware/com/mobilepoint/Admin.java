package rubixware.com.mobilepoint;

/**
 * Admin class, Java normal class
 */
public class Admin {
    private Integer id;
    private String username;
    private String password;

    public Admin(Integer id, String username ,String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
