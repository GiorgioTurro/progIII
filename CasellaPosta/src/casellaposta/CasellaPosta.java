/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casellaposta;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Giorgio
 */
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
        Box casellaModello = new Box(l);
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

class Box extends Observable{
    private ArrayList<Email> lista;
    private String nomeUtente;
    private String lista1;
    
    public Box(){
        lista = new ArrayList();
        nomeUtente="Giorgio";
    }
    
    public Box(ArrayList<Email> em){
        lista = em;
        nomeUtente="Giorgio";
    }
    
    public void setFrame(){
        MyFrame m = new MyFrame();
        
        setChanged();
        notifyObservers();
        
    }
    
    public void setLista(){
        /*String out="<html>";
        Iterator<Email> it = lista.iterator();
        
        
        while(it.hasNext()){
            out = out + it.next() + "<br>";
            System.out.println(out);
        }
        out = out + " </html>";
        lista1=out;*/
        setChanged();
        notifyObservers();
        
        
    }
    
    public ArrayList<Email> getLista(){
        System.out.println("sono qui");
        return lista;
    }
      
    @Override
    public String toString(){
        return ("Nome proprietario: "+nomeUtente);
    }
    
}

class BoxControl extends JPanel implements ActionListener{
    private Box casella; 
    
    
    
    public BoxControl(Box cas){
        super(new FlowLayout());
        casella = cas;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        System.out.println("ciao");
        if(source.getText().equals("Lista")) casella.setLista();
        if(source.getText().equals("Scrivi")){
            casella.setFrame();
        }
    }
    
}

class BoxView extends JPanel implements Observer{
    private JLabel label;
    private JLabel msgLabel;
    private JButton bottoneLista;
    private JButton creaMessaggio;
    private ArrayList<MyLabel> labels;
    private JPanel panelCenter = new JPanel(new GridLayout(0,1));
    
    public BoxView(BoxControl controller){
        super(new BorderLayout());
        JPanel panelNorth = new JPanel(new FlowLayout());
        add(panelNorth,BorderLayout.NORTH);
        
        label = new JLabel("Account di Posta di Giorgio");
        panelNorth.add(label);
        
        add(panelCenter,BorderLayout.CENTER);
        
        labels = new ArrayList<MyLabel>();
        
        
        
        JPanel panelSouth = new JPanel(new FlowLayout());
        add(panelSouth,BorderLayout.SOUTH);
        
        bottoneLista = new JButton("Lista");
        panelSouth.add(bottoneLista);
        
        creaMessaggio = new JButton("Scrivi");
        panelSouth.add(creaMessaggio);
        
        
        
        
        
        
    }
    

    @Override
    public void update(Observable ob,Object extra_arg){
        Box b = (Box) ob;
        MyLabel l;
        /*msgLabel.setText(b.getLista());*/
        Iterator<Email> it = (b.getLista()).iterator();
        while(it.hasNext()){
            System.out.println("gg");
            Email e = it.next();
            l= new MyLabel(e, e.toString());
            
            l.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
                MyLabel ml = (MyLabel) e.getSource();
                System.out.println(ml.getE());
            }
        });
            labels.add(l);
            panelCenter.add(l);
        }
        //add(panelCenter, BorderLayout.CENTER);
        validate();
        
        
    }
    
    public void setListeners(BoxControl c){
        bottoneLista.addActionListener(c);
        creaMessaggio.addActionListener(c);
    }
}

class MyLabel extends JLabel{
    private Email z;
    
    public MyLabel(Email e,String s){
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
