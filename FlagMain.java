// PROJECT TITLE: FINAL PROJECT 106
// AUTHOR NAME: GIORGOS-PANAGIOTIS KATSONIS
// PURPOSE OF PROJECT: TO CREATE A SIMPLE FLAG-GUESSING GAME
// VERSION or DATE: 03/2020
// AUTHORS: giorgos_katsonis@hotmail.com 
// COPYRIGHT INFORMATION:  Content is open source.

//Calls required resources.
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;

//All elements of the menu GUI are declared. 
//The purpose of the commented out code was to create an interchangeable interface to allow the user to change from one mode
//to another whithout reseting the program. I was unable to make this venture operable (likely due to the need of multithreading)
//but have decided to maintain the code for documentation and further experimentation.
public class FlagMain extends JFrame implements ActionListener {
    private JTextField infoText;
    // private JTextField questionText;

    //Creates partA and partB frames but retains them invisible. This causes the main song to start playing even though it is 
    //called inside partA. Could move this in the action command to prevent such occurance but decided it benefits the experience.
    private PartA frameA = new PartA();
    private PartB frameB = new PartB();
    private WindowQuitter wquit = new WindowQuitter();

    private JButton modeOne;
    private JButton modeTwo;
    // private JButton YES;
    // private JButton NO;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    // private JPanel questionPanelText;
    // private JPanel questionPanelButtons;
    // private JPanel questionPanelBig;

    //Constructor. Elements of the GUI and needed objects are created and have their parameters adjusted.
    public FlagMain() {
        setTitle("Main Menu");

        //Creates and adjusts the info textfield
        infoText = new JTextField(20);
        infoText.setText("Please select a mode.");
        infoText.setHorizontalAlignment(JTextField.CENTER);
        infoText.setEditable(false);

        // questionText = new JTextField(20);
        // questionText.setText("Do you want to change modes? All progress will be lost.");
        // questionText.setHorizontalAlignment(JTextField.CENTER);
        // questionText.setEditable(false);

        //Creates the buttons and provides them with action listeners
        modeOne = new JButton("Country Guessing");
        modeOne.addActionListener(this);

        modeTwo = new JButton("Flag Guessing");
        modeTwo.addActionListener(this);

        // YES = new JButton("YES");
        // YES.addActionListener(this);

        // NO = new JButton("NO");
        // NO.addActionListener(this);

        //Creates panels used for organisation purposes, gives panel one some texture.
        panel1 = new JPanel();
        panel1.add(infoText);
        panel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(modeOne, BorderLayout.WEST);
        panel2.add(modeTwo, BorderLayout.EAST);

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(panel1, BorderLayout.NORTH);
        panel3.add(panel2, BorderLayout.SOUTH);

        // questionPanelText = new JPanel();
        // questionPanelText.add(questionText);
        // questionPanelText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // questionPanelButtons = new JPanel();
        // questionPanelButtons.setLayout(new BorderLayout());
        // questionPanelButtons.add(YES, BorderLayout.EAST);
        // questionPanelButtons.add(NO, BorderLayout.WEST);

        // questionPanelBig = new JPanel();
        // questionPanelBig.setLayout(new BorderLayout());
        // questionPanelBig.add(questionPanelText, BorderLayout.NORTH);
        // questionPanelBig.add(questionPanelButtons, BorderLayout.SOUTH);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panel3);

        //Sets the parameters for the game frames. Needed a bit more space for partB hence the difference in dimensions.
        frameA.addWindowListener(wquit);
        frameA.setResizable(false);
        frameA.setSize(500, 410);

        frameB.addWindowListener(wquit);
        frameB.setResizable(false);
        frameB.setSize(600, 470);

    }

    //Very simple code that determines the button pressed and makes the corresponding frame visible while it disposes the other
    // to save processing power.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Country Guessing")) {
            frameA.setVisible(true);
            frameB.dispose();

            // if (frameB.isShowing()) {
            //     getContentPane().remove(panel3);
            //     getContentPane().add(questionPanelBig);
            //     if (e.getActionCommand().equals("YES")) {
            //         frameB.dispose();
            //         frameA.dispose();

            //         PartA frameA = new PartA();
            //         frameA.addWindowListener(wquit);
            //         frameA.setResizable(false);
            //         frameA.setSize(500, 410);
            //         frameA.setVisible(true);

            //     } else if (e.getActionCommand().equals("NO")) {
            //         getContentPane().remove(questionPanelBig);
            //         getContentPane().add(panel3);
            //     }
            // } else {
            //     frameA.setVisible(true);
            // }
        }
        
        if (e.getActionCommand().equals("Flag Guessing")) {
            frameB.setVisible(true);
            frameA.dispose();
            
            // if (frameA.isShowing()) {
            //     getContentPane().remove(panel3);
            //     getContentPane().add(questionPanelBig);
            //     if (e.getActionCommand().equals("YES")) {
            //         frameB.dispose();
            //         frameA.dispose();

            //         PartB frameB = new PartB();
            //         frameB.addWindowListener(wquit);
            //         frameB.setResizable(false);
            //         frameB.setSize(500, 410);
            //         frameB.setVisible(true);

            //     } else if (e.getActionCommand().equals("NO")) {
            //         getContentPane().remove(questionPanelBig);
            //         getContentPane().add(panel3);
            //     }
            // }
            // else {
            //     frameB.setVisible(true);
            // }
                
        }
    }


    //Main method used to call the menu frame.
    public static void main(String[] args) {
        FlagMain frameSwitch = new FlagMain();
        WindowQuitter wquit = new WindowQuitter();
        frameSwitch.addWindowListener(wquit);
        frameSwitch.setResizable(false);
        frameSwitch.setSize(300, 200);
        frameSwitch.setVisible(true);
    }
}