package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxControl implements ActionListener {
    private BoxModel boxModel;
    private BoxView boxView; //***Dato il pattern Observer-Observable il riferimento alla View potrebbe non servire
    private MyFrame mf;

    public BoxControl(BoxModel bm, BoxView bv){
        boxModel = bm;
        boxView = bv; //***
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String source = (String) ((JButton) e.getSource()).getText();
        if(source.equals("Messaggi")) boxModel.setLista();
        if(source.equals("Nuovo")) mf = MyFrame.getMyFrame(this);
        if(source.equals("Invio")){
            Email email = mf.getEmail();
            boxModel.addEmail(email);
        }
    }

    public String getNomeUtente(){
        return boxModel.getNomeUtente();
    }

}