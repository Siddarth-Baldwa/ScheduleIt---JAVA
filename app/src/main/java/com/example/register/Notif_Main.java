package com.example.register;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class Notif_Main extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Exampleadapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Exampleitem> mexamplelist, mreminderlist;
    private TextView tv, tv2;
    private final String Channel_ID = "My_Channel";
    private final int Notification_ID = 001;
    ProgressDialog progressDialog;
    boolean doubleBackToExitPressedOnce = false;
    DatabaseReference db, dbb;
    private LinearLayout no_task_ll;
    public static int now = 0, firstrun = 0;
/*    Intent intentReceived = getIntent();
    Bundle data = intentReceived.getExtras();
    String  workvalue1 = data.getString("work");*/
    String workvalue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif_activity_main);
        setTitle("  ");
        settvcolor();
        workvalue1 = getIntent().getStringExtra("work");
        Log.i("PARAMS","passed value is " + workvalue1);
        mexamplelist = new ArrayList<>();
        mreminderlist = new ArrayList<>();
        no_task_ll = findViewById(R.id.notask);
        no_task_ll.setVisibility(View.GONE);
        if (firstrun == 0) {
            progressDialog = new ProgressDialog(Notif_Main.this);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Getting your tasks...");
            progressDialog.show();
            Runnable progressRunnable = new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            };
            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 4500);
        }
        firstrun++;
        FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
        if (curuser != null) {
            String uid = curuser.getUid();
            if (now == 2) {
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Reminder").child(workvalue1);
                db.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> arr = new ArrayList<String>();
                        int k;
                        for (k = 0; k < mexamplelist.size(); k++) {
                            arr.add(mexamplelist.get(k).getFull());
                        }
                        k = 0;
                        for (DataSnapshot childsnap : dataSnapshot.getChildren()) {
                            String date = childsnap.getKey();
                            HashMap<String, String> hmp;
                            hmp = (HashMap<String, String>) childsnap.getValue();
                            Boolean exist = arr.contains(date);
                            if (exist == false) {
                                k++;
                                mexamplelist.add(new Exampleitem(hmp.get("title"), hmp.get("des"), hmp.get("date"), hmp.get("time"), date, hmp.get("repeat"), hmp.get("marker")));
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            } else {
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1);
                db.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> arr = new ArrayList<String>();
                        int k;
                        for (k = 0; k < mexamplelist.size(); k++) {
                            arr.add(mexamplelist.get(k).getFull());
                        }
                        k = 0;
                        for (DataSnapshot childsnap : dataSnapshot.getChildren()) {
                            String date = childsnap.getKey();
                            HashMap<String, String> hmp;
                            hmp = (HashMap<String, String>) childsnap.getValue();
                            Boolean exist = arr.contains(date);
                            if (exist == false) {
                                k++;
                                mexamplelist.add(new Exampleitem(hmp.get("title"), hmp.get("des"), hmp.get("date"), hmp.get("time"), date, hmp.get("repeat"), hmp.get("marker")));
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        }
        buildrecylerview();
        if (now != 2) {
            refreshTask();
        }
        /*Toolbar toolbar = findViewById(R.id.toolbar);*/
        /*setSupportActionBar(toolbar);*/
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Notif_Main.this, TaskAdderActivity.class);
                intent.putExtra("now", now);
                intent.putExtra("work",workvalue1);
                startActivity(intent);
            }
        });
        /*DrawerLayout drawer = findViewById(R.id.drawer_layout);*/
        /*NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);*/



        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                        Toast.makeText(Notif_Main.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void refreshTask() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = getTimeMethod("dd-MMM-yy-hh-mm-ss a");
                        if (s.substring(16, 18).equals("00")) {
                            final String curtime = process(s);
                            mexamplelist = new ArrayList<>();
                            FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
                            final String uid = curuser.getUid();
                            if (curuser != null) {
                                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1);
                                db.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        List<String> arr = new ArrayList<String>();
                                        int k;
                                        for (k = 0; k < mexamplelist.size(); k++) {
                                            arr.add(mexamplelist.get(k).getFull());
                                        }
                                        k = 0;
                                        for (DataSnapshot childsnap : dataSnapshot.getChildren()) {
                                            String date = childsnap.getKey();
                                            HashMap<String, String> hmp;
                                            hmp = (HashMap<String, String>) childsnap.getValue();
                                            String tasktime = date.substring(0, 12);
                                            if (curtime.compareTo(tasktime) == 0) {
                                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                                                    showNotification1();
                                                } else {
                                                    showNotification2(hmp.get("title"),hmp.get("des"));
                                                }
                                            } else if (curtime.compareTo(tasktime) > 0) {
                                                String repeat = hmp.get("repeat");
                                                if (repeat.equals("None")) {
                                                    dbb = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Pasttask").child(workvalue1);
                                                    Map<String, Object> val = new TreeMap<>();
                                                    Info info = new Info(hmp.get("title"), hmp.get("des"), hmp.get("date"), hmp.get("time"), "None", date, hmp.get("marker"));
                                                    val.put(date, info);
                                                    dbb.updateChildren(val);
                                                    db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1).child(date);
                                                    db.setValue(null);
                                                } else {
                                                    ArrayList<Integer> dates;
                                                    Process p = new Process();
                                                    dates = p.getdatelist(hmp.get("date"));
                                                    int y = dates.get(0);
                                                    int m = dates.get(1) + 1;
                                                    int d = dates.get(2);
                                                    int x = p.dateToInt(y, m, d);
                                                    dates.clear();
                                                    String curdate = "";
                                                    if (repeat.equals("Daily")) {
                                                        x++;
                                                        dates = p.intToDate(x);
                                                        y = dates.get(0);
                                                        m = dates.get(1);
                                                        d = dates.get(2);
                                                    } else if (repeat.equals("Weekly")) {
                                                        x += 7;
                                                        dates = p.intToDate(x);
                                                        y = dates.get(0);
                                                        m = dates.get(1);
                                                        d = dates.get(2);
                                                    } else if (repeat.equals("Monthly")) {
                                                        if (m == 12) {
                                                            m = 1;
                                                            y++;
                                                        } else {
                                                            m++;
                                                        }
                                                    } else {
                                                        y++;
                                                    }
                                                    String ys = Integer.toString(y);
                                                    String ms = Integer.toString(m - 1);
                                                    String ds = Integer.toString(d);
                                                    if (ms.length() != 2)
                                                        ys += "0";
                                                    if (ds.length() != 2)
                                                        ms += "0";
                                                    curdate = ys + ms + ds;
                                                    Map<String, Object> val2 = new TreeMap<>();
                                                    String date2 = curdate + hmp.get("time") + date.substring(12, 15);
                                                    Info info2 = new Info(hmp.get("title"), hmp.get("des"), curdate, hmp.get("time"), repeat, date2, hmp.get("marker"));
                                                    val2.put(date2, info2);
                                                    db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1).child(date);
                                                    db.setValue(null);
                                                    dbb = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1);
                                                    dbb.updateChildren(val2);
                                                }
                                            }
                                            Boolean exist = arr.contains(date);
                                            if (exist == false) {
                                                k++;
                                                mexamplelist.add(new Exampleitem(hmp.get("title"), hmp.get("des"), hmp.get("date"), hmp.get("time"), date, hmp.get("repeat"), hmp.get("marker")));
                                                mAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                            }
                            buildrecylerview();
                        }
                    }

                    ;
                });
            }
        }, 0, 1000);
    }

    private void showNotification1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_ID);
        builder.setSmallIcon(R.drawable.icons8_today_96);
        builder.setContentTitle("Schedule It");
        builder.setContentText("You have task now.Click to open app.");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(Notification_ID, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification2(String notif_title, String notif_des) {
        String id = "main_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        CharSequence name = "Channel Name";
        String description = "Channel Description";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
        notificationChannel.setDescription(description);
        notificationChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Intent resintent = new Intent(this, com.example.register.Notif_Main.class);
        resintent.putExtra("work","chodu2");
        PendingIntent respenindent = PendingIntent.getActivity(this, 1, resintent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
        builder.setSmallIcon(R.drawable.icons8_hexa_480);
        builder.setContentTitle("Schedule It");
        builder.setContentText(notif_title);
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(notif_des));
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(respenindent);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1000, builder.build());
    }

    public void removeitem(int position) {
        Exampleitem curitem = mexamplelist.get(position);
        String delid = curitem.getFull();
        FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = curuser.getUid();
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1).child(delid);
        db.setValue(null);
        mexamplelist.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void buildrecylerview() {
        mRecyclerView = findViewById(R.id.mainll);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Exampleadapter(mexamplelist);
        mRecyclerView.setLayoutManager(mLayoutManager);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Exampleadapter.OnItemClickListener() {
            @Override
            public void onitemclick(int position) {
                Intent intent = new Intent(com.example.register.Notif_Main.this, Popup.class);
                Exampleitem e = mexamplelist.get(position);
                intent.putExtra("title", e.getTitle());
                intent.putExtra("date", e.getDate());
                intent.putExtra("time", e.getTime());
                intent.putExtra("des", e.getDes());
                intent.putExtra("repeat", e.getRepeat());
                intent.putExtra("marker", e.getMarker());
                startActivity(intent);
            }

            @Override
            public void ondelete(int position) {
                removeitem(position);
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Exampleitem curitem = mexamplelist.get(position);
            String delid = curitem.getFull();
            FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
            String uid = curuser.getUid();
            if (now == 2) {
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Reminder").child(workvalue1).child(delid);
            } else {
                db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Task").child(workvalue1).child(delid);
            }
            db.setValue(null);
            mexamplelist.remove(position);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_view) {
            return true;
        }
        if (id == R.id.actionsettings) {
            Intent intent = new Intent(this.getApplicationContext(), SettingActivity.class);
            intent.putExtra("work",workvalue1);
            startActivity(intent);
        } else if (id == R.id.actionpasttasks) {
            Intent intent = new Intent(this.getApplicationContext(), PastTaskActivity.class);
            intent.putExtra("work",workvalue1);
            startActivity(intent);
        }
        /*else if (id == R.id.actionupdate) {
            Intent intent = new Intent(this.getApplicationContext(), UpdateActivity.class);
            startActivity(intent);
        }*/
        return super.onOptionsItemSelected(item);
    }

 /*   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_prof) {
            Intent intent = new Intent(com.example.register.Notif_Main.this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_past) {
            Intent intent = new Intent(com.example.register.Notif_Main.this, PastTaskActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calender) {
            Intent intent = new Intent(com.example.register.Notif_Main.this, CalenderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(com.example.register.Notif_Main.this, InviteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            finish();
            moveTaskToBack(true);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/
    public void onTimetask(View view) {
        TextView ttv = findViewById(R.id.timetasktv);
        TextView rtv = findViewById(R.id.remindertv);
        ttv.setTextColor(Color.parseColor("#00BFFF"));
        rtv.setTextColor(Color.parseColor("#000000"));
        now = 1;
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onReminder(View view) {
        TextView ttv = findViewById(R.id.timetasktv);
        TextView rtv = findViewById(R.id.remindertv);
        ttv.setTextColor(Color.parseColor("#000000"));
        rtv.setTextColor(Color.parseColor("#00BFFF"));
        now = 2;
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void settvcolor() {
        TextView ttv = findViewById(R.id.timetasktv);
        TextView rtv = findViewById(R.id.remindertv);
        if (now == 2) {
            ttv.setTextColor(Color.parseColor("#000000"));
            rtv.setTextColor(Color.parseColor("#00BFFF"));
        } else {
            ttv.setTextColor(Color.parseColor("#00BFFF"));
            rtv.setTextColor(Color.parseColor("#000000"));
        }
    }

    private String process(String s) {
        String f = "20" + s.substring(7, 9);
        String month = s.substring(3, 6);
        if (month.equals("Jan")) {
            f += "00";
        } else if (month.equals("Feb")) {
            f += "01";
        } else if (month.equals("Mar")) {
            f += "02";
        } else if (month.equals("Apr")) {
            f += "03";
        } else if (month.equals("May")) {
            f += "04";
        } else if (month.equals("Jun")) {
            f += "05";
        } else if (month.equals("Jul")) {
            f += "06";
        } else if (month.equals("Aug")) {
            f += "07";
        } else if (month.equals("Sep")) {
            f += "08";
        } else if (month.equals("Oct")) {
            f += "09";
        } else if (month.equals("Nov")) {
            f += "10";
        } else {
            f += "11";
        }
        f += s.substring(0, 2);
        String h = s.substring(10, 12);
        int hr = Integer.parseInt(h);
        if (s.charAt(19) == 'P' && hr != 12) {
            hr += 12;
        }
        String hour = String.valueOf(hr);
        if (hour.length() == 1) {
            f += "0";
        }
        f += hour;
        f += s.substring(13, 15);
        return f;
    }

    private String getTimeMethod(String formate) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}