package domain;

public class Manuscript extends Document{
    int NumberOfWords;
    int NumberOfPages;

    public Manuscript(String autor, int NumberOfWords, int NumberOfPages) {
        super(autor);
        this.NumberOfWords = NumberOfWords;
        this.NumberOfPages = NumberOfPages;

    }

    @Override
    public boolean isConformant() {
        if (this.NumberOfPages<=5 && this.NumberOfWords>=2000)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "")+", Nr.Cuvinte"+NumberOfWords+", Nr.Pagini:"+NumberOfPages +"}";

    }

    public int getNumberOfPages() {
        return NumberOfPages;
    }
    public int getNumberOfWords() {
        return NumberOfWords;
    }
}
