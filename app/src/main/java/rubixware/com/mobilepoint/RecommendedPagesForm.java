package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class RecommendedPagesForm extends Activity {

    Intent intent;
    String username;
    User user;
    TextView title;
    String textViewTitle;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommended_pages_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);
        title = (TextView) findViewById(R.id.title);
        title.setText(title(user));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private String title(User user){
        String ageOption;
        if(user.getAge() == 1){
            ageOption = "niños";
        }else if(user.getAge() == 2){
            ageOption = "adolescentes";
        }else{
            ageOption = "adultos";
        }
        return "Páginas para "+ageOption;
    }


}
