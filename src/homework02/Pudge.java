package homework02;

public class Pudge {

    String name;
    int level;
    String phrases[];
    
    public Pudge(String name, int level, String phrases[]){
        this.name = name;
        this.level = level;
        this.phrases = phrases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String[] getPhrases() {
        return phrases;
    }

    public void setPhrases(String[] phrases) {
        this.phrases = phrases;
    }
    
}
