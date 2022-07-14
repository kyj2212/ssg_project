package com.ll.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/*Request class*/
/* queryStr의 param 이 id 만 있는 것이 아니므로 HashMap으로 구성하자.*/

public class Rq {
    private String url;
    private String path;
    private String queryParams=null;
    Map<String,String> queryParmMap;

    public Rq(String url) {
        this.url=url;
        String [] urlBits=getBits(url,"\\?");
        this.path=urlBits[0];
        if(urlBits.length >1) { // 없을 때는 초기값 null
            this.queryParams=urlBits[1];
            this.queryParmMap = getQueryParm();
        }
    }

    private String[] getBits(String url, String regex){
        return url.split(regex);
    }


    public Map<String,String> getQueryParm(){

        queryParmMap = new HashMap<>();
        String [] paramBits = getBits(queryParams, "\\=*\\&");

        for(int i=0;i<paramBits.length;i++){
            StringTokenizer token = new StringTokenizer(paramBits[i],"=");
            queryParmMap.put(token.nextToken(),token.nextToken());
        }
        return queryParmMap;
    }

    public boolean isParamName(String paramName){
        return queryParmMap.keySet().contains(paramName);
    }
    public int getIntParamValue(String paramName){
        if(isParamName(paramName))
            return Integer.parseInt(queryParmMap.get(paramName));
        else return -1;
    }
    public String getStringParamValue(String paramName){
        if(isParamName(paramName))
            return queryParmMap.get(paramName);
        else return null;
    }
    public String getPath() {
        return this.path;
    }

}
