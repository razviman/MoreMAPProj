package Repository;
import Repository.Repository;
import domain.*;

import java.util.ArrayList;

public abstract class AbstractFileRepo<T extends Document> extends Repository<T> {
    protected String fileName;

    public AbstractFileRepo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void Add(T elem) {
        super.Add(elem);
        SaveToFile();
    }

    protected abstract void SaveToFile() ;
    protected abstract void LoadFromFile() ;
}