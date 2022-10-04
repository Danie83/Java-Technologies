# Java-Technologies
Reminder: Do not install any server in a filepath that contains space.

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
