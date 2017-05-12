package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoxControl implements ActionListener,MouseListener {
    private BoxModel boxModel;
    private BoxView boxView; //***Dato il pattern Observer-Observable il riferimento alla View potrebbe non servire
    private MyFrame mf;
    private EmailLabel ml;

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
        if(source.equals("Elimina")){
            boxModel.elimina(ml);
            ml=null;
        }
        
        if(source.equals("Forward")) mf = MyFrame.forward(this,ml.getE());
    }
    
    public void mouseClicked(MouseEvent e){
        ml = (EmailLabel) e.getSource();
        
        ReadMsg msg = new ReadMsg(this,ml);
        
    }

    public String getNomeUtente(){
        return boxModel.getNomeUtente();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}