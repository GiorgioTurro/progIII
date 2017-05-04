package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxControl extends JPanel implements ActionListener {
    private BoxModel casella;
    private BoxView boxView;

    public BoxControl(BoxModel cas){
        super(new FlowLayout());
        casella = cas;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String source = (String) ((JButton) e.getSource()).getText();
        if(source.equals("Messaggi")) casella.setLista();
        if(source.equals("Nuovo")) MyFrame.getMyFrame();
    }

    public String getNomeUtente(){
        return casella.getNomeUtente();
    }

}