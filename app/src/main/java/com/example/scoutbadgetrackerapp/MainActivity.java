package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity{
    Button btnLogin, btnBadges, btnGroups, btnSelectedBadges;
    Intent activity;
    Date stringDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);
        //Test User
//        db.addUser(new UserList("SamWilmer", "1234", "Sam Wilmer", "2004-06-07", "Test@test.com", "0567438921", "Leader", null));

//        //Core Awards
//        db.addBadge(new BadgeList("Membership","core",null,"membership_award"));
//        db.addBadge(new BadgeList("Chief Scout Award Gold","core",null,"sc_chief_scout_award_gold"));
//        db.updateBadge(new BadgeList("Chief Scout Award Gold","core","Complete all nine challenge awards.","sc_chief_scout_award_gold"));

//        //Challenge Awards
//        db.addBadge(new BadgeList("Adventure","challenge",null,"challenge_sc_adventure"));
//        db.updateBadge(new BadgeList("Adventure","challenge","Take part in four different adventurous activities.","challenge_sc_adventure"));

//        db.addBadge(new BadgeList("Creative","challenge",null,"challenge_sc_creative"));
//        db.addBadge(new BadgeList("Expedition","challenge",null,"challenge_sc_expedition"));
//        db.addBadge(new BadgeList("Outdoors","challenge",null,"challenge_sc_outdoors"));
//        db.addBadge(new BadgeList("Personal Challenge","challenge",null,"challenge_sc_personal_challenge"));
//        db.addBadge(new BadgeList("Skills","challenge",null,"challenge_sc_skills"));
//        db.addBadge(new BadgeList("Team Leader","challenge",null,"challenge_sc_team_leader"));
//        db.addBadge(new BadgeList("Teamwork","challenge",null,"challenge_sc_teamwork"));
//        db.addBadge(new BadgeList("World","challenge",null,"challenge_sc_world"));

//        //Staged Awards
//        db.addBadge(new BadgeList("Air Activities","staged",null,"staged_air_activities"));
//        db.addBadge(new BadgeList("Community","staged",null,"staged_community"));
//        db.addBadge(new BadgeList("Digital Citizen","staged",null,"staged_digital_citizen"));
//        db.addBadge(new BadgeList("Digital Maker","staged",null,"staged_digital_maker"));
//        db.addBadge(new BadgeList("Emergency Aid","staged",null,"staged_emergency_aid"));
//        db.addBadge(new BadgeList("Hikes Away","staged",null,"staged_hikes_away"));
//        db.addBadge(new BadgeList("Musician","staged",null,"staged_musician"));
//        db.addBadge(new BadgeList("Nautical","staged",null,"staged_nautical"));
//        db.addBadge(new BadgeList("Navigator","staged",null,"staged_navigator"));
//        db.addBadge(new BadgeList("Nights Away","staged",null,"staged_nights_away"));
//        db.addBadge(new BadgeList("Paddle Sports","staged",null,"staged_paddle_sports"));
//        db.addBadge(new BadgeList("Sailing","staged",null,"staged_sailing"));
//        db.addBadge(new BadgeList("Snowsports","staged",null,"staged_snowsports"));
//        db.addBadge(new BadgeList("Swimmer","staged",null,"staged_swimmer"));
//        db.addBadge(new BadgeList("Time On The Water","staged",null,"staged_time_on_the_water"));

