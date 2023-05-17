@Override
public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    // Set the marker color to green
    BitmapDescriptor greenMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

    // Set the initial camera position to JUET, Guna
    LatLng juetGuna = new LatLng(24.6463, 77.3134);
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(juetGuna, 15f));

    // Add a marker for JUET, Guna
    mMap.addMarker(new MarkerOptions()
            .position(juetGuna)
            .title("JUET, Guna")
            .icon(greenMarker));

    // Request location updates
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    } else {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}
