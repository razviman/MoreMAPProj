package a4.Repo;

import a4.domain.Aectivitate;

import java.util.ArrayList;

public class Repository {
    public ArrayList<Aectivitate> lista;

    public Repository() {
        lista = new ArrayList<>();
    }

    public void add(Aectivitate a) {
        lista.add(a);
    }

    public ArrayList<Aectivitate> getAll() {
        return lista;
    }
}
