package com.example.scoutbadgetrackerapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addData {
    public static void addGroups(DBHelper db) {
        //Groups
        db.addGroup(new GroupList("Anchorsholme Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton-Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("2nd Cleveleys Scout Group", "Wyre", "West Lancashire"));
        db.addGroup(new GroupList("1st Thornton Scout Group", "Wyre", "West Lancashire"));

        db.addGroup(new GroupList("1st Norbreck","Blackpool","West Lancashire"));
        db.addGroup(new GroupList("8th Blackpool","Blackpool","West Lancashire"));
        db.addGroup(new GroupList("1st Staining","Blackpool","West Lancashire"));
        db.addGroup(new GroupList("57th Blackpool","Blackpool","West Lancashire"));

        db.addGroup(new GroupList("Ashton Central","Preston & District","West Lancashire"));
        db.addGroup(new GroupList("2nd Ashton","Preston & District","West Lancashire"));
        db.addGroup(new GroupList("1st Broughton","Preston & District","West Lancashire"));
        db.addGroup(new GroupList("1st Catforth","Preston & District","West Lancashire"));

        db.addGroup(new GroupList("1st Croxteth Park","Liverpool North","Merseyside"));
        db.addGroup(new GroupList("2nd Fairfield Scout","Liverpool North","Merseyside"));
        db.addGroup(new GroupList("3rd Knowsley Scout","Liverpool North","Merseyside"));
        db.addGroup(new GroupList("12th Fairfield Scout","Liverpool North","Merseyside"));

        db.addGroup(new GroupList("26th St Helens","St Helens","Merseyside"));
        db.addGroup(new GroupList("25th St Helens","St Helens","Merseyside"));
        db.addGroup(new GroupList("23rd St Helens","St Helens","Merseyside"));
        db.addGroup(new GroupList("21st St Helens","St Helens","Merseyside"));

    }

    public static void addBadges(DBHelper db) {

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
        db.addBadge(new BadgeList("World", "challenge", "challenge_sc_world"));//11

        //Staged Awards
        db.addBadge(new BadgeList("Air Activities", "staged", "staged_air_activities"));//12
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
        db.addBadge(new BadgeList("Time On The Water", "staged", "staged_time_on_the_water"));//26

        //Activity Awards
        db.addBadge(new BadgeList("Activity Center Service", "activity", "activity_sc_activitycenterservice"));//27
        db.addBadge(new BadgeList("Air or Sea Navigation", "activity", "activity_sc_air_or_sea_nav"));
        db.addBadge(new BadgeList("Air Researcher", "activity", "activity_sc_air_researcher"));
        db.addBadge(new BadgeList("Air Spotter", "activity", "activity_sc_air_spotter"));
        db.addBadge(new BadgeList("Angler", "activity", "activity_sc_angler"));
        db.addBadge(new BadgeList("Artist", "activity", "activity_sc_artist"));
        db.addBadge(new BadgeList("Astronomer", "activity", "activity_sc_astronomer"));
        db.addBadge(new BadgeList("Athletics", "activity", "activity_sc_athletics"));
        db.addBadge(new BadgeList("Athletics Plus", "activity", "activity_sc_athleticsplus"));
        db.addBadge(new BadgeList("Camper", "activity", "activity_sc_camper"));//36

        db.addBadge(new BadgeList("Caver", "activity", "activity_sc_caver"));
        db.addBadge(new BadgeList("Chef", "activity", "activity_sc_chef"));
        db.addBadge(new BadgeList("Circus Skills", "activity", "activity_sc_circus_skills"));
        db.addBadge(new BadgeList("Climber", "activity", "activity_sc_climber"));
        db.addBadge(new BadgeList("Communicator", "activity", "activity_sc_communicator"));
        db.addBadge(new BadgeList("Craft", "activity", "activity_sc_craft"));
        db.addBadge(new BadgeList("Cyclist", "activity", "activity_sc_cyclist"));
        db.addBadge(new BadgeList("DIY", "activity", "activity_sc_diy"));
        db.addBadge(new BadgeList("Dragon Boating", "activity", "activity_sc_dragon_boating"));
        db.addBadge(new BadgeList("Electronics", "activity", "activity_sc_electronics"));//46

        db.addBadge(new BadgeList("Entertainer", "activity", "activity_sc_entertainer"));
        db.addBadge(new BadgeList("Environmental Conservation", "activity", "activity_sc_environmental_conservation"));
        db.addBadge(new BadgeList("Equestrian", "activity", "activity_sc_equestrian"));
        db.addBadge(new BadgeList("Farming", "activity", "activity_sc_farming"));
        db.addBadge(new BadgeList("Fire Safety", "activity", "activity_sc_fire_safety"));
        db.addBadge(new BadgeList("Forrester", "activity", "activity_sc_forrester"));
        db.addBadge(new BadgeList("Fundraising", "activity", "activity_sc_fundraising"));
        db.addBadge(new BadgeList("Geocaching", "activity", "activity_sc_geocaching"));
        db.addBadge(new BadgeList("Global Issues", "activity", "activity_sc_global_issues"));
        db.addBadge(new BadgeList("Hill Walker", "activity", "activity_sc_hill_walker"));//56

        db.addBadge(new BadgeList("Hobbies", "activity", "activity_sc_hobbies"));
        db.addBadge(new BadgeList("International", "activity", "activity_sc_international"));
        db.addBadge(new BadgeList("Librarian", "activity", "activity_sc_librarian"));
        db.addBadge(new BadgeList("Lifesaver", "activity", "activity_sc_lifesaver"));
        db.addBadge(new BadgeList("Local Knowledge", "activity", "activity_sc_local_knowledge"));
        db.addBadge(new BadgeList("Martial Arts", "activity", "activity_sc_martial_arts"));
        db.addBadge(new BadgeList("Master at Arms", "activity", "activity_sc_master_at_arms"));
        db.addBadge(new BadgeList("Mechanic", "activity", "activity_sc_mechanic"));
        db.addBadge(new BadgeList("Media Relations", "activity", "activity_sc_mediarelations"));
        db.addBadge(new BadgeList("Meteorologist", "activity", "activity_sc_meteorologist"));//66

        db.addBadge(new BadgeList("Model Maker", "activity", "activity_sc_model_maker"));
        db.addBadge(new BadgeList("Money Skills", "activity", "activity_sc_money_skills"));
        db.addBadge(new BadgeList("My Faith", "activity", "activity_sc_my_faith"));
        db.addBadge(new BadgeList("Naturalist", "activity", "activity_sc_naturalist"));
        db.addBadge(new BadgeList("Orienteer", "activity", "activity_sc_orienteer"));
        db.addBadge(new BadgeList("Parascending", "activity", "activity_sc_parascending"));
        db.addBadge(new BadgeList("Photographer", "activity", "activity_sc_photographer"));
        db.addBadge(new BadgeList("Physical Recreation", "activity", "activity_sc_physical_recreation"));
        db.addBadge(new BadgeList("Pioneer", "activity", "activity_sc_pioneer"));
        db.addBadge(new BadgeList("Power Coxswain", "activity", "activity_sc_power_coxswain"));//76

        db.addBadge(new BadgeList("Pulling", "activity", "activity_sc_pulling"));
        db.addBadge(new BadgeList("Quartermaster", "activity", "activity_sc_quartermaster"));
        db.addBadge(new BadgeList("Scientist", "activity", "activity_sc_scientist"));
        db.addBadge(new BadgeList("Sports Enthusiast", "activity", "activity_sc_sports_enthusiast"));
        db.addBadge(new BadgeList("Street Sports", "activity", "activity_sc_street_sports"));
        db.addBadge(new BadgeList("Survival Skills", "activity", "activity_sc_survival_skills"));
        db.addBadge(new BadgeList("Water Activities", "activity", "activity_sc_wateractivities"));
        db.addBadge(new BadgeList("World Faiths", "activity", "activity_sc_world_faiths"));
        db.addBadge(new BadgeList("Writer", "activity", "activity_sc_writer"));//85

        //External Awards
        db.addBadge(new BadgeList("Duke Of Edinburgh: Bronze", "external", "dofe_bronze"));
        db.addBadge(new BadgeList("Duke Of Edinburgh: Silver", "external", "dofe_silver"));
        db.addBadge(new BadgeList("Duke Of Edinburgh: Gold", "external", "dofe_gold"));//88

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
                "d. Learn what to do at Investiture.", 1, 1));
        db.addRequirement(new RequirementsList("Become a Scout by making the Promise.", 1, 1));

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
//    "Activity Center Service"27
        db.addRequirement(new RequirementsList("Help the warden or manager and work for at least five days at a permanent District, County or National Scout campsite.", 1, 27));
        db.addRequirement(new RequirementsList("Explain and show how to use three items of equipment that you’ve used in your work with the activity centre.", 1, 27));
        db.addRequirement(new RequirementsList("Choose four of these to explain and if possible demonstrate:\n" +
                "a. the use of computers in campsite management\n" +
                "b. how to take care of campers’ needs while respecting wildlife\n" +
                "c. how to prevent frozen pipes and what to do if an overground pipe bursts or leaks\n" +
                "d. the need for good site drainage and clear ditches\n" +
                "e. rubbish disposal and how to recycle materials wherever possible\n" +
                "f. the reasons for having clean toilets\n" +
                "g. how to unblock a drain\n" +
                "h. how to use and maintain equipment for an activity you have become familiar with\n" +
                "i. other important tasks that the warden or manager has pointed out", 4, 27));
        db.addRequirement(new RequirementsList("Talk about developments and improvements you would like to see at a permanent campsite.", 1, 27));
//    "Air or Sea Navigation"28
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Air navigation\n" +
                "a. Using simple diagrams, illustrate latitude and longitude.\n" +
                "b. Using a compass, show how an aircraft can be turned on to various compass headings.\n" +
                "c. Show that you understand how a compass works, including the difference between magnetic, true and grid north.\n" +
                "e. Explain what magnetic deviation is and how it applies to air navigation.\n" +
                "f. Show that you know about the latest developments in electronic navigation aids.\n" +
                "g. You’ll be given three headings and corresponding tracks.\n" +
                "h. For each one, work out the amount of drift in degrees and the type. Show your answers by drawing a simple diagram.\n" +
                "g. Choose one of these to do:\n" +
                "-- On a topographical air map, draw a track for an imaginary flight of at least 80 nautical miles. Point out the landmarks that would show up on both sides of the track, in clear visibility, at an altitude of about 600 feet.\n" +
                "-- Identify landmarks on a topographical air map that you would see during a flight of about half an hour in clear weather.\n" +
                "h. On a topographical map, draw the track between any two places at least 100 nautical miles apart.\n" +
                "i. Show that you can calculate overhead flight times, using the air speed of an aircraft, and adjust your calculations for head and tail winds of different speeds.\n" +
                "2. Sea navigation\n" +
                "a. Gain a good working knowledge of charts, chart datum and symbols used.\n" +
                "b. Show your skills in compass work by completing all of these tasks:\n" +
                "-- Read a mariner’s compass marked in points and degrees and demonstrate your knowledge of compasses generally.\n" +
                "-- Show how compass error can be found from a transit bearing.\n" +
                "-- Show that you know about variation and avoiding deviation.\n" +
                "-- Show you’re able to correct a magnetic compass course for variation and deviation to obtain a true bearing.\n" +
                "Next, adjust a given true bearing to obtain a compass course.\n" +
                "c. Complete two of these tasks:\n" +
                "-- Show how a position can be found from two intersecting position lines.\n" +
                "-- Plot a position using the ‘running fix’ method.\n" +
                "-- Learn what is meant by a ‘cocked hat’ position and how to use it safely. Plot a position from any three cross bearings.\n" +
                "-- Plot a position using a combination of compass bearings and one or more of these: satellite navigation system, vertical sextant angle, horizontal sextant angle, line of sounding or transits.\n" +
                "d. Learn how to use tide tables and tidal stream atlases.\n" +
                "e. Learn how to use the marine log to obtain distance run and speed.\n" +
                "f. Show you understand the buoyage system for United Kingdom coastal waters and other methods of marking dangers and channels.\n" +
                "g. Show that you’re aware of the latest developments in electronic technology, like the Global Positioning System and electronic charts.\n" +
                "h. Go on a coastal voyage of between four and six hours and act as the navigator.\n" +
                "You should keep a log showing courses steered, distance run, navigation marks passed and weather experienced. During the voyage:\n" +
                "-- plot the estimated position every hour by keeping up the dead reckoning\n" +
                "-- at least once per hour, and whenever appropriate, plot an observed position by bearings or other means of obtaining a fix.", 1, 28));
