# Java-Technologies
Reminder - Lab 1: Do not install any server in a filepath that contains space.
Reminder - Lab 2: If the project previously worked, and then it stopped working... Simply create a new project, deploy on the same server as the previous project, close the server. Return to the original project and deploy it again! It's scary when it happens for the first time and don't know what to do :')
Reminder - Lab 3: Do not include a .xhtml file into a .jsp file and use #{...} notation. 
Reminder - Lab 4: If console can't read a path, simply place the target file/jar in the root folder of the asadmin console.

## Lab7 - Contexts and Dependency Injection (CDI)
I use Payara Server for this laboratory.

### Part 1
I created a login page where an existent user can log in using an username and a password or a new user can register. New users will have the "basic" type account which is just a type to differentiate between admin, author, reviewer types. Users can only register in a set timeframe (I used a separate class to hold values for the hours in which users can register and get Calendar values depending on the current day). I made a form where logged authors can upload a form, the form is currently not saved in the database, but the specific entity for it exists.

## Lab6 - Enterprise Java Beans (EJB)
I use Payara Server for this laboratory.

Use the following route to test the exercise: `http://localhost:8080/JTLab6`.

### Part 1 
Used EJB to implement the data access layer of the application. The application uses the same database from Laboratory 3 (JTLab3). I used JTA (Java Transaction API) as 
transaction type in the persistence unit, meaning that using .getTransaction.begin() and .getTransaction.commit() wasn't required anymore. Similar to previous laboratories, I made an application with the JSF framework and used primefaces to display an ordered and clear table of contents of the Schedule Entity (Position, Team 1, Team 2, Week, Start period, End period). The exercise has a stateless session bean that only accesses the elements of type Schedule from the database and check if a new schedule can be added among the current ones (the resource is LOCKED when reading data), a stateful session bean that is tasked with storing Schedules added by a user in a List and allow the user to add them all at once (I used buttons, one button for adding a schedule and one for saving all the schedules after the user is done adding them) and the resource is LOCKED when the schedules are added. A singleton session bean is instantiated at application startup and is tasked with determining what session bean is used (Stateless or Stateful) in order to read or write data, I named it ScheduleController.

### Part 2
When using Second-Level Cache an entity instance is shared by all sessions crated in the same session factory. If we try to access the entity when it is locked and this second-level cache is enabled, the instance is fetched from the first-level cache if it exists there or if it is not found there and it is cached in the second-level cache and then an instance is assembled and returned with the data fetched. If nothing is found, the data is taked from the database and an instance is assembled and returned.

Lazy Loading means that a resource initialization is delayed until that resource is needed. For example, we can take a large application with many pages and subpages, instead of loading the entire app which is large, the customer can access a single page (that can be cached and then accessed by another user even faster) and each time the same page is accessed, the user benefits from the activity of another user.

Entity Graphs has the goal to improve runtime performance when loading the entity's relatesd associations and basic fields. I remember doing something pretty similar in a Django application where I needed to get data of a related association of a model. I don't think this can be considered close to Entity Graph but it shared the same intention. I managed to serialize a few required fields of the related model and use their data in the application.

I create an interceptor in the stateful session bean and observed the execution time of each of the methods from the bean (I used the Interceptor defined in the course).

I created a timer that saves the Schedules added by a user every 60 seconds. The timer could be set to 10 seconds but I forgot to change it. The List of Schedules is reset after each save, so when the user tries to save the Schedules himself, no errors would occur (I used a similar implementation to the one in the course).

## Lab 5 - Java Persistence API (JPA)
I use Payara Server for this laboratory.

### Compulsory
I used the same JDBC Resource defined in Lab 4 and redesigned the Team.java into an Entity. I also Defined persistence.xml file for setting up JPA.
`http://localhost:8080/JTLab5/test` will display a list of elements of type Team from the database.

### Homework 
I used EJB injection in Compulsory and defined an Entity class already. I created a few name queries and a repository is used to READ and CREATE data of type Team.

