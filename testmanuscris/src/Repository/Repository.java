package Repository;

import domain.Document;

import java.util.ArrayList;

public class Repository<T extends Document> extends InterfaceRepo<T>{



    @Override
    public void Add(T doc) {
        documents.add(doc);
    }

    @Override
    public ArrayList<T> getDocuments() {
        return documents;
    }

    @Override
    public boolean isConform(T elem) {
        return elem.isConformant();
    }

}

