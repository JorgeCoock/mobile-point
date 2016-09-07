package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jorge on 28/08/16.
 */
public class LoginOption extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_option);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    public void goToUserLogin(View button){
        Toast.makeText(this, "Go to user!", Toast.LENGTH_SHORT).show();
    }

    public void gotToAdminLogin(View button){
        startActivity(new Intent(LoginOption.this, AdminLogin.class));
    }

    private void setFaIconsToButtons(Typeface font){
        Button userLogin = (Button)findViewById( R.id.userLogin);
        Button adminLogin = (Button) findViewById(R.id.adminLogin);
        userLogin.setTypeface(font);
        adminLogin.setTypeface(font);
    }
}
