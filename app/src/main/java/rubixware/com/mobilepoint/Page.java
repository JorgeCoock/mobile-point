package rubixware.com.mobilepoint;


public class Page {

    private int id, user_id;
    private String url;

    public Page(){}

    public Page(int id, int user_id, String url) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
    }

    public Page(int user_id, String url) {
        this.user_id = user_id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
