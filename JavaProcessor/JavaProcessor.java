// Tim Meek II
// File: JavaProccessor.java
// Description: This program will prompt the user to enter a .java file and will go through the file and will fix syntax errors such
//              as missing semicolons, missing opening and closing braces, while accounting for constructors, methods, if, else, else if statements,
//              for, while, switch loops and many other considerations. The program will also format the file based on the amount of open braces and closing braces.
//              Singly indented if, else, while, and for statements are also indented correctly.
//              The testing for the program was to reach the point where it could format itself. There are most likely many missed possibilities that are not tested for.
//
// Date Created: 03/26/2021
// Date Last Modified: 04/01/2021

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class JavaProcessor extends JFrame implements ActionListener {

    private static final int WIDTH = 575;
    private static final int HEIGHT = 320;

    private JTextField inputName;
    private JTextField outputName;
    
    private static String inName;
    private static String outName;

    private Image processorIcon = Toolkit.getDefaultToolkit().getImage("icon\\JavaProcessorIcon.png");

    public JavaProcessor()
    {
      	super();
		setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);  // setting the location to the middle of the screen
        setTitle("Java Processor");
		Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2,1));
		contentPane.setBackground(Color.decode("#d3d3d3"));
        setIconImage(processorIcon);

		addWindowListener(new WindowDestroyer());

    	JPanel instructionPanel = new JPanel(); // Java panel to hold buttons
    	instructionPanel.setLayout(new FlowLayout()); // Setting the layout of buttons

    	JPanel textPanel = new JPanel(); // Java panel to hold labels and text fields
		textPanel.setLayout(new FlowLayout());
        
        JLabel instructions = new JLabel();
        instructions.setText("Please input the name of the .java file that you wish to check for syntax errors.");

        JLabel instructions2 = new JLabel();
        instructions2.setText("The program will correct any errors found and save it to a new .java file with");
        
        JLabel instructions3 = new JLabel();
        instructions3.setText("the name given in the Output File text area.");

    	JPanel InputName = new JPanel();
    	InputName.setLayout(new FlowLayout());
    	JLabel inputname = new JLabel("Input File");
    	inputName = new JTextField(15);

        JPanel OutputName = new JPanel();
    	OutputName.setLayout(new FlowLayout());
    	JLabel outputname = new JLabel("Output File");
    	outputName = new JTextField(15);

    	InputName.add(inputname);
    	InputName.add(inputName);
		InputName.setBackground(Color.decode("#d3d3d3"));

    	OutputName.add(outputname);
    	OutputName.add(outputName);
		OutputName.setBackground(Color.decode("#d3d3d3"));

        instructionPanel.add(instructions);
        instructionPanel.add(instructions2);
        instructionPanel.add(instructions3);
    	textPanel.add(InputName);
    	textPanel.add(OutputName);
        textPanel.setBackground(Color.decode("#d3d3d3"));
        instructionPanel.setBackground(Color.decode("#d3d3d3"));

    	JButton checkButton = new JButton("Check");
    	checkButton.addActionListener(this);
		checkButton.setToolTipText("Once an input and output file names are entered this will correct syntax errors from the .java file.");
    	
        textPanel.add(checkButton);

        contentPane.add(instructionPanel);
        contentPane.add(textPanel);
        contentPane.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Check"))
		{
                
            inName = inputName.getText();
            outName = outputName.getText();
            if(!outName.endsWith(".java"))
                outName += ".java";

            if(inName.isBlank() || outName.isBlank())
            {
                JOptionPane.showMessageDialog(this,"Please enter an Input and Output file name. Please try again.");
            }

            else
            {
                char startingOutChar = outName.charAt(0);

                if (!inName.endsWith(".java"))
                {
                    JOptionPane.showMessageDialog(this,"The Input file must be a .java file. Please try again.");
                }

                else if (Character.isDigit(startingOutChar))
                    JOptionPane.showMessageDialog(this,"The Output file name cannot start with a number. Please try again.");

                else
                {
                    fileCorrect(inName, outName);
                }
            }
		}
    }

    public static void main( String args[] )
    {
        JavaProcessor window = new JavaProcessor();
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    private void fileCorrect(String inputFile, String outputFile)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(inName));
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(outputFile));
            String line = inputStream.readLine();
            LinkedList<String> outputFileLines = new LinkedList<String>();
            int tabCount = 0;
            int braceDiff = 0;

            // run until all lines from the input file are read
            while (line != null)
            {
                line = line.stripLeading().stripTrailing();

                // Situations to test for in input file lines

                // will replace all instances of the use of the old name with the new name even in comments
                if (line.contains(inName.replace(".java", "")))
                {
                    line = line.replace((inName.replace(".java", "")), (outName.replace(".java", "")));
                    continue;
                }
                
                // all comments will end in a period
                else if (line.startsWith("//"))
                {
                    if (line.endsWith("."))
                        outputFileLines.add(line);
                    else
                        outputFileLines.add(line+".");

                    line = inputStream.readLine();
                    continue;
                }

                // for, if, else if, else, while statements
                else if (line.startsWith("for") || line.startsWith("if") || line.startsWith("else") || line.startsWith("while"))
                {
                    ArrayList<String> lineChars = new ArrayList<String>();
                    for (int i = 0; i < line.length(); i++)
                        lineChars.add(line.substring(i,i+1));

                    // will iterate to make sure there is not a semicolon at the end of the statement and used for formatting purposes
                    Iterator<String> lineItr = lineChars.iterator();
                    int parenthDiff = 0;
                    boolean firstParenthFound = false;
                    line = "";
                    String currentLetter = "";
                    while (lineItr.hasNext())
                    {
                        currentLetter = lineItr.next().toString();
                        line += currentLetter;

                        if (lineItr.toString().contains("("))
                        {    
                            parenthDiff++;
                            firstParenthFound = true;
                        }

                        else if (lineItr.toString().contains(")"))
                            parenthDiff--;

                        else if (parenthDiff == 0 && firstParenthFound)
                            break;

                    }
                    // used for missing parentheses
                    while(parenthDiff >0)
                    {
                        line += ")";
                        parenthDiff--;
                    }

                    outputFileLines.add(line);
                    line = inputStream.readLine();

                    // will insert next line in a brace if it is not a single statement within the block
                    if (line.stripLeading().stripTrailing().startsWith("{") && line.stripLeading().stripTrailing().endsWith("{"))
                    {
                        outputFileLines.add("{");
                        line = inputStream.readLine();
                        braceDiff++;
                    }
                    continue; // since already on next line
                }

                // class, try, catch with opening brace
                else if ((line.contains("class")) || (line.startsWith("try")) || (line.startsWith("catch")) && line.endsWith("{"))
                {
                    line = line.replace("{", "");
                    outputFileLines.add(line);
                    outputFileLines.add("{");
                    braceDiff++;
                }

                // class, try, catch without opening brace
                else if ((line.contains("class")) || (line.startsWith("try")) || (line.startsWith("catch")) && !line.endsWith("{"))
                {
                    outputFileLines.add(line);
                    outputFileLines.add("{");
                    braceDiff++;
                }

                // will not add braces after declaration of private variables
                else if (((line.startsWith("public")) || (line.startsWith("private")) || (line.startsWith("protected")) || (line.startsWith("default"))) && (line.toLowerCase().contains(";")))
                {
                    outputFileLines.add(line);
                }

                // constructors
                else if (((line.startsWith("public")) || (line.startsWith("private")) || (line.startsWith("protected")) || (line.startsWith("default"))) && ((line.contains(outName.replace(".java", "")))) && ((line.toLowerCase().contains("(")) && (line.toLowerCase().contains(")"))))
                {
                    outputFileLines.add(line);
                    outputFileLines.add("{");
                    braceDiff++;
                }

                // test for methods
                // need to find a way to test for method statements other than int, string, char, float
                else if (((line.startsWith("public")) || (line.startsWith("private")) || (line.startsWith("protected")) || (line.startsWith("default"))) && ((line.toLowerCase().contains("void")) || (line.toLowerCase().contains("int")) || (line.toLowerCase().contains("string")) || (line.toLowerCase().contains("float")) || (line.toLowerCase().contains("char"))) && ((line.toLowerCase().contains("(")) && (line.toLowerCase().contains(")"))))
                {
                    outputFileLines.add(line);
                    outputFileLines.add("{");
                    braceDiff++;
                }

                // main method statement
                else if (line.startsWith("public static void main"))
                {
                    if (line.endsWith("{"))
                    {
                        line = line.replace("{", "");
                        outputFileLines.add(line);
                        outputFileLines.add("{");
                        braceDiff++;
                    }
                    else
                    {
                        outputFileLines.add(line);
                        outputFileLines.add("{");
                        braceDiff++;
                        line = inputStream.readLine();
                        continue;
                    }
                }
 
                // opening brace lines
                // will print open brace alone
                else if (line.startsWith("{"))
                {
                    // will not print repeating open braces
                    if (outputFileLines.getLast().contains("{"))
                    {   
                        line = line.replace("{", "");
                        continue;
                    }
                    else
                    {
                        line = line.replace("{", "");
                        outputFileLines.add("{");
                        braceDiff++;
                    }
                }

                // if multiple line are in one line this will break the lines at the semicolons
                else if (line.contains(";"))
                {
                    String lineStorage = "";
                    int quotationCount = 0;
                    for(int i = 0; i<line.split(";").length; i++)
                    {
                        String currentLinePortion = line.split(";")[i];
                        char quotation = '"';

                        // counts the amount of quotations in the current line
                        for (int j = 0; j < currentLinePortion.length(); j++)
                        {
                            if (currentLinePortion.charAt(j) == quotation)
                                quotationCount++;
                        }

                        // accounts for semicolons in quotations and will not break line at that point
                        if(quotationCount % 2 == 1)
                        {
                            lineStorage += line.split(";")[i].stripTrailing().stripLeading()+";";
                            continue;
                        }
                        
                        else 
                        {
                            if (line.split(";")[i].stripTrailing().stripLeading().endsWith("}"))
                                continue;

                            else
                            {
                                lineStorage += (line.split(";")[i].stripTrailing().stripLeading()+";");
                                // add all segments to the LinkedList
                                outputFileLines.add(lineStorage);
                                lineStorage = "";
                            }
                        }
                    }

                    // add remaining segments to the LinkedList if there are any
                    if (lineStorage != "")
                        outputFileLines.add(lineStorage);
                    
                }

                // line ending with close brace after commands
                else if (line.endsWith("}") && !line.startsWith("}"))
                {
                    line = line.replace("}", "");
                    outputFileLines.add(line);
                    outputFileLines.add("}");
                    braceDiff--;
                }

                // closing brace alone
                else if (line.endsWith("}") && line.startsWith("}"))
                {
                    outputFileLines.add("}");
                    braceDiff--;
                }

                // skips blank lines
                else if (line.isBlank())
                {
                    // do nothing
                    // allowing for blank lines would require the above code to be changed
                }

                // add missing semicolon if all above cases are not applicable
                else 
                {
                    outputFileLines.add(line+";");
                }

                // Read next line
                line = inputStream.readLine();

            }

            // add in missing closing braces
            Iterator<String> itr = outputFileLines.iterator();
            String currentLine = "";

            for(int i = 0; i<braceDiff; i++)
                outputFileLines.add("}");

            // print edited lines from the LinkedList into new file with inserted tabs
            itr = outputFileLines.iterator();
            currentLine = "";
            boolean onNextLine = false;
            boolean inLoop = false;
            while (itr.hasNext())
            {
                // used for if, else, while, for blocks with only one line inside them
                if(!onNextLine)
                    currentLine = (String)itr.next();

                // when there needs to be more tabs
                if (currentLine.stripTrailing().endsWith("{"))
                {
                    outputStream.println(tabs(tabCount)+currentLine);
                    tabCount++;
                }

                // indentation for while, for, if, else, switch statements
                else if (currentLine.stripLeading().startsWith("for") || currentLine.stripLeading().startsWith("while") || currentLine.stripLeading().startsWith("if") || currentLine.stripLeading().startsWith("else") || currentLine.stripLeading().startsWith("switch"))
                {
                    outputStream.println(tabs(tabCount)+currentLine);

                    // check for braces in these statements
                    currentLine = (String)itr.next();
                    if (currentLine.stripTrailing().endsWith("{"))
                    {
                        outputStream.println(tabs(tabCount)+currentLine);
                        currentLine = (String)itr.next();
                        tabCount++;
                        outputStream.println(tabs(tabCount)+currentLine);
                        inLoop = true;
                    }

                    //if no braces are found then will only indent for one line
                    else
                    {
                        tabCount++;
                        // used to prevent the while loop from skipping the single indended line
                        onNextLine = true;
                    }
                    continue;
                }

                // when tab amount needs to be decremented
                else if (currentLine.stripTrailing().endsWith("}"))
                {
                    tabCount--;
                    outputStream.println(tabs(tabCount)+currentLine);
                    inLoop = false;
                }

                // will reduce tab size after single if, else, else if, for, while and switch statements
                else if (onNextLine)
                {
                    outputStream.println(tabs(tabCount)+currentLine);
                    tabCount--;
                }

                // will print with one tab while inside for, while, if, else if, else, and switch statements or loops
                else if (inLoop)
                {
                    outputStream.println(tabs(tabCount)+currentLine);
                }
                else
                    outputStream.println(tabs(tabCount)+currentLine);


                onNextLine = false; // reset to false
            }

            inputStream.close();
            outputStream.close();

            // tell user the program ran successfully and new document was created
            JOptionPane.showMessageDialog(this,"The file check was successful and was saved under "+outName);
        }

        // exception handling
        catch(FileNotFoundException e)
        {  
            JOptionPane.showMessageDialog(this,"The Input file was not found. Please try again.");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"There was an issue checking the file. Please try a new file.");
        }
    }

    // method used to know how many tabs are needed per line of the program
    private String tabs(int amountOfTabs)
    {
        String spaces = "";
        for (int i = 0; i  < amountOfTabs; i++)
        {
            spaces += ("\t");
        }
        return spaces;
    }
}

class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
	    System.exit(0);
	}
}