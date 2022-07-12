package com.ll.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


/*Request class*/

/* queryStr의 param 이 id 만 있는 것이 아니므로 HashMap으로 구성하자.*/

public class Rq {
    String url;
    String path;
    String queryStr;
    //String param;

    //HashMap<String,String> queryStr;
    HashMap<String,String> query;

    public Rq(String url) {
        this.url=url;
        this.path=getPath();
    //    this.queryStr=getQueryStr();
     //   this.query=getQuery();
    }

    public HashMap<String,String> getQuery(){

        query = new HashMap<>();
        String [] q = queryStr.split("\\=*\\&");

        for(int i=0;i<q.length;i++){
            StringTokenizer token = new StringTokenizer(q[i],"=");
            query.put(token.nextToken(),token.nextToken());
        }
        return query;
    }

    // delete?id=2
    public String getQueryStr(){
        // path 이후의 queryStr만 가져오기
        return url.split("\\?",2)[1];
    }

    public String getPath() {
       // StringTokenizer token = new StringTokenizer(url,"?");
        String [] q = url.split("\\?");
        if(q.length>1){
            this.queryStr=getQueryStr();
            this.query=getQuery();
        }
        return q[0];
    }


    public boolean isParam(String param){
        return query.keySet().contains(param);
    }
    public int getIntParam(String param){
        if(isParam(param))
            return Integer.parseInt(query.get(param));
        else return -1;
    }
    public String getStringParam(String param){
        if(isParam(param))
            return query.get(param);
        else return null;
    }
}
