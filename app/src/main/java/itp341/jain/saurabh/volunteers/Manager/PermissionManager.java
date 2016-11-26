package itp341.jain.saurabh.volunteers.Manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

/**
 * Created by saurabhj80 on 11/26/16.
 */
public class PermissionManager {

    // Singleton support
    private PermissionManager() {}
    private static PermissionManager manager = new PermissionManager();

    public static synchronized PermissionManager getInstance() {
        return manager;
    }

    // Permission request codes
    private static final int TAG_CODE_PERMISSION_LOCATION = 0;
    private static final int TAG_CODE_PERMISSION_CALL = 1;

    /******** LOCATION ********/

    public boolean locationPermission(Activity activity) {
        return  (checkPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                && checkPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION));
    }

    public int requestLocationPermission(Activity activity) {
        activity.requestPermissions(
                new String[] {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                TAG_CODE_PERMISSION_LOCATION);
        return TAG_CODE_PERMISSION_LOCATION;
    }

    /******** PHONE **********/

    // Do we have permission to make a phone
    public boolean isCanMakePhone(Context context) {
        return checkPermission(context, Manifest.permission.CALL_PHONE);
    }

    // Request permission to make a call
    public int requestPhonePermission(Activity activity) {
        activity.requestPermissions(
                new String[] {
                        Manifest.permission.CALL_PHONE
                },
                TAG_CODE_PERMISSION_CALL);
        return TAG_CODE_PERMISSION_CALL;
    }

    /****** HELPER *********/

    private boolean checkPermission(Context context, String permission) {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    // Whether the permission was granted
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == TAG_CODE_PERMISSION_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (permissions[0] == Manifest.permission.CALL_PHONE) {
                    return true;
                }
            }
        } else if (requestCode == TAG_CODE_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }

        return false;
    }


}