//    "Air Researcher"
        db.addRequirement(new RequirementsList( "Research one historical aspect of flight, and share what you find out with the Troop.\n", 1, 29));
        db.addRequirement(new RequirementsList( "While you’re doing your research, visit at least one place of interest that relates to your chosen subject.", 1, 29));
        db.addRequirement(new RequirementsList( "Present what you have found out.", 1, 29));
//    "Air Spotter"
        db.addRequirement(new RequirementsList( "Learn to recognise 38 out of the 50 aircraft listed in The Scout Association’s Aircraft Recognition List:\n" +
                "Combat aircraft:\n" +
                "BAe Hawk\n" +
                "BAe / McDonnell-Douglas Harrier II   \n" +
                "Lockheed F-16 Fighting Falcon   \n" +
                "Lockheed Martin F35 Lightning II   \n" +
                "Eurofighter Typhoon   \n" +
                "McDonnell-Douglas F-15 Eagle   \n" +
                "McDonnell-Douglas F-18 Hornet   \n" +
                "Panavia Tornado   \n" +
                "SAAB Gripen   \n" +
                "McDonnell Douglas F-22 Raptor   \n" +
                "Dassault Rafale   \n" +
                "Sukhoi Su-27/30/35 Flanker\n" +
                "Large military aircraft:\n" +
                "Airbus A400M   \n" +
                "Boeing C-17 Globemaster   \n" +
                "Boeing KC-135 Stratotanker/E-3 Sentry   \n" +
                "Lockheed C-130/J Hercules\n" +
                "Light and executive aircraft:  \n" +
                "BAe Jetstream 32/41   \n" +
                "Cessna 150/152   \n" +
                "Grumman Gulfstream 2/3/4/5   \n" +
                "Piper PA-28 Cherokee   \n" +
                "Beechcraft King Air   \n" +
                "Robin DR.400/500 series   \n" +
                "Cessna Citation   \n" +
                "LearJet   \n" +
                "Grob Tutor G115E\n" +
                "Helicopters:\n" +
                "McDonnell Douglas MD-900 Explorer   \n" +
                "Boeing-Vertol CH-47 Chinook   \n" +
                "Eurocopter EC135   \n" +
                "McDonnell-Douglas AH-64 Apache   \n" +
                "Robinson R-22/R-44   \n" +
                "Aérospatiale SA 330 Puma   \n" +
                "Agusta Westland Merlin   \n" +
                "Sikorsky UH-60 Black Hawk   \n" +
                "Sikorsky S-92\n" +
                "Airliners:   \n" +
                "Aerospatiale/Aeritalia ATR-42/72   \n" +
                "Airbus A-318/319/320/321   \n" +
                "Airbus A-350   \n" +
                "Airbus A-380   \n" +
                "Boeing 737   \n" +
                "Boeing 747   \n" +
                "Boeing 777   \n" +
                "Boeing 787 Dreamliner   \n" +
                "Bombardier Dash-8   \n" +
                "Bombardier Regional Jet CRJ700/900/1000   \n" +
                "Embraer ERJ-135/145   \n" +
                "Embraer EMB170/190\n" +
                "Heritage aircraft:   \n" +
                "Hawker Hurricane   \n" +
                "Supermarine Spitfire   \n" +
                "Avro Vulcan   \n" +
                "Avro Lancaster", 1, 30));
        db.addRequirement(new RequirementsList( "Complete one of these activities:\n" +
                "a. By yourself or with another Scout, take photographs or collect pictures of at least 10 different aircraft types. Name the different types and their uses.\n" +
                "b. Visit an airfield or airport viewing area, for at least an hour. Record the aircraft you spot, including any distinctive features and the aircraft’s approximate heading.", 1, 30));
        db.addRequirement(new RequirementsList( "Complete one of these activities: (2)\n" +
                "a. Recognise and name the national aircraft markings, both service and civil, of at least six countries including the United Kingdom.\n" +
                "b. Learn the RAF and NATO system of letter designation according to aircraft function. Give examples of three designations.\n" +
                "c. Name three basic training aircraft used in private flying. Give a brief report on one, naming a club and airfield where it is used.\n" +
                "d. Try to make it a local airfield, if possible", 1, 30));
        db.addRequirement(new RequirementsList( "Describe the main features you would use to recognise six aircraft, which have been chosen by an appropriate adult.", 6, 30));
//    "Angler"
        db.addRequirement(new RequirementsList( "Learn the water safety rules and the proper precautions to take when fishing from the bank, shore line, or from a boat.", 1, 31));
        db.addRequirement(new RequirementsList( "Understand how the Countryside code should be followed by anglers, and where and how to fish legally.", 1, 31));
        db.addRequirement(new RequirementsList( "Explain how different species of fish have different habitats.", 1, 31));
        db.addRequirement(new RequirementsList( "Learn about invasive species and make sure to check, clean, and dry your equipment to stop them spreading.", 1, 31));
        db.addRequirement(new RequirementsList( "Learn the basic hygiene precautions to take when fishing.\n", 1, 31));
        db.addRequirement(new RequirementsList( "Go fishing at least four times in two different places.", 4, 31));
        db.addRequirement(new RequirementsList( "Choose the correct equipment and method for where you plan to fish.", 1, 31));
        db.addRequirement(new RequirementsList( "Show you can cast correctly and accurately into a target area a suitable distance away, depending on the equipment you are using.", 1, 31));
        db.addRequirement(new RequirementsList( "Show how to correctly handle a fish, unhook it and return it into the water.", 1, 31));
//    "Artist"
        db.addRequirement(new RequirementsList( "Choose one of the following options:\n" +
                "1. Artist\n" +
                "a. Paint, draw or illustrate each of these:\n" +
                "-- a scene from a story\n" +
                "-- a person or object\n" +
                "-- a landscape\n" +
                "b. Show a selection of your own recent work.\n" +
                "2. Arts enthusiast\n" +
                "a. Choose a favourite art form or artist to take an active interest in. It doesn’t have to be painting – it could be pop music, sculpture, theatre, architecture, break dancing or anything similar.\n" +
                "b. Describe two visits you have made that are connected to your interest. You can use photographs, films, recordings, concert programmes, ticket stubs, newspaper reviews or websites to illustrate your point.\n" +
                "c. Show that you know a lot about an aspect of your interest. You could talk about a particular piece of art, like a painting, performance, sculpture or building. Or you could discuss a particular person or historical period connected with your chosen art form.\n" +
                "d. Make a list of major events, exhibitions or venues connected with your chosen subject. Talk about why the items on your list are important.", 1, 32));
