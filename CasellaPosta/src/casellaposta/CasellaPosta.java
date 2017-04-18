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
public class CasellaPosta extends JFrame implements ActionListener{

    
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
        
        //setLayout(new BorderLayout());
        add(casellaVista, BorderLayout.CENTER);
        add(new ExitButton(), BorderLayout.SOUTH);
        add(new sideToolShelf(), BorderLayout.EAST);

        setTitle("Casella di Posta");
        setSize(320, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

    //Potrebbe essere inutile a seconda di cosa ha fatto Giorgio
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("New")){
            //Crea un pannello all'interno nel center del BorderLayout con due text field per To: e Message: (Creare i relativi label)
        }

    }
}
//Casella con i pulsanti new inbox forward e delete da posizionare nel lato sinistro del frame principale
class sideToolShelf extends JPanel {

    public sideToolShelf() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JButton("New"));
        add(new JButton("Inbox"));
        add(new JButton("Forward"));
        add(new JButton("Delete"));
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
    
    public void setLista(){
        String out="<html>";
        Iterator<Email> it = lista.iterator();
        
        
        while(it.hasNext()){
            out = out + it.next() + "<br>";
            System.out.println(out);
        }
        out = out + " </html>";
        lista1=out;
        setChanged();
        notifyObservers();
    }
    
    public String getLista(){
        System.out.println("sono qui");
        return lista1;
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
    }
    
}

class BoxView extends JPanel implements Observer{
    private JLabel label;
    private JLabel msgLabel;
    private JButton bottoneLista;
    
    public BoxView(BoxControl controller){
        super(new BorderLayout());
        JPanel panelNorth = new JPanel(new FlowLayout());
        add(panelNorth,BorderLayout.NORTH);
        
        label = new JLabel("Account di Posta di Giorgio");
        panelNorth.add(label);
        
        JPanel panelCenter = new JPanel(new FlowLayout());
        add(panelCenter,BorderLayout.CENTER);
        
        msgLabel = new JLabel("Empty Box");
        panelCenter.add(msgLabel);
        
        msgLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println(e);
            }
        });
        
        JPanel panelSouth = new JPanel(new FlowLayout());
        add(panelSouth,BorderLayout.SOUTH);
        
        bottoneLista = new JButton("Lista");
        panelSouth.add(bottoneLista);
        
        addMouseListener(new MouseAdapter(){
            
        });
        
        
        
        
    }

    @Override
    public void update(Observable ob,Object extra_arg){
        Box b = (Box) ob;
        msgLabel.setText(b.getLista());
    }
    
    public void setListeners(BoxControl c){
        bottoneLista.addActionListener(c);
    }

    public static void main(String[] args) {
        CasellaPosta p = new CasellaPosta();
    }
}
