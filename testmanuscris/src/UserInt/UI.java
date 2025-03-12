package UserInt;
import Service.*;
import domain.Document;
import domain.Manuscript;
import domain.Presentation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UI {
    Service<Manuscript> serviceManuscript;
    Service<Presentation> servicePresentation;

    public UI(Service<Manuscript> serviceManuscript, Service<Presentation> servicePresentation) {
        this.serviceManuscript = serviceManuscript;
        this.servicePresentation = servicePresentation;
    }

    public void AfisareMenu() {
        System.out.println("MENIU");
        System.out.println("1.Adauga Manuscript");
        System.out.println("2.Adauga Presentation");
        System.out.println("3.Afisare Documente");
        System.out.println("4.Afisare Documente Non-Conforme");
        System.out.println("0.Exit");
    }

    public void Meniu() {
        int action;
        boolean ruleaza=true;
        while(ruleaza) {
            Scanner sc = new Scanner(System.in);
            AfisareMenu();
            action = sc.nextInt();
            if (action == 1) {
                System.out.println("Introduceti numele autorului: ");
                String autor = sc.next();
                System.out.println("Introduceti numarul de cuvinte: ");
                int nrcuvinte = sc.nextInt();
                System.out.println("Introduceti numarul de pagini ");
                int nrpagini = sc.nextInt();
                Manuscript man = new Manuscript(autor, nrcuvinte, nrpagini);
                serviceManuscript.add(man);
            }
            else if (action == 2) {
                System.out.println("Introduceti numele autorului: ");
                String autor = sc.next();
                System.out.println("Introduceti numarul de slideuri: ");
                int nrslide = sc.nextInt();
                System.out.println("Introduceti textul ");
                String text = sc.next();
                Presentation p = new Presentation(autor, nrslide, text);
                servicePresentation.add(p);

            }

            else if (action == 3) {
                ArrayList<Manuscript> listaManuscript = serviceManuscript.getAll();
                ArrayList<Presentation> listaPresentation = servicePresentation.getAll();
                for(Manuscript manuscript : listaManuscript) {
                    System.out.println(manuscript.toString());
                }
                for(Presentation presentation : listaPresentation) {
                    System.out.println(presentation.toString());
                }
            }
            else if (action == 4) {
                ArrayList<Document> listaDocs = new ArrayList<Document>();
                ArrayList<Manuscript> listaManuscript = serviceManuscript.getAll();
                ArrayList<Presentation> listaPresentation = servicePresentation.getAll();
                listaDocs.addAll(listaManuscript);
                listaDocs.addAll(listaPresentation);
                listaDocs.sort(Comparator.comparing(Document::getAutor));
                for(Document doc : listaDocs) {
                    if (!doc.isConformant())
                        System.out.println(doc.toString());
                }

            }
            else if(action == 0) {
                ruleaza=false;
            }
        }
    }

}
