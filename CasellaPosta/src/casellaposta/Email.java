/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casellaposta;

import java.util.Date;

/**
 *
 * @author Giorgio
 */
public class Email {
    
    private String mittente;
    private String destinatario;
    private String oggetto;
    private String testo;
    private String priorita;
    private Date dataInvio;
    
    public Email(){
        mittente="";
        destinatario="";
        oggetto = "";
        testo = "";
        priorita = "";
        dataInvio = null;
    }
    
    public Email(String mit,String dest,String arg,String tes,String prt,Date dt){
        mittente=mit;
        destinatario=dest;
        oggetto=arg;
        testo=tes;
        priorita=prt;
        dataInvio=dt;
    }
    
    public String getMit(){
        return mittente;
    }
    
    public String getDes(){
        return destinatario;
    }
    
    public String getArg(){
        return oggetto;
    }
    
    public String getTes(){
        return testo;
    }
    
    public String getPrt(){
        return priorita;
    }
    
    public Date getDt(){
        return dataInvio;
    }
    
    @Override
    public String toString(){
        return mittente + " " + oggetto + " " + dataInvio;
    }
    
}
