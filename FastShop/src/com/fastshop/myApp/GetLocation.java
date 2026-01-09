
/*import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends Activity {

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Get location (latitude and longitude)
        Location location = getLocation(); // Implement getLocation() method to get the location
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            // Check network connection before using Geocoder
            if (isNetworkAvailable()) {
                getAddressFromLocation(latitude, longitude);
            } else {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Location getLocation() {
        // Implement this method to get the location (latitude and longitude)
        // For example, using FusedLocationProviderClient or LocationManager
        // Return the location object or null if location is not available
        return null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getAddressFromLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String country = address.getCountryName();
                String state = address.getAdminArea();
                String city = address.getLocality();
                String district = address.getSubAdminArea();
                String addressLine = address.getAddressLine(0);

                Log.d("Location", "Country: " + country);
                Log.d("Location", "State: " + state);
                Log.d("Location", "City: " + city);
                Log.d("Location", "District: " + district);
                Log.d("Location", "Address: " + addressLine);

                // Use the address details as needed
            }
        } catch (IOException e) {
            Log.e("Location", "Error getting address", e);
        }
    }
}
*/