# FilmQueryProject

## Description
Welcome to FILMDB! 

Your one stop shop for movie searches. Upon starting the program, you are greeted with wonderful banner and a menu with 3 options. You can search the database for a film by entering it's film id. If you don't know the id of the film you are looking for, you have the ability to search our entire database by a keyword. If the keyword you entered is found in either a film's Title or Description, it will display it. Keep in mind that when you search by a keyword, it will list all of the movies in our database that include that keyword. So be sure to be specific. 
Finally, if you are done with our database, you can simply enter 3 in the main menu and the program will quit. 

## Lessons Learned
This was my most enjoyable project yet while at SD. With this film query project, I began connecting the dots and seeing infinite possibilities on what was capable with this project. The use of incorporating JDBC allowed me to see how this would apply in real world situations. The part of this project that I struggled with the most, was incorporating the keyword search function. PreparedStatements were tricky to get right when dealing with a string as the bind variable. After some trial and error, I realized that my syntax and formatting was correct. However, I needed to use a next() instead of a nextLine() method. As the latter for some reason was not storing my string correctly. Once changed, the keyword search funtion was functioning properly. Overall, I really enjoyed working with databases and being able to map out exactly what I wanted.


## Technologies Used
GitHub, Eclipse, Java, SQL, JDBC, Maven, MAMP
