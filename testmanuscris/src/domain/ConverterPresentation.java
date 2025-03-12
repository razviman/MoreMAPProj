package domain;

public class ConverterPresentation extends Converter<Presentation> {
    @Override
    public String toString(Presentation elem) {

        return elem.getAutor()+";"+elem.getNumberOfSlides()+";"+elem.getText();
    }
    @Override
    public Presentation toObject(String string) {
        String[] tokens = string.split(";");
        String tip = tokens[0];
        int nr1 = Integer.parseInt(tokens[1]);
        String nr2= tokens[2];
        Presentation presentation = new Presentation(tip, nr1, nr2);
        return presentation;
    }
}
