package djandroid.com.androiddesignsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dhaval sodha parmar on 6/2/2015.
 */
public class DetailActivity extends AppCompatActivity {

    String[] items = {"Stack", "Google+", "Git hub"};
    private ViewPager mViewPager;
    TabLayout tabLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);


        mViewPager.setAdapter(new SamplePagerAdapter());

        tabLayout.setupWithViewPager(mViewPager);

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

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link }.
     */
    class SamplePagerAdapter extends PagerAdapter {
        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return items.length;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(android.view.ViewGroup, int)} is the
         * same object as the {@link android.view.View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }

        /**
         * Instantiate the {@link android.view.View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
// Inflate a new layout from our resources
            View view = getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
// Add the newly created View to the ViewPager
            container.addView(view);
// Retrieve a TextView from the inflated View, and update it's text
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(items[position]));
            //Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
// Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link android.view.View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            // Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
        }
    }
}
