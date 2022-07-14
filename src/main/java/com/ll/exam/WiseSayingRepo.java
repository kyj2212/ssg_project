package com.ll.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepo {

    private List<WiseSaying> wslist;
    private int lastidx;

    FileController fc = new FileController();

    WiseSayingRepo () throws IOException {
        this.wslist=getWslist();
        this.lastidx=wslist.size();
    }



    public WiseSaying getWiseSaying(int id){
        for(WiseSaying ws : wslist){
            if(ws.getId()==id)
                return ws;
        }
        System.out.println("id : "+id+ " 인 명언이 없습니다.");
        return null;
    }

    public List<WiseSaying> getWslist() throws IOException {
        wslist = new ArrayList<>();
        fc.readFile(wslist);
        return wslist;
    }

    public void regist(String saying, String author) throws IOException {

        WiseSaying ws = new WiseSaying(++lastidx,saying,author);
        wslist.add(ws);

        fc.addFile(lastidx, ws);

        System.out.println(lastidx+"번 명언이 등록되었습니다.");
    }



    public void list()  {
        System.out.println("Num / Author / Saying");
        System.out.println("----------------------");

        for(WiseSaying ws : wslist)
            System.out.println(ws.getId()+" / "+ ws.getAuthor()+" / "+ ws.getSaying());

    }


    public void delete(int id, WiseSaying del){
        wslist.remove(del);
        fc.deleteFile(id);
        lastidx--;
        System.out.println("명언"+id+"이 삭제되었습니다.");
    }



    public void modify(int id, String newSaying,String newAuthor) throws IOException {

        for(WiseSaying ws : wslist){
            if(ws.getId()==id){
                System.out.println("기존 명언 : "+ws.getSaying());
                //ws.replace("saying",newSaying); // key로 받는거 추후 추가
                ws.replaceSaying(newSaying);
                ws.replaceAuthor(newAuthor);
                fc.updateFile(id,ws);
                System.out.println("새 명언 : "+ws.getSaying());
            }
        }
    }




}
