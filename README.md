# Java-Technologies
Reminder - Lab 1: Do not install any server in a filepath that contains space.
Reminder - Lab 2: If the project previously worked, and then it stopped working... Simply create a new project, deploy on the same server as the previous project, close the server. Return to the original project and deploy it again! It's scary when it happens for the first time and don't know what to do :')
Reminder - Lab 3: Do not include a .xhtml file into a .jsp file and use #{...} notation. 
Reminder - Lab 4: If console can't read a path, simply place the target file/jar in the root folder of the asadmin console.

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
