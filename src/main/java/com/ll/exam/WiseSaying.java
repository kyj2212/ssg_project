package com.ll.exam;

public class WiseSaying {
    int id;
    String saying;
    String author;

    WiseSaying(int id, String saying, String author){
        this.id =id;
        this.saying=saying;
        this.author=author;
    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", saying='" + saying + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

