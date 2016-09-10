package com.apogiatzis.cygazetteapp.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by andre on 26/08/2016.
 */
public class PermissionsManager {

    public static final int STORAGE_PERMISSION_CODE = 200;

    private Activity activity;

    public interface OnStoragePermissionListener {

        void OnStoragePermissionGranted();

    }

    public PermissionsManager(Activity activity) {
        this.activity = activity;
    }

    public static boolean IsPermissionRequestNeeded(){
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestStoragePermission(Activity activity){
        String[] perms = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        activity.requestPermissions(perms, STORAGE_PERMISSION_CODE);
    }
}
