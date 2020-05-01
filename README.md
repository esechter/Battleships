# Battleships
Battleship is a game of person v computer, the winning objective is to be the first to destroy all of the opponent's ships.

## Project Context
This is the first assignment in Microsoft's edX course Object Oriented Programming in Java (DEV277x). The primary concept this assignment seeks to teach is 2D arrays.

I started this project on Apr 21, 2020. This is the first program I've ever written entirely from scratch which consists of more than a single method. It's the first time trying to write unit tests, which has not been taught in any of my online classes but I want to start learning this so I'll rely on Google searches to help me here. This is my first time saving my work to a GitHub repository, which is also something I'm learning on my own and relying on GitHub user guide for instruction. A lot of firsts here!

## Assignment Requirements
**User Interaction**

*Output*

The assignment calls for using `Stdout` to prompt for user input, to show the current state of the game board, 
and to update the user on game status such as outcome of user and computer moves.

*Input*

The assignment calls for taking user input via `Stdin` with two consecutive requests: 
The user will be prompted first to provide an X coordinate, and then they should be prompted to provide a Y coordinate.

**Game Board**
The game board is a 10x10 grid represented by a 2D array. 

**Ships**
The user and computer each get to place 5 ships onto the game board. 
The user places their ships first, and then the computer. 
Ships are placed onto a single (X, Y) coordinate; only one ship per coordinate is allowed. 
User and computer do not know the location of the other's ships on the game board.

**Battling**
User and computer take turns choosing an (X, Y) coordinate to attack. 
If the coordinate contains an opponent's ship, the attack is successful and the ship is destroyed. 
The following attack inputs should not be accepted by the program:
- a previously attacked coordinate
- a coordinate not contained on the game board

**Planning my approach**
Although the assignment pretty clearly lays out the expected UI for this game, 
other than specifying use of a 2D array (and how to store the ships within this array), the approach isn't clearly defined. 
This means I have quite a bit of flexibility in my design. 

The purpose of this course is to learn about objects in Java, so let's assume at this point I don't know a lot 
about using classes to create various objects; however I am comfortable with creating a class with members and 
I know that classes can call other classes, so with that in mind I plan to use two classes:
- a `Battleship` class which will be called to start the game and hold the logic for input and output
- a `Gameboard` class which will create the game board and contain methods for checking validity of a given 
coordinate, updating the board, checking if a coordinate is a ship, a `toString` method for printing the map, etc.

**Updates - notes made during build**
- I pretty quickly ran into issues calling non-static methods from a static context (and vise versa), which was a new error for me as this is my first time writing code inside and outside of a main method. What I ended up doing was adding a `Game` class to contain all the logic to get ship and attack coordinates from the user and computer and create and use the 'Gameboard' object; all main does is create an instance of the `Game` class. I'm not 100% certain that's the best practice, so I'll be looking more into this for my own knowledge.
- I've never made unit tests before. I didn't make as many as I should, but the tests helped me to catch some bugs early on so I'm really glad I tried this!
- One of the very first features I added was the ability to quit the game any time by entering 'q'. This wasn't in the project requirements but I'm glad I did it because it made it very easy for me to try running the program often without having to play all the way through or kill it forcefully. It did make it a lot trickier to deal with user input because it meant at any time the program should look for and accept a string or an integer, instead of only using Scanner.nextInt(). Initially I structured this very poorly with a lot of nested if statements, and then got better at pulling parts of this logic out to helper methods. Even so, I think the code that deals with taking and checking user input is still the least refined because of this and could be improved more. The project ultimately took a lot longer to write because of the quit feature but I think it was the right decision to include it.
- I initially pushed myself to use an enum to handle the possible board states (empty, player ship, computer ship, sunk ship, etc) and it was really confusing at first because I have only worked with enums once before in a lesson; however, in the end this ended up being really simplifying the code for updating the board and printing it. I'm glad I pushed myself to do this, even though it also added time to the project.
- Once I thought the program was finished and correct, I shared it with a friend to try. They immediately noticed that the board didn't update in the location they guessed. The reason was that I had the X and Y axes flipped in my head. Embarassing! This is why getting literally anyone else to test your work is great - you don't know your own blind spots.

TODO:
- [X] Create a `Gameboard` class to create the game board object
- [X] Add a method to `Gameboard` to return if the given X and Y coordinates are valid (within the board)
- [X] Add a method to `Gameboard` to add a player or computer ship to the board
- [X] Add a method to return if the the given X and Y coordinates contain a ship
- [X] Add a method to update the gameboard after a move
- [X] Add a member variable to store the computer's move history

EXTRAS (not in the requirements):
- [X] Quit the game if user enters 'q'
- [ ] /(Optional) Give the user and computer each their own game board (like the real game)
