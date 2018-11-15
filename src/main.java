
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leahluong
 */
public class main extends JFrame{
     private JPanel panel;
     static JTextArea output;
     
     public main(){
         setTitle("Theme Park Stimulation");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        panel = new JPanel();
        panel.setLayout(new BorderLayout(5,5));
        
        output = new JTextArea();
        output.setBackground(Color.BLACK);
        output.setForeground(Color.WHITE);
        output.setEditable(false);
        
        output.setFont(new Font("Courier New",Font.PLAIN, 28));
        
        JScrollPane scrollPane = new JScrollPane(output);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        this.add(panel);
     }
    public static void main(String[]args) throws InterruptedException, FileNotFoundException{
        
        
        ThemePark mythemepark = new ThemePark();
        mythemepark.run();
        
        
    }
    
   
}
