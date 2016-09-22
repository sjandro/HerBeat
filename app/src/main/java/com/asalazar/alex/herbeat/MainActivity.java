package com.asalazar.alex.herbeat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23)
            getPermissionToWriteStorage();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv); // layout reference

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        //rv.addItemDecoration(new SimpleDividerItemDecoration(this));
        rv.setHasFixedSize(true); // to improve performance

        rv.setAdapter(new HomeTabs()); // the data manager is assigner to the RV

        rv.addOnItemTouchListener(new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, StartActivity.class);

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.startIcon), getString(R.string.transition_name_start)),
                                new Pair<View, String>(view.findViewById(R.id.card), getString(R.string.transition_name_layout)),
                                new Pair<View, String>(view.findViewById(R.id.startTV), getString(R.string.transition_name_communication))
                        );
                        ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                        break;
                    case 1:
                        Intent intentEd = new Intent(MainActivity.this, EducationActivity.class);

                        ActivityOptionsCompat optionsEd = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.startIcon), getString(R.string.transition_name_start)),
                                new Pair<View, String>(view.findViewById(R.id.card), getString(R.string.transition_name_layout)),
                                new Pair<View, String>(view.findViewById(R.id.startTV), getString(R.string.transition_name_communication))
                        );
                        ActivityCompat.startActivity(MainActivity.this, intentEd, optionsEd.toBundle());
                        break;
                    case 2:
                        Intent intentHD = new Intent(MainActivity.this, HealthDataActivity.class);

                        ActivityOptionsCompat optionsHD = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.startIcon), getString(R.string.transition_name_start)),
                                new Pair<View, String>(view.findViewById(R.id.card), getString(R.string.transition_name_layout)),
                                new Pair<View, String>(view.findViewById(R.id.startTV), getString(R.string.transition_name_communication))
                        );
                        ActivityCompat.startActivity(MainActivity.this, intentHD, optionsHD.toBundle());

                        break;
                    case 3:
                        Intent intentHC = new Intent(MainActivity.this, HealthCoachActivity.class);

                        ActivityOptionsCompat optionsHC = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.startIcon), getString(R.string.transition_name_start)),
                                new Pair<View, String>(view.findViewById(R.id.card), getString(R.string.transition_name_layout)),
                                new Pair<View, String>(view.findViewById(R.id.startTV), getString(R.string.transition_name_communication))
                        );
                        ActivityCompat.startActivity(MainActivity.this, intentHC, optionsHC.toBundle());
                        break;
                    case 4:
                        Intent intentC = new Intent(MainActivity.this, CommunicationActivity.class);

                        ActivityOptionsCompat optionsC = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                MainActivity.this,
                                new Pair<View, String>(view.findViewById(R.id.startIcon), getString(R.string.transition_name_start)),
                                new Pair<View, String>(view.findViewById(R.id.card), getString(R.string.transition_name_layout)),
                                new Pair<View, String>(view.findViewById(R.id.startTV), getString(R.string.transition_name_communication))
                        );
                        ActivityCompat.startActivity(MainActivity.this, intentC, optionsC.toBundle());
                        break;
                    default:
                        break;

                }
            }
        }));
    }

    private static final int WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermissionToWriteStorage() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
//        UTILITIES.writeSnoozeColor("#7e7e7e");
//        UTILITIES.writeDismissColor("#7e7e7e");
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Storage write permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Storage write permission denied", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
