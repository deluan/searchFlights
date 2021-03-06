Requirements
============
- JDK 1.7


Installation
============
$ ./gradlew installApp

This will create an image of the application in the 'dist' folder


Running
=======
To execute this application you will need at least one provider data file in the
current directory. See examples at the end of this README.

$ dist/bin/searchFlights -o {Origin} -d {Destination}

Args:
-d <Destination>   (mandatory)
-o <Origin>        (mandatory)


What could be better
====================
- A way to override configuration with external file, allowing to add more providers
- More defensive programming (Validating Provider files for example)


Original Coding Problem
=======================

Write a command line “Search Flights” application that takes data from three separate
provider text files simultaneously/in parallel (see attached) with the following
command line interface:

$ searchFlights -o {Origin} -d {Destination}

Args:
-o - Origin, mandatory
-d - Destination, mandatory

Example:
$ searchFlights -o YYZ -d YYC


Expected Output:
1. Display one flight per line in the format:
{Origin} -> {Destination} ({Departure Time} -> {Destination Time}) - Price

Example:
YYC --> YYZ (6/30/2014 9:30:00 --> 6/30/2014 17:05:00) - $535.00
YYC --> YYZ (6/30/2014 10:30:00 --> 6/30/2014 18:10:00) - $548.00

2. Remove duplicate rows (yes, there are duplicate flights in the provider files).
3. Order by two fields: “Price” as primary and “Departure Time” as secondary, both
in ascending order.



Provider1.txt
--------------
Origin,Departure Time,Destination,Destination Time,Price
LAS,6/23/2014 13:30:00,LAX,6/23/2014 14:40:00,$151.00
YYZ,6/15/2014 6:45:00,YYC,6/15/2014 8:54:00,$578.00
MIA,6/23/2014 19:40:00,ORD,6/23/2014 21:45:00,$532.00
YYC,6/12/2014 11:00:00,YVR,6/12/2014 11:24:00,$379.00
LHR,6/21/2014 11:05:00,BOS,6/21/2014 17:06:00,$975.00
YVR,6/18/2014 9:10:00,YYZ,6/18/2014 19:47:00,$1093.00
LAX,6/19/2014 8:45:00,YYC,6/19/2014 12:45:00,$356.00
MIA,6/20/2014 7:45:00,ORD,6/20/2014 12:36:00,$422.00

Provider2.txt
--------------
Origin,Departure Time,Destination,Destination Time,Price
JFK,6-21-2014 17:55:00,YEG,6-21-2014 23:23:00,$589.00
LAS,6-22-2014 9:45:00,YYZ,6-22-2014 21:20:00,$549.00
YVR,6-23-2014 9:20:00,YYZ,6-23-2014 15:22:00,$1122.00
MSY,6-19-2014 5:55:00,YUL,6-19-2014 10:58:00,$480.00
YYZ,6-26-2014 12:00:00,YYC,6-26-2014 14:09:00,$630.00
LAX,6-19-2014 11:00:00,YYC,6-19-2014 17:45:00,$543.00
YYC,6-23-2014 12:40:00,YYZ,6-23-2014 14:54:00,$541.00
MIA,6-23-2014 19:40:00,ORD,6-23-2014 21:45:00,$532.00
YVR,6-23-2014 22:10:00,YYZ,6-24-2014 2:22:00,$1151.00
LAS,6-16-2014 8:11:00,YYZ,6-16-2014 19:28:00,$703.00
LAX,6-21-2014 8:55:00,YYC,6-21-2014 15:10:00,$577.00
YYZ,6-15-2014 7:00:00,YVR,6-15-2014 9:00:00,$647.00
LHR,6-19-2014 6:30:00,BOS,6-19-2014 12:42:00,$1243.00

Provider3.txt
--------------
Origin|Departure Time|Destination|Destination Time|Price
LAS|6/29/2014 14:55:00|LAX|6/29/2014 16:10:00|$201.00
MIA|6/17/2014 14:55:00|ORD|6/17/2014 17:10:00|$542.00
LAS|6/29/2014 7:30:00|YYZ|6/29/2014 13:58:00|$678.00
YYZ|6/22/2014 12:00:00|YYC|6/22/2014 14:09:00|$630.00
JFK|6/15/2014 9:30:00|YEG|6/15/2014 17:50:00|$730.00
YYZ|6/13/2014 7:00:00|YVR|6/13/2014 9:00:00|$648.00
MIA|6/22/2014 6:50:00|ORD|6/22/2014 9:02:00|$576.00
YYC|6/23/2014 14:15:00|YYZ|6/23/2014 21:59:00|$573.00
YYZ|6/15/2014 18:00:00|YVR|6/15/2014 20:00:00|$698.00
LAS|6/16/2014 8:11:00|YYZ|6/16/2014 19:28:00|$703.00
LHR|6/27/2014 16:40:00|BOS|6/27/2014 18:50:00|$1616.00
MSY|6/19/2014 14:55:00|YUL|6/19/2014 23:24:00|$645.00
YYZ|6/22/2014 12:00:00|YYC|6/22/2014 14:09:00|$630.00
LAS|6/15/2014 9:54:00|LAX|6/15/2014 11:05:00|$286.00
YYC|6/30/2014 9:30:00|YYZ|6/30/2014 17:05:00|$535.00