//        //Activity Awards
//        db.addBadge(new BadgeList("Activity Center Service","activity", null,"activity_sc_activitycenterservice"));
//        db.addBadge(new BadgeList("Air or Sea Navigation","activity", null,"activity_sc_air_or_sea_nav"));
//        db.addBadge(new BadgeList("Air Researcher","activity", null,"activity_sc_air_researcher"));
//        db.addBadge(new BadgeList("Air Spotter","activity",null,"activity_sc_air_spotter"));
//        db.addBadge(new BadgeList("Angler","activity", null,"activity_sc_angler"));
//        db.addBadge(new BadgeList("Artist","activity", null,"activity_sc_artist"));
//        db.addBadge(new BadgeList("Astronomer","activity", null,"activity_sc_astronomer"));
//        db.addBadge(new BadgeList("Athletics","activity",null,"activity_sc_athletics"));
//        db.addBadge(new BadgeList("Athletics Plus","activity", null,"activity_sc_athleticsplus"));
//        db.addBadge(new BadgeList("Camper","activity", null,"activity_sc_camper"));
//        db.addBadge(new BadgeList("Caver","activity", null,"activity_sc_caver"));
//        db.addBadge(new BadgeList("Chef","activity",null,"activity_sc_chef"));
//        db.addBadge(new BadgeList("Circus Skills","activity", null,"activity_sc_circus_skills"));
//        db.addBadge(new BadgeList("Climber","activity", null,"activity_sc_climber"));
//        db.addBadge(new BadgeList("Communicator","activity", null,"activity_sc_communicator"));
//        db.addBadge(new BadgeList("Craft","activity",null,"activity_sc_craft"));
//        db.addBadge(new BadgeList("Cyclist","activity", null,"activity_sc_cyclist"));
//        db.addBadge(new BadgeList("DIY","activity", null,"activity_sc_diy"));
//        db.addBadge(new BadgeList("Dragon Boating","activity", null,"activity_sc_dragon_boating"));
//        db.addBadge(new BadgeList("Electronics","activity",null,"activity_sc_electronics"));
//        db.addBadge(new BadgeList("Entertainer","activity", null,"activity_sc_entertainer"));
//        db.addBadge(new BadgeList("Environmental Conservation","activity", null,"activity_sc_environmental_conservation"));
//        db.addBadge(new BadgeList("Equestrian","activity", null,"activity_sc_equestrian"));
//        db.addBadge(new BadgeList("Farming","activity",null,"activity_sc_farming"));
//        db.addBadge(new BadgeList("Fire Safety","activity", null,"activity_sc_fire_safety"));
//        db.addBadge(new BadgeList("Forrester","activity", null,"activity_sc_forrester"));
//        db.addBadge(new BadgeList("Fundraising","activity", null,"activity_sc_fundraising"));
//        db.addBadge(new BadgeList("Geocaching","activity",null,"activity_sc_geocaching"));
//        db.addBadge(new BadgeList("Global Issues","activity", null,"activity_sc_global_issues"));
//        db.addBadge(new BadgeList("Hill Walker","activity", null,"activity_sc_hill_walker"));
//        db.addBadge(new BadgeList("Hobbies","activity", null,"activity_sc_hobbies"));
//        db.addBadge(new BadgeList("International","activity",null,"activity_sc_international"));
//        db.addBadge(new BadgeList("Librarian","activity", null,"activity_sc_librarian"));
//        db.addBadge(new BadgeList("Lifesaver","activity", null,"activity_sc_lifesaver"));
//        db.addBadge(new BadgeList("Local Knowledge","activity", null,"activity_sc_local_knowledge"));
//        db.addBadge(new BadgeList("Martial Arts","activity",null,"activity_sc_martial_arts"));
//        db.addBadge(new BadgeList("Master at Arms","activity", null,"activity_sc_master_at_arms"));
//        db.addBadge(new BadgeList("Mechanic","activity", null,"activity_sc_mechanic"));
//        db.addBadge(new BadgeList("Media Relations","activity", null,"activity_sc_mediarelations"));
//        db.addBadge(new BadgeList("Meteorologist","activity",null,"activity_sc_meteorologist"));
//        db.addBadge(new BadgeList("Model Maker","activity", null,"activity_sc_model_maker"));
//        db.addBadge(new BadgeList("Money Skills","activity", null,"activity_sc_money_skills"));
//        db.addBadge(new BadgeList("My Faith","activity", null,"activity_sc_my_faith"));
//        db.addBadge(new BadgeList("Naturalist","activity",null,"activity_sc_naturalist"));
//        db.addBadge(new BadgeList("Orienteer","activity", null,"activity_sc_orienteer"));
//        db.addBadge(new BadgeList("Parascending","activity", null,"activity_sc_parascending"));
//        db.addBadge(new BadgeList("Photographer","activity", null,"activity_sc_photographer"));
//        db.addBadge(new BadgeList("Physical Recreation","activity",null,"activity_sc_physical_recreation"));
//        db.addBadge(new BadgeList("Pioneer","activity", null,"activity_sc_pioneer"));
//        db.addBadge(new BadgeList("Power Coxswain","activity", null,"activity_sc_power_coxswain"));
//        db.addBadge(new BadgeList("Pulling","activity", null,"activity_sc_pulling"));
//        db.addBadge(new BadgeList("Quartermaster","activity",null,"activity_sc_quartermaster"));
//        db.addBadge(new BadgeList("Scientist","activity", null,"activity_sc_scientist"));
//        db.addBadge(new BadgeList("Sports Enthusiast","activity", null,"activity_sc_sports_enthusiast"));
//        db.addBadge(new BadgeList("Street Sports","activity", null,"activity_sc_street_sports"));
//        db.addBadge(new BadgeList("Survival Skills","activity",null,"activity_sc_survival_skills"));
//        db.addBadge(new BadgeList("Water Activities","activity", null,"activity_sc_wateractivities"));
//        db.addBadge(new BadgeList("World Faiths","activity", null,"activity_sc_world_faiths"));
//        db.addBadge(new BadgeList("Writer","activity", null,"activity_sc_writer"));
//



        ArrayList<ArrayList<String>> badges = db.getAllBadges();
        badges.forEach(element -> Log.d("Badge", String.valueOf(element)));
        Log.d("Badges", badges.toString());

        ArrayList<ArrayList<Object>> users = db.getAllUsers();
        users.forEach(element -> Log.d("User", String.valueOf(element)));
        Log.d("Users", badges.toString());

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnSelectedBadges = findViewById(R.id.btnSelectedBadges);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
        btnBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, Badges_Activity.class);
                startActivity(activity);

            }
        });
        btnGroups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, Groups_Activity.class);
                startActivity(activity);

            }
        });
        btnSelectedBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, SelectedBadges_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
