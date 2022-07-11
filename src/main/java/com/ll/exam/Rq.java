package com.ll.exam;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Rq {
    String url;
    String path;
    String queryStr;

    public Rq(String url) {
        this.url=url;
        this.path=getPath();
       // this.path=url.split("\\?",2)[0];
        this.queryStr=getQueryStr();
       // this.queryStr=url.split("\\?",2)[1];
    }

    public String getQueryStr(){
        String [] q = url.split("\\?",2);
        if(q.length<2){
           // System.out.println("잘못된 입력입니다.");
            return "-1";
        }
        return q[1];
       //return url.split("\\?",2)[1];
       // return this.queryStr;
    }

    public String getPath() {
        //int idx= url.indexOf('?');
        //return url.substring(0,idx);
        String [] q = url.split("\\?",2);
        return q[0];
      // return this.path;
    }


    public int getIdParam(){

        //return Integer.parseInt(String.valueOf(url.charAt(url.length()-1)));
        // int idx = s.indexOf('=');
        // return Integer.parseInt(s.substring(idx+1));

        //System.out.println(url.split("\\=",2)[0]);
        //System.out.println(url.split("\\=",2)[1]);
        String[] q = queryStr.split("\\=", 2);
        if (q[0].equals("id"))
            return Integer.parseInt(q[1]);
        else
            //System.out.println("잘못된 입력입니다. id를 입력해주세요.");
            return -1;

    }

}
