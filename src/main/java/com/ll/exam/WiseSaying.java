package com.ll.exam;

public class WiseSaying {
    int id;
    String saying;
    String author;

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
       // System.out.println(json.split("id\\=",2)[1].split(",")[0]);
        return Integer.parseInt(json.split("id\\=",2)[1].split(",")[0]);
    }

    public String parseSaying(String json){
       // System.out.println(json.split("saying\\=\\'",2)[1].split("\\'")[0]);
        return json.split("\\'",2)[1].split("\\'")[0];
    }

    public String parseAuthor(String json){
       // System.out.println(json.split("author\\=\\'",2)[1].split("\\'")[0]);
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

    public void replace(String key,String newVal){

    }

    public void replaceAuthor(String newAuthor) {
        this.author=newAuthor;
    }
}

