package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxControl extends JPanel implements ActionListener {
    private BoxModel casella;



    public BoxControl(BoxModel cas){
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