# Chess Console Game

## Summary
This Java project allows you to play chess inserting commands in order to move both teams' pieces. The command should comply with the regular expresion `([a-h]|[A-H])[1-8]([a-h]|[A-H])[1-8]`.

## Important files
- **./BoardReference.xlsx**
  - Graphic that shows the board from programmer's and user's perspective.
- **./src/logic/Main.java**
  - Program that will be executed.
- **./src/exceptions/\*.java**
  - Different exceptions used by the program.
- **./src/pieces/Piece.java**
  - Abstract class that will be extended by each of the different types of pieces.
- **./src/logic/Board.java**
  - Implementation of the chess board.
- **./src/pieces/*\[other\]*.java**
  - Implementation of the different types of pieces.
  
## Example
The following code will be the console output when executing the program (including input from user) executing the ***Fool's Mate*** (*checkmate in the fewest possible number of moves from the start of the game*).

```
 #########
## CHESS ##
 #########

Press ENTER to play the game.

#########################################################################
#	♜	♞	♝	♛	♚	♝	♞	♜	# 8
#	♟	♟	♟	♟	♟	♟	♟	♟	# 7
#	□	■	□	■	□	■	□	■	# 6
#	■	□	■	□	■	□	■	□	# 5
#	□	■	□	■	□	■	□	■	# 4
#	■	□	■	□	■	□	■	□	# 3
#	♙	♙	♙	♙	♙	♙	♙	♙	# 2
#	♖	♘	♗	♕	♔	♗	♘	♖	# 1
#########################################################################
	a	b	c	d	e	f	g	h
Write movement (i.e.: b1c3):
f2f3
#########################################################################
#	♜	♞	♝	♛	♚	♝	♞	♜	# 8
#	♟	♟	♟	♟	♟	♟	♟	♟	# 7
#	□	■	□	■	□	■	□	■	# 6
#	■	□	■	□	■	□	■	□	# 5
#	□	■	□	■	□	■	□	■	# 4
#	■	□	■	□	■	♙	■	□	# 3
#	♙	♙	♙	♙	♙	■	♙	♙	# 2
#	♖	♘	♗	♕	♔	♗	♘	♖	# 1
#########################################################################
	a	b	c	d	e	f	g	h
Write movement (i.e.: b1c3):
e7e5
#########################################################################
#	♜	♞	♝	♛	♚	♝	♞	♜	# 8
#	♟	♟	♟	♟	■	♟	♟	♟	# 7
#	□	■	□	■	□	■	□	■	# 6
#	■	□	■	□	♟	□	■	□	# 5
#	□	■	□	■	□	■	□	■	# 4
#	■	□	■	□	■	♙	■	□	# 3
#	♙	♙	♙	♙	♙	■	♙	♙	# 2
#	♖	♘	♗	♕	♔	♗	♘	♖	# 1
#########################################################################
	a	b	c	d	e	f	g	h
Write movement (i.e.: b1c3):
g2g4
#########################################################################
#	♜	♞	♝	♛	♚	♝	♞	♜	# 8
#	♟	♟	♟	♟	■	♟	♟	♟	# 7
#	□	■	□	■	□	■	□	■	# 6
#	■	□	■	□	♟	□	■	□	# 5
#	□	■	□	■	□	■	♙	■	# 4
#	■	□	■	□	■	♙	■	□	# 3
#	♙	♙	♙	♙	♙	■	□	♙	# 2
#	♖	♘	♗	♕	♔	♗	♘	♖	# 1
#########################################################################
	a	b	c	d	e	f	g	h
Write movement (i.e.: b1c3):
d8h4
#########################################################################
#	♜	♞	♝	■	♚	♝	♞	♜	# 8
#	♟	♟	♟	♟	■	♟	♟	♟	# 7
#	□	■	□	■	□	■	□	■	# 6
#	■	□	■	□	♟	□	■	□	# 5
#	□	■	□	■	□	■	♙	♛	# 4
#	■	□	■	□	■	♙	■	□	# 3
#	♙	♙	♙	♙	♙	■	□	♙	# 2
#	♖	♘	♗	♕	♔	♗	♘	♖	# 1
#########################################################################
	a	b	c	d	e	f	g	h
 ###########################
## CHECKMATE - BLACK WINS! ##
 ###########################
```
