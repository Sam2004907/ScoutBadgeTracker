# Scout Badge Tracker

Scout Badge Tracker is a mobile application developed as part of my final-year university research and development project. The application is designed to help young members of the Scouting organisation track their progress towards completing badges and provide evidence of completed activities for review by their leaders.

## Motivation

As a Scout leader in the UK, I have identified a lack of mobile applications specifically designed to help young members of Scouting, particularly **Cubs, Scouts and Explorers**, manage and track their badge progression.

Through my experience within Scouting, I have also encountered situations where members have completed work towards a badge but have forgotten to bring the necessary evidence to meetings. This can make it difficult for leaders to verify their work and can delay the member from receiving their badge.

Scout Badge Tracker was therefore developed as a prototype to investigate how a dedicated mobile application could address these issues. The application allows Scouts to:

* View the requirements for their badges.
* Track their progress towards completing badges.
* Upload evidence of completed badge requirements.
* Submit evidence for review by their Scout leaders.
* Receive approval for completed requirements.

The project explores how digital tools could support young members in managing their own badge progression while providing leaders with a simple way to review and approve completed activities.

## Installation

Scout Badge Tracker is currently developed natively for **Android smartphones**. The prototype operates as a standalone application on a single device and does not currently provide synchronisation between multiple devices.

### Requirements

To build and run the application, it is recommended that you use:

* **Android Studio**
* **Android SDK 33 or later**
* **Android 14 (API level 34) or later**

### Setup

1. Clone or download this repository.
2. Open the project in **Android Studio**.
3. Allow Android Studio to synchronise and install any required dependencies.
4. Ensure that the project is configured to use the required Android SDK version.
5. Build and run the application on an Android device or emulator.

### Initial Data Setup

The prototype requires initial data to be added to the application's local database.

To populate the database:

1. Open `login.java`.
2. Locate the data insertion functions.
3. Temporarily uncomment the relevant `addData` functions.
4. Run the application and allow the data to be inserted.
5. Once the data has been successfully loaded, **re-comment the `addData` functions**.
6. Restart the application.

**Important:** The `addData` functions must be re-commented after the initial data has been loaded. If they remain uncommented, the application will attempt to insert the data every time the login page is loaded, which may result in duplicate records.
