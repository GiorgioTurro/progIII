package casellaposta;

import java.awt.*;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CasellaPosta extends JFrame {

    public CasellaPosta(){

        BoxModel casellaModello = new BoxModel();
        BoxView casellaVista = new BoxView(casellaModello);

        casellaModello.addObserver(casellaVista);
    }
    
    public static void main(String[] args) {
        CasellaPosta p = new CasellaPosta();
    }
}
