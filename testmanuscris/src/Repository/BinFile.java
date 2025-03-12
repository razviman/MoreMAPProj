package Repository;
import domain.*;
import java.io.*;
import java.util.ArrayList;
import Repository.*;

public class BinFile <T extends Document> extends AbstractFileRepo<T> {

    public BinFile(String fileName) {
        super(fileName);
        try {
            LoadFromFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void SaveToFile() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            oos.writeObject(documents);
        } catch (IOException e) {
            System.out.println("Eroare la salvare");
        }

    }

    @Override
    protected void LoadFromFile()  {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName))){
            this.documents = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException exc) {
            System.out.println("Fisierul a fost initializat!");
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println("Eroare la salvare");
        }
    }
}