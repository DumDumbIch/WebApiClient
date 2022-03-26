# WebApiClient
Training project
Developed by Dmitry Lebedev (DumDumbIch-ДимДимЫч) dmitrylebedev@mail.ru dumdumbich@gmail.com

Description:
------------
Target internet resource: web-service: GitHub
UI:
screen 1 - list of users
screen 2 - user info and a list of his repositories
screen 3 - repository info
Data:
cache storage - RAM
local database - Room
internet - Retrofit

The main scenario:
------------------
- when the application is launched, a screen with a list of users opens.
  -- first, the availability of information in the local database is checked
  -- if the database is empty, the web service is accessed and the list is received, followed by saving it in the local database
- when a specific user is selected, a second screen opens
  -- first, the availability of information in the local database is checked
  -- if the database is empty, the web service is accessed and the list is received, followed by saving it in the local database
- when you select a specific repository, the third screen opens
  -- first, the availability of information in the local database is checked
  -- if the database is empty, the web service is accessed and the list is received, followed by saving it in the local database
- pressing the "back" button returns to the previous screen with the return of the previous state
- by pressing the "back" button in the first screen, the application exits


26/03/2022
Implementation of working with data via Data Center
Implemented work with cache (RAM) data storage

23/03/2022
Implemented work with fake data source and web service GitHub via REST API

19/03/2022
Start project