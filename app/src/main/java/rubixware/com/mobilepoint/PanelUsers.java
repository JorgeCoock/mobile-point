package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;


public class PanelUsers  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_users);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
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

}
