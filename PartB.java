//Imports all needed resources.
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class PartB extends JFrame implements ActionListener {
    
    //Declares all needed variables and objects
    private Sounds effects = new Sounds();
    private JTextField resultText;
    private JTextField streakText;
    private JTextField scoreText;
    private JTextField livesText;
    private JTextField countriesText;

    private TitledBorder scoreBorder;
    private TitledBorder streakBorder;
    private TitledBorder livesBorder;

    private JButton submitButton;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    private JPanel[] flagPanels = new JPanel[20];

    private JLabel[] flagLabels = new JLabel[20];

    private ImageIcon[] flagIcons = new ImageIcon[20];

    private boolean[] flagCheck = new boolean[20];
    private boolean[] countryCheck = new boolean[20];

    private JRadioButton[] flagButtons = new JRadioButton[20];
    private ButtonGroup flagButtonGroup = new ButtonGroup();

    private int streakCounter;
    private int scoreCounter;
    private int livesCounter;
    private int round;
    private int randFlagNum;
    private int randCountryNum;

    private boolean repeatCheck;

    //This doesn't need to be in the constructor as the values are fixed.
    private String[] countries = { "Wales", "Japan", "Mozambique", "Greece", "Antwerp", "Nepal", "Friesland",
            "North Caucasian Emirate", "Ghana", "Senegal", "Sicily", "Swaziland", "Bermuda", "Virgin Islands",
            "Kyrgyzstan", "Northen Marianas Islands", "Korea", "Easter Islands", "Hong Kong", "Vatican City" };

            
    public PartB() {
        setTitle("PartB");

        //FlagIcons is filled with the image files. FlagLabels is filled with FlagIcons, and given gray line borders. FlagButtons are created, given numeric
        //action commands and added to flagButtonGroup. FlagPanels are created, given layouts and filled with all other objects.
        for (int i = 0; i < 20; i++) {
            flagIcons[i] = new ImageIcon("small".concat(Integer.toString(i).concat(".jpg")));
            flagLabels[i] = new JLabel(flagIcons[i]);
            flagLabels[i].setBorder(LineBorder.createGrayLineBorder());
            flagButtons[i] = new JRadioButton();
            flagButtons[i].setActionCommand(Integer.toString(i));
            flagPanels[i] = new JPanel();
            flagPanels[i].setLayout(new BoxLayout(flagPanels[i], 1));
            flagPanels[i].add(flagLabels[i]);
            flagPanels[i].add(flagButtons[i]);
            flagButtonGroup.add(flagButtons[i]);
        }

        //Creates titled borders
        scoreBorder = new TitledBorder("Score");
        streakBorder = new TitledBorder("Streak");
        livesBorder = new TitledBorder("Lives");

        //Creates and initialises all counters.
        scoreCounter = 0;
        round = 0;
        livesCounter = 3;
        streakCounter = 0;

        //Creates, initialises, fills and gives texture to all textfields.
        resultText = new JTextField(15);
        resultText.setHorizontalAlignment(JTextField.CENTER);
        resultText.setText("BEGIN!");
        resultText.setEditable(false);
        resultText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        
        streakText = new JTextField(10);
        streakText.setHorizontalAlignment(JTextField.CENTER);
        streakText.setText(Integer.toString(streakCounter));
        streakText.setEditable(false);
        streakText.setBorder(streakBorder);

        
        livesText = new JTextField(10);
        livesText.setHorizontalAlignment(JTextField.CENTER);
        livesText.setText(Integer.toString(livesCounter));
        livesText.setEditable(false);
        livesText.setBorder(livesBorder);

        
        scoreText = new JTextField(15);
        scoreText.setHorizontalAlignment(JTextField.CENTER);
        scoreText.setText(Integer.toString(scoreCounter).concat("/".concat(Integer.toString(round))));
        scoreText.setEditable(false);
        scoreText.setBorder(scoreBorder);

        //The text in this field is randomized by the CountryPick() method at the start of every game.
        countriesText = new JTextField(15);
        countriesText.setHorizontalAlignment(JTextField.CENTER);
        countryPick();
        countriesText.setEditable(false);
        countriesText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        //Creates, names and gives an action listener to the submit button.
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);

        //Creates, fills, organises, and gives texture and layouts to the panels.
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel1.setLayout(new BorderLayout());
        panel1.add(panel5, BorderLayout.CENTER);
        panel1.add(panel3, BorderLayout.SOUTH);
        panel1.add(panel4, BorderLayout.NORTH);

        panel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel2.setLayout(new BorderLayout());
        panel2.add(countriesText, BorderLayout.CENTER);

        panel2.add(submitButton, BorderLayout.EAST);

        panel3.setLayout(new FlowLayout());
        panel3.add(resultText);

        panel4.setLayout(new FlowLayout());
        panel4.add(streakText);
        panel4.add(scoreText);
        panel4.add(livesText);

        //The flags are randomised through flagPick() at the start of every game.
        panel5.setLayout(new FlowLayout());
        panel5.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        flagPick();

        //Adds everything to the content pane.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel1, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {

        //Code performed when the user clicks on the submit answer button.
        if (e.getActionCommand().equals("Submit Answer")) {
            round++;

            //Checks if the user is coorect by comparing the nuber of the selected radiobutton to the entries of the countries list. If the user is correct the text
            //changes to CORRECT!, a sound plays, the score and streak numbers go up, the flags are updated. If the user has a score of 20 the flags are not updated.
            if (flagButtons[randCountryNum].isSelected()) {
                resultText.setText("CORRECT!");
                effects.correctEffect();
                scoreCounter++;
                if (scoreCounter != 20) {
                    countryPick();
                    flagPick();
                }
                streakCounter++;
                streakText.setText(Integer.toString(streakCounter));
                scoreText.setText(Integer.toString(scoreCounter).concat("/".concat(Integer.toString(round))));
            }
            //If the user is wrong and still has lives the text changes to WRONG!, he loses a life, and his streak is reset. If he has no lives left the game 
            //is over. The text changes to GAME OVER, a sound plays and the submit button becomes unusable.
            else {
                if (livesCounter != 0) {
                    effects.incorrectEffect();
                    resultText.setText("WRONG!");
                    livesCounter--;
                    livesText.setText(Integer.toString(livesCounter));
                    streakCounter = 0;
                    streakText.setText(Integer.toString(streakCounter));
                    scoreText.setText(Integer.toString(scoreCounter).concat("/".concat(Integer.toString(round))));
                } else {
                    resultText.setText("GAME OVER");
                    effects.DefeatEffect();
                    submitButton.removeActionListener(this);
                }
            }
            //If the user reaches a score of 20 he has won the game. Text changes to CONGRATULATIONS and the victory sound plays. The submit button is unusable
            //and the country text says YOU WIN!
            if (scoreCounter == 20) {
                resultText.setText("CONGRATULATIONS!");
                effects.VictoryEffect();
                submitButton.removeActionListener(this);
                countriesText.setText("You Win!");
            }

            //If the user answers thrice correctly in a row he gains a life. The text changes to EXTRA LIFE! and a sound plays.
            if (streakCounter % 3 == 0 && streakCounter != 0) {
                resultText.setText("EXTRA LIFE!");
                effects.ExtraLifeEffect();
                livesCounter++;
                livesText.setText(Integer.toString(livesCounter));
            }
        }
    }
    
    //Used to randomly pick and display flags. The correct flag is represented by randCountryNum, meaning the random number dictating the requested country.
    //There are two errors with this method. First is that it wont display the same ammount of flags every round.
    //Tried to circumvent this problem by making the program loop one extra time for every repeat flag it skips. This means that a flag will only be displayed
    //if it is not already showing (.isShowing()), and that if it is showing it will decrease i, therefore extending the loop number. It will still display a 
    //random number of flags nevertheless.
    //Second problem lies within the display of the needed flag to proceed. In order for it to not be in the same spot every round it has been "tied" to the
    //flagNum, a random number that changes with every loop. The needed flag is to be displayed the first time the condition is met and then never again through
    //a boolean switch. As a contigency plan in the case that after all the loops the condition wasnt met, an if statement was added that checked if the flag was
    //not showing and if so it would display it in the final position. This caused the program to always display the needed flag in the final position no matter
    //what. Afterwards, a statement was added inside the loop that aimed to increase the loop counter if the correct flag was not showing. This, for some reason,
    //caused the menu frame not to appear. In the end i set the loop counter high enough to guarantee the display of the needed flag while imiting the ammount
    //of shown flags with an if statement.
    public void flagPick() {
        if (scoreCounter < 20) {
            repeatCheck = false;
            panel5.setVisible(false);
            panel5.removeAll();

            for (int i = 0; i < 20; i++) {

                randFlagNum = (int) (Math.random() * 20);

                if (i < 8) {

                    if (flagPanels[randFlagNum].isShowing())
                        i--;
                    else {
                        panel5.add(flagPanels[randFlagNum]);
                    }
                }

                if ((randFlagNum % 3 == 0) && repeatCheck == false) {
                    panel5.add(flagPanels[randCountryNum]);
                    repeatCheck = true;
                }

                // if (!flagLabels[randCountryNum].isShowing()) {
                //     i--;
                // }
            }

            // if (!flagLabels[randCountryNum].isShowing()) {
            //     panel5.add(flagPanels[randCountryNum]);
            // }

            panel5.setVisible(true);
        }
    }

    //Picks a country to ask the user and makes sure it doesnt repeat.
    public void countryPick() {
        if (scoreCounter < 20) {
            do{
                randCountryNum = (int) (Math.random() * 20);
            } while (countryCheck[randCountryNum] == true);

            countryCheck[randCountryNum] = true;
            countriesText.setText(countries[randCountryNum]);
        }
    }

}
