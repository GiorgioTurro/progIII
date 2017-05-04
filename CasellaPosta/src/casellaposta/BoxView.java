package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private BoxModel boxModel;
    private BoxControl boxControl;

    public BoxView(BoxModel boxModel){

        //Seguendo il primo pattern MVC alla view creata gli si passa il model e il controller viene creato dalla view
        //al controller gli si passano sia model che view, possibilmente in futuro gli si priverà della view
        this.boxModel = boxModel;
        boxControl = new BoxControl(boxModel, this);

        JPanel toolShelfP = new JPanel();   //Pannello laterale contenente diversi pulsanti di utilità
        toolShelfP.setLayout(new BoxLayout(toolShelfP, BoxLayout.PAGE_AXIS));
        JPanel nomeUtenteP = new JPanel();

        JLabel nomeUtenteL = new JLabel(boxControl.getNomeUtente()); //E' il caso che venga fatto dal Controller o dal Model?

        JButton newEmailB = new JButton("Nuovo");
        JButton allMessagesB = new JButton("Messaggi");
        JButton exitB = new JButton("Esci");

        //Listener dei bottoni
        newEmailB.addActionListener(boxControl);
        allMessagesB.addActionListener(boxControl);
        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //Parametri del Frame
        setTitle("Casella di Posta");
        setSize(555, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Layout del Panel toolShelP
        toolShelfP.add(newEmailB);
        toolShelfP.add(allMessagesB);
        toolShelfP.add(Box.createVerticalGlue());

        //Layout del Panel nomeUtenteP
        nomeUtenteP.add(nomeUtenteL);

        //Layout del frame
        setLayout(new BorderLayout());
        add(nomeUtenteP, BorderLayout.NORTH);
        add(exitB, BorderLayout.SOUTH);
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

}

class MyFrame extends JFrame{

    static MyFrame mf = null;

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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                mf = null;
            }
        });
    }

    public static synchronized MyFrame getMyFrame(){
        if(mf == null)
            mf = new MyFrame();
        return mf;
    }

}

