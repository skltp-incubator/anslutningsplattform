package se.skltp.av.service;

import javax.xml.datatype.XMLGregorianCalendar;

public interface ProducentAnslutning {

    public String getUrl();
    public String getRivProfil();
    public String getTjansteKontrakt();
    public XMLGregorianCalendar getFromTidpunkt();
    public XMLGregorianCalendar getTomTidpunkt();

}