//    "Astronomer"
        db.addRequirement(new RequirementsList( "Find out what happens to an astronaut's body in space. Learn how the lack of gravity affects their body, and what they do to stay fit.", 1, 33));
        db.addRequirement(new RequirementsList( "Learn about the speed of light and how we use light years to measure distances in space.", 1, 33));
        db.addRequirement(new RequirementsList( "Discover how different space technologies have helped us to understand and explore the universe.", 1, 33));
        db.addRequirement(new RequirementsList( "Find out what a black hole is, how it forms and how we can detect where they are in space.", 1, 33));
        db.addRequirement(new RequirementsList( "Discover something new about the universe and share it with the group.", 1, 33));
        db.addRequirement(new RequirementsList( "You can use the stars to find your way. Explore how you can navigate using stars and constellations.", 1, 33));
        db.addRequirement(new RequirementsList( "Imagine you’re planning an expedition to a distant solar system. Design how you’ll travel there and think about what you will need to take with you.", 1, 33));
//    "Athletics"
        db.addRequirement(new RequirementsList( "Run through an appropriate warm-up and warm-down routine using all the main muscle groups. Explain why both routines are important.", 1, 34));
        db.addRequirement(new RequirementsList( "Talk about the safety rules in athletics, particularly throwing and jumping events.", 1, 34));
        db.addRequirement(new RequirementsList( "Take part in six athletics events.", 6, 34));
        db.addRequirement(new RequirementsList( "Find out and explain to your leader how to take part in athletics in your local area.", 1, 34));
//    "Athletics Plus"
        db.addRequirement(new RequirementsList( "Run through an appropriate warm-up and cool-down routine using all the main muscle groups. Explain why both routines are important.", 1, 35));
        db.addRequirement(new RequirementsList( "Talk about the safety rules for athletics, particularly throwing and jumping events.", 1, 35));
        db.addRequirement(new RequirementsList( "Compete in any three events (two track and one field, or vice versa) and gain the points set out as indicated on the score chart available on: https://www.scouts.org.uk/scouts/activity-badges/athletics-plus/ .", 3, 35));
//    "Camper"
        db.addRequirement(new RequirementsList( "Camp as a Scout for at least 15 nights", 1, 36));
        db.addRequirement(new RequirementsList( "Lead a group of Scouts in the pitching, striking and packing of a four to six-person tent.", 1, 36));
        db.addRequirement(new RequirementsList( "Find out what to look for when choosing a campsite and in deciding the best position to pitch tents.", 1, 36));
        db.addRequirement(new RequirementsList( "Show that you understand and act on the reasons for hygiene and the importance of being safe and tidy in camp.", 1, 36));
        db.addRequirement(new RequirementsList( "Show how you store food hygienically at camp.", 1, 36));
        db.addRequirement(new RequirementsList( "Learn how to dispose of your rubbish safely and responsibly.", 1, 36));
        db.addRequirement(new RequirementsList( "Construct two useful camp gadgets.", 2, 36));
        db.addRequirement(new RequirementsList( "Plan a full menu for a weekend camp. Cook a least one full meal over an open fire.", 1, 36));
        db.addRequirement(new RequirementsList( "With others in your Troop, plan and carry out a programme of activities for a weekend camp.", 1, 36));

//    "Caver"
        db.addRequirement(new RequirementsList( "Take part in at least four trips to at least two different cave systems.", 4, 37));
        db.addRequirement(new RequirementsList( "Keep a record of your trips and the routes you followed.", 1, 37));
        db.addRequirement(new RequirementsList( "Develop a good knowledge of the caving and cave conservation codes.", 1, 37));
//    "Chef"
        db.addRequirement(new RequirementsList( "Plan a menu for a weekend event for between four and six people.", 1, 38));
        db.addRequirement(new RequirementsList( "Show how and where to shop for the food and the best way to transport it.", 1, 38));
        db.addRequirement(new RequirementsList( "Show how to store food properly and hygienically to prevent food poisoning and keep it fresh for longer.", 1, 38));
        db.addRequirement(new RequirementsList( "Cook and serve at least one meal from your menu, including at least two courses.", 1, 38));
        db.addRequirement(new RequirementsList( "Demonstrate how to clear up and manage leftovers safely and responsibly.", 1, 38));
//    "Circus Skills"
        db.addRequirement(new RequirementsList( "Select two skills from the table available at: https://www.scouts.org.uk/scouts/activity-badges/circus-skills/", 2, 39));
        db.addRequirement(new RequirementsList( "Carry on putting effort into your two selected skills and show some achievement.", 2, 39));
        db.addRequirement(new RequirementsList( "Demonstrate your two selected skills in front of an audience.", 2, 39));
        db.addRequirement(new RequirementsList( "Find out things about circus life and talk about these with an adult.", 1, 39));
        db.addRequirement(new RequirementsList( "Watch at least two circus or street performance events.", 1, 39));
//    "Climber"
        db.addRequirement(new RequirementsList( "Show that you know the rope types used for rock climbing.", 1, 40));
        db.addRequirement(new RequirementsList( "Show that you know how to fit a climbing helmet and harness and how to tie in correctly.", 1, 40));
        db.addRequirement(new RequirementsList( "Show you understand the calls used in climbing.", 1, 40));
        db.addRequirement(new RequirementsList( "Show that you know how to abseil down a face. It should usually be at least 10 metres high.", 1, 40));
        db.addRequirement(new RequirementsList( "Take part in four or more separate climbs", 4, 40));
        db.addRequirement(new RequirementsList( "Explain the safety rules for climbing on both natural and artificial rock faces.", 1, 40));
        db.addRequirement(new RequirementsList( "Show that you’re aware of environmental issues around climbing on natural rock faces.", 1, 40));
        db.addRequirement(new RequirementsList( "Explain how to care for the equipment you used.", 1, 40));
//    "Communicator"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Radio communication\n" +
                "You automatically gain this activity badge if you already hold, or working towards, any of these qualifications:\n" +
                "-- Radio Amateur Licence (Foundation, Intermediate or Full)\n" +
                "If not, complete all of these:\n" +
                "a. Learn the regulations governing the use of amateur radio equipment.\n" +
                "b. Visit an amateur radio station.\n" +
                "c. Log 25 different amateur radio stations. Note the date, time, call sign, frequency, readability and location. You may include some broadcast stations.\n" +
                "d. Show how to tune a simple communications receiver.\n" +
                "e. Give an example of a typical greetings message.\n" +
                "f. Explain in simple terms how radio waves travel around the world. Learn the more commonly used HF and VHF amateur frequency bands.\n" +
                "g. Learn the phonetic alphabet and define at least eight international Q code signals.\n" +
                "h. Show that you can recognise call signs from the UK and near continent.\n" +
                "2. Communication codes\n" +
                "a. Send and receive a short message by Morse code or semaphore at a rate of five words per minute.\n" +
                "b. Show that you know the proper procedure for sending and receiving a message.\n" +
                "c. Learn the International Phonetic Alphabet and define at least eight international Q code signals.\n" +
                "d. Construct a simple Morse code oscillator and send a short message.\n" +
                "3. Mobile and internet communication\n" +
                "a. Show you know how to use your mobile safely and how to keep it safe.\n" +
                "b. Learn the meaning of these terms: SMS, MMS, 3G, 4G, WAP and Bluetooth.\n" +
                "c. Send a creative text, multimedia or video message to invite a friend to a Scouts event.\n" +
                "d. Manage a mobile phone address book and set up groups of contacts.\n" +
                "e. Show you can text accurately at a rate of 50 characters per minute.\n" +
                "f. Show you know the meaning of some popular chat abbreviations.\n" +
                "g. Share photos and videos of a Scouting activity you’ve been involved with, using available technology.\n" +
                "h. Manage an email address book and set up groups of contacts.\n" +
                "4. Foreign languages\n" +
                "Complete these tasks in any foreign language:\n" +
                "a. Carry on a simple conversation for about 10 minutes.\n" +
                "b. Act as an interpreter for a visitor who does not speak your native language.\n" +
                "c. Write a letter of around 150 words.\n" +
                "d. After a few minutes of study, translate a paragraph of basic text.\n" +
                "e. Communicate with a person who does not speak your native language.\n" +
                "5. Translator\n" +
                "Complete these tasks in braille or a recognised sign language, such  BSL.\n" +
                "a. Carry out a simple conversation for about 10 minutes.\n" +
                "b. Use sign language to describe a Scouting experience to another person.\n" +
                "c. Act as a translator for a short conversation between a sign language user and someone with no sign language experience.\n" +
                "d. Invite a sign language user to talk to your Troop about what it’s like to have impaired hearing or speech. Help by translating for them during their visit.", 1, 41));
//    "Craft"
        db.addRequirement(new RequirementsList( "Make or decorate one or more articles using, whenever possible, original design ideas.", 1, 42));
