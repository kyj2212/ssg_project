package com.ll.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


/*Request class*/

/* queryStr의 param 이 id 만 있는 것이 아니므로 HashMap으로 구성하자.*/

public class Rq {
    String url;
    String path;
    //String queryStr;
    //String param;

    HashMap<String,String> queryStr;

    public Rq(String url) {
        this.url=url;
        this.path=getPath();
        this.queryStr=getQueryStr();
    }


    // delete?id=2
    public HashMap<String,String> getQueryStr(){

        queryStr = new HashMap<>();
        String [] q = url.split("\\?",2);
        if(q.length>1) {
            String [] param = q[1].split("=",2);
            queryStr.put(param[0],getIntParam(param[1]));
            return queryStr;
        }else return null;
    }

    public String getPath() {
        String [] q = url.split("\\?",2);
        return q[0];
    }


    public String getIntParam(String str){
        //return Integer.parseInt(str.split("\\&", 2)[0]);
        return str.split("\\&", 2)[0];
    }
}
