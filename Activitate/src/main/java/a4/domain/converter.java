package a4.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class converter {


    public String toString(Aectivitate elem) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(elem.getZi());
        return formattedDate +";" + elem.getPasi() +";" + elem.getSomn()+";"+elem.getActivitati();
    }


    public Aectivitate toEntity(String elem) throws ParseException {

        String[] tokens = elem.split(";");
        String data = tokens[0];
        int pasi = Integer.parseInt(tokens[1]);
        int somn = Integer.parseInt(tokens[2]);
        ArrayList<ActFizica> lista = new ArrayList<>();
        String[] separator = tokens[3].split(",");
        Date date;
        int ok=0;
        while(separator.length>ok) {
            try {
                String act = separator[ok].replace("[", "");
               String timp = separator[ok+1].replace("]", "");

                lista.add(new ActFizica(act, Integer.parseInt(timp)));
                ok+=2;


            } catch (Exception e) {
                System.out.println("Eroare la parsaree");
            }


        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = formatter.parse(data);

        return new Aectivitate(date, pasi, somn, lista);
    }
}