//    "Cyclist"
        db.addRequirement(new RequirementsList( "Use a bicycle that is properly equipped.", 1, 43));
        db.addRequirement(new RequirementsList( "Show that you can carry out essential maintenance and repair.", 1, 43));
        db.addRequirement(new RequirementsList( "Choose one of the following options:\n" +
                "1. Road cycling\n" +
                "You can automatically complete this option if you gain Bikeability Level 2 or 3. Otherwise, here’s what you need to do for this option:\n" +
                "a. Explain what extra precautions you should take when cycling in the dark or in wet weather. Show you understand why motor vehicles take longer to stop in the wet.\n" +
                "b. Learn the basics of first aid and what to do if an accident happens.\n" +
                "c. Develop a working knowledge of map reading. Orientate a map using a compass or conspicuous features. Estimate distances and times taken to travel.\n" +
                "d. Plan and carry out an all-day cycle ride of at least 40 kilometres (25 miles).\n" +
                "e. Complete one of these:\n" +
                "-- Show you can control a cycle along a slalom course\n" +
                "-- Show you understand the Highway Code, including road signs and helmet use\n" +
                "2. Off-road cycling\n" +
                "a. Show you understand the Mountain Bike Code of Conduct.\n" +
                "b. Show you can control your cycle over different types of terrain.\n" +
                "c. Show you’re aware of the damage that may be caused to the environment through careless cycling across the countryside.\n" +
                "d. Learn the basics of first aid, including the treatment of hypothermia and find out what to do in the case of an accident.\n" +
                "e. Gain a working knowledge of map reading. Orientate a map using a compass or conspicuous features. Estimate distances and times taken to travel.\n" +
                "f. Plan and carry out an all-day ride of at least 30 kilometres (20 miles).\n" +
                "3. External qualifications\n" +
                "You can complete this option if you reach one of these standards:\n" +
                "a. The Gold Trix Award of the British Schools Cycling Association\n" +
                "b. Level 3 Go-MTB Award from Cycling Scotland, Sustrans, CTC or Scottish Cycling MBLA.", 1, 43));
//    "DIY"
        db.addRequirement(new RequirementsList( "Show you can work safely and know about the following:\n" +
                "a. hazard symbols and signs (for dangers such as toxic, flammable, irritant, electrical danger or slippery surface)\n" +
                "b. safety equipment (such as goggles, gloves, masks, ear defenders)\n" +
                "c. how and where to turn off mains supplies (such as water, electricity and gas) to the house.", 1, 44));
        db.addRequirement(new RequirementsList( "Learn how to safely isolate individual electrical circuits at the consumer unit.", 1, 44));
        db.addRequirement(new RequirementsList( "Show or explain how you would deal with four DIY emergencies.", 4, 44));
        db.addRequirement(new RequirementsList( "Take an active part in two major DIY projects, indoors or outside", 2, 44));
//    "Dragon Boating"
        db.addRequirement(new RequirementsList( "Learn:\n" +
                "a. the safety rules\n" +
                "b. the capsize drill\n" +
                "c. the water buddy system", 1, 45));
        db.addRequirement(new RequirementsList( "Learn the procedures for loading, numbering off, stopping, bracing the boat, forward and backward paddling.\n", 1, 45));
        db.addRequirement(new RequirementsList( "Learn the instructions and commands issued by The Dragon Boat Racing Association.", 1, 45));
        db.addRequirement(new RequirementsList( "Carry out practice training sessions for a minimum of two hours.", 1, 45));
        db.addRequirement(new RequirementsList( "Take part in a competitive or timed dragon boat event over a course of at least 250 metres.", 1, 45));
//    "Electronics"
        db.addRequirement(new RequirementsList( "Identify a number of common electronic components that are shown to you.", 1, 46));
        db.addRequirement(new RequirementsList( "Describe the systems used for marking components with their values.", 1, 46));
        db.addRequirement(new RequirementsList( "Show that you know the symbols used to represent common components in circuit diagrams.", 1, 46));
        db.addRequirement(new RequirementsList( "Show that you’re aware of the safe working practices to be followed when handling electronic components, circuit boards and when soldering.", 1, 46));
        db.addRequirement(new RequirementsList( "Use a multimeter to measure voltage, current and resistance in a simple circuit.", 1, 46));
        db.addRequirement(new RequirementsList( "Talk about the main differences in the operation of digital and analogue circuits.", 1, 46));
        db.addRequirement(new RequirementsList( "Construct three simple circuits.", 3, 46));
        db.addRequirement(new RequirementsList( "Explain the principles behind the operation of each circuit and the typical values of voltage and current found in each.", 1, 46));
        db.addRequirement(new RequirementsList( "Explain the possible uses of the three circuits you built.", 1, 46));
        db.addRequirement(new RequirementsList( "Talk about the uses and benefits of electronics in everyday life.", 1, 46));

//
//    "Entertainer"
        db.addRequirement(new RequirementsList( "Choose one of the options. Then, as part of a group, complete all tasks to achieve this badge.\n" +
                "Option 1\n" +
                "a. Write and plan some original entertainment. It could involve a campfire or stage routine involving mime, drama, music, storytelling or conjuring. \n" +
                "b. Rehearse the entertainment and make sure everyone has a job to do. You might need actors, a producer, a stage manager, front of house or a publicity manager.\n" +
                "c. Present the entertainment to an audience at a school, for your Troop or at a parents’ evening.\n" +
                "Option 2\n" +
                "a. Take an active part in a Scout show or another production. You’ll need to commit to regular rehearsals.", 1, 47));
//    "Environmental Conservation"
        db.addRequirement(new RequirementsList( "Find out about an environmental issue that is important to your local community.", 1, 48));
        db.addRequirement(new RequirementsList( "Take part in an activity or project that improves local conservation.", 1, 48));
        db.addRequirement(new RequirementsList( "Get involved in a campaign to make others aware of an environmental issue.", 1, 48));
//    "Equestrian"
        db.addRequirement(new RequirementsList( "Riding\n" +
                "a. Show you can catch a pony from a field or stable and tack up for riding.\n" +
                "b. Walk, trot and canter a pony.\n" +
                "c. Jump over small fences", 1, 49));
        db.addRequirement(new RequirementsList( "Grooming and care\n" +
                "a. Groom your pony correctly and explain why it is necessary.\n" +
                "b. Show you know how to take care of saddlery and other equipment.", 1, 49));
        db.addRequirement(new RequirementsList( "Horse health\n" +
                "a. Find out about some of the basic health issues affecting horses and ponies, including worms and laminitis.\n" +
                "b. Show you have a basic knowledge of grass management.\n" +
                "c. Show you recognise poisonous plants and the results of overgrazing.", 1, 49));
        db.addRequirement(new RequirementsList( "Safety\n" +
                "a. Talk about road safety for riding.\n" +
                "b. Explain why you need a hat, body protector, stirrup and other safety equipment.", 1, 49));
//    "Farming"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Farming\n" +
                "a. Learn about farming practices in your area.\n" +
                "b.Find out about the organisation, as well as the daily and seasonal operations, of a farm of your choice. Make a note of practices relating to livestock, crops, cultivation, rotation, machinery and labour force.\n" +
                "c. Find photos to demonstrate the changes in farming practices that have taken place over a number of years. Explain the reasons for these.\n" +
                "2. Smallholder\n" +
                "a. Cultivate an area of garden or an allotment for a specific length of time. Agree this beforehand with your leadership team.\n" +
                "b. Show you’re successful in growing:\n" +
                "-- three kinds of hardy annual flower\n" +
                "-- three kinds of vegetable\n" +
                "-- two kinds of bulbs, herbaceous plants, flowering shrubs or rose\n" +
                "c. Keep a record of the work you've done and the results you've achieved. This could include height records of the flowers, for example.\n" +
                "3. Livestock\n" +
                "a. Keep any kind of livestock for at least three months. You could:\n" +
                "-- look after farm animals or birds, and learn how they should be housed, fed and bred as well as their economic uses. Show how to handle them safely and learn about animal welfare.\n" +
                "-- manage a hive. Bring in some of the honey you produce.", 1, 50));
//    "Fire Safety"
        db.addRequirement(new RequirementsList( "Explain what action should be taken and why, if you discover a fire has broken out in the home or at camp.", 1, 51));
        db.addRequirement(new RequirementsList( "Explain the process of combustion.", 1, 51));
        db.addRequirement(new RequirementsList( "Be aware of dangers in the home and the fire precautions necessary for:\n" +
                "a. oil heaters and open solid fuel fires\n" +
                "b. portable electric fires\n" +
                "c. drying clothes\n" +
                "d. electric wiring and fuses\n" +
                "e. smoking paraphernalia, particularly matches\n" +
                "f. uses of household gas, including gas fires\n" +
                "g. party decorations and candles\n" +
                "h. closing doors and windows\n" +
                "i. using BBQs", 1, 51));
        db.addRequirement(new RequirementsList( "Explain the benefits of installing smoke detectors.", 1, 51));
        db.addRequirement(new RequirementsList( "Learn what the dangers of campfires are and what precautions should be taken.", 1, 51));
        db.addRequirement(new RequirementsList( "Find out about the causes of heath and grass fires.", 1, 51));
        db.addRequirement(new RequirementsList( "Learn how to make an emergency call for the fire service.", 1, 51));
        db.addRequirement(new RequirementsList( "Identify different fire extinguisher types such as water, dry powder, foam and carbon dioxide.", 1, 51));
        db.addRequirement(new RequirementsList( "Find out what to do if a person’s clothes are on fire.", 1, 51));
        db.addRequirement(new RequirementsList( "Talk to your family about what to do in the event of a fire at home.", 1, 51));

//    "Forrester"
        db.addRequirement(new RequirementsList( "Identify at least eight common types of tree that grow in your area, including both deciduous and coniferous.", 8, 52));
        db.addRequirement(new RequirementsList( "Find out how to identify trees using identification keys.", 1, 52));
        db.addRequirement(new RequirementsList( "Prepare the soil and successfully transplant a young tree.", 1, 52));
        db.addRequirement(new RequirementsList( "Find out how both natural woodland and commercial forests are managed.", 1, 52));
        db.addRequirement(new RequirementsList( "Show how to select, use and care for forestry equipment, and know the safety issues involved.", 1, 52));
        db.addRequirement(new RequirementsList( "Show how to fell and trim out a tree or lay a hedge.", 1, 52));
