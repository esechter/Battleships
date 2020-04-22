# Battleships
Battleship is a game of person v computer, the winning objective is to be the first to destroy all of the opponent's ships.

## Project Context
This is the first assignment in Microsoft's edX course Object Oriented Programming in Java (DEV277x). 
I started this project on Apr 21, 2020. 
The primary concept this assignment seeks to teach is 2D arrays.

## Assignment Requirements
###User Interaction

*Output*

The assignment calls for using `Stdout` to prompt for user input, to show the current state of the game board, 
and to update the user on game status such as outcome of user and computer moves.

*Input*

The assignment calls for taking user input via `Stdin` with two consecutive requests: 
The user will be prompted first to provide an X coordinate, and then they should be prompted to provide a Y coordinate.

###Game Board
The game board is a 10x10 grid represented by a 2D array. 

###Ships
The user and computer each get to place 5 ships onto the game board. 
The user places their ships first, and then the computer. 
Ships are placed onto a single (X, Y) coordinate; only one ship per coordinate is allowed. 
User and computer do not know the location of the other's ships on the game board.

###Battling
User and computer take turns choosing an (X, Y) coordinate to attack. 
If the coordinate contains an opponent's ship, the attack is successful and the ship is destroyed. 
The following attack inputs should not be accepted by the program:
- a previously attacked coordinate
- a coordinate not contained on the game board

##Planning my approach
Although the assignment pretty clearly lays out the expected UI for this game, 
other than specifying use of a 2D array (and how to store the ships within this array), the approach isn't clearly defined. 
This means I have quite a bit of flexibility in my design. 

The purpose of this course is to learn about objects in Java, so let's assume at this point I don't know a lot 
about using classes to create various objects; however I am comfortable with creating a class with members and 
I know that classes can call other classes, so with that in mind I plan to use two classes:
- a `Battleship` class which will be called to start the game and hold the logic for input and output
- a `Gameboard` class which will create the game board and contain methods for checking validity of a given 
coordinate, updating the board, checking if a coordinate is a ship, a `toString` method for printing the map, etc.

TODO:
- [ ] Create a `Gameboard` class to create the game board object
- [ ] Add a method to `Gameboard` to return if the given X and Y coordinates are valid (within the board)
- [ ] Add a method to `Gameboard` to add a player or computer ship to the board
- [ ] Add a method to return if the the given X and Y coordinates contain a ship
- [ ] Add a method to update the gameboard after a move
- [ ] Add a member variable to store the computer's move history

EXTRAS (not in the requirements):
- [ ] Quit the game if user enters 'q'
- [ ] /(Optional) Give the user and computer each their own game board (like the real game)