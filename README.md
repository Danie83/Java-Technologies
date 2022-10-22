# Java-Technologies
Reminder - Lab 1: Do not install any server in a filepath that contains space.
Reminder - Lab 2: If the project previously worked, and then it stopped working... Simply create a new project, deploy on the same server as the previous project, close the server. Return to the original project and deploy it again! It's scary when it happens for the first time and don't know what to do :')
Reminder - Lab 3: Do not include a .xhtml file into a .jsp file and use #{...} notation. 

## Lab 3 - JavaServer Faces
I use a Payara Server for this laboratory.
### Compulsory
I created a PostgreSQL database in order to store data about Teams and Cities in a JSF Project using Maven. The main `index.xhtml` page that is rendered initially uses primefaces in order to display the id, found date and city using a basic datatable.

`http://localhost:8080/JTLab3`

### Homework
Similar project to Compulsory, but on top of the datatable created above, I introduced CRUD (create, read, update, delete) using separate pages (except delete).

`http://localhost:8080/JTLab3-Homework`

## Lab 2 - Web Components
I use a Payara Server for this laboratory.
### Compulsory
The exercise consists of a form that can be completed with a word and a size and then submitted. Upon submitting the form, the user is redirected to another page where a table is displayed. The table will have as column headers each letter of the word and in the first row it will show each letter multiplied by the size e.g. Column header is letter 'a' and size is 3, the first row will display 'aaa'.

`http://localhost:8080/JTLab2/controller`

### Homework
This exercise was done on top of Compulsory and most changes are done in the code. The changes include a model for the elements sent through the form, a Singleton class for business-logic that is used to by the logging web filter to write information into server.log and a decorator web filter that adds a prelude and a coda to the response (The web-flow was already implemented in the previous exercise).

`http://localhost:8080/JTLab2/controller`, complete the form and submit. The decorator is present in both form and result pages, the server.log can be accessed from the project files.

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
