package com.ll.exam;

public class WiseSaying {
    int id;
    String saying;
    String author;


    /* 싱글턴 패턴으로 만들어보기 */
    private static WiseSaying instance;

    private WiseSaying(String json){
        this.id=parseId(json);
        this.saying=parseSaying(json);
        this.author=parseAuthor(json);
    }
    private WiseSaying(int id, String saying, String author){
        this.id =id;
        this.saying=saying;
        this.author=author;
    }

    public static WiseSaying getInstance(String json){
        if(instance == null) {
            instance = new WiseSaying(json);
        }
        return instance;
    }
    public static WiseSaying getInstance(int id, String saying, String author){
        if(instance == null) {
            instance = new WiseSaying(id,saying,author);
        }
        return instance;
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
}