//    "Fundraising"
        db.addRequirement(new RequirementsList( "Identify and organise a fundraising initiative for either:\n" +
                "a. The Scout Association, to promote and grow Scouting nationally, or your local group to pay for something like new equipment or a new roof. You could even fundraise for both\n" +
                "b. another UK registered charity", 1, 53));
        db.addRequirement(new RequirementsList( "Think of a short reason that tells potential donors about your chosen cause.", 1, 53));
        db.addRequirement(new RequirementsList( "Set a target and a date by which you’ll have raised the money.", 1, 53));
        db.addRequirement(new RequirementsList( "Write a short report about your fundraising challenge and submit it to your leader, Troop Forum or Patrol Leaders’ Council.", 1, 53));
//    "Geocaching"
        db.addRequirement(new RequirementsList( "Show that you know about the Global Positioning System (GPS) by explaining:\n" +
                "a. how it works\n" +
                "b. ownership and control of the system\n" +
                "c. its benefits to society\n" +
                "d. what factors affect its accuracy", 1, 54));
        db.addRequirement(new RequirementsList( "Programme a handheld GPS receiver to:\n" +
                "a. find your location (grid reference, plus latitude and longitude) and record it\n" +
                "b. enter the latitude and longitude coordinates of a nearby point and navigate to the waypoint.\n" +
                "c. enter the grid reference of a local landmark and navigate to the waypoint\n" +
                "d. walk on a bearing using the GPS and a map", 1, 54));
        db.addRequirement(new RequirementsList( "Show that you know the difference between Ordnance Survey, and latitude and longitude coordinates.", 1, 54));
        db.addRequirement(new RequirementsList( "Using an Ordnance Survey map (1:25000 or 1:50000 scale) to plan a route of at least 4km that contains a minimum of 10 waypoints.", 1, 54));
        db.addRequirement(new RequirementsList( "Sign up to a geocaching website.", 1, 54));
        db.addRequirement(new RequirementsList( "Show that you understand the safety and environmental aspects of geocaching, such as the Highway Code, Countryside Code and the Geocaching Association of Great Britain (GAGB) guidelines.", 1, 54));
        db.addRequirement(new RequirementsList( "Find five geocaches using a GPS. At least three geocaches must be ‘multi-caches’, with at least two waypoints.", 5, 54));
        db.addRequirement(new RequirementsList( "With the help of an adult:\n" +
                "a. plan, assemble and hide two caches, one of which should be a multi-cache. Make sure the location is suitable and that other navigators have proper access to the land and terrain\n" +
                "b. either submit your caches to a geocaching website or give the details to other Scouts so they can find the caches", 1, 54));
//    "Global Issues"
        db.addRequirement(new RequirementsList( "Identify where your clothing or other belongings have been made.", 1, 55));
        db.addRequirement(new RequirementsList( "Investigate a recent natural disaster by looking at photos, news articles or videos online.", 1, 55));
        db.addRequirement(new RequirementsList( "Find out about an international sporting event and discuss with your Troop how sporting events can contribute to international peace and understanding.", 1, 55));
        db.addRequirement(new RequirementsList( "In small groups, learn about an international health issue and use a creative way to share what you have learnt with your Troop.", 1, 55));
        db.addRequirement(new RequirementsList( "Think about the rights that we have in the UK (like the right to vote, the right to go to school and equal gender rights).", 1, 55));

//    "Hill Walker"
        db.addRequirement(new RequirementsList( "With others, plan at least five one-day journeys of at least 14km in hilly country.", 5, 56));
        db.addRequirement(new RequirementsList( "Using Ordnance Survey maps, complete all the information you need to plan the route for each journey.", 1, 56));
        db.addRequirement(new RequirementsList( "Learn how to summon help in an emergency.", 1, 56));
        db.addRequirement(new RequirementsList( "Make sure you know the different types of hazards you might come across.", 1, 56));
        db.addRequirement(new RequirementsList( "Before setting out, list the equipment that should be taken and explain how to use them correctly.", 1, 56));
        db.addRequirement(new RequirementsList( "Complete your planned journeys.", 1, 56));
        db.addRequirement(new RequirementsList( "During the journey, show how you use the map and compass.", 1, 56));
        db.addRequirement(new RequirementsList( "Show you’re aware of ways to preserve the natural environment, such as avoiding erosion and conserving wildlife habitats.", 1, 56));
        db.addRequirement(new RequirementsList( "Show that you’re aware of developments in technology, such as the Global Positioning System (GPS), digital mapping or waterproof maps.", 1, 56));
        db.addRequirement(new RequirementsList( "Show that you know the publication Safety on Mountains, published by the British Mountaineering Council.", 1, 56));


//
//    "Hobbies"
        db.addRequirement(new RequirementsList( "Choose one of the following:\n"+
                "Option 1:\n" +
                "a. Take up a hobby or interest that you do not already have an activity badge for.\n" +
                "b. Keep a record of your hobby for at least four months.\n" +
                "Option 2:\n" +
                "a. Make a collection or study of objects for at least four months. You could collect books or magazines, films, cards, key rings, figurines or similar.\n" +
                "b. Talk to your Patrol Leader or your Troop about the collection or study you chose. Explain why you chose your objects and what you like about them.", 1, 57));

//    "International"
        db.addRequirement(new RequirementsList( "Find out about the World Scout Movement’s history and what it does today.", 1, 58));
        db.addRequirement(new RequirementsList( "Take part in a traditional craft or creative activity from another country.\n", 1, 58));
        db.addRequirement(new RequirementsList( "In a language other than your own, introduce yourself and say a few basic, useful, everyday phrases.", 1, 58));
        db.addRequirement(new RequirementsList( "Take part in one of these events individually or with the Troop and report back to other Scouts:\n" +
                "a. Jamboree On The Internet (JOTI) or Jamboree On The Air (JOTA)\n" +
                "b. an international camp held in the UK or abroad\n" +
                "c. a link to Scouts in another country", 1, 58));


//    "Librarian"
        db.addRequirement(new RequirementsList( "Show that you know how to look after books and e-readers.", 1, 59));
        db.addRequirement(new RequirementsList( "Show that you can use a library catalogue.", 1, 59));
        db.addRequirement(new RequirementsList( "Explain how fiction and non-fiction books are arranged on the shelves.", 1, 59));
        db.addRequirement(new RequirementsList( "Learn what is meant by a reference book or material.", 1, 59));
        db.addRequirement(new RequirementsList( "Show how you would search for information using the internet.", 1, 59));
        db.addRequirement(new RequirementsList( "Talk to your assessor about:\n" +
                "-- books you have read and why you enjoyed them\n" +
                "-- information you found from books or websites\n" +
                "-- getting books on the internet for e-readers.", 1, 59));


//    "Lifesaver"
        db.addRequirement(new RequirementsList( "Explain and, if you can, show how you would carry out a rescue from water using these methods:\n" +
                "-- shout\n" +
                "-- reach\n" +
                "-- throwing a buoyant aid\n" +
                "-- throwing a rope\n" +
                "-- wade.", 1, 60));
        db.addRequirement(new RequirementsList( "Reach the standard of one of these:\n" +
                "a. Royal Lifesaving Society UK (RLSS UK) Rookie Lifeguard Gold Level 1 award\n" +
                "b. Royal Lifesaving Society UK (RLSS UK) Survive and Save Silver Medallion award", 1, 60));
        db.addRequirement(new RequirementsList( "Explain and, if you can, show what you would do if:\n" +
                "-- a person fell through ice\n" +
                "-- a pet fell through ice", 1, 60));
//    "Local Knowledge"
        db.addRequirement(new RequirementsList( "Choose one of these three options, depending on where you live, then complete all the tasks.\n" +
                "1. Rural and suburban areas\n" +
                "a. Show that you know the local area surrounding your home or Scout Headquarters, up to a radius of 2 kilometres in suburban districts and 5 kilometres in rural districts. Locate as many as you can of these:\n" +
                "-- doctors, veterinary surgeons, dentists, hospitals and ambulance station\n" +
                "-- fire station, police station, garages, shopping centres, retail parks and convenience stores\n" +
                "-- main bus stops, railway stations and local routes of buses and trains\n" +
                "-- local Scout Headquarters, public parks, theatres, sports and leisure complexes and cinemas\n" +
                "-- places of worship, museums, schools, colleges and local government buildings\n" +
                "-- local routes that take you to the nearest motorway or national routes.\n" +
                "b. Use a street map to point out six locations from step 1. From your home or Scout Headquarters, show the quickest route to one of the places.\n" +
                "2. Urban areas\n" +
                "a. Gain a general knowledge of what parts of the country are served from  your local airport, mainline railway and coach stations.\n" +
                "b. Find out how to reach the local airport, mainline railway and coach stations, and major tourist attractions from your Scout Headquarters or home.\n" +
                "c. Show how to use a map of your district. Use it to point out any six places of interest. Show how to get to these places from your Scout Headquarters or home.\n" +
                "d. Give clear directions to a place of interest 8 kilometres away, to a person travelling by car or public transport.\n" +
                "e. Find out which major local roads link to the motorway and A-road network and the main cities these roads serve.\n" +
                "3. Heritage\n" +
                "a. Study an aspect of national history, local history or family heritage.\n Exhibit or present the results to other people.\n" +
                "b. Over a period agreed with your leader, get involved in a project to help preserve some aspect of national or local heritage.", 1, 61));
