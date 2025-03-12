package domain;

public class ConverterManuscript extends Converter<Manuscript>{
    @Override
    public String toString(Manuscript elem) {

        return elem.getAutor()+";"+elem.getNumberOfPages()+";"+elem.getNumberOfWords();
    }

    @Override
    public Manuscript toObject(String string) {
        String[] tokens = string.split(";");
        String tip = tokens[0];
        int nr1 = Integer.parseInt(tokens[1]);
        int nr2 = Integer.parseInt(tokens[2]);
        Manuscript manuscript = new Manuscript(tip,nr1,nr2);
        return manuscript;
    }
}
