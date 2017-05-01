package casellaposta;

import java.awt.*;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CasellaPosta extends JFrame {

    public CasellaPosta(){
        
        Email e1 = new Email("Mario","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e2 = new Email("Carlo","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e3 = new Email("Luca","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e4 = new Email("Laura","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e5 = new Email("Martina","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        ArrayList<Email> l = new ArrayList();
        l.add(e5);
        l.add(e1);
        l.add(e2);
        l.add(e3);
        l.add(e4);
        BoxModel casellaModello = new BoxModel(l);
        BoxControl casellaControllo = new BoxControl(casellaModello);
        BoxView casellaVista = new BoxView(casellaControllo);
        
        casellaVista.setListeners(casellaControllo);
        casellaModello.addObserver(casellaVista);
        
        setLayout(new BorderLayout());
        add(casellaVista, BorderLayout.CENTER);
        add(new ExitButton(), BorderLayout.SOUTH);
        setTitle("Casella di Posta");
        setSize(555, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        CasellaPosta p = new CasellaPosta();
    }
    
    class ExitButton extends JButton {
    public ExitButton () {
        super("Exit");
        addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
        		System.exit(0);
			}
		});
    }
    }
    
}

class EmailLabel extends JLabel{
    private Email z;
    
    public EmailLabel(Email e,String s){
        super(s);
        this.z=e;
    }
    
    public Email getE(){
        return this.z;
    }
    
}

class MyFrame extends JFrame{
    private JLabel label;
    private JLabel testo;
    private JLabel testo1;
    private JButton sub;
    
    public MyFrame(){
        super();
        setTitle("Scrittura messaggio");
        setVisible(true);
        setSize(555, 521);

        //BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel toP = new JPanel(new FlowLayout(FlowLayout.LEFT));   //Pannello destinatari messaggio
            toP.setBackground(new Color(0,0,255));
            toP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        JPanel objP = new JPanel(new FlowLayout(FlowLayout.LEFT));  //Pannello oggetto messaggio
            objP.setBackground(new Color(255, 0,0));
            objP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        JPanel textP = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Pannello testo messaggio
            textP.setBackground(new Color(0,255,0));

        JLabel toL = new JLabel("A:");          //Label per pannello toP
        JLabel objL = new JLabel("Oggetto");    //Label per pannello objP
        JLabel textL = new JLabel("Testo");     //Label per pannello textP

        JTextField toTF = new JTextField();        //Casella di testo per pannello toP
        JTextField objTF = new JTextField();       //Casella di testo per pannello objP
        JTextArea textA = new JTextArea();      //Area di testo per pannello textP

        toP.add(toL);
        toP.add(toTF);
        objP.add(objL);
        objP.add(objTF);
        textP.add(textL);
        textP.add(textA);

        add(toP);
        add(objP);
        add(textP);
        
        sub = new JButton("Invio");

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   L'intera app si chiude
    }
    
}