//    "Martial Arts"
        db.addRequirement(new RequirementsList( "Take part in a regular martial arts activity that’s recognised by your sports council for at least six months.", 1, 62));
        db.addRequirement(new RequirementsList( "Take part in a competition or demonstration and talk about your performance with an instructor.", 1, 62));
//    "Master at Arms"
        db.addRequirement(new RequirementsList( "Attend regular training sessions in a relevant activity like fencing, shooting or archery.", 1, 63));
        db.addRequirement(new RequirementsList( "Learn the safety rules associated with your activity and show how to follow them.", 1, 63));
        db.addRequirement(new RequirementsList( "Take part in your chosen activity at an officially supervised contest.", 1, 63));
//    "Mechanic"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Motor car\n" +
                "a. Learn the principles of operating an engine. Make sure you understand the function of the clutch, gearbox and rear axle differential.\n" +
                "b. Show how to check and refill the windscreen wash bottle of a car.\n" +
                "c. Show how to change a bulb at the front and in the rear light cluster of a car.\n" +
                "d. Show how to check the level of coolant in the radiator, ‘top up’ the radiator and explain the importance of anti-freeze.\n" +
                "e. Show how to check tyre pressures and inflate a tyre correctly.\n" +
                "f. Remove and replace a road wheel.\n" +
                "g. Explain what to look for when checking that a tyre conforms to the legal requirement. Find out why tyres with different amounts of wear should not be mixed on the same axle.\n" +
                "h. Show how to change a wiper blade.\n" +
                "i. Explain the outline requirements for an MOT road test.\n" +
                "2. Power boat\n" +
                "a. Complete one of these activities (part 1):\n" +
                "-- Discuss the principles and performance of several types of motorboat engines, other than two-stroke. Show that you know the maintenance needed for a familiar type of marine internal combustion engine, other than two-stroke.\n" +
                "-- Assist with the maintenance, dismantle, service and reassemble an outboard engine. Show how to fit it properly to the transom of a boat. Explain how to detect minor faults in starting and running whilst afloat.\n" +
                "b. Complete one of these activities (part 2):\n" +
                "-- As driver or mechanic member of a power boat’s crew, help to prepare the boat for a voyage by checking the engine for possible minor faults, checking the fuel supply and pump and mustering the fire-fighting equipment. Show you know how to leave the engine in a proper manner and how to drain the engine in an emergency.\n" +
                "b. Check the engine of a motorboat in preparation for a cruise or expedition, making sure there is fuel that is stored safely, an adequate tool kit and effective fire-fighting apparatus. Accompany the expedition, either as the mechanic or assistant, and be fully or jointly responsible for the operation, care and maintenance of the engine throughout.\n" +
                "3. Aircraft\n" +
                "a. Learn the basic principles of one these component parts and be able to point them out:\n" +
                "-- an aircraft piston engine\n" +
                "-- an aircraft gas turbine engine.\n" +
                "b. Learn the basic principles of flight and airframe construction of a fixed wing aircraft.\n" +
                "c. Learn and then demonstrate Aircraft Marshalling signals used by day and night.\n" +
                "d. Show you can carry out any four of these:\n" +
                "-- replenishing a light aircraft fuel and oil system safely\n" +
                "-- rigging and de-rigging a glider\n" +
                "-- picketing a light aircraft\n" +
                "-- changing plugs on a light aircraft engine\n" +
                "-- inspecting aircraft main and tail or nose wheel tyres for serviceability\n" +
                "-- repairing a small tear in the fabric surface of a light aircraft or glider\n" +
                "-- checking the control system of a light aircraft or glider for correct sense of movement.\n" +
                "4. Motorcycle or scooter\n" +
                "a. Learn the principles of operating an engine. Learn about the function of the clutch, gearbox, carburettor and transmission of a motorcycle.\n" +
                "b. Remove and replace a road wheel.\n" +
                "c. Check and top up the level of the engine oil.\n" +
                "d. Explain how to adjust the tension of the final drive chain.\n" +
                "e. Show how you change a bulb at the front and in the rear light cluster.\n" +
                "f. Show how you would check tyre pressures and inflate a tyre correctly.\n" +
                "g. Explain what to look for when checking that a tyre conforms to the legal requirement.\n" +
                "h. Explain the outline requirements for an MOT road test.", 1, 64));
//    "Media Relations"
        db.addRequirement(new RequirementsList( "Choose from three of these activities to achieve this badge:\n" +
                "1. Produce and give a presentation about Scouting.\n" +
                "2. Write a press release about a Scout event that has taken place.\n" +
                "3. Find out about local media outlets such as radio, TV, newspapers and online.\n" +
                "4. Prepare and present an audio or video package about a Scouting event or activity.\n" +
                "5. Prepare a creative display about your Troop or Group that can be exhibited in your local library, information centre or public place.\n" +
                "6. Interview a local public figure or someone in the local news, such as a church leader, politician or celebrity and present the interview to your Troop.\n" +
                "7. Produce some media which can be used within Scouting, such as a district newsletter story or a piece of website content.", 3, 65));
//    "Meteorologist"
        db.addRequirement(new RequirementsList( "Explain how each of these are measured:\n" +
                "a. Wind force and direction\n" +
                "b. Cloud type and extent\n" +
                "c. Temperature\n" +
                "d. Pressure\n" +
                "e. Rainfall\n" +
                "f. Humidity", 1, 66));
        db.addRequirement(new RequirementsList( "Record the weather conditions every day for two weeks, or once a week for three months.", 1, 66));
        db.addRequirement(new RequirementsList( "Identify different cloud types.", 1, 66));
        db.addRequirement(new RequirementsList( "What do ‘warm’ and ‘cold’ air masses in summer and winter do to the typical weather in your area?", 1, 66));
        db.addRequirement(new RequirementsList( "Explain how weather forecasts are created.", 1, 66));
        db.addRequirement(new RequirementsList( "Show that you understand a synoptic weather map, including fronts and isobars.", 1, 66));
//
//    "Model Maker"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. General model making\n" +
                "a. Choose one of these activities:\n" +
                "-- Build a model using a plastic or white metal kit or pre-cast figures.\n" +
                "-- Design and construct a model from a wood, plastic or metal construction set, such as Lego or Meccano.\n" +
                "d. Show that you know the different types of kits or parts available in the material you chose.\n" +
                "c. Talk about the experience of building the model with a knowledgeable adult.\n" +
                "2. Model aeroplanes\n" +
                "a. Build a model aeroplane, using a kit if you want to. It must meet one of these target flight performances:\n" +
                "-- A hand-launched glider must fly for 25 seconds.\n" +
                "-- A tow-launched glider must fly for 45 seconds, with 50 metres maximum line length.\n" +
                "-- A rubber-powered aircraft must fly for 30 seconds.\n" +
                "-- An engine-powered aircraft must fly for 45 seconds, with 15 seconds maximum motor run.\n" +
                "-- A control line aircraft must show a smooth take-off and landing, with three laps of level flight at about 2 metres, with a climb and dive.\n" +
                "b. Talk about the experience of building and flying the model with a knowledgeable adult.\n" +
                "3. Model boats\n" +
                "a. Build an electric or engine-powered model boat or yacht at least 45 cm in length. Show that it’s capable of maintaining a straight course of at least 25 metres.\n" +
                "b. Talk about the experience of building the model with a knowledgeable adult.\n" +
                "4. Model cars\n" +
                "a. Choose one of these two activities:\n" +
                "-- Build an electric slot car racer. Drive it a minimum distance of 122 metres on any track, without stopping or leaving the slot more than four times.\n" +
                "-- Build a free running car of any type. Show that it can run for at least 18 metres.\n" +
                "b. Talk about the experience of building the model with a knowledgeable adult.\n" +
                "4. Model trains\n" +
                "a. Build a model coach or wagon. Show that it can run properly behind a scale locomotive.\n" +
                "b. Build a scaled scenic model, such as a station or farmhouse for a railway layout.\n" +
                "c. Talk about the experience of building your models with a knowledgeable adult.", 1, 67));
//    "Money Skills"
        db.addRequirement(new RequirementsList( "Learn about how money is used online.", 1, 68));
        db.addRequirement(new RequirementsList( "Learn how to budget and make smart choices with your money.", 1, 68));
        db.addRequirement(new RequirementsList( "Find out how saving and borrowing money works.", 1, 68));
        db.addRequirement(new RequirementsList( "Explore how money can affect your feelings and wellbeing.", 1, 68));


//    "My Faith"
        db.addRequirement(new RequirementsList( "Take an active part in your place of worship.", 1, 69));
        db.addRequirement(new RequirementsList( "Find out more about the origins of your faith.", 1, 69));
        db.addRequirement(new RequirementsList( "Explore something about the history of your faith at a local, national or international level.", 1, 69));
        db.addRequirement(new RequirementsList( "Explain to an adult some of the teachings of your faith.", 1, 69));
//    "Naturalist"
        db.addRequirement(new RequirementsList( "With appropriate permission, spend at least one day at one of these locations and investigate the wildlife and plants found there:\n" +
                "-- woodland or parkland\n" +
                "-- down land\n" +
                "-- moor land\n" +
                "-- seashore or sand dune\n" +
                "-- hedgerow\n" +
                "-- roadside verge\n" +
                "-- stream, river or canal\n" +
                "--small pond\n" +
                "-- wetland or marshland", 1, 70));
        db.addRequirement(new RequirementsList( "Tell a knowledgeable adult what you discovered.", 1, 70));
        db.addRequirement(new RequirementsList( "Find out more about a plant, animal or particular wildlife from your chosen location.", 1, 70));
        db.addRequirement(new RequirementsList( "Discuss what you found out, giving sources for information from places like museums, field guides or the internet.", 1, 70));
        db.addRequirement(new RequirementsList( "Discuss how human activities or land management can affect wildlife.", 1, 70));
