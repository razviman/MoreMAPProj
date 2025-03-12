package Repository;
import domain.*;
import Repository.AbstractFileRepo;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FileRepo<T extends Document> extends AbstractFileRepo<T> {

    protected Converter<T> converter;

    public FileRepo(String fileName, Converter<T> converter) {
        super(fileName);
        this.converter = converter;
        LoadFromFile();

    }

    @Override
    protected void SaveToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName)   )) {
            documents.sort(Comparator.comparing(Document::getAutor));
            for (var entity : this.documents) {
                if(entity.isConformant()) {
                String asString = converter.toString(entity);
                bw.write(asString);
                bw.newLine();}
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    @Override
    protected void LoadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            while (line != null) {
                documents.add(converter.toObject(line));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de date este gol");
        } catch (IOException e) {
            System.out.println("Error loading file");
        }
    }
}