## Lab 4 - JavaServer Faces, Facelets. JNDI and Resources.
I use a Payara Server for this laboratory.

### Compulsory
I had problems setting up a jdbc connection pool because asadmin wouldn't recognize any path to the postgresql-\*.jar I gave. The solution was to place the .jar in the same folder as the console. I managed to create a jdbc connection pool and a datasource from the Payara Administration Console. The DataSource object was created using  resource injection.

This exercise is only related to backend, this means that there is no frontend specification available.

### Homework
Created a page using <ui:composition></ui:composition> that uses page.xhtml as a template. The page.xhtml includes the header, content and footer which are weparate pages. Since the dataView.xhtml needs to be a generic page, this means that it should not be part of the page.xhtml (?), so I took the Homework exercise from Lab-03 and expanded the current Homework.

`http://localhost:8080/JTLab4-Compulsory`, enter the page.xhtml view and press the only button existent in the page to access dataView.xhtml which displays a datatable with information about a series of Teams. CRUD operations are available, each action is redirected to another generic page for each oepration except the delete action which only needs to delete an entry without any confirmation.

## Lab 3 - JavaServer Faces
I use a Payara Server for this laboratory.
### Compulsory
I created a PostgreSQL database in order to store data about Teams and Cities in a JSF Project using Maven. The main `index.xhtml` page that is rendered initially uses primefaces in order to display the id, found date and city using a basic datatable.

`http://localhost:8080/JTLab3`

### Homework
Similar project to Compulsory, but on top of the datatable created above, I introduced CRUD (create, read, update, delete) using separate pages (except delete, which didn't require a new page in order to perform the action).

`http://localhost:8080/JTLab3-Homework`

## Lab 2 - Web Components
I use a Payara Server for this laboratory.
### Compulsory
The exercise consists of a form that can be completed with a word and a size and then submitted. Upon submitting the form, the user is redirected to another page where a table is displayed. The table will have as column headers each letter of the word and in the first row it will show each letter multiplied by the size e.g. Column header is letter 'a' and size is 3, the first row will display 'aaa'.

`http://localhost:8080/JTLab2/controller`

### Homework
This exercise was done on top of Compulsory and most changes are done in the code. The changes include a model for the elements sent through the form, a Singleton class for business-logic that is used to by the logging web filter to write information into server.log and a decorator web filter that adds a prelude and a coda to the response (The web-flow was already implemented in the previous exercise).

`http://localhost:8080/JTLab2/controller`, complete the form and submit. The decorator is present in both form and result pages, the server.log can be accessed from the project files.

### Bonus
Created a web listener that reads a context init parameter and stores it in a class. Whenever the category is not displayed, it is replaced with a default category from the context init parameter read. At all times, the category is displayed in the response page upon completing the form. Also created a hand-made cookie that is displayed alongside the default category in the response page.

`http://localhost:8080/JTLab2-Bonus/home`, complete the form and submit. The cookies and the category are displayed on top of the page.

## Lab 1 - Java Servlet Technology
I use a Payara Server for this laboratory.
### Compulsory
By giving a word as a parameter, it will be taken and converted in a char array. The char array will be displayed in a HTML page in an ordered list.

`http://localhost:8080/Compulsory/compulsory?parameter={your_word_here}`

### Homework
An upgrade of Compulsory. Being given a word as a parameter, it will return permutations of that specific word. The size parameter is used to filter permutations of that specific size. The default size is 0. Access is used to filter sequences forming valid words. If access is `yes` and size is `0` then all valid words will be displayed, otherwise all the sequences will be displayed.

`http://localhost:8080/Compulsory/homework?parameter={your_word_here}&size={allowed_permutation_size}&access={yes/no}`

### Bonus
Bonus is similar to Homework, but an external script is used to send requests and a logger was added to keep track of useful information from each request.
Bonus exercise can be tested by using `python3 servlet_test.py`.
