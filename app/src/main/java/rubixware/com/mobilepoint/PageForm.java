package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PageForm  extends Activity{

    Intent intent;
    String username;
    User user;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    PageQueries pageQueries = new PageQueries();
    UserQueries userQueries = new UserQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setFaIconsToButtons(Typeface font){
        Button goBack = (Button) findViewById(R.id.go_back);
        goBack.setTypeface(font);
    }

    public void goBack(View view){
        Intent intent = new Intent(PageForm.this, PagesOption.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private String getUrlTextField(){
        EditText urlText = (EditText) findViewById(R.id.url);
        String url = urlText.getText().toString();
        return url.trim().replaceAll("\\s+", "");
    }


    public void createPage(View view){
        if (getUrlTextField().isEmpty()){
            Toast.makeText(this, "Introduzca una dirección", Toast.LENGTH_SHORT).show();
        }else{
            Page checkPage = pageQueries.showPage(getUrlTextField(), user.getId(), dbHelper);
            if (checkPage == null){
                Page newPage = new Page(user.getId(), getUrlTextField());
                pageQueries.createPage(newPage, dbHelper);
                Toast.makeText(this, "Página guardada!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El usuario ya tiene esa página", Toast.LENGTH_LONG).show();
            }
        }
    }




}
