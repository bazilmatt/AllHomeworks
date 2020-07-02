package Homework15.task1.pojo;


public class ArticlePojo {

    String label;
    String author;


    public ArticlePojo(String label, String author) {
        this.label = label;
        this.author = author;

    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}
