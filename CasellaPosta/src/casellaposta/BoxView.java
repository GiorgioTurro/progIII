package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class BoxView extends JFrame implements Observer {
    private JLabel label;
    private JLabel msgLabel;
    private JButton bottoneLista;
    private JButton creaMessaggio;
    private ArrayList<EmailLabel> labels;
    private JPanel panelCenter = new JPanel(new GridLayout(0,1));

    public BoxView(BoxControl controller){

        JPanel toolShelfP = new JPanel();   //Pannello laterale contenente diversi pulsanti di utilità
        toolShelfP.setLayout(new BoxLayout(toolShelfP, BoxLayout.PAGE_AXIS));

        JLabel nomeUtenteL = null;  //BoxModel.getNomeUtente()

        JButton newEmailB = new JButton("Nuovo");
        JButton allMessagesB = new JButton("Messaggi");

        //Parametri del Frame
        setTitle("Casella di Posta");
        setSize(555, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Layout del Panel toolShelP
        toolShelfP.add(newEmailB);
        toolShelfP.add(allMessagesB);
        toolShelfP.add(Box.createVerticalGlue());

        //Layout del frame
        setLayout(new BorderLayout());
        add(nomeUtenteL, BorderLayout.NORTH);
        add(new BoxView.ExitButton(), BorderLayout.SOUTH);
        add(panelCenter,BorderLayout.CENTER);
        add(toolShelfP, BorderLayout.WEST);


        //Al momento inutile, non so dirti ma boh
        /*
        labels = new ArrayList<EmailLabel>();

        JPanel panelSouth = new JPanel(new FlowLayout());
        add(panelSouth,BorderLayout.SOUTH);

        bottoneLista = new JButton("Lista");
        panelSouth.add(bottoneLista);

        creaMessaggio = new JButton("Scrivi");
        panelSouth.add(creaMessaggio);
        */
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

class MyFrame extends JFrame{
    private JLabel label;
    private JLabel testo;
    private JLabel testo1;
    private JButton sub;

    public MyFrame(){
        super();
        setTitle("Scrittura messaggio");
        setVisible(true);
        setSize(555, 521);

        //BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));


        JPanel toP = new JPanel(new FlowLayout(FlowLayout.LEFT));   //Pannello destinatari messaggio
        //toP.setBackground(new Color(0,0,255));    //A solo scopo di debug
        toP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        JPanel objP = new JPanel(new FlowLayout(FlowLayout.LEFT));  //Pannello oggetto messaggio
        //objP.setBackground(new Color(255, 0,0));  //A solo scopo di debug
        objP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        JPanel textP = new JPanel(new BorderLayout()); //Pannello testo messaggio
        //textP.setBackground(new Color(0,255,0));  //A solo a scopo di debug

        JLabel toL = new JLabel("A:");          //Label per pannello toP
        JLabel objL = new JLabel("Oggetto");    //Label per pannello objP
        JLabel textL = new JLabel("Testo");     //Label per pannello textP

        JTextField toTF = new JTextField();        //Casella di testo per pannello toP
        JTextField objTF = new JTextField();       //Casella di testo per pannello objP
        JTextArea textA = new JTextArea();      //Area di testo per pannello textP

        toP.add(toL);
        toP.add(toTF);
        objP.add(objL);
        objP.add(objTF);
        textP.add(textL, BorderLayout.NORTH);
        textP.add(textA, BorderLayout.CENTER);

        add(toP);
        add(objP);
        add(textP);

        // 1. Da rivedere
        Dimension textFieldDimension = new Dimension(450, (int) toTF.getPreferredSize().getHeight());
        toTF.setPreferredSize(textFieldDimension);
        objTF.setPreferredSize(textFieldDimension);
        // 1. Fine da rivedere

        sub = new JButton("Invio");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //L'intera app si chiude
    }

}

