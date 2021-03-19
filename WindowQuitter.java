import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//Simple class that relates the x button on a frame to the termination of the program.
public class WindowQuitter extends WindowAdapter
{
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}