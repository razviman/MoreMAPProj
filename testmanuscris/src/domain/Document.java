package domain;

import java.io.Serializable;

public abstract class Document implements Serializable {
    String autor;

    public Document(String autor) {
        this.autor = autor;
    }

@Override
    public String toString() {
        return "{" + "autor=" + autor + '}';
}

    public abstract boolean isConformant() ;

    public String getAutor() {
        return autor;
    }

}
