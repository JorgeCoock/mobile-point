package rubixware.com.mobilepoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PanelAdmins extends Activity {

    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    AdminQueries adminQueries = new AdminQueries();
    public ArrayAdapter<String> adminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_admins);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        ArrayList<String> admins = getAdminsUsernamesArrayList();
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

    public ArrayList<String> getAdminsUsernamesArrayList(){
        ArrayList<Admin> adminUsernamesResponse = adminQueries.getAdminUsernames(dbHelper);
        ArrayList<String> adminsArray = new ArrayList<>();
        for (int i = 0; i < adminUsernamesResponse.size(); i++){
            adminsArray.add(adminUsernamesResponse.get(i).getUsername());
        }
        return adminsArray;
    }

    private void setAdminsOnListView(final ArrayList<String> admins){
        adminAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, android.R.id.text1, admins);
        final ListView listView = (ListView) findViewById(R.id.admin_collection);
        listView.setAdapter(adminAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String adminUsername = (String) listView.getItemAtPosition(position);
                deleteAdminAlert(adminUsername, position);
            }
        });
    }

    private void deleteAdminAlert(final String adminUsername, final Integer position){
        AlertDialog.Builder adminDelete = new AlertDialog.Builder(this);
        adminDelete.setTitle("Eliminar Administrador")
            .setMessage("Seguro que desea eliminar al administrador: "+adminUsername+" ?")
            .setCancelable(false)
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteAdmin(adminUsername, position);
                }
            });
        adminDelete.create();
        adminDelete.show();
    }

    private void deleteAdmin(String adminUsername, Integer position){
        if(adminQueries.deleteAdmin(adminUsername, dbHelper)){
            Toast.makeText(this, "Se ha eliminado al admin: "+adminUsername, Toast.LENGTH_LONG).show();
            adminAdapter.remove(adminAdapter.getItem(position));
            adminAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "NO se pudo eliminar al admin", Toast.LENGTH_LONG).show();
        }
    }
}
