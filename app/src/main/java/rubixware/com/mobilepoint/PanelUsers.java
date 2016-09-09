package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class PanelUsers  extends Activity{

    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();
    public ArrayAdapter<String> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_users);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        ArrayList<String> users = getUsersUsernamesArrayList();
        setUsernamesOnListView(users);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button create_user_button = (Button)findViewById( R.id.create_user);
        Button goBack = (Button) findViewById(R.id.go_back);
        create_user_button.setTypeface(font);
        goBack.setTypeface(font);
    }

    public void createUser(View view){
        startActivity(new Intent(PanelUsers.this, UserFormPage.class));
    }

    public void goBack(View view){
        startActivity(new Intent(PanelUsers.this, AdminPanel.class));
        finish();
    }

    private ArrayList<String> getUsersUsernamesArrayList(){
        ArrayList<User> usernamesCollection = userQueries.getUserUsernames(dbHelper);
        ArrayList<String> usernamesArray = new ArrayList<>();
        for (int i =0; i < usernamesCollection.size(); i++){
            usernamesArray.add(usernamesCollection.get(i).getUsername());
        }
        return usernamesArray;
    }

    private void setUsernamesOnListView(final ArrayList<String> users){
        userAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, android.R.id.text1, users);
        final ListView listView = (ListView) findViewById(R.id.users_collection);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = (String) listView.getItemAtPosition(position);
                Intent intent = new Intent(PanelUsers.this, PanelUsersOption.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

}
