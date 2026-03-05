package com.example.scoutbadgetrackerapp;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {
    Button btnLogin, btnBadges, btnGroups, btnSelectedBadges, btnViewEvidence;
    Intent activity;
    Date stringDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission", "Already Granted");
        }else{
            String[] permissions = {Manifest.permission.READ_MEDIA_IMAGES};
            ActivityCompat.requestPermissions(this, permissions, 0);
            Log.d("Permission", "Granted");
        }

        DBHelper db = new DBHelper(this);

//        new currentUser("sam", "leader", 1);

        ArrayList<ArrayList<String>> badges = db.getAllBadges();
        badges.forEach(element -> Log.d("Badge", String.valueOf(element)));
        Log.d("Badges", badges.toString());

        ArrayList<ArrayList<Object>> users = db.getAllUsers();
        users.forEach(element -> Log.d("User", String.valueOf(element)));
        Log.d("Users", users.toString());
        Log.d("Current User", String.valueOf(currentUser.getUserID()));

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnSelectedBadges = findViewById(R.id.btnSelectedBadges);
        btnViewEvidence = findViewById(R.id.btnViewEvidence);
        if (currentUser.getUserRole().equals("Leader")) {
            btnViewEvidence.setVisibility(View.VISIBLE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
        btnBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ;
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
        btnViewEvidence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, ViewEvidence_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static void addData(DBHelper db) {
        //Groups
        db.addGroup(new GroupList("Anchorsholme Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton-Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("2nd Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton Scout Group", "Wyre", "West Lancashire"));

        //Core Awards
        db.addBadge(new BadgeList("Membership", "core", "membership_award"));
        db.addBadge(new BadgeList("Chief Scout Award Gold", "core", "sc_chief_scout_award_gold"));

        //Challenge Awards
        db.addBadge(new BadgeList("Adventure", "challenge", "challenge_sc_adventure"));
        db.addBadge(new BadgeList("Creative", "challenge", "challenge_sc_creative"));
        db.addBadge(new BadgeList("Expedition", "challenge", "challenge_sc_expedition"));
        db.addBadge(new BadgeList("Outdoors", "challenge", "challenge_sc_outdoors"));
        db.addBadge(new BadgeList("Personal Challenge", "challenge", "challenge_sc_personal_challenge"));
        db.addBadge(new BadgeList("Skills", "challenge", "challenge_sc_skills"));
        db.addBadge(new BadgeList("Team Leader", "challenge", "challenge_sc_team_leader"));
        db.addBadge(new BadgeList("Teamwork", "challenge", "challenge_sc_teamwork"));
        db.addBadge(new BadgeList("World", "challenge", "challenge_sc_world"));

        //Staged Awards
        db.addBadge(new BadgeList("Air Activities", "staged", "staged_air_activities"));
        db.addBadge(new BadgeList("Community", "staged", "staged_community"));
        db.addBadge(new BadgeList("Digital Citizen", "staged", "staged_digital_citizen"));
        db.addBadge(new BadgeList("Digital Maker", "staged", "staged_digital_maker"));
        db.addBadge(new BadgeList("Emergency Aid", "staged", "staged_emergency_aid"));
        db.addBadge(new BadgeList("Hikes Away", "staged", "staged_hikes_away"));
        db.addBadge(new BadgeList("Musician", "staged", "staged_musician"));
        db.addBadge(new BadgeList("Nautical", "staged", "staged_nautical"));
        db.addBadge(new BadgeList("Navigator", "staged", "staged_navigator"));
        db.addBadge(new BadgeList("Nights Away", "staged", "staged_nights_away"));
        db.addBadge(new BadgeList("Paddle Sports", "staged", "staged_paddle_sports"));
        db.addBadge(new BadgeList("Sailing", "staged", "staged_sailing"));
        db.addBadge(new BadgeList("Snowsports", "staged", "staged_snowsports"));
        db.addBadge(new BadgeList("Swimmer", "staged", "staged_swimmer"));
        db.addBadge(new BadgeList("Time On The Water", "staged", "staged_time_on_the_water"));

        //Activity Awards
        db.addBadge(new BadgeList("Activity Center Service", "activity", "activity_sc_activitycenterservice"));
        db.addBadge(new BadgeList("Air or Sea Navigation", "activity", "activity_sc_air_or_sea_nav"));
        db.addBadge(new BadgeList("Air Researcher", "activity", "activity_sc_air_researcher"));
        db.addBadge(new BadgeList("Air Spotter", "activity", "activity_sc_air_spotter"));
        db.addBadge(new BadgeList("Angler", "activity", "activity_sc_angler"));
        db.addBadge(new BadgeList("Artist", "activity", "activity_sc_artist"));
        db.addBadge(new BadgeList("Astronomer", "activity", "activity_sc_astronomer"));
        db.addBadge(new BadgeList("Athletics", "activity", "activity_sc_athletics"));
        db.addBadge(new BadgeList("Athletics Plus", "activity", "activity_sc_athleticsplus"));
        db.addBadge(new BadgeList("Camper", "activity", "activity_sc_camper"));

        db.addBadge(new BadgeList("Caver", "activity", "activity_sc_caver"));
        db.addBadge(new BadgeList("Chef", "activity", "activity_sc_chef"));
        db.addBadge(new BadgeList("Circus Skills", "activity", "activity_sc_circus_skills"));
        db.addBadge(new BadgeList("Climber", "activity", "activity_sc_climber"));
        db.addBadge(new BadgeList("Communicator", "activity", "activity_sc_communicator"));
        db.addBadge(new BadgeList("Craft", "activity", "activity_sc_craft"));
        db.addBadge(new BadgeList("Cyclist", "activity", "activity_sc_cyclist"));
        db.addBadge(new BadgeList("DIY", "activity", "activity_sc_diy"));
        db.addBadge(new BadgeList("Dragon Boating", "activity", "activity_sc_dragon_boating"));
        db.addBadge(new BadgeList("Electronics", "activity", "activity_sc_electronics"));

        db.addBadge(new BadgeList("Entertainer", "activity", "activity_sc_entertainer"));
        db.addBadge(new BadgeList("Environmental Conservation", "activity", "activity_sc_environmental_conservation"));
        db.addBadge(new BadgeList("Equestrian", "activity", "activity_sc_equestrian"));
        db.addBadge(new BadgeList("Farming", "activity", "activity_sc_farming"));
        db.addBadge(new BadgeList("Fire Safety", "activity", "activity_sc_fire_safety"));
        db.addBadge(new BadgeList("Forrester", "activity", "activity_sc_forrester"));
        db.addBadge(new BadgeList("Fundraising", "activity", "activity_sc_fundraising"));
        db.addBadge(new BadgeList("Geocaching", "activity", "activity_sc_geocaching"));
        db.addBadge(new BadgeList("Global Issues", "activity", "activity_sc_global_issues"));
        db.addBadge(new BadgeList("Hill Walker", "activity", "activity_sc_hill_walker"));

        db.addBadge(new BadgeList("Hobbies", "activity", "activity_sc_hobbies"));
        db.addBadge(new BadgeList("International", "activity", "activity_sc_international"));
        db.addBadge(new BadgeList("Librarian", "activity", "activity_sc_librarian"));
        db.addBadge(new BadgeList("Lifesaver", "activity", "activity_sc_lifesaver"));
        db.addBadge(new BadgeList("Local Knowledge", "activity", "activity_sc_local_knowledge"));
        db.addBadge(new BadgeList("Martial Arts", "activity", "activity_sc_martial_arts"));
        db.addBadge(new BadgeList("Master at Arms", "activity", "activity_sc_master_at_arms"));
        db.addBadge(new BadgeList("Mechanic", "activity", "activity_sc_mechanic"));
        db.addBadge(new BadgeList("Media Relations", "activity", "activity_sc_mediarelations"));
        db.addBadge(new BadgeList("Meteorologist", "activity", "activity_sc_meteorologist"));

        db.addBadge(new BadgeList("Model Maker", "activity", "activity_sc_model_maker"));
        db.addBadge(new BadgeList("Money Skills", "activity", "activity_sc_money_skills"));
        db.addBadge(new BadgeList("My Faith", "activity", "activity_sc_my_faith"));
        db.addBadge(new BadgeList("Naturalist", "activity", "activity_sc_naturalist"));
        db.addBadge(new BadgeList("Orienteer", "activity", "activity_sc_orienteer"));
        db.addBadge(new BadgeList("Parascending", "activity", "activity_sc_parascending"));
        db.addBadge(new BadgeList("Photographer", "activity", "activity_sc_photographer"));
        db.addBadge(new BadgeList("Physical Recreation", "activity", "activity_sc_physical_recreation"));
        db.addBadge(new BadgeList("Pioneer", "activity", "activity_sc_pioneer"));
        db.addBadge(new BadgeList("Power Coxswain", "activity", "activity_sc_power_coxswain"));

        db.addBadge(new BadgeList("Pulling", "activity", "activity_sc_pulling"));
        db.addBadge(new BadgeList("Quartermaster", "activity", "activity_sc_quartermaster"));
        db.addBadge(new BadgeList("Scientist", "activity", "activity_sc_scientist"));
        db.addBadge(new BadgeList("Sports Enthusiast", "activity", "activity_sc_sports_enthusiast"));
        db.addBadge(new BadgeList("Street Sports", "activity", "activity_sc_street_sports"));
        db.addBadge(new BadgeList("Survival Skills", "activity", "activity_sc_survival_skills"));
        db.addBadge(new BadgeList("Water Activities", "activity", "activity_sc_wateractivities"));
        db.addBadge(new BadgeList("World Faiths", "activity", "activity_sc_world_faiths"));
        db.addBadge(new BadgeList("Writer", "activity", "activity_sc_writer"));

    }
    public static void addCoreRequirements(DBHelper db) {
        //Membership Award
        db.addRequirement(new RequirementsList("Know about the Scout Troop:\n" +
                "a. Find out about the ceremonies and traditions in the Troop.\n" +
                "b. Get to know other members and Leaders in the Patrol and Troop.\n" +
                "c. Find out about the activities that the Patrol and Troop does.", 1, 1));
        db.addRequirement(new RequirementsList("Know about joining your Troop:\n" +
                "a. Learn and understand the Scout Motto, sign, salute and handshake.\n" +
                "b. Show you know the general history and family of Scouts and Scouting around the world. Learn about Scouts history.\n" +
                "c. Learn and understand the Scout Promise and Law and the rules of the Troop. See versions of the Scout Promise.\n" +
                "d. Learn what to do at Investiture.", 1, 2));
        db.addRequirement(new RequirementsList("Become a Scout by making the Promise.", 1, 3));

        //Chief Scout's Gold Award
        db.addRequirement(new RequirementsList("Complete all nine challenge awards", 9, 2));
        db.addRequirement(new RequirementsList("Complete 6 Activity or Staged Badges", 6, 2));

        //Scouts Adventure Challenge Award
        db.addRequirement(new RequirementsList("Take part in four different adventurous activities", 4, 3));
        db.addRequirement(new RequirementsList("Show how you have developed your skill and expertise in one of these activities", 1, 3));
        db.addRequirement(new RequirementsList("Learn about any environmental issues caused by your activity", 1, 3));
        db.addRequirement(new RequirementsList("Research other ways you can take part, or develop your skills, in your chosen activities", 1, 3));

        //Scouts Creative Challenge Award
        db.addRequirement(new RequirementsList("Over a period of time, take part in at least four creative activities", 4, 4));
        db.addRequirement(new RequirementsList("Show that you have developed your skills in one of these activities", 1, 4));
        db.addRequirement(new RequirementsList("Use your creative ability to produce something that promotes a Scouting activity or an event", 1, 4));
        db.addRequirement(new RequirementsList("Construct a model using materials like a plastic kit or recycled items", 1, 4));
        db.addRequirement(new RequirementsList("Show how to use social media or the internet in a creative and safe way", 1, 4));
        db.addRequirement(new RequirementsList("Take part in a performance", 1, 4));

        //Scouts Expedition Challenge Award
        db.addRequirement(new RequirementsList("Take part in either an expedition or an exploration over two days with at least three other Scouts.", 1, 5));
        db.addRequirement(new RequirementsList("Take an active part in planning the expedition or exploration.", 1, 5));
        db.addRequirement(new RequirementsList("During the expedition or exploration: \n" +
                "a. Play a full part in the team \n" +
                "b. Use a map or other navigation device to keep track of where you are \n" +
                "c. Cook and eat at least one hot meal \n" +
                "d. Do a task, investigation, or exploration as agreed with your Leader.", 1, 5));
        db.addRequirement(new RequirementsList("Produce an individual report or presentation within the three weeks following your expedition or exploration.", 1, 5));

        //Scouts Outdoors Challenge Award
        db.addRequirement(new RequirementsList("Take an active part in at least eight nights away as a Scout.", 8, 6));
        db.addRequirement(new RequirementsList("With others, pitch and strike your tent.", 1, 6));
        db.addRequirement(new RequirementsList("Lead, or help to lead, a group of Scouts to set up a well-organised site.", 1, 6));
        db.addRequirement(new RequirementsList("Prepare and light an open fire or set up a suitable stove.", 1, 6));
        db.addRequirement(new RequirementsList("Understand the three points of the Countryside Code.", 1, 6));
        db.addRequirement(new RequirementsList("Find out why personal and campsite hygiene is important.", 1, 6));
        db.addRequirement(new RequirementsList("Using knots that you have learned, build a simple pioneering project, object or camp gadget.", 1, 6));
        db.addRequirement(new RequirementsList("Explore the environment of your camp and make sure you know where everything is.", 1, 6));
        db.addRequirement(new RequirementsList("Find out what accidents and incidents can happen outdoors or during your camp.", 1, 6));
        db.addRequirement(new RequirementsList("Show how to use an axe, saw or knife safely.", 1, 6));
        db.addRequirement(new RequirementsList("Complete at least four of these tasks: \n" +
                "a. Provide a service commitment to the site for about an hour.\n" +
                "b. Take part in a wide game.\n" +
                "c. Take part in a campfire or other entertainment.\n" +
                "d. Working with others, successfully complete a two-hour activity or project.\n" +
                "e. Plan a balanced menu for a short camp.\n" +
                "f. Lead the cooking of a meal for the group.\n" +
                "g. Show how to pack a rucksack correctly, with appropriate kit for the camp or event.\n" +
                "h. Cook a backwoods meal with the group.\n" +
                "i. Build a bivouac and sleep in it.\n" +
                "j. Show that you know the safety precautions for using lamps and stoves.", 4, 6));

        //Scouts Personal Challenge Challenge Award
        db.addRequirement(new RequirementsList("Complete two personal challenges which you and your leader agree on.", 2, 7));

        //Scouts Skills Challenge Award
        db.addRequirement(new RequirementsList("Regularly take part in physical activities over a period of four to six weeks.", 1, 8));
        db.addRequirement(new RequirementsList("Show you understand why eating a sensible diet and getting enough sleep is important.", 1, 8));
        db.addRequirement(new RequirementsList("Do some research so that you can explain the dangers and harmful effects of smoking, alcohol and drugs.", 1, 8));
        db.addRequirement(new RequirementsList("Learn and use at least five of these skills:\n" +
                "a. Mend or customise an item of clothing\n" +
                "b. Cook and serve a two-course meal, for at least four people\n" +
                "c. Fix a puncture or a dropped chain on a bike\n" +
                "d. Wash up after a meal, making sure everything is clean and dry\n" +
                "e. Use a washing machine to wash a load of clothes\n" +
                "f. Iron your uniform shirt\n" +
                "g. Change a lightbulb, in a ceiling light\n" +
                "h. Set a heating timer and thermostat as needed for the time of year\n" +
                "i. Clean a toilet, hob or oven\n" +
                "j. Do another similar home skill", 5, 8));
        db.addRequirement(new RequirementsList("Take part in at least three activities that require a number of problem-solving skills.", 3, 8));

        //Scouts Team Leader Challenge Award
        db.addRequirement(new RequirementsList("Successfully lead a Scout team at a camp or all day event.", 1, 9));
        db.addRequirement(new RequirementsList("Help a new Scout to be part of the Troop with an understanding of what is expected of them.", 1, 9));
        db.addRequirement(new RequirementsList("Help another Scout to develop a Scouting skill.", 1, 9));
        db.addRequirement(new RequirementsList("Represent the views of other Scouts.", 1, 9));

        //Scouts Teamwork Challenge Award
        db.addRequirement(new RequirementsList("On at least three separate occasions, be part of a Scout team, where you work together to achieve a goal.", 3, 10));
        db.addRequirement(new RequirementsList("Give at least three examples of when you’ve been in different types of teams.", 3, 10));
        db.addRequirement(new RequirementsList("Take part in at least three team building activities that you have not tried before.", 3, 10));
        db.addRequirement(new RequirementsList("Take an active part in at least four Troop or Patrol Forums.", 4, 10));

        //Scouts World Challenge Award
        db.addRequirement(new RequirementsList("Choose an aspect of local community life and find out as much as you can about it.", 1, 11));
        db.addRequirement(new RequirementsList("Spend a day volunteering with and finding out about a service in your local community.", 1, 11));
        db.addRequirement(new RequirementsList("Take part in an activity that reflects upon and explores your own beliefs, attitudes and values (this may or may not include religious beliefs).", 1, 11));
        db.addRequirement(new RequirementsList("Take part in an activity that explores common beliefs and attitudes towards gender or disability in different societies.", 1, 11));
        db.addRequirement(new RequirementsList("Take an active part in an environmental project.", 1, 11));
        db.addRequirement(new RequirementsList("Investigate and try to make contact with Scouts in another country.", 1, 11));
        db.addRequirement(new RequirementsList("Take part in an activity that explores an international issue.", 1, 11));
    }
    public static void addStagedRequirements(DBHelper db) {
        //Staged Awards Stage 1
        // add other stages at later date.
//    "Air Activities"
        db.addRequirement(new RequirementsList("Make an aircraft out of paper and see how well it flies.", 1, 12));
        db.addRequirement(new RequirementsList("Identify four different types of aircraft.", 4, 12));
        db.addRequirement(new RequirementsList("Describe any aircraft (including fictional) you’d like to fly in and explain why.", 1, 12));
        db.addRequirement(new RequirementsList("Talk to someone who's flown in an aircraft.", 1, 12));
        db.addRequirement(new RequirementsList("Learn about the different jobs an aircraft can do.", 1, 12));


//    "Community"
        db.addRequirement(new RequirementsList("Identify need. Investigate what issues and challenges exist in your chosen community – it could be local, national or international.", 1, 13));
        db.addRequirement(new RequirementsList("Plan action. Decide what issue your section should take action on and what you want to change. Talk to your section about what actions you would like to take.", 1, 13));
        db.addRequirement(new RequirementsList("Take action over three months. You should:\n" +
                "a. spend at least four hours personally taking action on your chosen issue. You can achieve more impact by spreading your time out over a month, instead of doing it all in one go.\n" +
                "b. involve others in the action. Work in a team with your section and preferably people in the community you are trying to help.", 1, 13));
        db.addRequirement(new RequirementsList("Learn and make more change. Discuss with your section what you’ve learned, how you have made people’s lives better, how taking action has developed you, and what you could do to help even more people in your chosen community.", 1, 13));
        db.addRequirement(new RequirementsList("Tell the world. Help other people to understand why the issue you took action on is important, what you did and how they can help.", 1, 13));

//    "Digital Citizen"
        db.addRequirement(new RequirementsList("In a creative way of your choice, map out your regular digital actions, such as searches, clicks or posts.", 1, 14));
        db.addRequirement(new RequirementsList("Learn how to create a secure password and make one of your own.", 1, 14));
        db.addRequirement(new RequirementsList("Share three ways young people can respond positively when someone’s being unkind or dishonest, including telling an adult if someone’s making you feel uncomfortable.", 1, 14));
        db.addRequirement(new RequirementsList("Use an online service to learn a new skill and show others what you’ve learnt.", 1, 14));


//    "Digital Maker"
        db.addRequirement(new RequirementsList("Show that you know what a computer is and understand that there are lots of uses for digital technology in everyday life.", 1, 15));
        db.addRequirement(new RequirementsList("Create a graphic for a computer game, app, or website.", 1, 15));
        db.addRequirement(new RequirementsList("Write clear instructions for a computer or person to follow to complete a task.", 1, 15));

//    "Emergency Aid"
        db.addRequirement(new RequirementsList("Explain to your leader or another adult about:\n" +
                "a. the importance of getting help\n" +
                "b. what to say when you call 999\n" +
                "c. helping someone who is unconscious\n" +
                "d. helping someone who is bleeding\n" +
                "e. reassuring someone at the scene of an emergency.", 1, 16));

//    "Hikes Away"
        db.addRequirement(new RequirementsList("Complete 1 hike or journey to earn this badge:", 1, 17));

//    "Musician"
        db.addRequirement(new RequirementsList("Skill\n" +
                "a. Listen to a short tune of a couple of lines and then sing it back.\n" +
                "b. Listen to another tune and then beat or clap out the rhythm.", 1, 18));
        db.addRequirement(new RequirementsList("Performance\n" +
                "a. Sing or play two different types of song or tune on your chosen instrument – remember your voice is an instrument too. You must perform in front of other people, either in Scouting or at a public performance such as a group show or school concert.", 1, 18));
        db.addRequirement(new RequirementsList("Knowledge\n" +
                "a. Demonstrate some of the musical exercises that you use to practice your skills.\n" +
                "b. Talk about your instrument and why you enjoy playing it. Alternatively, you could talk about the songs you sing and why you enjoy singing them.", 1, 18));
        db.addRequirement(new RequirementsList("Interest\n" +
                "a. Tell your assessor about the music that you most like to listen to.", 1, 18));

//    "Nautical"
        db.addRequirement(new RequirementsList("Take part in a water activity taster session.", 1, 19));
        db.addRequirement(new RequirementsList("Correctly identify the different equipment used for the activity you chose.", 1, 19));
        db.addRequirement(new RequirementsList("Gain an understanding of the safety equipment used.", 1, 19));

//    "Navigator"
        db.addRequirement(new RequirementsList("Locate yourself on a simple map.", 1, 20));
        db.addRequirement(new RequirementsList("Identify a number of features or locations on that map.",1, 20));
        db.addRequirement(new RequirementsList("Learn the four cardinal points of a compass.", 1, 20));
        db.addRequirement(new RequirementsList("Draw a simple map of where you live, your meeting place or another area local to you.", 1, 20));
        db.addRequirement(new RequirementsList("Use a map during an outdoor activity.", 1, 20));
        db.addRequirement(new RequirementsList("Show you understand how to dress appropriately and what equipment you and the adults will need on the activity.", 1, 20));

//    "Nights Away"
        db.addRequirement(new RequirementsList("Spend 1 night away at a Scouting activity", 1, 21));

//    "Paddle Sports"
        db.addRequirement(new RequirementsList("Choose 1 of the following options:\n" +
                "1. Complete the following badge requirements:\n" +
                "a. Identify different types of paddle craft.\n" +
                "b. Name three places you could safely go canoeing or kayaking.\n" +
                "c. Take part in a warm-up activity to prepare you for canoeing or kayaking.\n" +
                "d. You could practice balancing whilst kneeling, getting in and out of a boat or practising a paddling action.\n" +
                "e. Dress appropriately for your chosen activity. Show you know the importance of buoyancy aids and how to put one on correctly.\n" +
                "f. Take part in a taster session that covers:\n" +
                "g. Naming equipment used and the parts of the boat.\n" +
                "h. Getting into and out of a boat safely.\n" +
                "i. Balancing a boat.\n" +
                "j. Manoeuvring your boat in different directions, including moving forward.\n" +
                "2. Gain the British Canoeing Paddle Start Award.", 1, 22));

//    "Sailing"
        db.addRequirement(new RequirementsList("Choose 1 of the following options:\n" +
                "1. Complete the following badge requirements:\n" +
                "a. Identify different types of sailing crafts.\n" +
                "b. Name three places you could safely go sailing.\n" +
                "c. Take part in a warm-up activity to prepare you for a sailing activity. You could try tacking and gybing, hiking out or syncro-jump to cross the boat together in a tack or gybe.\n" +
                "d. Dress appropriately for a sailing activity, showing you know the importance of buoyancy aids and how to put one on correctly.\n" +
                "e. Take part in a taster session that covers:\n" +
                "-- Being able to name equipment used and parts of the boat.\n" +
                "-- Getting into and out of a boat safely.\n" +
                "-- Balancing a boat.\n" +
                "-- Manoeuvring your boat in different directions, including moving forward.\n" +
                "2. Complete the RYA Youth Sailing Scheme Stage 1.", 1, 23));

//    "Snowsports"
        db.addRequirement(new RequirementsList("Identify different types of snowsports.", 1, 24));
        db.addRequirement(new RequirementsList("Name three places you could safely take part in snowsport activities.", 1, 24));
        db.addRequirement(new RequirementsList("Take part in a warm-up activity to prepare you for a snowsport activity such as skiing or snowboarding.", 1, 24));
        db.addRequirement(new RequirementsList("Dress properly for your chosen activity.", 1, 24));
        db.addRequirement(new RequirementsList("Take part in a taster session that covers:\n" +
                "a. Naming equipment used\n" +
                "b. How to get in and out of your skis or snowboard\n" +
                "c. Balancing on your skis or snowboard\n" +
                "d. Moving around on your skis or snowboard including;\n" +
                "-- Moving in a straight line\n" +
                "-- Controlling your speed\n" +
                "-- Stopping", 1, 24));

//    "Swimmer"
        db.addRequirement(new RequirementsList("Staying safe near water is very important. Learn how you can stay safe while being in or around water.", 1, 25));
        db.addRequirement(new RequirementsList("Warming up before swimming helps you move better in the water. Discover ways to stretch your body as part of your warm up before getting in.", 1, 25));
        db.addRequirement(new RequirementsList("It’s time to get in and have a splash around. Learn how to get safely in and out of the water, with support if needed.", 1, 25));
        db.addRequirement(new RequirementsList("Swimming can mean that you need to have your head under the water. Take part in an activity to help you build your confidence in getting your hair wet.", 1, 25));
        db.addRequirement(new RequirementsList("Moving away from the wall can help you feel more confident in the water. Push away from the wall on either your front or back, then glide further into the middle of the pool.", 1, 25));
        db.addRequirement(new RequirementsList("Floating in the water can help you stay safe, but is also lots of fun. Float on your back or front, with or without support.", 1, 25));
        db.addRequirement(new RequirementsList("Moving through the water helps you get to different places. Swim at least 5 metres with or without support from a float.", 1, 25));
        db.addRequirement(new RequirementsList("Being in the water helps build your confidence but is also good fun. Play a game in the water with your friends and practice the skills you've learned.", 1, 25));

//    "Time On The Water"
        db.addRequirement(new RequirementsList("Complete 1 session on the water to earn this badge", 1, 26));
    }
    public static void addActivityRequirements(DBHelper db){
        //https://www.scouts.org.uk/scouts/activity-badges/
//    Activity awards
//    "Activity Center Service"
//    "Air or Sea Navigation"
//    "Air Researcher"
//    "Air Spotter"
//    "Angler"
//    "Artist"
//    "Astronomer"
//    "Athletics"
//    "Athletics Plus"
//    "Camper"
//    "Caver"
//    "Chef"
//    "Circus Skills"
//    "Climber"
//    "Communicator"
//    "Craft"
//    "Cyclist"
//    "DIY"
//    "Dragon Boating"
//    "Electronics"
//
//    "Entertainer"
//    "Environmental Conservation"
//    "Equestrian"
//    "Farming"
//    "Fire Safety"
//    "Forrester"
//    "Fundraising"
//    "Geocaching"
//    "Global Issues"
//    "Hill Walker"
//
//    "Hobbies"
//    "International"
//    "Librarian"
//    "Lifesaver"
//    "Local Knowledge"
//    "Martial Arts"
//    "Master at Arms"
//    "Mechanic"
//    "Media Relations"
//    "Meteorologist"
//
//    "Model Maker"
//    "Money Skills"
//    "My Faith"
//    "Naturalist"
//    "Orienteer"
//    "Parascending"
//    "Photographer"
//    "Physical Recreation"
//    "Pioneer"
//    "Power Coxswain"
//
//    "Pulling"
//    "Quartermaster"
//    "Scientist"
//    "Sports Enthusiast"
//    "Street Sports"
//    "Survival Skills"
//    "Water Activities"
//    "World Faiths"
//    "Writer"

    }
}
