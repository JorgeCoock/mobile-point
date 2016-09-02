package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PanelAdmins extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_admins);
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        setFaIconsToButtons(font);
        ArrayList<Admin> admins = getAdmins();
        setAdminsOnListView(admins);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button create_admin_button = (Button)findViewById( R.id.create_admin_button);
        create_admin_button.setTypeface(font);
    }

    public void createAdmin(View view){
        startActivity(new Intent(PanelAdmins.this, AdminFormPage.class));
    }

    public ArrayList<Admin> getAdmins(){
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        AdminQueries adminQueries = new AdminQueries();
        return adminQueries.getAdminIndex(dbHelper);
       // Log.e("ADMINS", " "+admin_list.get(0).getUsername());
    }

    private void setAdminsOnListView(ArrayList<Admin> admins){
        ArrayAdapter<Admin> adminAdapter = new ArrayAdapter<Admin>(this,
                +android.R.layout.simple_list_item_1, android.R.id.text1, admins);
        ListView listView = (ListView) findViewById(R.id.admin_collection);
        listView.setAdapter(adminAdapter);
    }
}
