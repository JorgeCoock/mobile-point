package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        setFaIconsToButtons(font);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button user_option_button = (Button)findViewById( R.id.user_option_button);
        Button admin_option_button = (Button)findViewById(R.id.admin_option_button);
        Button goBack = (Button) findViewById(R.id.goBack);
        user_option_button.setTypeface(font);
        admin_option_button.setTypeface(font);
        goBack.setTypeface(font);
    }

    public void startPanelUsers(View button){
    }


    public void startPanelAdmins(View button){
        startActivity(new Intent(AdminPanel.this, PanelAdmins.class));
    }

    public void goBack(View view){
        startActivity(new Intent(AdminPanel.this, LoginOption.class));
        finish();
    }

}
