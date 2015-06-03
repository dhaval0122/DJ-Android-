package djandroid.com.androiddesignsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dhaval sodha parmar on 6/2/2015.
 */
public class AboutPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment, container, false);

        //Toolbar toolbar = (Toolbar)  rootView.findViewById(R.id.toolbar);
        //setTitle("dj-android");

        /*ListView list = (ListView) rootView.findViewById(R.id.listView);
        ListViewAdapter listAdapter = new ListViewAdapter(getActivity().getApplicationContext(),
                getResources().getStringArray(R.array.android_version));
        list.setAdapter(listAdapter);*/

        return  rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
