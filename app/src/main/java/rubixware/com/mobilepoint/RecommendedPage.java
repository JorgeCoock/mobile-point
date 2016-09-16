package rubixware.com.mobilepoint;


public class RecommendedPage {

    private int id, age;
    private String url;

    public RecommendedPage(){}

    public RecommendedPage(int id, String url, int age) {
        this.id = id;
        this.url = url;
        this.age = age;
    }

    public RecommendedPage(String url, int age) {
        this.url = url;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
