# MVVM Project Structure

This project is only focused on MVVM structure and doesn't contain any UI components.
This project shows how you can structure your project according to MVVM model with dependency 
injection Dagger-Hilt and cached database RoomDB. This project has network calls to get data
from API and convert json data to corresponding object then show data to a textview.


# Steps to follow:
1. Add dependencies to the gradle file.
2. Create model class based on data you'll be fetching.
3. We will be implementing mapper class to map json data to our object.
4. Create a package named retrofit to simplify all the network related files to a single directory. 
5. Create a entity class inside retrofit package to match json object entity to our data model entity.
6. Now create entity mapper to map json object to data model.
7. Then we set up the cached database RoomDB.
8. Create a package named room and create database entity class BlogCachedEntity also a mapper class 
   for map the database entity to model object.
9. Then create the Room database using the database entity class.
10. To use the database we need a database accessing object (DAO).