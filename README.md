# Android #
## Android Projects ##

### Project 1 ###
* The project demonstrates the use of implicit intents to start another activity based on action specified in intent
* User enters a string containing a combination of characters and numbers and presses the button
* The activity detects if the entered string contains number in format “(xxx) yyy-zzzz” or “(xxx)yyy-zzzz” and then creates an intent with action as send message and start an activity
* If the entered string does not contain a number in the above format, no action is performed

### Project 2 ###
* The project demonstrates the custom implementation of adapter in Android and use of context menus
* A view is constructed from a list of song titles, artist/band names & album images using a custom adapter and then passed on to ListView for display in an activity
* Clicking an item in the list navigates a user to the youtube page of the song
* Long press click of an item in listview displays a context menu with options to navigate to youtube or wikipedia page of the song

### Project 3 ###
* The project demonstrates implementation of broadcast receivers, dynamic fragments and handling orientation changes in android applications
* The project consists of 2 applications - App1 & App2
* App1 contains a single activity with 2 read only text views and 2 buttons
* App2 contains 2 activities, 2 fragments, a broadcast receiver, action bar with options menu
* Clicking on one of the buttons in App1 will send a broadcast to start an activity in App2 depending on which button (Chicago or IndianPolis) is selected

### Project 4 ###
* The project demonstrates starting a bound service, interprocess communication between 2 apps using IBinder and hosting SQLite database in android
* The project consists of 2 applications - PlayerClient & AudioServer
* PlayerClient consists of a single activity and offers a user with a EditText view to enter a song number and buttons to play, pause stop the song and retrieve list of all requests made to the server
* AudioServer consists of a bind service and becomes active when user sends a song request from PlayerClient application
* AudioServer also hosts a SQLite database which records all user requests as transactions with their timestamps
