package com.ll.exam;


import java.io.*;
import java.util.ArrayList;

public class WiseSayingController {

    WiseSayingService service = new WiseSayingService(); // 생성의 위치는 리팩토링 해보자 나중에


    public void load() throws IOException {
        service.load();
    }

    public boolean delete(Rq rq) throws IOException {
        service.load(); // reload
        int id = rq.getIntParam("id");
        if(id==-1)
            return false;
        else {

            service.delete(id);
                return true;
            }
        }

    public void regist(BufferedReader br) throws IOException {
        service.load(); // reload
        System.out.printf("Saying : ");
        String saying= br.readLine();
        System.out.printf("author : ");
        String author= br.readLine();
        service.regist(saying,author);
    }

    public void list() throws IOException {
        service.load(); // reload
        service.list();
    }

    public void modify(Rq rq, BufferedReader br) throws IOException {
        service.load(); // reload
        int id = rq.getIntParam("id");
        System.out.println("새 명언을 입력하세요.");
        String newSaying = br.readLine();
        System.out.println("새 작가를 입력하세요.");
        String newAuthor = br.readLine();
        service.modify(id,newSaying,newAuthor);
    }
}






