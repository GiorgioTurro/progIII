package casellaposta;

import java.awt.*;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CasellaPosta extends JFrame {

    public CasellaPosta(){

        BoxModel casellaModello = new BoxModel();
        BoxControl casellaControllo = new BoxControl(casellaModello);
        BoxView casellaVista = new BoxView(casellaControllo);

        casellaModello.addObserver(casellaVista);
    }
    
    public static void main(String[] args) {
        /*  Queste istruzioni sono state inserite nel modello BoxModel
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
        */
        CasellaPosta p = new CasellaPosta();
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