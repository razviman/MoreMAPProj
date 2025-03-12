package a4.domain;

public class ActFizica {
    String desc;
    int timp;

   public ActFizica(String desc, int timp) throws Exception {

    this.desc = desc;
    this.timp = timp;

    }

    public String getDesc() {
        return desc;

    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTimp() {
        return timp;
    }
    public void setTimp(int timp) {
        this.timp = timp;
    }

    public String toString() {
       return desc + "," + timp ;
    }

}


