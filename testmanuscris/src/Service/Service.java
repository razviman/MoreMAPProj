package Service;

import Repository.AbstractFileRepo;
import domain.Document;

import javax.print.Doc;
import java.util.ArrayList;

public class Service<T extends Document> {

     AbstractFileRepo<T> repository;

    public Service(AbstractFileRepo repository) {
        this.repository = repository;
    }
    public void add(T elem) {
        repository.Add(elem);
    }

    public ArrayList<T> getAll() {
        return repository.getDocuments();
    }

    public boolean isConform(T elem) {
        return repository.isConform(elem);
    }

}
