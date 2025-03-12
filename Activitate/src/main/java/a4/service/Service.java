package a4.service;

import a4.Repo.Repository;
import a4.domain.ActFizica;
import a4.domain.Aectivitate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Service{
    Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public void add(Date data, int pasi, int somn, ArrayList<ActFizica> activitati ) {

        Aectivitate activ = new Aectivitate(data, pasi, somn, activitati);

        try {
            repo.add(activ);
        }catch(Exception e) {
            System.out.println("Eroare");
        }
    }

    public ArrayList<Aectivitate> getAll() {
        ArrayList<Aectivitate> lista = repo.getAll();
        lista.sort(Comparator.comparing(Aectivitate::getZi));

        return lista;
    }

    public ArrayList<Aectivitate> search(int min, int max) {
        ArrayList<Aectivitate> lista = repo.getAll();
        ArrayList<Aectivitate> lista_new = new ArrayList<>();
        for(Aectivitate a : lista) {
            int timp =0;
            for(ActFizica b : a.getActivitati())
            {
                timp = timp + b.getTimp();
            }
            if (timp>=min && timp<=max) {
                lista_new.add(a);
            }
        }
        return lista_new;
    }

    public boolean find(Date data) {
        for (Aectivitate a : repo.getAll()) {
            if (a.getZi().compareTo(data)==0)
                return true;
        }
        return false;
    }

    public void update(Date data, int pasi, int somn) {
        for (Aectivitate a : repo.getAll()) {
            if (a.getZi().compareTo(data)==0) {
                a.setPasi(pasi);
                a.setSomn(somn);
            }
        }
    }
}
