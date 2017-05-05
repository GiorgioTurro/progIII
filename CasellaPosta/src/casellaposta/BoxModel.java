package casellaposta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class BoxModel extends Observable {
    private String nomeUtente;
    private ArrayList<Email> lista;

    public BoxModel(){
        lista = new ArrayList();
        Email e1 = new Email("Mario","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e2 = new Email("Carlo","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e3 = new Email("Luca","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e4 = new Email("Laura","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        Email e5 = new Email("Martina","Giorgio","Posta","Ho bisogno di un favore","alta",new Date());
        lista.add(e5);
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);
        nomeUtente="Giorgio";
    }

    public BoxModel(ArrayList<Email> em){
        lista = em;
        nomeUtente="Giorgio";
    }

    public String getNomeUtente(){
        return nomeUtente;
    }

    public void setLista(){
        //Qui andrebbe inserito il recupero delle mail dal server, tipo un refresh
        setChanged();
        notifyObservers();
    }

    public ArrayList<Email> getLista(){
        System.out.println("sono qui");
        return lista;
    }
    
    public void addEmail(Email em){
        lista.add(em);
        setChanged();
        notifyObservers();
        
    }

    @Override
    public String toString(){
        return ("Nome proprietario: "+nomeUtente);
    }
}

class EmailLabel extends JLabel {
    private Email z;

    public EmailLabel(Email e,String s){
        super(s);
        this.z=e;
    }

    public Email getE(){
        return this.z;
    }
}