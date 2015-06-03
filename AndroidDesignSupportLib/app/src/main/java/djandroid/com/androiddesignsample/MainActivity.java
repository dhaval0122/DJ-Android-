package djandroid.com.androiddesignsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    View root;
    NavigationView nav_draw;
    DrawerLayout drawer_layout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        root = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(root);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getResources().getString(R.string.app_name));

        ((TextView) findViewById(R.id.textView)).setText(Html.fromHtml(getResources().getString(R.string.intro)));

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_draw = (NavigationView) findViewById(R.id.nav_draw);
        nav_draw.setNavigationItemSelectedListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab_action);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure?", Snackbar.LENGTH_SHORT)
                        .setAction("Share", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String shareBody = "Android Design Support Sample, https://github.com/dhaval0122";
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Android Design Support Sample");
                                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));
                                Log.e("Test", "Test");
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.navigation_item_1) {
            Snackbar
                    .make(fab, "First item Selected", Snackbar.LENGTH_LONG)
                            //.setAction(R.string.snackbar_action, myOnClickListener)
                    .show();
        } else if (menuItem.getItemId() == R.id.navigation_item_2) {
            //startActivity(new Intent(MainActivity.this, DetailActivity.class));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(this, DetailActivity.class);
                startActivity(intent,
                        ActivityOptionsCompat
                                .makeSceneTransitionAnimation(this).toBundle());
            } else {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        } else if (menuItem.getItemId() == R.id.navigation_item_3) {
            String url = "http://www.dj-android.blogspot.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (menuItem.getItemId() == R.id.navigation_item_4) {
            /*Snackbar
                    .make(root, "", Snackbar.LENGTH_LONG)
                            //.setAction(R.string.snackbar_action, myOnClickListener)
                    .setActionTextColor(getResources().getColor(R.color.colorRed))
                    .show();*/
        }
        menuItem.setChecked(true);
        drawer_layout.closeDrawers();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //if (!drawer_layout.isShown()) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //restoreActionBar();
        return true;
        //}
        //return super.onCreateOptionsMenu(menu);
    }
}
