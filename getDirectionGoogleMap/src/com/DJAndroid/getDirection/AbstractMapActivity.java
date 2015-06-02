package com.DJAndroid.getDirection;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class AbstractMapActivity extends SherlockFragmentActivity {
	protected static final String TAG_ERROR_DIALOG_FRAGMENT = "errorDialog";

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	protected boolean readyToGo() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

		if (status == ConnectionResult.SUCCESS) {
			return (true);
		} else if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
			ErrorDialogFragment.newInstance(status).show(
					getSupportFragmentManager(), TAG_ERROR_DIALOG_FRAGMENT);
		} else {
			Toast.makeText(this, "No Maps, Please try again", Toast.LENGTH_LONG)
					.show();
			finish();
		}

		return (false);
	}

	public static class ErrorDialogFragment extends DialogFragment {
		static final String ARG_STATUS = "status";

		static ErrorDialogFragment newInstance(int status) {
			Bundle args = new Bundle();

			args.putInt(ARG_STATUS, status);

			ErrorDialogFragment result = new ErrorDialogFragment();

			result.setArguments(args);

			return (result);
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Bundle args = getArguments();

			return GooglePlayServicesUtil.getErrorDialog(
					args.getInt(ARG_STATUS), getActivity(), 0);
		}

		@Override
		public void onDismiss(DialogInterface dlg) {
			if (getActivity() != null) {
				getActivity().finish();
			}
		}
	}
}

// 04-12 18:23:38.147: E/AndroidRuntime(1170): java.lang.NoClassDefFoundError:
// com.google.android.gms.R$styleable
// 04-12 18:23:38.147: E/AndroidRuntime(1170): at
// com.google.android.gms.maps.GoogleMapOptions.createFromAttributes(Unknown
// Source)
