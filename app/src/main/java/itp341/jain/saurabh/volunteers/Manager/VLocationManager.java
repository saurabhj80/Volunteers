package itp341.jain.saurabh.volunteers.Manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by saurabhj80 on 11/25/16.
 */

public class VLocationManager {

    // Singleton support
    private VLocationManager() {}
    private static VLocationManager manager = new VLocationManager();

    public static synchronized VLocationManager getInstance() {
        return manager;
    }

    // Must be called to request permission from the user
    public VLocationManager initialize(Activity activity)
    {
        // if we don't have coarse or fine location permission, then we request both of them
        PermissionManager permissionManager = PermissionManager.getInstance();
        if (!permissionManager.locationPermission(activity))
        {
            permissionManager.requestLocationPermission(activity);
        }
        return manager;
    }

    // The last known location
    private Location previousLocation = null;
    public Location getLastLocation() {
        return previousLocation;
    }

    // Fetch a new location
    public void getLocation(Activity activity, final LocationListener listener)
    {
        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        // initialize should be called by the user
        // and that should take care of the permission requirements
        try {
            // GPS provider is enabled, then request a one time location
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        previousLocation = location;
                        listener.onLocationChanged(location);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {
                        listener.onStatusChanged(s, i, bundle);
                    }

                    @Override
                    public void onProviderEnabled(String s) {
                        listener.onProviderEnabled(s);
                    }

                    @Override
                    public void onProviderDisabled(String s) {
                        listener.onProviderDisabled(s);
                    }
                }, null);
            }
        } catch (SecurityException e) {}
    }

}
