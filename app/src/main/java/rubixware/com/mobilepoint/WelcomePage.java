package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePage extends Activity {

    DataBaseHelper mDatabaseHelper = new DataBaseHelper(this, null, null, 1);
    AdminQueries adminQueries = new AdminQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        ArrayList<Admin> admin_list =  adminQueries.getAdminIndex(mDatabaseHelper);
        launchNextActivity(admin_list.isEmpty());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    public void launchNextActivity(final boolean option){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (option) {
                    createRecommendedPages();
                    Intent intent = new Intent(WelcomePage.this, AdminFormPage.class);
                    intent.putExtra("where", "WelcomePage");
                    startActivity(intent);
                } else {
                    startActivity(new Intent(WelcomePage.this, LoginOption.class));
                }
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onResume(){
        ArrayList<Admin> admin_list =  adminQueries.getAdminIndex(mDatabaseHelper);
        launchNextActivity(admin_list.isEmpty());
        super.onResume();
    }

    private void createRecommendedPages(){
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        RecommendedPageQueries recommendedPageQueries = new RecommendedPageQueries();
        ArrayList<RecommendedPage> array = new ArrayList<>();

        RecommendedPage recommendedPage1 = new RecommendedPage("http://disneylatino.com/", 1);
        array.add(recommendedPage1);
        RecommendedPage recommendedPage2 = new RecommendedPage("http://spaceplace.nasa.gov/sp/", 1);
        array.add(recommendedPage2);
        RecommendedPage recommendedPage3 = new RecommendedPage("http://www.cartoonnetwork.com.mx/", 1);
        array.add(recommendedPage3);
        RecommendedPage recommendedPage4 = new RecommendedPage("http://mx.mundonick.com/", 1);
        array.add(recommendedPage4);
        RecommendedPage recommendedPage5 = new RecommendedPage("http://www.mundoprimaria.com/juegos-de-ingles/juegos-para-aprender-ingles-1o-y-2o-primaria/", 1);
        array.add(recommendedPage5);
        RecommendedPage recommendedPage6 = new RecommendedPage("http://www.juegos.com/juegos/ninos", 1);
        array.add(recommendedPage6);
        RecommendedPage recommendedPage7 = new RecommendedPage("http://www.nintendo.com/es_LA/", 1);
        array.add(recommendedPage7);
        RecommendedPage recommendedPage8 = new RecommendedPage("http://www.pelispekes.com/", 1);
        array.add(recommendedPage8);
        RecommendedPage recommendedPage9 = new RecommendedPage("http://www.pokemon.com/us/", 1);
        array.add(recommendedPage9);
        RecommendedPage recommendedPage10 = new RecommendedPage("http://www.dblatino.com/", 1);
        array.add(recommendedPage10);

        RecommendedPage recommendedPage11 = new RecommendedPage("https://es.wikipedia.org/", 2);
        array.add(recommendedPage11);
        RecommendedPage recommendedPage12 = new RecommendedPage("https://twitter.com/?lang=es", 2);
        array.add(recommendedPage12);
        RecommendedPage recommendedPage13 = new RecommendedPage("https://www.easports.com/mx/fifa", 2);
        array.add(recommendedPage13);
        RecommendedPage recommendedPage14 = new RecommendedPage("https://www.xbox.com/es-MX/xbox-one", 2);
        array.add(recommendedPage14);
        RecommendedPage recommendedPage15 = new RecommendedPage("https://www.gamers.vg/tag/tienda-en-linea/", 2);
        array.add(recommendedPage15);
        RecommendedPage recommendedPage16 = new RecommendedPage("https://www.youtube.com/", 2);
        array.add(recommendedPage16);
        RecommendedPage recommendedPage17 = new RecommendedPage("http://www.facebook.com ", 2);
        array.add(recommendedPage17);
        RecommendedPage recommendedPage18 = new RecommendedPage("http://www.instagram.com", 2);
        array.add(recommendedPage18);
        RecommendedPage recommendedPage19 = new RecommendedPage("http://www.tumblr.com", 2);
        array.add(recommendedPage19);
        RecommendedPage recommendedPage20 = new RecommendedPage("http://www.hipertextual.com", 2);
        array.add(recommendedPage20);

        RecommendedPage recommendedPage21 = new RecommendedPage("http://www.amazon.com.mx", 3);
        array.add(recommendedPage21);
        RecommendedPage recommendedPage22 = new RecommendedPage("http://www.ebay.com", 3);
        array.add(recommendedPage22);
        RecommendedPage recommendedPage23 = new RecommendedPage("http://www.elnorte.com", 3);
        array.add(recommendedPage23);
        RecommendedPage recommendedPage24 = new RecommendedPage("http://www.publimetro.com.mx", 3);
        array.add(recommendedPage24);
        RecommendedPage recommendedPage25 = new RecommendedPage("http://www.meteored.mx", 3);
        array.add(recommendedPage25);
        RecommendedPage recommendedPage26 = new RecommendedPage("http://www.20minutos.com.mx", 3);
        array.add(recommendedPage26);
        RecommendedPage recommendedPage27 = new RecommendedPage("http://www.espn.com.mx", 3);
        array.add(recommendedPage27);
        RecommendedPage recommendedPage28 = new RecommendedPage("http://www.espanol.answers.yahoo.com", 3);
        array.add(recommendedPage28);
        RecommendedPage recommendedPage29 = new RecommendedPage("http://www.cnn.com", 3);
        array.add(recommendedPage29);
        RecommendedPage recommendedPage30 = new RecommendedPage("http://www.preciodolar.com", 3);
        array.add(recommendedPage30);

        for (int i =0; i < array.size(); i++){
            recommendedPageQueries.createRecommendedPage(array.get(i), dbHelper);
        }


    }



}
