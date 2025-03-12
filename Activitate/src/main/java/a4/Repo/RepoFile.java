package a4.Repo;


import a4.domain.*;

import java.io.*;
import java.util.ArrayList;

public class RepoFile extends Repository {
    String fileName ;
    protected converter converter = new converter();

    public RepoFile(String fileName) {
        this.fileName = fileName;
        try {
            LoadFromFile();

        }catch (Exception e) {
            throw new RuntimeException();
        }
    }


    protected void SaveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Object entity : lista) {
                String asString = converter.toString((Aectivitate) entity);
                bw.write(asString);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }


    protected void LoadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();

            while (line != null) {

                Aectivitate act=  converter.toEntity(line);
                if(act.getActivitati().size()>1)
                    for(ActFizica a: act.getActivitati())
                    {
                        ArrayList<ActFizica> activitati = new ArrayList<>();
                        activitati.add(a);
                        Aectivitate actiune = new Aectivitate(act.getZi(), act.getPasi(), act.getSomn(), activitati);
                        lista.add(actiune);
                    }
                else lista.add(act);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de date este gol");
        } catch (IOException e) {
            System.out.println("Error loading file");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void add(Aectivitate entity) {

        super.add(entity);
        SaveToFile();

    }
}
