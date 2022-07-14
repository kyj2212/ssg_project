package com.ll.exam;

public class WiseSaying {
    public int id;
    public String saying;
    public String author;

    public WiseSaying(String json){
        this.id=parseId(json);
        this.saying=parseSaying(json);
        this.author=parseAuthor(json);
    }
    public WiseSaying(int id, String saying, String author){
        this.id =id;
        this.saying=saying;
        this.author=author;
    }

    public int parseId(String json){
        return Integer.parseInt(json.split("id\\=",2)[1].split(",")[0]);
    }

    public String parseSaying(String json){
        return json.split("\\'",2)[1].split("\\'")[0];
    }

    public String parseAuthor(String json){
        return json.split("author\\=\\'",2)[1].split("\\'")[0];
    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", saying='" + saying + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public int getId(){
        return this.id;
    }
    public String getSaying(){
        return this.saying;
    }
    public String getAuthor(){
        return this.author;
    }

    public void replaceSaying(String newSaying){
        this.saying=newSaying;
    }
    public void replaceAuthor(String newAuthor) {
        this.author=newAuthor;
    }
}

