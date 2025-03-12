import Repository.Repository;
import Service.Service;
import UserInt.UI;
import config.properties;
import domain.*;
import Repository.*;

import java.io.File;

public class Main {
    public static void main(String[] args) {






        try {

            AbstractFileRepo<Manuscript> repManuscript;
            AbstractFileRepo<Presentation> repPresentation;

            properties prop = new properties("src/data/settings.properties");
            switch (prop.getRepositoryType()) {
                case "binary":
                    repManuscript = new BinFile<Manuscript>(prop.getManuscriptFile());
                    repPresentation = new BinFile<Presentation>(prop.getPresentationFile());
                    break;
                case "text":

                    ConverterManuscript converterManuscript = new ConverterManuscript();
                    repManuscript = new FileRepo<Manuscript>(prop.getManuscriptFile(), converterManuscript);
                    ConverterPresentation converterPresentation = new ConverterPresentation();
                    repPresentation = new FileRepo<Presentation>(prop.getPresentationFile(), converterPresentation);
                    break;
                default:
                    throw new Exception("Invalid repository type!");
            }

            Service service = new Service(repManuscript);
            Service service2 = new Service(repPresentation);
            UI ui = new UI(service, service2);
            ui.Meniu();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }




}}