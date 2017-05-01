package casellaposta;

import java.awt.*;
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
        setLayout(new BorderLayout());
        setTitle("Scrittura messaggio");
        setSize(555, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel j = new JPanel(new FlowLayout());
        add(j,BorderLayout.NORTH);
        
        label = new JLabel("Scrittura messaggi");
        j.add(label);
        
        JPanel z = new JPanel(new GridLayout(0,1));
        add(z,BorderLayout.CENTER);
        
        testo = new JLabel("Testo");
        z.add(testo);
        JTextArea a = new JTextArea();
        z.add(new JScrollPane(a));
        
        testo1 = new JLabel("Argomento");
        z.add(testo1);
        JTextArea b = new JTextArea();
        z.add(new JScrollPane(b));
        
        sub = new JButton("Invio");
    }
    
}
