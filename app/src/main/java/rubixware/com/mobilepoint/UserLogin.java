package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity{

    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private String usernameField(){
        final EditText username = (EditText) findViewById(R.id.username);
        return username.getText().toString();
    }

    private String passwordField(){
        final EditText password = (EditText) findViewById(R.id.password);
        return password.getText().toString();
    }

    public void userLogin(View view){
        if (usernameField().isEmpty() || passwordField().isEmpty()){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show();
        }else{
            User checkUser = userQueries.showUser(usernameField(), dbHelper);
            if (checkUser==null){
                Toast.makeText(this, "Usario no encontrado", Toast.LENGTH_SHORT).show();
            }else{
                if(checkUser.getPassword().equals(passwordField())){
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserLogin.this, PointPage.class);
                    intent.putExtra("username", usernameField());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "Las contraseña está equivocada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
