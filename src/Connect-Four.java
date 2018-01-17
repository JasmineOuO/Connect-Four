/*Jasmine Ou
December 26, 2014
This is a connect four game. The user sees a brief animation of a winning connect four game then proceeds to the main menu which has 4 options: instructions,play,highscores and exit. The instructions option leads the user to an instructions page, the high scores shows the user the top 10 scores of winners if there are any and the exit goes to the goodbye screen and closes the window after the user presses any key to continue. If the user selects the play option, they proceed to the game board screen and are prompted to enter a column number in which they want to insert a piece in to with an errortrap to limit their input from 0-7. The side bar shows whose turn it is, how many moves were made and that the user could return to the main menu by selecting 0 when prompted to enter a column number. When there is a winner, a window pops up indicating the winner and proceeds to the results page that tells the user who wins and in how many moves. Then they are prompted to enter their name in less than 13 charactars. If it is a tie, the the user gets a message saying so and does not get prompted to enter their name. The user can choose to play again for as many times as they wish and the game automatically resets. The user can delete the high scores file to clear data as well.*/

import java.awt.*;
import hsa.*;
import java.io.*;
import java.lang.*;
//explain conditional statements.
public class ConnectFour extends Thread
{

    /*                                          Global Variable Declaration Dictionary
    +----------------------------------------------------------------------------------------------------------------------------------+
    ¦Name         ¦Type            ¦Description/Purpose of variable                                                                    ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦c            ¦reference       ¦It points to the Console class in RAM to use the methods within it.                                ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦ROW          ¦static final int¦The value is 6, the number of rows in the connect four game board to be used in a 2D array         ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦COL          ¦static final int¦The value is 7, the number of columns in the connect four game board to be used in a 2D array      ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦choice       ¦String          ¦It stores the user input of the option selected in the main menu.                                  ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦playerNum    ¦int             ¦Stores the player number for alternating turns and displaying who wins in the winner page.         ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦moveNum      ¦int             ¦Stores the number of moves both players make and stores it to the high scores file if someone wins.¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------+
    ¦input        ¦BufferedReader  ¦Variable that is used to read files for file IO
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦greenTea     ¦Color           ¦Creates a green color for the background.                                                          ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦chocolate    ¦Color           ¦Creates a brown color for the background.                                                          ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦vanilla      ¦Color           ¦Creates a light yellow color for the background.                                                   ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦redBean      ¦Color           ¦Creates a purple color for the background.                                                         ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦strawberry   ¦Color           ¦Creates a pink color for the background.                                                           ¦
    +-------------+----------------+---------------------------------------------------------------------------------------------------¦
    ¦darkChocolate¦Color           ¦Creates a dark brown color for the fancy text.                                                     ¦
    +----------------------------------------------------------------------------------------------------------------------------------+*/
    Console c;
    static final int ROW = 6;
    static final int COL = 7;
    String choice;
    int playerNum;
    int moveNum;
    BufferedReader input;
    Color greenTea = new Color (187, 241, 73);
    Color chocolate = new Color (186, 100, 5);
    Color vanilla = new Color (255, 255, 128);
    Color redBean = new Color (131, 65, 65);
    Color strawberry = new Color (255, 128, 128);
    Color darkChocolate = new Color (85, 43, 0);

