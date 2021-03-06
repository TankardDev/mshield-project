// This file was generated by PermissionsDispatcher. Do not modify!
package com.cuisec.mshield;

import android.support.v4.app.ActivityCompat;


import com.cuisec.mshield.activity.home.HomeActivity;
import com.cuisec.mshield.activity.home.RealHomeActivity;

import java.lang.ref.WeakReference;

import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public final class MainActivityPermissionsDispatcher {
  private static final int REQUEST_NEEDSTORAGE = 0;

  private static final String[] PERMISSION_NEEDSTORAGE = new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"};

  private MainActivityPermissionsDispatcher() {
  }

  public static void needStorageWithPermissionCheck(RealHomeActivity target) {
    if (PermissionUtils.hasSelfPermissions(target, PERMISSION_NEEDSTORAGE)) {
      //target.needStorage();
    } else {
      if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_NEEDSTORAGE)) {
        target.showRational(new MainActivityNeedStoragePermissionRequest(target));
      } else {
        ActivityCompat.requestPermissions(target, PERMISSION_NEEDSTORAGE, REQUEST_NEEDSTORAGE);
      }
    }
  }

  public static void onRequestPermissionsResult(RealHomeActivity target, int requestCode, int[] grantResults) {
    switch (requestCode) {
      case REQUEST_NEEDSTORAGE:
      if (PermissionUtils.verifyPermissions(grantResults)) {
        //target.needStorage();
      } else {
        target.deniedStorage();
      }
      break;
      default:
      break;
    }
  }

  public static final class MainActivityNeedStoragePermissionRequest implements PermissionRequest {
    private final WeakReference<RealHomeActivity> weakTarget;

    private MainActivityNeedStoragePermissionRequest(RealHomeActivity target) {
      this.weakTarget = new WeakReference<RealHomeActivity>(target);
    }

    @Override
    public void proceed() {
      RealHomeActivity target = weakTarget.get();
      if (target == null) return;
      ActivityCompat.requestPermissions(target, PERMISSION_NEEDSTORAGE, REQUEST_NEEDSTORAGE);
    }

    @Override
    public void cancel() {
      RealHomeActivity target = weakTarget.get();
      if (target == null) return;
      target.deniedStorage();
    }
  }
}
