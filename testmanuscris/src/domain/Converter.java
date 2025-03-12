package domain;

public abstract class Converter<T extends Document> {


    public abstract String toString(T elem) ;
    public abstract T toObject(String string) ;

}
