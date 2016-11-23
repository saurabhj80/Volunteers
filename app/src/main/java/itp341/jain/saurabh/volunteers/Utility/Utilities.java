package itp341.jain.saurabh.volunteers.Utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by saurabhj80 on 11/22/16.
 */
public class Utilities {

    // Displays a toast
    static public void DisplayToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
