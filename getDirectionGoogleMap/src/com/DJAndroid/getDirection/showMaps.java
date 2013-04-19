package com.DJAndroid.getDirection;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class showMaps extends AbstractMapActivity {

	private GoogleMap map;

	private String selectedFrom_lat;

	private String selectedFrom_long;

	private String selectedTo_lat;

	private String selectedTo_long;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (readyToGo()) {
			setContentView(R.layout.showmaps);

			selectedFrom_lat = getIntent().getStringExtra("spFrom_lat");
			selectedFrom_long = getIntent().getStringExtra("spFrom_long");
			selectedTo_lat = getIntent().getStringExtra("spTo_lat");
			selectedTo_long = getIntent().getStringExtra("spTo_long");

			double from_lat = Double.parseDouble(selectedFrom_lat);
			double from_long = Double.parseDouble(selectedFrom_long);

			double To_lat = Double.parseDouble(selectedTo_lat);
			double To_long = Double.parseDouble(selectedTo_long);

			SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);

			map = mapFrag.getMap();

			if (savedInstanceState == null) {
				CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
						from_lat, from_long));
				CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

				map.moveCamera(center);
				map.animateCamera(zoom);
			}

			addMarker(map, from_lat, from_long, "Start", "start from here!");
			addMarker(map, To_lat, To_long, "End", "end from here!");

			LatLng fromPosition = new LatLng(from_lat, from_long);
			LatLng toPosition = new LatLng(To_lat, To_long);

			GMapV2Direction md = new GMapV2Direction();

			Document doc = md.getDocument(fromPosition, toPosition,
					GMapV2Direction.MODE_DRIVING);
			ArrayList<LatLng> directionPoint = md.getDirection(doc);
			PolylineOptions rectLine = new PolylineOptions().width(3).color(
					Color.RED);

			for (int i = 0; i < directionPoint.size(); i++) {
				rectLine.add(directionPoint.get(i));
			}

			map.addPolyline(rectLine);
		}

	}

	private void addMarker(GoogleMap map, double lat, double lon,
			String string, String string2) {
		map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
				.title(string).snippet(string2));
	}

}
