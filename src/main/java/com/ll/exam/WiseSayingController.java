package com.ll.exam;


import java.io.*;
import java.util.ArrayList;

/*
* Controller declaration
* 1. private Service
* 2. controctor() {Service 객체 생성}
*
* Controller do what?
* 1. load : if app launch, call this.load(). and this.load call service.load
* 2. regist : parameter로 받은 input -> each string saying,author 에 저장, and push service.regist(saying,author)
* 3. list : just call servie.list
* 4. delete : parameter로 받은 url -> id parse, id에 저장, and push service.delete(id)
* 5. modify : parmeter로 url,input -> id parse, id에 저장, input -> each string saying,author에 저장, and push service.modify(id,s,a)
* */


public class WiseSayingController {

    private WiseSayingService service;

    public WiseSayingController() throws IOException {
        this.service=new WiseSayingService();
    }

    public void load() throws IOException {
        service.load();
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


    public boolean delete(Rq rq) throws IOException {
        service.load(); // reload
        int id = rq.getIntParamValue("id");
        if(id==-1)
            return false;
        else {

            service.delete(id);
            return true;
        }
    }


    public void modify(Rq rq, BufferedReader br) throws IOException {
        service.load(); // reload
        int id = rq.getIntParamValue("id");
        System.out.println("새 명언을 입력하세요.");
        String newSaying = br.readLine();
        System.out.println("새 작가를 입력하세요.");
        String newAuthor = br.readLine();
        service.modify(id,newSaying,newAuthor);
    }
}






