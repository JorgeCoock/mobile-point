package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class FormPage extends Activity{
    //nombre del archivo de informacion
    public String filename = "etc";
    //Objeto de la clase storage, llamado s1
    private Storage s1 ;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_page);
        //Creamos el nuevo objeto storage con esta actividad
        s1 = new Storage(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    //Sacamos el string del primer campo
    private String passwordField(){
        final EditText passwordField = (EditText) findViewById(R.id.Password);
        return passwordField.getText().toString();
    }

    //Sacamos el string del segundo campo
    private String checkPasswordField(){
        final EditText checkPasswordField = (EditText) findViewById(R.id.PasswordVerification);
        return checkPasswordField.getText().toString();
    }

    //Checamos los posibles errores de los campos
    private boolean checkBlankFields(String password, String checkPassword){
        if (password.equals("")|| checkPassword.equals("")){return true;}
        else{return false;}
    }

    //Checamos que los dos campos coincidan
    private boolean checkFields(String password, String checkPassword,boolean blankField) {
        if (blankField){
            Toast.makeText(this, "Llene todos los campos",Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.equals(checkPassword)&&!password.equals("")){
            s1.simplyStorage(filename,password);
            Toast.makeText(this, "Constraseña guardada:  "+password,Toast.LENGTH_SHORT).show();
            //cambiamos de actividad
            Intent launchAct = new Intent(this,WelcomePage.class);
            startActivity(launchAct);
            finish();
            return false;
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden, escríbalas nuevamente.",Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public void sendPassword(View button){
        String password = passwordField();
        String checkPassword = checkPasswordField();
        boolean blankFields = checkBlankFields(password, checkPassword);
        boolean checkFields = checkFields(password,checkPassword,blankFields);

    }
}
