package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecommendedPagesForm extends Activity {

    Intent intent;
    String username;
    User user;
    TextView title;
    String textViewTitle;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();
    RecommendedPageQueries recommendedPageQueries = new RecommendedPageQueries();
    public ArrayAdapter<String> pagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommended_pages_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);
        title = (TextView) findViewById(R.id.title);
        title.setText(title(user));
        setPagesOnListView();

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

    private void setPagesOnListView(){
        pagesAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, android.R.id.text1, recommendedPageQueries.getRecommendedPagesUrls(user.getAge(), dbHelper));
        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(pagesAdapter);
    }


}