//    "Orienteer"
        db.addRequirement(new RequirementsList( "Learn about the map colours and common symbols used on an orienteering map.", 1, 71));
        db.addRequirement(new RequirementsList( "Orientate a map using either terrain or a compass.", 1, 71));
        db.addRequirement(new RequirementsList( "Complete three courses at orienteering events recognised by British Orienteering or another similar standard.", 3, 71));
        db.addRequirement(new RequirementsList( "Show you know the safety procedures, basic first aid, appropriate clothing and equipment for countryside navigation.", 1, 71));
        db.addRequirement(new RequirementsList( "Show that you know the Countryside Code.", 1, 71));
//    "Parascending"
        db.addRequirement(new RequirementsList( "Take part in a parascending course.", 1, 72));
        db.addRequirement(new RequirementsList( "Learn the main characteristics and different types of parachutes used by parascenders.", 1, 72));
        db.addRequirement(new RequirementsList( "Show a basic knowledge of the theory of flight.", 1, 72));
        db.addRequirement(new RequirementsList( "Experience at least four parascending flights.", 4, 72));
//    "Photographer"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Still photography\n" +
                "a. Choose one of these two activities:\n" +
                "-- Produce 12 photographs, featuring at least two of these photographic techniques: portrait, still life (or similar), landscape or seascape, sport or action, or timelapse.\n" +
                "-- Produce six black and white photographs, based on a theme of your choice. Explain the steps you took to create them and the impact of using black and white as an alternative to colour images. You could produce high quality prints on photographic paper or present them on screen.\n" +
                "b. Show that you know the main settings on a digital camera or a smartphone camera.\n" +
                "-- This should include focus and exposure control, and flash settings. Explain the impact of shutter speed and aperture size on the image.\n" +
                "c. Describe what accessories are available to use with digital cameras or smartphone cameras.\n" +
                "d. Edit a selection of your images, using editing software on a computer or using an app on a smartphone.\n" +
                "-- This could include cropping, colour, contrast or light levels. Explain what you have changed and how it improved your image.\n" +
                "e. Diagnose typical faults that happen at the photographing or editing stages, such as over or under exposure and high or low contrast. Explain how to reduce camera shake and how to respond to subject movement.\n" +
                "f. Show that you know how to care for a digital camera or smartphone camera.\n" +
                "2. Video photography\n" +
                "a. Produce at least two short films from two of these categories.\n" +
                "-- documentary\n" +
                "-- music video\n" +
                "-- drama\n" +
                "-- comedy\n" +
                "-- advertisement\n" +
                "-- training film\n" +
                "Create a storyboard and script for each of these. Edit the film using editing software on a computer or a smartphone app.\n" +
                "b. Show that you understand:\n" +
                "-- camera techniques such as panning, zooming, close-ups, long shots and using additional lighting\n" +
                "-- production techniques such as editing, how to avoid jump cuts and maintaining continuity.\n" +
                "c. Choose one of these two activities:\n" +
                "-- Show that you know how to care for a video camera and accessories, such as storage media, batteries, microphones and lights\n" +
                "-- Discuss the differences between recording video on a video camera, digital camera and a smartphone.", 1, 73));
//    "Physical Recreation"
        db.addRequirement(new RequirementsList( "Regularly take part in an active sport or physical pursuit, which you haven’t already gained an activity badge for.", 1, 74));
        db.addRequirement(new RequirementsList( "Show a reasonable level of skill in your sport or pursuit.", 1, 74));
        db.addRequirement(new RequirementsList( "Explain the rules or guidelines that govern the sport or pursuit you chose.", 1, 74));
        db.addRequirement(new RequirementsList( "Show how you would prepare before taking part in your sport or pursuit.", 1, 74));
        db.addRequirement(new RequirementsList( "Explain how to care for the equipment you use.", 1, 74));
//    "Pioneer"
        db.addRequirement(new RequirementsList( "As a member of a group, take part in:\n" +
                "a. an indoor pioneering project, like constructing a catapult or chariot\n" +
                "b. an outdoor pioneering project, like building a monkey bridge, a raft or parallel runway", 1, 75));
        db.addRequirement(new RequirementsList( "Show the correct way to do these:\n" +
                "a. a whipping or safe rope sealing\n" +
                "b. a splice\n" +
                "c. coiling and storing a rope\n" +
                "d. using levers to extract objects or move heavy weights\n" +
                "e. being safe in pioneering projects. Explain why it’s important.", 1, 75));
        db.addRequirement(new RequirementsList( "Name and tie at least six knots and three lashings that are useful in pioneering.", 9, 75));
//    "Power Coxswain"
        db.addRequirement(new RequirementsList( "Here's what you need to do:\n" +
                "-- choose and identify the boat you will use\n" +
                "-- choose the waters you will visit\n" +
                "-- identify the features and hazards of this water\n" +
                "-- learn what the rules are for boating on the water you're using\n" +
                "-- wear the proper clothing and make sure you have the correct equipment for your craft", 1, 76));
        db.addRequirement(new RequirementsList( "Complete the following steps under proper supervision:\n" +
                "-- Locate the engine and know how to start and stop it safely.\n" +
                "-- Take part in a man overboard drill.\n" +
                "-- Launch and recover your craft (if that’s possible) and come alongside. Moor and berth your craft.\n" +
                "-- Control the speed and direction of your craft to steer around a course or on a journey.", 1, 76));
//
//    "Pulling"
        db.addRequirement(new RequirementsList( "Show that you understand the equipment required for the activity.", 1, 77));
        db.addRequirement(new RequirementsList( "Show that you can take part in this activity safely.", 1, 77));
        db.addRequirement(new RequirementsList( "Carry out these manoeuvres in sequence:\n" +
                "-- take the boat away from a bank side mooring\n" +
                "-- row in a straight line for 100 metres\n" +
                "-- complete a figure-of-eight course\n" +
                "-- come alongside in your dinghy, to moor at a ring, post, bollard or buoy, using a round turn and two half hitches", 1, 77));
        db.addRequirement(new RequirementsList( "Complete two of these tasks:\n" +
                "a. scull over the stern between two points, 20 metres apart, and turn through 180 degrees\n" +
                "b. draw stroke over the bow\n" +
                "c. take a place as an oarsman, including stroke\n" +
                "d. give boat orders effectively, or listen to boat orders and act on them\n" +
                "e. as bowman, be a lookout and report hazards to the coxswain using standard maritime directions, such as starboard, port quarter and dead ahead", 1, 77));

//    "Quartermaster"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "1. Assist a Group or Troop Quartermaster for at least three months.\n" +
                "a. Show ability in these areas:\n" +
                "-- care and storage of tents, including how to do simple repairs\n" +
                "-- care and storage of cordage. This includes whipping, splicing, hanking, coiling and safety inspections\n" +
                "-- safe storage and handling of fuels such as methylated spirits, paraffin, petrol and gas\n" +
                "-- care of cooking stoves and cooking utensils, including simple repairs, cleaning and general maintenance\n" +
                "-- convenient storage of a section’s training and games equipment\n" +
                "-- caring for and storing equipment used for adventurous  activities, such as sailing gear, canoes and paddles, life jackets and buoyancy aids, go-karts or climbing ropes.\n" +
                "b. Keep a simple record showing equipment issued and returned.\n" +
                "c. Show you understand that general tidiness is the secret of good quartermastering. Explain how you achieved this in the Troop or Group store.\n" +
                "2. As equipment quartermaster, assist at a Nights Away experience for at least two days.\n" +
                "a. During the experience show you’re capable in at least three of these areas:\n" +
                "-- care and maintenance of all tentage, including the ability to do simple repairs to guy lines and fabric tears\n" +
                "-- care and storage of all cordage. This includes whipping, splicing, hanking, coiling and safety inspections.\n" +
                "-- safe storage and handling of fuels used by the camp, such as methylated  spirits, paraffin, petrol and gas\n" +
                "-- care, maintenance and general storage of all tools such as axes, spades and saws\n" +
                "-- care and storage of equipment used for adventurous  activities, such as sailing gear, canoes and paddles, life jackets and buoyancy aids, and climbing ropes.\n" +
                "b. Keep simple records, showing the equipment issued and returned.\n" +
                "c. Keep a portable first aid kit well stocked and maintained.\n" +
                "d. Choose tools to take to camp, to complete emergency repairs on equipment.\n" +
                "e. Show you understand that general tidiness is the secret of good quartermastering. Explain you achieved this at the Nights Away experience.", 1, 78));
//    "Scientist"
        db.addRequirement(new RequirementsList( "Choose 1 of the following options:\n" +
                "Option 1\n" +
                "a. Explore and discuss the science behind two Scouting activities or hobbies.\n" +
                "b. Complete one of these:\n" +
                "-- Plan and complete your own experiment to explore the science behind one Scouting activity or hobby. Record your findings and explain what these mean to others.\n" +
                "--Plan and run an activity, demonstration or presentation to help others understand the science behind a Scouting activity or hobby.\n" +
                "Option 2\n" +
                "a. Plan and complete three science experiments or activities. Check your plan with an adult first, then for each experiment:\n" +
                "b. Find out how one of your experiments or activities links to the real world. Then, explain it to others.", 1, 79));
