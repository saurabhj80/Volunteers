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

    private static final int TAG_CODE_PERMISSION_CALL = 1;

    // Do we have permission to make a phone
    public boolean isCanMakePhone(Context context) {
        return context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
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

    // Whether the permission was granted
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == TAG_CODE_PERMISSION_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }

        return false;
    }


}
