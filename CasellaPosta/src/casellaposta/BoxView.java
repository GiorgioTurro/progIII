package casellaposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class BoxView extends JFrame implements Observer {
    private JLabel label;
    private JLabel msgLabel;
    private JButton bottoneLista;
    private JButton creaMessaggio;
    private JPanel panelCenter;

    private BoxModel boxModel;
    private BoxControl boxControl;

    public BoxView(BoxModel boxModel){

        //Seguendo il primo pattern MVC alla view creata gli si passa il model e il controller viene creato dalla view
        //al controller gli si passano sia model che view, possibilmente in futuro gli si priverà della view
        this.boxModel = boxModel;
        boxControl = new BoxControl(boxModel, this);

        panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));

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
        BorderLayout frameBL = new BorderLayout();
        frameBL.setHgap(20);
        setLayout(frameBL);
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

        panelCenter.removeAll();

        Iterator<Email> it = (b.getLista()).iterator();
        while(it.hasNext()){
            Email e = it.next();
            l = new EmailLabel(e);
            l.addMouseListener(boxControl);
            l.setBackground(new Color(0,0,255));
            panelCenter.add(l);
        }
        //panelCenter.add(Box.createVerticalBox());
        panelCenter.setBackground(new Color(255,0,0));
        validate();
    }
}

class MyFrame extends JFrame{
    static MyFrame mf = null;

    private JLabel label;
    private JLabel testo;
    private JLabel testo1;
    private JTextField toTF;
    private JTextField objTF;
    private JTextArea textA;
    private JButton sub;


    public MyFrame(BoxControl c){
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

        toTF = new JTextField();        //Casella di testo per pannello toP
        objTF = new JTextField();       //Casella di testo per pannello objP
        textA = new JTextArea();      //Area di testo per pannello textP

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
        add(sub);
        sub.addActionListener(c);
        
        

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                mf = null;
            }
        });
    }

    public MyFrame(BoxControl c,Email em){
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

        toTF = new JTextField();        //Casella di testo per pannello toP

        objTF = new JTextField();       //Casella di testo per pannello objP
        objTF.setText("FW :"+em.getArg());
        textA = new JTextArea();      //Area di testo per pannello textP
        textA.setText("FW :"+em.getTes());

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
        add(sub);
        sub.addActionListener(c);
        
        

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                mf = null;
            }
        });
    }    
    

    public static synchronized MyFrame getMyFrame(BoxControl c){
        if(mf == null)
            mf = new MyFrame(c);
        return mf;
    }
    
    public static synchronized MyFrame forward(BoxControl c,Email em){
        if(mf == null){
            mf = new MyFrame(c,em);
        }
        return mf;
    }
    
    public Email getEmail(){
        return new Email("Giorgio",toTF.getText(),objTF.getText(),textA.getText(),"alta",new Date());
    }

}

class ReadMsg extends JFrame{
    
    public ReadMsg(BoxControl c,EmailLabel ml){
        setTitle("Messaggio");
        setVisible(true);
        setSize(555, 521);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));


        JPanel toP = new JPanel(new FlowLayout(FlowLayout.LEFT));   //Pannello destinatari messaggio
        //toP.setBackground(new Color(0,0,255));    //A solo scopo di debug
        toP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        JPanel objP = new JPanel(new FlowLayout(FlowLayout.LEFT));  //Pannello oggetto messaggio
        //objP.setBackground(new Color(255, 0,0));  //A solo scopo di debug
        objP.setMaximumSize(new Dimension(Short.MAX_VALUE, 15));
        
        JPanel textP = new JPanel(new BorderLayout());
                
        
        JLabel toL = new JLabel("Mittente: "+ml.getE().getMit());          //Label per pannello toP
        JLabel objL = new JLabel("Oggetto: "+ml.getE().getArg());    //Label per pannello objP
        JLabel textL = new JLabel("Testo: "+ml.getE().getTes()); 
                    
        toP.add(toL);
        objP.add(objL);
        textP.add(textL, BorderLayout.NORTH);
 
        add(toP);
        add(objP);
        add(textP);
                    
        JButton del = new JButton("Elimina");
        JButton fwd = new JButton("Forward");
                   
        add(del);
        add(fwd);
        
        del.addActionListener(c);
        fwd.addActionListener(c);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                
            }
        });
    }
    
    
}