    /* Local Variable Declaration Dictionary
    +-------------------------------------------+
    ¦Name ¦Type ¦Description/Purpose of variable¦
    +-----+-----+-------------------------------¦
    ¦brown¦Color¦Creates the color brown        ¦
    +-----+-----+-------------------------------+
    ¦x    ¦int  ¦Loop variable for animation that starts at 0, increments by 1 and runs until 300 but stops when 15 for the flashing pieces afterwards. ¦
    +-------------------------------------------+
    A splash screen that shows up momentarily when the user executes the program that displays an animation of a winning connect four game board. The four loop animates both pieces to fall. */
    public void splashScreen ()
    {
	Color brown = new Color (176, 88, 0);
	c.setColor (brown);
	c.fillRect (0, 0, 720, 525);
	c.setColor (darkChocolate);
	c.setFont (new Font ("Century Gothic", Font.PLAIN, 55));
	c.drawString ("C o nnect", 200, 60);
	c.drawString ("F o ur", 300, 130);
	try
	{
	    sleep (1000);
	}
	catch (Exception e)
	{
	}
	c.setColor (Color.blue);
	c.fillRect (50, 160, 180, 300);
	c.setColor (brown);
	c.fillOval (160, 180, 50, 50);
	c.setColour (Color.red);
	c.fillOval (160, 250, 50, 50);
	c.fillOval (70, 180, 50, 50);
	c.setColor (Color.yellow);

	c.fillOval (70, 250, 50, 50);

	c.fillOval (70, 320, 50, 50);
	c.fillOval (70, 390, 50, 50);

	c.fillOval (160, 390, 50, 50);
	c.fillOval (160, 320, 50, 50);
	for (int x = 0 ; x < 300 ; x++)
	{
	    c.setColor (Color.blue);
	    c.fillRect (230, 160, 180, 300);
	    c.setColor (brown);
	    c.fillOval (340, 250, 50, 50);
	    c.fillOval (250, 250, 50, 50);
	    c.fillOval (340, 180, 50, 50);
	    c.fillOval (250, 180, 50, 50);
	    c.fillOval (340, 320, 50, 50);
	    c.fillOval (250, 320, 50, 50);
	    c.fillOval (340, 390, 50, 50);
	    c.setColor (Color.yellow);
	    c.fillOval (250, 390, 50, 50);
	    c.setColor (brown);
	    c.fillOval (250, 20 + x, 50, 50);
	    c.fillOval (340, 90 + x, 50, 50);
	    c.setColour (Color.red);
	    c.fillOval (250, 21 + x, 50, 50);
	    c.fillOval (340, 91 + x, 50, 50);
	    try
	    {
		sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 0 ; x < 15 ; x++)
	{
	    if (x % 2 == 0)
		c.setColor (strawberry);
	    else
		c.setColor (greenTea);
	    c.fillOval (160, 250, 50, 50);
	    c.fillOval (70, 180, 50, 50);
	    c.fillOval (250, 321, 50, 50);
	    c.fillOval (340, 391, 50, 50);
	    try
	    {
		sleep (100);
	    }
	    catch (Exception e)
	    {
	    }
	}
	try
	{
	    sleep (1000);
	}
	catch (Exception e)
	{
	}
    }


    /* Local Variable Declaration Dictionary
    +--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
    ¦Name         ¦Type    ¦Description/Purpose of variable                                                                                                                                                  ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦spot         ¦2D array¦The 2D array that stores the most recent location of a user's piece placement                                                                                                    ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦row          ¦int     ¦Stores the row number that the most recent user's piece is in.                                                                                                                   ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦column       ¦int     ¦Stores the column number that the most recent user's piece is in.                                                                                                                ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦playerNum    ¦int     ¦Stores either 0,1 or 2 to be assigned to the 2d array spot to indicate which player's piece, if any, is in a specific spot.                                                      ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦oppositeCheck¦boolean ¦The variable is false while the pieces are checked in one direction but if no match is found, the checker switches to the opposite direction and becomes true to allow the check.¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦counter      ¦int     ¦The variable counts the number of times a matching piece is found for the same player in the initial direction check.                                                            ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦otherCounter ¦int     ¦The variable counts the number of times a matching piece is found for the same player in the opposite direction check.                                                           ¦
    +--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
    The black box return method that returns true if a connect four match is found, or else it returns false. The first while loop checks to the right and then the left of the current piece's location. Then there is an if statement that makes sure the check does not check out of bounds of the array index. The next if statement checks that the piece to the right is the same player's piece and that opposite check is false. If the if statement is true and counter adds one. Then it continues checking back up to the label horizontalCheck until either on of the if statements become false.If either of the first two if statements are false, opposite check becomes true and now pieces to the left of the current piece's location is checked followed by an if statement to prevent out of bounds checking and an if statement to check if the piece to left is the same player's piece. If both if statement's are true, then the otherCounter adds one. Then it continues checking back up to the label horizontalCheck until either one of the if statements become false. Then there is an if statement that checks if both counters add up to 3 or if either on is equal to 3 to return true for a connect four since the first piece is already counted for. Then there is a break after the variables are reset to false or 0. The next segements are code are the same but they check up then down, diagonal right up then diagonal left down, diagonal left up then diagonal right down. If all do not return true, then it returns false. */
    private boolean winCheck (int[] [] spot, int row, int column, int playerNum)
    {
	boolean oppositeCheck = false;
	int counter = 0;
	int otherCounter = 0;

	horizontalCheck:
	while (true)
	{
	    if (column + (counter + 1) < 8)
	    {
		if (spot [row] [column + counter] == playerNum && oppositeCheck == false)
		{
		    counter++;
		    continue horizontalCheck;
		}
	    }
	    oppositeCheck = true;
	    if (column - (otherCounter + 1) > 0)
	    {
		if (spot [row] [(column - 2) - otherCounter] == playerNum)
		{
		    otherCounter++;
		    continue horizontalCheck;
		}
	    }
	    if (counter == 3 || counter + otherCounter == 3 || otherCounter == 3)
	    {
		return true;
	    }
	    oppositeCheck = false;
	    counter = 0;
	    otherCounter = 0;
	    break;

	}
	verticalCheck:
	while (true)
	{
	    if (row + counter < 4)
	    {
		if (spot [row + 1 + counter] [column - 1] == playerNum && oppositeCheck == false)
		{
		    counter++;
		    continue verticalCheck;
		}
	    }
	    oppositeCheck = true;
	    if (row - otherCounter > 0)
	    {
		if (spot [row - 1 - otherCounter] [column - 1] == playerNum)
		{
		    otherCounter++;
		    continue verticalCheck;
		}
	    }
	    if (counter == 3 || counter + otherCounter == 3 || otherCounter == 3)
	    {
		return true;
	    }
	    oppositeCheck = false;
	    counter = 0;
	    otherCounter = 0;
	    break;
	}

	rightDiagonalCheck:
	while (true)
	{
	    if (row + counter < 4 && column + (counter + 1) < 8)
	    {
		if (spot [row + 1 + counter] [column + counter] == playerNum && oppositeCheck == false)
		{
		    counter++;
		    continue rightDiagonalCheck;
		}
	    }
	    oppositeCheck = true;
	    if (row - otherCounter > 0 && column - (otherCounter + 1) > 0)
	    {
		if (spot [row - 1 - otherCounter] [(column - 2) - otherCounter] == playerNum)
		{
		    otherCounter++;
		    continue rightDiagonalCheck;
		}
	    }
	    if (counter == 3 || counter + otherCounter == 3 || otherCounter == 3)
	    {
		return true;
	    }
	    oppositeCheck = false;
	    counter = 0;
	    otherCounter = 0;
	    break;
	}
	leftDiagonalCheck:
	while (true)
	{
	    if (row + counter < 4 && column - (counter + 1) > 0)
	    {
		if (spot [row + 1 + counter] [(column - 2) - counter] == playerNum && oppositeCheck == false)
		{
		    counter++;
		    continue leftDiagonalCheck;
		}
	    }
	    oppositeCheck = true;
	    if (row - otherCounter > 0 && column + (otherCounter + 1) < 8)
	    {
		if (spot [row - 1 - otherCounter] [column + otherCounter] == playerNum)
		{
		    otherCounter++;
		    continue leftDiagonalCheck;
		}
	    }
	    if (counter == 3 || counter + otherCounter == 3 || otherCounter == 3)
	    {
		return true;
	    }
	    oppositeCheck = false;
	    counter = 0;
	    otherCounter = 0;
	    break;
	}
	return false;
    }


    //clears the screen and outputs a centred title on the screen. The striped background is drawn everytime.
    private void title ()
    {
	c.clear ();
	c.setColor (strawberry);
	c.fillRect (0, 0, 720, 105);
	c.setColor (vanilla);
	c.fillRect (0, 105, 720, 105);
	c.setColor (chocolate);
	c.fillRect (0, 210, 720, 105);
	c.setColor (greenTea);
	c.fillRect (0, 315, 720, 105);
	c.setColor (redBean);
	c.fillRect (0, 420, 720, 105);
	c.setTextBackgroundColor (strawberry);
	c.println ();
	c.println ();
	c.setColor (darkChocolate);
	c.setFont (new Font ("Century Gothic", Font.BOLD, 16));
	c.drawString ("Connect Four", 297, 20);
    }


    //Pauses the program to allow the user to read the screen and press any key to continue to the next screen.
    private void pauseProgram ()
    {
	c.println ();
	c.print (' ', 25);
	c.println ("Press any key to continue...");
	c.getChar ();
    }


    //outputs the title, welcome message and prompts the user to enter an option from 1-4 with an errortrap. The if statement checks if the user entered one of the options, or else there is an error window and keeps making the user re-enter until they enter a correct option.
    public void mainMenu ()
    {
	title ();
	c.setColor (darkChocolate);
	c.setFont (new Font ("segoe print", Font.BOLD, 24));
	c.drawString ("Welcome to Connect Four.", 195, 55);
	c.drawString ("Instructions", 280, 90);
	c.drawString ("Play", 280, 125);
	c.drawString ("High Scores", 280, 160);
	c.drawString ("Exit", 280, 195);
	c.drawString ("Please select a choice: ", 220, 250);
	c.fillOval (240, 65, 30, 30);
	c.fillOval (240, 100, 30, 30);
	c.fillOval (240, 135, 30, 30);
	c.fillOval (240, 170, 30, 30);
	c.setColor (vanilla);
	c.drawString ("1", 245, 87);
	c.drawString ("2", 248, 122);
	c.drawString ("3", 248, 158);
	c.drawString ("4", 245, 192);
	c.setTextBackgroundColor (chocolate);
	while (true)
	{
	    c.setCursor (12, 55);
	    choice = c.readLine ();
	    if (choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4"))
	    {
		break;
	    }
	    new Message ("Please enter 1, 2, 3 or 4!", "Error");
	    c.setCursor (12, 55);
	    c.println ();
	}
    }


    //displays the title, background and the instructions with a pauseprogram to allow the screen to pause until any key is pressed.
    public void instructions ()
    {

	title ();
	c.println ("Players: 2");
	c.println ();
	c.println ("Goal: Connect 4 peices vertically, horizontally or diagonally to win.");
	c.setTextBackgroundColor (vanilla);
	c.println ();
	c.println ("How to play: Select a column number to insert a piece.");
	pauseProgram ();
    }


    /*Local Variable Declaration Dictionary
    +-------------------------------------------------------------------------------------+
    ¦Name¦Type  ¦Description/Purpose of Variable                                          ¦
    +----+------+-------------------------------------------------------------------------¦
    ¦z   ¦int   ¦Stores the number of entries in the high score text file.                ¦
    +----+------+-------------------------------------------------------------------------¦
    ¦line¦String¦Stores the first line when read to check if it is null to be stored in z.¦
    +----+------+-------------------------------------------------------------------------+
    ¦x   ¦int   ¦ Loop variable for constructing the chart that starts at 0, increments by one and stops when less than z.¦
    +-------------------------------------------------------------------------------------+
    Displays the title and the high scores in a chart format with the name, score and position where the lower the score, the better. The user can delete the file and this page will have an empty chart. Pause program  pauses the program until any key is pressed. The for loop outputs the data directly from the input file in chart format.*/
    public void highScores ()
    {
	int z = 0;
	String line;
	title ();
	c.print (' ', 10);
	c.print ("Name", 27);
	c.print ("Score", 27);
	c.println ("Position");
	try
	{
	    input = new BufferedReader (new FileReader ("HighScores.txt"));
	    if ((line = input.readLine ()) != null)
		z = Integer.parseInt (line);
	    for (int x = 0 ; x < z ; x++)
	    {
		if (x <= 1)
		{
		    c.setTextBackgroundColor (strawberry);
		}
		else if (x >= 2 && x <= 6)
		{
		    c.setTextBackgroundColor (vanilla);
		}
		else
		{
		    c.setTextBackgroundColor (chocolate);
		}
		c.print (' ', 10);
		c.print (input.readLine ()
			, 27);
		c.print (input.readLine ());
		c.print (' ', 27);
		c.println (x + 1);
	    }
	}
	catch (IOException e)
	{
	}
	c.setTextBackgroundColor (chocolate);
	c.setCursor (15, 11);
	c.println ("The score is the number of moves the winner used to win a game.");
	c.setCursor (16, 11);
	c.setTextBackgroundColor (greenTea);
	c.println ("Therefore, the lower the score, the higher the ranking.");
	c.setCursor (18, 11);
	pauseProgram ();
    }

    /*Local Variable Declaration Dictionary
    +--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
    ¦Name         ¦Type    ¦Description/Purpose of variable                                                                                                                                                  ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦spot         ¦2D array¦The 2D array that stores the most recent location of a user's piece placement                                                                                                    ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦row          ¦int     ¦Stores the row number that the most recent user's piece is in.                                                                                                                   ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦column       ¦int     ¦Stores the column number that the most recent user's piece is in.                                                                                                                ¦
    +-------------+--------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦r            ¦int     ¦Loop variable starting at 0, increments by 1, runs while less than 6 to check if the row is filled already.                                                                                                                         ¦
    +-------------+--------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦temp         ¦String  ¦Stores the value of x plus one because drawString does not output integer values.                                                                                                                                                   ¦
    +-------------+--------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦x            ¦int     ¦Loop variable starts from 0 and runs until more than 400 and increments by 70 to output the white circles of game board. The next loop increments by 1, starts at 0 and runs while less than 0 to output the rainbow column numbers.¦
    +-------------+--------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------¦
    ¦rainbow      ¦Color array¦Stores 7 colors of the rainbow.                                                                                                                                                                                                     ¦
    +--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+

    Displays the title, gameboard and prompts the user to enter a column number from 1-7. There is an errortrap for input 0-7. They input 0 to return to the main menu. The sidebar displays the number of moves made, the player number's turn and extra info. The first for loop outputs all the white circles and the second for loop outputs the rainbow column numbers. The while loop runs while nobody wins, or else there is a break. The while loop that errortraps the column number input to 0-7 and erases bad input as well as if they press enter right away, it puts the cursor back and erases bad input to let them enter a correct value. */
    public void display ()
    {
	int[] [] spot = new int [ROW] [COL];
	int column;
	int row = -1;
	int r = 0;
	int x=0;
	String temp;
	Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, redBean};
	playerNum = 2;
	moveNum = 0;
	title ();
	c.setColour (Color.blue);
	c.fillRect (25, 40, 495, 420);
	c.setColour (Color.white);
	for (x = 0 ; x <= 400 ; x = x + 70)
	{
	    for (int y = 0 ; y <= 460 ; y = y + 70)
	    {
		c.fillOval (40 + y, 50 + x, 50, 50);
	    }
	}
	c.setFont (new Font ("Berlin Sans FB", Font.PLAIN, 18));
	for (x = 0 ; x < 7 ; x++)
	{
	    c.setColour (rainbow [x]);
	    temp = Integer.toString (x + 1);
	    c.drawString (temp, 60 + (x * 70), 35);
	}
	c.setTextBackgroundColor (chocolate);
	c.setCursor (13, 60);
	c.println ("Press 0 to return");
	c.setCursor (14, 60);
	c.println ("to the main menu");
	c.setCursor (23, 1);
	c.setTextBackgroundColor (redBean);
	c.print ("Please enter a column number: ");

	while (true)
	{
	    if (playerNum == 1)
	    {
		playerNum = 2;
	    }
	    else
	    {
		playerNum = 1;
		moveNum++;
	    }
	    if (moveNum == 22)
	    {
		playerNum = 3;
		new Message ("It's a tie!", "Result");
		break;
	    }
	    c.setTextBackgroundColor (strawberry);
	    c.setCursor (4, 60);
	    c.println ("Turn: Player " + playerNum);
	    c.setTextBackgroundColor (vanilla);
	    c.setCursor (8, 60);
	    c.print ("Move #" + moveNum);

	    while (true)
	    {

		try
		{
		    c.setCursor (23, 31);
		    c.setTextBackgroundColor (redBean);
		    column = Integer.parseInt (c.readLine ());
		    if (column == 0)
		    {
			choice = "0";
			break;
		    }
		    else if (column > 0 && column <= 7)
		    {
			for (r = 0 ; r < 6 ; r++)
			{
			    if (spot [r] [column - 1] != 1 && spot [r] [column - 1] != 2)
			    {
				row = r;
				break;
			    }
			}
			if (row != r)
			{
			    new Message ("That column is already filled! Please choose another one.", "Error");
			}
			else
			{
			    break;
			}
		    }
		    else
		    {
			new Message ("Please enter an integer between 0 to 7!", "Error");
		    }
		}
		catch (NumberFormatException e)
		{
		    new Message ("Please enter an integer!", "Error");
		}
		c.setCursor (23, 31);
		c.println ();
		//just in case the user presses enter first
		c.println ();
	    } //end while of errortrap
	    if (column == 0)
	    {
		break;
	    }
	    else
	    {
		if (playerNum == 1)
		{
		    c.setColor (Color.red);
		}
		else
		{
		    c.setColor (Color.yellow);
		}
		c.fillOval (40 + (column - 1) * 70, 400 - row * 70, 50, 50);
		spot [row] [column - 1] = playerNum;
	    }
	    c.setCursor (23, 31);
	    c.println ();
	    //just in case the user presses enter first
	    c.println ();
	    if (winCheck (spot, row, column, playerNum) == true)
	    {
		new Message ("Player " + playerNum + " is the winner!", "Congratulations!");
		break;
	    }
	} //end while of if nobody wins
    } //end method body


    public void win ()
    {
	PrintWriter output;
	String[] playerName = new String [11];
	int[] score = new int [11];
	int first, temp;
	String temp2;
	int w = 0;
	int z = 1;
	String line;
	title ();
	c.setFont (new Font ("segoe script", Font.BOLD, 50));
	c.setColour (darkChocolate);
	if (playerNum != 3)
	{
	    c.drawString ("Player " + playerNum + " is the winner!", 40, 120);
	    c.drawString ("You won in " + moveNum + " moves!", 60, 190);
	    c.setTextBackgroundColor (chocolate);
	    c.setCursor (13, 18);
	    c.print ("Please enter the winner's name: ");
	    while (true)
	    {
		c.setCursor (13, 50);
		playerName [0] = c.readLine ();
		if (playerName [0].length () <= 13)
		{
		    break;
		}
		new Message ("Please enter a name less or equal to 13 charactars in length!", "Error");
		c.setCursor (13, 50);
		c.println ();
	    }
	    score [0] = moveNum;
	    try
	    {
		input = new BufferedReader (new FileReader ("HighScores.txt"));
		if ((line = input.readLine ()) != null)
		{
		    if (!line.equals ("11"))
		    {
			z += Integer.parseInt (line);
		    }
		    else
		    {
			z = 11;
		    }
		}
		for (int e = 1 ; e < z ; e++)
		{
		    playerName [e] = input.readLine ();
		    score [e] = Integer.parseInt (input.readLine ());
		}
		for (int x = z - 1 ; x > 0 ; x--)
		{
		    first = 0;   //initialize to first array number
		    for (int y = 1 ; y <= x ; y++) //find smallest value of array
		    {
			if (score [y] > score [first])
			    first = y;
		    }
		    temp = score [first];    //exchange the smallest value of array with the first position
		    temp2 = playerName [first];
		    score [first] = score [x];
		    playerName [first] = playerName [x];
		    score [x] = temp;
		    playerName [x] = temp2;
		}
	    }
	    catch (IOException e)
	    {
	    }
	    try
	    {
		output = new PrintWriter (new FileWriter ("HighScores.txt"));
		if (z == 11)
		    output.println (10);
		else
		    output.println (z);
		for (w = 0 ; w < 10 && w < z ; w++)
		{
		    output.println (playerName [w]);
		    output.println (score [w]);
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }

	}
	else
	{
	    c.drawString ("It's a tie! Good game!", 90, 120);
	    c.drawString ("There is no winner.", 105, 190);
	    c.setCursor (13, 1);
	    pauseProgram ();
	}
    }


    public void goodbye ()
    {
	title ();
	c.setFont (new Font ("segoe script", Font.BOLD, 80));
	c.setColour (darkChocolate);
	c.drawString ("Thank you", 100, 125);
	c.setFont (new Font ("segoe script", Font.PLAIN, 50));
	c.drawString ("for using this program!", 60, 190);
	c.drawString ("Jasmine Ou", 180, 310);
	c.setFont (new Font ("segoe print", Font.PLAIN, 24));
	c.drawString ("Programmer and Designer", 180, 240);
	for (int x = 0 ; x < 4 ; x++)
	{
	    c.drawLine (90, 260 - x, 610, 260 - x);
	}
	c.setTextBackgroundColor (greenTea);
	c.setCursor (17, 0);
	pauseProgram ();
	c.close ();
    }


    public ConnectFour ()
    {
	c = new Console (15);
    }


    public static void main (String[] args)
    {
	ConnectFour f = new ConnectFour ();
	f.splashScreen ();
	menu:
	do
	{
	    f.mainMenu ();
	    if (f.choice.equals ("1"))
	    {
		f.instructions ();
	    }
	    else if (f.choice.equals ("2"))
	    {
		f.display ();
		if (!f.choice.equals ("2"))
		{
		    continue menu;
		}
		f.win ();
	    }
	    else
	    {
		if (f.choice.equals ("3"))
		{
		    f.highScores ();
		}
	    }
	}


	while (!f.choice.equals ("4"));
	f.goodbye ();
    }
}


