package a4.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Aectivitate {
    Date zi;
    int pasi;
    int somn;
    ArrayList<ActFizica> activitati;

    public Aectivitate(Date zi, int pasi, int somn, ArrayList<ActFizica> activitati) {
        this.zi = zi;
        this.pasi = pasi;
        this.somn = somn;
        this.activitati = activitati;
    }

    public Date getZi() {
        return zi;
    }

    public void setZi(Date zi) {
        this.zi = zi;
    }

    public int getPasi() {
        return pasi;

    }

    public void setPasi(int pasi) {
        this.pasi = pasi;
    }


    public int getSomn() {
        return somn;
    }

    public void setSomn(int somn) {
        this.somn = somn;
    }

    public ArrayList<ActFizica> getActivitati() {
        return activitati;
    }

    public void setActivitati(ArrayList<ActFizica> activitati) {
        this.activitati = activitati;
    }


    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(zi);
        return formattedDate + " | " + pasi + " | " + somn + " | " + activitati;
    }

}


