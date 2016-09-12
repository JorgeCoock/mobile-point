package rubixware.com.mobilepoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PanelUsersOption extends Activity{

    String username;
    Intent intent;
    TextView editUserTitle;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_users_option);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        intent = getIntent();
        username = intent.getStringExtra("username");
        editUserTitle = (TextView) findViewById(R.id.edit_user_title);
        editUserTitle.setText(username);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button editUser = (Button)findViewById( R.id.edit_user);
        Button editPages = (Button) findViewById(R.id.edit_pages);
        Button goBack = (Button) findViewById(R.id.goBack);
        Button deleteUser = (Button) findViewById(R.id.delete_user);
        editUser.setTypeface(font);
        editPages.setTypeface(font);
        goBack.setTypeface(font);
        deleteUser.setTypeface(font);
    }

    public void editUser(View view){
        Intent intent = new Intent(PanelUsersOption.this, UserFormPageUpdate.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void deleteUser(View view){
        final AlertDialog.Builder userDelete = new AlertDialog.Builder(this);
        userDelete.setTitle("Eliminar Usuario")
                .setMessage("Seguro que desea eliminar al usuario: "+username+" ?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDelete(username);
                    }
                });
        userDelete.create();
        userDelete.show();
    }

    public void startPagesOption(View view){
        Intent intent = new Intent(PanelUsersOption.this, PagesOption.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void userDelete(String username){
        if (userQueries.deleteUser(username, dbHelper)){
            Toast.makeText(this, "Se eliminó al usuario", Toast.LENGTH_LONG).show();
            startActivity(new Intent(PanelUsersOption.this, PanelUsers.class));
            finish();
        }else{
            Toast.makeText(this, "No se pudo eliminar al usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(PanelUsersOption.this, PanelUsers.class);
        startActivity(intent);
        finish();
    }

}
