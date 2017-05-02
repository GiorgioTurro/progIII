package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class BoxView extends JPanel implements Observer {
    private JLabel label;
    private JLabel msgLabel;
    private JButton bottoneLista;
    private JButton creaMessaggio;
    private ArrayList<EmailLabel> labels;
    private JPanel panelCenter = new JPanel(new GridLayout(0,1));

    public BoxView(BoxControl controller){
        super(new BorderLayout());
        JPanel panelNorth = new JPanel(new FlowLayout());
        add(panelNorth,BorderLayout.NORTH);

        label = new JLabel("Account di Posta di Giorgio");
        panelNorth.add(label);

        add(panelCenter,BorderLayout.CENTER);

        labels = new ArrayList<EmailLabel>();

        JPanel panelSouth = new JPanel(new FlowLayout());
        add(panelSouth,BorderLayout.SOUTH);

        bottoneLista = new JButton("Lista");
        panelSouth.add(bottoneLista);

        creaMessaggio = new JButton("Scrivi");
        panelSouth.add(creaMessaggio);
    }

    @Override
    public void update(Observable ob, Object extra_arg){
        BoxModel b = (BoxModel) ob;
        EmailLabel l;
        /*msgLabel.setText(b.getLista());*/
        Iterator<Email> it = (b.getLista()).iterator();
        while(it.hasNext()){
            System.out.println("gg");
            Email e = it.next();
            l= new EmailLabel(e, e.toString());

            l.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){

                    EmailLabel ml = (EmailLabel) e.getSource();
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
