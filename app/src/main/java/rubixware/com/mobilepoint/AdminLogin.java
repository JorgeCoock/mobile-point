package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void adminLogin(View button) {
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        AdminQueries adminQuery = new AdminQueries();
        Admin admin = adminQuery.showAdmin(usernameField(), dbHelper);
        String password = passwordField();
        boolean fieldsFill = fieldsFill(usernameField(), password);
        if (fieldsFill){
            tryLogin(admin, password);
        }
    }

    private void tryLogin(Admin admin, String password){
        if (admin == null){
            Toast.makeText(this, "Administrador no encontrado", Toast.LENGTH_SHORT).show();
        }else{
            login(password, admin);
        }
    }

    private void login(String password, Admin admin){
        if (password.equals(admin.getPassword())){
            Toast.makeText(this, "Pase usted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Las contraseña está equivocada", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean fieldsFill(String usernameField, String passwordField){
        if (usernameField().isEmpty() || passwordField().isEmpty()){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    private String usernameField(){
        final EditText username = (EditText) findViewById(R.id.username);
        return username.getText().toString();
    }

    private String passwordField(){
        final EditText password = (EditText) findViewById(R.id.password);
        return password.getText().toString();
    }

}
