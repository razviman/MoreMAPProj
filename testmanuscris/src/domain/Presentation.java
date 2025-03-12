package domain;

public class Presentation extends Document {
    int NumberOfSlides;
    String text;

    public Presentation(String autor, int NumberOfSlides, String text) {
        super(autor);
        this.NumberOfSlides = NumberOfSlides;
        this.text = text;
    }

    @Override
    public boolean isConformant() {
        return text.length()/NumberOfSlides < 200;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") + ", Nr.Slideuri: " + NumberOfSlides + ", Text: " + text + "}";

    }

    public int getNumberOfSlides() {
        return NumberOfSlides;
    }
    public String getText() {
        return text;
    }
}
