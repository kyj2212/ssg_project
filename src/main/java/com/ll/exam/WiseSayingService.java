package com.ll.exam;

import java.io.*;

public class WiseSayingService {

    private WiseSayingRepo repo;

    WiseSayingService() throws IOException {
        this.repo=new WiseSayingRepo();
    }


    public void delete(int id){
        WiseSaying ws = repo.getWiseSaying(id);
        repo.delete(id, ws);
    }

    public void load() throws IOException {
        repo.getWslist();
    }

    public void regist(String saying, String author) throws IOException {
        repo.regist(saying,author);
    }

    public void list() {
        repo.list();
    }

    public void modify(int id, String newSaying,String newAuthor) throws IOException {
        repo.modify(id,newSaying,newAuthor);
    }
}
