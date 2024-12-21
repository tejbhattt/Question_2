package com.example.notificationmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private Switch switchSound, switchVibration, switchLED, switchBanners, switchContent, switchLockScreen, switchDoNotDisturb;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize switches
        switchSound = findViewById(R.id.switchSound);
        switchVibration = findViewById(R.id.switchVibration);
        switchLED = findViewById(R.id.switchLED);
        switchBanners = findViewById(R.id.switchBanners);
        switchContent = findViewById(R.id.switchContent);
        switchLockScreen = findViewById(R.id.switchLockScreen);
        switchDoNotDisturb = findViewById(R.id.switchDoNotDisturb);

        // Load saved preferences
        loadPreferences();

        findViewById(R.id.btnSave).setOnClickListener(view -> showConfirmationDialog());
    }

    private void loadPreferences() {
        switchSound.setChecked(sharedPreferences.getBoolean("Sound", false));
        switchVibration.setChecked(sharedPreferences.getBoolean("Vibration", false));
        switchLED.setChecked(sharedPreferences.getBoolean("LED", false));
        switchBanners.setChecked(sharedPreferences.getBoolean("Banners", false));
        switchContent.setChecked(sharedPreferences.getBoolean("Content", false));
        switchLockScreen.setChecked(sharedPreferences.getBoolean("LockScreen", false));
        switchDoNotDisturb.setChecked(sharedPreferences.getBoolean("DoNotDisturb", false));
    }

    private void savePreferences() {
        editor.putBoolean("Sound", switchSound.isChecked());
        editor.putBoolean("Vibration", switchVibration.isChecked());
        editor.putBoolean("LED", switchLED.isChecked());
        editor.putBoolean("Banners", switchBanners.isChecked());
        editor.putBoolean("Content", switchContent.isChecked());
        editor.putBoolean("LockScreen", switchLockScreen.isChecked());
        editor.putBoolean("DoNotDisturb", switchDoNotDisturb.isChecked());
        editor.apply();
        Toast.makeText(this, "Preferences saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_confirmation);
        bottomSheetDialog.findViewById(R.id.btnConfirm).setOnClickListener(view -> {
            savePreferences();
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.findViewById(R.id.btnCancel).setOnClickListener(view -> bottomSheetDialog.dismiss());
        bottomSheetDialog.show();
    }
}