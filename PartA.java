//Imports the necessary libraries.
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class PartA extends JFrame implements ActionListener {
    // Declares all needed variables and objects
    private JTextField resultText;
    private JTextField streakText;
    private JTextField scoreText;
    private JTextField livesText;

    private JButton submitButton;

    private TitledBorder scoreBorder;
    private TitledBorder streakBorder;
    private TitledBorder livesBorder;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    private JLabel[] flagLabels = new JLabel[20];

    private ImageIcon[] flagIcons = new ImageIcon[20];

    private boolean[] flagCheck = new boolean[20];

    private JComboBox dataBox;

    private int streakCounter;
    private int scoreCounter;
    private int livesCounter;
    private int round;
    private int randNum;

    //Object of the sounds class used to play music and sound effects
    private Sounds effects = new Sounds();

    // This doesnt need to be initialized in the constructor since the values are fixed.
    private String[] countries = { "Wales", "Japan", "Mozambique", "Greece", "Antwerp", "Nepal", "Friesland",
            "North Caucasian Emirate", "Ghana", "Senegal", "Sicily", "Swaziland", "Bermuda", "Virgin Islands",
            "Kyrgyzstan", "Northen Marianas Islands", "Korea", "Easter Islands", "Hong Kong", "Vatican City"};

    public PartA() {
        setTitle("Country Guessing (Part A)");

        //Calls the method that plays the main song
        effects.MainSongEffect();
        
        // Importing the images and placing it inside JLabels
        for (int i = 0; i < 20; i++) {
            flagIcons[i] = new ImageIcon("big".concat(Integer.toString(i).concat(".jpg")));
            flagLabels[i] = new JLabel(flagIcons[i]);
        }

        // Create TitledBorders
        scoreBorder = new TitledBorder("Score");
        streakBorder = new TitledBorder("Streak");
        livesBorder = new TitledBorder("Lives");

        // Initializes all counters.
        scoreCounter = 0;
        round = 0;
        streakCounter = 0;
        livesCounter = 3;

        // Creates the Textfields, makes the text allign to the center of the box and
        // gives a bevel effect
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

        scoreText = new JTextField(10);
        scoreText.setHorizontalAlignment(JTextField.CENTER);
        scoreText.setText(Integer.toString(scoreCounter).concat("/".concat(Integer.toString(round))));
        scoreText.setEditable(false);
        scoreText.setBorder(scoreBorder);

        // Created all needed panels
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        // Creates a comboBox and fills it with the entries of the "countries" array.
        dataBox = new JComboBox(countries);

        // Creates the submit answer button and provides it with an action listener.
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);

        // Adds all components to the panels and gives panel one and two a bevel
        // texture.
        panel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel1.setLayout(new BorderLayout());
        panel1.add(panel5, BorderLayout.CENTER);
        panel1.add(panel3, BorderLayout.SOUTH);
        panel1.add(panel4, BorderLayout.NORTH);

        panel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel2.setLayout(new BorderLayout());
        panel2.add(dataBox, BorderLayout.CENTER);

        panel2.add(submitButton, BorderLayout.EAST);

        panel3.setLayout(new FlowLayout());
        panel3.add(resultText);

        panel4.setLayout(new FlowLayout());
        panel4.add(streakText);
        panel4.add(scoreText);
        panel4.add(livesText);

        panel5.setLayout(new BorderLayout());
        flagPick();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel1, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {

        //Code performed when the user clicks on the submit answer button.
        if (e.getActionCommand().equals("Submit Answer")) {
            round++;

            //Checks if the user is correct by comparing the text on the selected cell of the databox to the entry of the displayed flag in the countries array.
            //If he is correct the text changes to CORRECT and a sound plays. Also the item is removed from the combobox and the score goes up.
            if (dataBox.getSelectedItem().equals(countries[randNum])) {
                resultText.setText("CORRECT!");
                effects.correctEffect();
                dataBox.removeItem(countries[randNum]);
                scoreCounter++;
                if (scoreCounter != 20)
                    flagPick();
                streakCounter++;
                streakText.setText(Integer.toString(streakCounter));
                scoreText.setText(Integer.toString(scoreCounter).concat("/".concat(Integer.toString(round))));
            }

            //If the user is wrong and has lives left, the text changes to wrong, a sound plays, streak is reset and a life is lost.
            //if the user has no more lives, the game ends, the text changes to GAME OVER and the submit answer button becomes unusable. Also a sound plays.
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
            //and the combobox only has one item that says YOU WIN!
            if (scoreCounter == 20) {
                resultText.setText("CONGRATULATIONS!");
                effects.VictoryEffect();
                submitButton.removeActionListener(this);
                dataBox.addItem("You win!");
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

    //Simple method that creates a random number, relates it to an entry on the flagLabels array and ,after checking through a boolean array if the flag has
    //been displayed before, adds the flag in the main panel. The visibility of the panel changes in order to allow for the change of the flags without any bugs.
    public void flagPick() {
        if (scoreCounter < 20) {
            panel5.setVisible(false);
            panel5.removeAll();
            do {
                randNum = (int) (Math.random() * 20);
            } while (flagCheck[randNum] == true);

            flagCheck[randNum] = true;
            panel5.add(flagLabels[randNum], BorderLayout.CENTER);
            panel5.setVisible(true);
        }
    }
    
}