//    "Sports Enthusiast"
        db.addRequirement(new RequirementsList( "Explain the rules governing your favourite sport.", 1, 80));
        db.addRequirement(new RequirementsList( "Describe the levels of achievement within your chosen sport locally, nationally or internationally.", 1, 80));
        db.addRequirement(new RequirementsList( "Show that you know some of the personalities, champions or other experts in your chosen sport.", 1, 80));
        db.addRequirement(new RequirementsList( "Talk about the equipment needed for the sport.", 1, 80));
        db.addRequirement(new RequirementsList( "Describe a recent major event, championship or landmark in the sport.", 1, 80));
        db.addRequirement(new RequirementsList( "Explain how you follow your sport. How do you keep up to date with developments?", 1, 80));

//    "Street Sports"
        db.addRequirement(new RequirementsList( "Take part in a street sport like skateboarding, roller or in-line skating, Parkour or another street sport agreed by your leadership team.", 1, 81));
        db.addRequirement(new RequirementsList( "Own or use equipment for a street sport.", 1, 81));
        db.addRequirement(new RequirementsList( "Explain how to care for the equipment used and explain what you should look out for when equipment is nearing the end of its life.", 1, 81));
        db.addRequirement(new RequirementsList( "Show that you’re skilled in your chosen street sport and show how you’ve improved over three months.", 1, 81));
        db.addRequirement(new RequirementsList( "Explain the safety rules for your chosen sport including where to undertake street sports safely and responsibly.", 1, 81));
//    "Survival Skills"
        db.addRequirement(new RequirementsList( "Show that you know:\n" +
                "a. first aid treatment you may need to use while on a survival event, considering you may have limited resources available\n" +
                "b. how to construct different kinds of shelter\n" +
                "c. how to build a fire and use basic lighting techniques. You should not use man-made materials such as paper or firelighters.\n" +
                "d. how to maintain hygiene in a survival situation\n" +
                "e. basic knife or multi-tool skills, including safety and sharpening and maintenance\n" +
                "f. the correct use of international distress signals, using a whistle, torch, mirror or markers\n" +
                "g. some basic actions to take while waiting rescue that will both keep you (and your group) safe and will assist your rescuers in locating you", 1, 82));
        db.addRequirement(new RequirementsList( "Put together a personal survival kit.", 1, 82));
        db.addRequirement(new RequirementsList( "With a group of at least three Scouts, take part in a survival exercise.", 1, 82));
//    "Water Activities"
        db.addRequirement(new RequirementsList( "Reach one of these standards:\n" +
                "a. Snorkel Diver Award of the British Sub-Aqua Club.\n" +
                "b. British Water Ski Federation Cutting Edge Bronze Award.\n" +
                "c. Royal Yachting Association National (RYA) Youth Windsurfing Scheme Stage 1.\n" +
                "d. British Sub-Aqua Club Try Dive or Adventure Diver, or the Discover Scuba Diving Award of the Professional Association of Diving Instructors.\n" +
                "e. BKSA (British Kite Sports Association) Level One.", 1, 83));
//    "World Faiths"
        db.addRequirement(new RequirementsList( "Complete one of these activities (Part 1):\n" +
                "a. Visit a place of worship for a faith that you are not familiar with. Find out the differences between this building and another place of worship.\n" +
                "b. Attend a festival or event linked to a faith that you are not familiar with.", 1, 84));
        db.addRequirement(new RequirementsList( "Complete one of these activities (Part two):\n" +
                "a. Learn about the life of a founder or a prominent leader of a faith. You could learn about people like Prince Siddhartha Gautama, Mohammed, Jesus Christ or a saint such as St George.\n" +
                "b. Find out about someone whose faith has had a significant impact upon their life.", 1, 84));
        db.addRequirement(new RequirementsList( "Read a text from a faith that you are not familiar with.", 1, 84));
        db.addRequirement(new RequirementsList( "Find out how following the teachings of a particular faith affects an individual’s daily life.", 1, 84));
//    "Writer"
        db.addRequirement(new RequirementsList( "Complete four of these activities.\n" +
                "a. Compose a poem of at least eight lines. Discuss its meaning and construction.\n" +
                "b. Create a short story of around 600 words. Talk about your story idea with an appropriate adult beforehand.\n" +
                "c. Write a descriptive passage of around 600 words on a subject, agreed with an appropriate adult beforehand.\n" +
                "d. Write a 600-word review of a favourite book, play or other work of literature and talk about it with an appropriate adult.\n" +
                "e. Produce a published article of around 600 words in length. You could contribute to a school, faith, community or Scout magazine or write a letter to a local paper.\n" +
                "f. Keep a diary on a subject, for a length of time agreed with an appropriate adult beforehand.\n" +
                "g. Write a play or dramatic sketch lasting at least 10 minutes.\n" +
                "h. Interview a local celebrity, or other notable person. Write or type out the interview to show the questions you asked and the interviewee’s replies.\n" +
                "i. Write a letter to a pen pal (real or imaginary) of at least 600 words.", 4, 85));

    }
    public static void addExternalRequirements(DBHelper db){
        //bronze
        db.addRequirement(new RequirementsList("See: https://www.scouts.org.uk/volunteers/running-your-section/programme-guidance/information-for-volunteers/top-awards-for-young-people/the-duke-of-edinburghs-award/ for details", 1, 86));

        //silver
        db.addRequirement(new RequirementsList("See: https://www.scouts.org.uk/volunteers/running-your-section/programme-guidance/information-for-volunteers/top-awards-for-young-people/the-duke-of-edinburghs-award/ for details", 1, 87));

        //gold
        db.addRequirement(new RequirementsList("See: https://www.scouts.org.uk/volunteers/running-your-section/programme-guidance/information-for-volunteers/top-awards-for-young-people/the-duke-of-edinburghs-award/ for details", 1, 88));
    }
    public static void addUsers(DBHelper db){
        db.addUser(new UserList("JoanneG", "password", "Joanne Gould", "01/01/1990", "test@test.com", "01234567891", "Leader", 1));
        db.addUser(new UserList("TayaR", "password", "Taya Rollins", "01/01/1990", "test@test.com", "01234567891", "Leader", 1));
        db.addUser(new UserList("GeogriaT", "password", "Georgia Trujillo", "01/01/2015", "test@test.com", "01234567891", "Scout", 1));
        db.addUser(new UserList("KurtisC", "password", "Kurtis Conway", "01/01/2015", "test@test.com", "01234567891", "Scout", 1));
        db.addUser(new UserList("CeliaH", "password", "Celia Hatfield", "01/01/2015", "test@test.com", "01234567891", "Scout", 1));

        db.addUser(new UserList("MacauleyS", "password", "Macauley Shaw", "01/01/1990", "test@test.com", "01234567891", "Leader", 2));
        db.addUser(new UserList("KamalH", "password", "Kamal Horn", "01/01/1990", "test@test.com", "01234567891", "Leader", 2));
        db.addUser(new UserList("RitaS", "password", "Rita Spence", "01/01/2015", "test@test.com", "01234567891", "Scout", 2));
        db.addUser(new UserList("RobertaH", "password", "Roberta Hayden", "01/01/2015", "test@test.com", "01234567891", "Scout", 2));
        db.addUser(new UserList("JazmineL", "password", "Jazmine Leach", "01/01/2015", "test@test.com", "01234567891", "Scout", 2));

        db.addUser(new UserList("GeorgianaH", "password", "Georgiana Horne", "01/01/1990", "test@test.com", "01234567891", "Leader", 3));
        db.addUser(new UserList("JamieC", "password", "Jamie Cochran", "01/01/1990", "test@test.com", "01234567891", "Leader", 3));
        db.addUser(new UserList("BethanyH", "password", "Bethany Hahn", "01/01/2015", "test@test.com", "01234567891", "Scout", 3));
        db.addUser(new UserList("JosieY", "password", "Josie Yates", "01/01/2015", "test@test.com", "01234567891", "Scout", 3));
        db.addUser(new UserList("InesF", "password", "Ines Frederick", "01/01/2015", "test@test.com", "01234567891", "Scout", 3));

        db.addUser(new UserList("MeredithR", "password", "Meredith Rosario", "01/01/1990", "test@test.com", "01234567891", "Leader", 4));
        db.addUser(new UserList("CoreyR", "password", "Corey Randolph", "01/01/1990", "test@test.com", "01234567891", "Leader", 4));
        db.addUser(new UserList("LyraH", "password", "Lyra Hood", "01/01/2015", "test@test.com", "01234567891", "Scout", 4));
        db.addUser(new UserList("SufyaanC", "password", "Sufyaan Collier", "01/01/2015", "test@test.com", "01234567891", "Scout", 4));
        db.addUser(new UserList("AshleyT", "password", "Ashley Tapia", "01/01/2015", "test@test.com", "01234567891", "Scout", 4));

        db.addUser(new UserList("SamW", "password", "Sam Wilmer", "01/01/1990", "test@test.com", "01234567891", "Leader", 1));
        db.addUser(new UserList("BobS", "password", "Bob Smith", "01/01/2015", "test@test.com", "01234567891", "Leader", 1));


    }

    public static void updateUsersStatus(DBHelper db){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDateString = dateFormat.format(new Date());
        db.updateGroupMemberStatus( "1",  currentDateString);
        db.updateGroupMemberStatus( "3",  currentDateString);
        db.updateGroupMemberStatus( "6",  currentDateString);
        db.updateGroupMemberStatus( "8",  currentDateString);
        db.updateGroupMemberStatus( "11",  currentDateString);
        db.updateGroupMemberStatus( "13",  currentDateString);
        db.updateGroupMemberStatus( "16",  currentDateString);
        db.updateGroupMemberStatus( "18",  currentDateString);
        db.updateGroupMemberStatus( "21",  currentDateString);
        db.updateGroupMemberStatus( "22",  currentDateString);
    }
}
