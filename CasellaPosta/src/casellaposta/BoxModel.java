package casellaposta;

import java.util.ArrayList;
import java.util.Observable;

public class BoxModel extends Observable {
    private ArrayList<Email> lista;
    private String nomeUtente;
    private String lista1;

    public BoxModel(){
        lista = new ArrayList();
        nomeUtente="Giorgio";
    }

    public BoxModel(ArrayList<Email> em){
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