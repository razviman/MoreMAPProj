package Repository;

import domain.Document;

import java.util.ArrayList;

public abstract class InterfaceRepo<T extends Document> {
    protected ArrayList<T> documents;

    public InterfaceRepo() {
        documents = new ArrayList<T>();
    }

    public abstract void Add(T doc) ;
    public abstract ArrayList<T> getDocuments();
    public abstract boolean isConform(T elem);

}
