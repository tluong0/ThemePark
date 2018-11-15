# themePark
queue data structure implementation
Create a Java program to stimulate a Theme Park Waiting line using generic queue 
The program has a clock that running from “opening time, 0” to “closing time,60”. Before the theme park open, it creates a random number of rides (0-5), the rides’ capacity (0-5 guests), and how long the ride run for (0-5mins). 
Every minute the clock advance, guests will be loaded on the ride and process, riding for the amount of time the ride runs for. The ride will also load guests with fast pass on and process before loading ones with regular pass. 
Also, there are random (0-2) guests arrive and be assigned either regular pass or fast pass. They will then come to shortest line to one of the ride, either regular pass or fast pass and wait to get on the ride.
At the end of the day (clock at 60), the theme park will close, the program then displays how many guests it served (happy riders), average time guests spend waiting, and maximum time guest waiting. 
