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
//        addData(db);
        new currentUser("sam", "leader", 1);

        ArrayList<ArrayList<String>> badges = db.getAllBadges();
        badges.forEach(element -> Log.d("Badge", String.valueOf(element)));
        Log.d("Badges", badges.toString());

        ArrayList<ArrayList<Object>> users = db.getAllUsers();
        users.forEach(element -> Log.d("User", String.valueOf(element)));
        Log.d("Users", users.toString());

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnSelectedBadges = findViewById(R.id.btnSelectedBadges);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
        btnBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {;
                activity = new Intent(MainActivity.this, Badges_Activity.class);
                startActivity(activity);

            }
        });
        btnGroups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, Groups_Activity.class);
                startActivity(activity);

            }
        });
        btnSelectedBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, SelectedBadges_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addData(DBHelper db){
        //Groups
        db.addGroup(new GroupList("Anchorsholme Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton-Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("2nd Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton Scout Group", "Wyre", "West Lancashire"));

        //User
        db.addUser(new UserList("SamWilmer", "1234", "Sam Wilmer", "2004-06-07", "Test@test.com", "0567438921", "Leader", 1));


        //Core Awards
        db.addBadge(new BadgeList("Membership","core","membership_award"));
        db.addBadge(new BadgeList("Chief Scout Award Gold","core","sc_chief_scout_award_gold"));

        //Test Requirements
        db.addRequirement(new RequirementsList("Complete all nine challenge awards",9, 2));
        db.addRequirement(new RequirementsList("Complete 6 Activity Badges",6, 2));

        //Challenge Awards
        db.addBadge(new BadgeList("Adventure","challenge","challenge_sc_adventure"));

        db.addBadge(new BadgeList("Creative","challenge","challenge_sc_creative"));
        db.addBadge(new BadgeList("Expedition","challenge","challenge_sc_expedition"));
        db.addBadge(new BadgeList("Outdoors","challenge","challenge_sc_outdoors"));
        db.addBadge(new BadgeList("Personal Challenge","challenge","challenge_sc_personal_challenge"));
        db.addBadge(new BadgeList("Skills","challenge","challenge_sc_skills"));
        db.addBadge(new BadgeList("Team Leader","challenge","challenge_sc_team_leader"));
        db.addBadge(new BadgeList("Teamwork","challenge","challenge_sc_teamwork"));
        db.addBadge(new BadgeList("World","challenge","challenge_sc_world"));

        //Staged Awards
        db.addBadge(new BadgeList("Air Activities","staged","staged_air_activities"));
        db.addBadge(new BadgeList("Community","staged","staged_community"));
        db.addBadge(new BadgeList("Digital Citizen","staged","staged_digital_citizen"));
        db.addBadge(new BadgeList("Digital Maker","staged","staged_digital_maker"));
        db.addBadge(new BadgeList("Emergency Aid","staged","staged_emergency_aid"));
        db.addBadge(new BadgeList("Hikes Away","staged","staged_hikes_away"));
        db.addBadge(new BadgeList("Musician","staged","staged_musician"));
        db.addBadge(new BadgeList("Nautical","staged","staged_nautical"));
        db.addBadge(new BadgeList("Navigator","staged","staged_navigator"));
        db.addBadge(new BadgeList("Nights Away","staged","staged_nights_away"));
        db.addBadge(new BadgeList("Paddle Sports","staged","staged_paddle_sports"));
        db.addBadge(new BadgeList("Sailing","staged","staged_sailing"));
        db.addBadge(new BadgeList("Snowsports","staged","staged_snowsports"));
        db.addBadge(new BadgeList("Swimmer","staged","staged_swimmer"));
        db.addBadge(new BadgeList("Time On The Water","staged","staged_time_on_the_water"));

        //Activity Awards
        db.addBadge(new BadgeList("Activity Center Service","activity","activity_sc_activitycenterservice"));
        db.addBadge(new BadgeList("Air or Sea Navigation","activity","activity_sc_air_or_sea_nav"));
        db.addBadge(new BadgeList("Air Researcher","activity","activity_sc_air_researcher"));
        db.addBadge(new BadgeList("Air Spotter","activity","activity_sc_air_spotter"));
        db.addBadge(new BadgeList("Angler","activity","activity_sc_angler"));
        db.addBadge(new BadgeList("Artist","activity","activity_sc_artist"));
        db.addBadge(new BadgeList("Astronomer","activity","activity_sc_astronomer"));
        db.addBadge(new BadgeList("Athletics","activity","activity_sc_athletics"));
        db.addBadge(new BadgeList("Athletics Plus","activity","activity_sc_athleticsplus"));
        db.addBadge(new BadgeList("Camper","activity","activity_sc_camper"));

        db.addBadge(new BadgeList("Caver","activity","activity_sc_caver"));
        db.addBadge(new BadgeList("Chef","activity","activity_sc_chef"));
        db.addBadge(new BadgeList("Circus Skills","activity","activity_sc_circus_skills"));
        db.addBadge(new BadgeList("Climber","activity","activity_sc_climber"));
        db.addBadge(new BadgeList("Communicator","activity","activity_sc_communicator"));
        db.addBadge(new BadgeList("Craft","activity","activity_sc_craft"));
        db.addBadge(new BadgeList("Cyclist","activity","activity_sc_cyclist"));
        db.addBadge(new BadgeList("DIY","activity","activity_sc_diy"));
        db.addBadge(new BadgeList("Dragon Boating","activity","activity_sc_dragon_boating"));
        db.addBadge(new BadgeList("Electronics","activity","activity_sc_electronics"));

        db.addBadge(new BadgeList("Entertainer","activity","activity_sc_entertainer"));
        db.addBadge(new BadgeList("Environmental Conservation","activity","activity_sc_environmental_conservation"));
        db.addBadge(new BadgeList("Equestrian","activity","activity_sc_equestrian"));
        db.addBadge(new BadgeList("Farming","activity","activity_sc_farming"));
        db.addBadge(new BadgeList("Fire Safety","activity","activity_sc_fire_safety"));
        db.addBadge(new BadgeList("Forrester","activity","activity_sc_forrester"));
        db.addBadge(new BadgeList("Fundraising","activity","activity_sc_fundraising"));
        db.addBadge(new BadgeList("Geocaching","activity","activity_sc_geocaching"));
        db.addBadge(new BadgeList("Global Issues","activity","activity_sc_global_issues"));
        db.addBadge(new BadgeList("Hill Walker","activity","activity_sc_hill_walker"));

        db.addBadge(new BadgeList("Hobbies","activity","activity_sc_hobbies"));
        db.addBadge(new BadgeList("International","activity","activity_sc_international"));
        db.addBadge(new BadgeList("Librarian","activity","activity_sc_librarian"));
        db.addBadge(new BadgeList("Lifesaver","activity","activity_sc_lifesaver"));
        db.addBadge(new BadgeList("Local Knowledge","activity","activity_sc_local_knowledge"));
        db.addBadge(new BadgeList("Martial Arts","activity","activity_sc_martial_arts"));
        db.addBadge(new BadgeList("Master at Arms","activity","activity_sc_master_at_arms"));
        db.addBadge(new BadgeList("Mechanic","activity","activity_sc_mechanic"));
        db.addBadge(new BadgeList("Media Relations","activity","activity_sc_mediarelations"));
        db.addBadge(new BadgeList("Meteorologist","activity","activity_sc_meteorologist"));

        db.addBadge(new BadgeList("Model Maker","activity","activity_sc_model_maker"));
        db.addBadge(new BadgeList("Money Skills","activity","activity_sc_money_skills"));
        db.addBadge(new BadgeList("My Faith","activity","activity_sc_my_faith"));
        db.addBadge(new BadgeList("Naturalist","activity","activity_sc_naturalist"));
        db.addBadge(new BadgeList("Orienteer","activity","activity_sc_orienteer"));
        db.addBadge(new BadgeList("Parascending","activity","activity_sc_parascending"));
        db.addBadge(new BadgeList("Photographer","activity","activity_sc_photographer"));
        db.addBadge(new BadgeList("Physical Recreation","activity","activity_sc_physical_recreation"));
        db.addBadge(new BadgeList("Pioneer","activity","activity_sc_pioneer"));
        db.addBadge(new BadgeList("Power Coxswain","activity","activity_sc_power_coxswain"));

        db.addBadge(new BadgeList("Pulling","activity","activity_sc_pulling"));
        db.addBadge(new BadgeList("Quartermaster","activity","activity_sc_quartermaster"));
        db.addBadge(new BadgeList("Scientist","activity","activity_sc_scientist"));
        db.addBadge(new BadgeList("Sports Enthusiast","activity","activity_sc_sports_enthusiast"));
        db.addBadge(new BadgeList("Street Sports","activity","activity_sc_street_sports"));
        db.addBadge(new BadgeList("Survival Skills","activity","activity_sc_survival_skills"));
        db.addBadge(new BadgeList("Water Activities","activity","activity_sc_wateractivities"));
        db.addBadge(new BadgeList("World Faiths","activity","activity_sc_world_faiths"));
        db.addBadge(new BadgeList("Writer","activity","activity_sc_writer"));
//
    }
}
