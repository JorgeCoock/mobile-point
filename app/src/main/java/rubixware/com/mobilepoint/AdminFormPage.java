package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminFormPage extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_form_page);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    //Get username field
    private String usernameField(){
        final EditText usernameField = (EditText) findViewById(R.id.username);
        return usernameField.getText().toString();
    }

    //Sacamos el string del primer campo
    private String passwordField(){
        final EditText passwordField = (EditText) findViewById(R.id.Password);
        return passwordField.getText().toString();
    }

    //Sacamos el string del segundo campo
    private String passwordConfirmationField(){
        final EditText checkPasswordField = (EditText) findViewById(R.id.PasswordVerification);
        return checkPasswordField.getText().toString();
    }


    public void saveAdmin(View button){
        formValidations(usernameField(), passwordField(), passwordConfirmationField());
    }

    private void formValidations(String username, String password, String passwordConfirmation){
        if ( username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if (password.equals(passwordConfirmation)){
                newAdmin(username, password);
                startActivity(new Intent(AdminFormPage.this, PointPage.class));
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void newAdmin(String username, String password){
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        AdminQueries adminQueries = new AdminQueries();
        Admin admin = new Admin(username, password);
        adminQueries.createAdmin(admin, dbHelper);
        Toast.makeText(this, "Administrador creado!", Toast.LENGTH_SHORT).show();
    }

}
