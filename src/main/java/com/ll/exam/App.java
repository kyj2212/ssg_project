package com.ll.exam;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;


public class App {

    static int lastidx=0;
    static int max = 10;
    static  ArrayList<JSONObject> jsonlist = new ArrayList<>();


    public void run() throws IOException, ParseException {

        int id;


        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");
        readFile();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        outer :
        while(true){

            System.out.printf("cmd)");
            //String cmd = br.readLine().trim();

            Rq rq = new Rq(br.readLine().trim());

            String cmd = rq.getPath();

            switch (rq.getPath()){
                case "exit" :
                    System.out.println("Program exiting ... ");
                    break outer;
                case "regist" :
                    System.out.printf("Saying : ");
                    String saying= br.readLine();
                    System.out.printf("Writer : ");
                    String writer= br.readLine();
                    regist(saying,writer);

                    break;
                case "list" :
                    list();
                    break;
                case "delete" :
                    id = rq.getIdParam();
                    if(id!=-1)
                        delete(id);
                    else {
                        System.out.println("잘못된 입력입니다. delete?id=1 와 같은 형태로 입력해주세요");
                        continue;
                    }
                    break;
                case "modify" :
                    System.out.println("modify?id=1 와 같은 형태로 입력해주세요");
                    id = rq.getIdParam();
                    System.out.println("새 명언을 입력하세요.");
                    String newSaying = br.readLine();
                    modify(id,newSaying);
                    break;
            }



        }
        br.close();
        System.out.println("Program successfully exited");
    }

    static void regist(String saying, String author) throws IOException {


        lastidx++;
        String path = ".\\json\\WiseSaying"+lastidx+".json";

        WiseSaying ws = new WiseSaying(lastidx,saying,author);
        JSONObject obj = new JSONObject();
        obj.put("id",ws.id);
        obj.put("author",ws.author);
        obj.put("saying",ws.saying);
        jsonlist.add(obj);
        PrintWriter pw = new PrintWriter(new FileWriter(path));
        pw.write(obj.toString());
        pw.flush();

        pw.close();

        System.out.println(lastidx+"번 명언이 등록되었습니다.");


    }
    static void list() throws IOException, ParseException {
        System.out.println("Num / Author / Saying");
        System.out.println("----------------------");
       // ArrayList<JSONObject> jsonlist = readFile();
        for(JSONObject json : jsonlist)
            System.out.println(json.get("id")+" / "+ json.get("author")+" / "+ json.get("saying"));

    }


    static ArrayList<JSONObject> readFile() throws IOException, ParseException {
        File[] filelist = new File(".\\json").listFiles();
      //  ArrayList<JSONObject> jsonlist = new ArrayList<>();
        for(File file : filelist){

            if(file.isFile()&&file.canRead()){
                JSONParser parser = new JSONParser();

                Object obj = parser.parse(new FileReader(file));
                 jsonlist.add((JSONObject)obj);

                //JSONTokener tokener = new JSONTokener(new FileReader(file));
                // Object obj = tokener.nextValue();

            }

        }
        return jsonlist;
    }

    static void delete(int id)  {

       // ArrayList<JSONObject> jsonlist = readFile();

        JSONObject del=null;
        for(JSONObject json : jsonlist){
            if((int)json.get("id")==id){
                del=json;
                break;
            }
        }
        if(del==null)
            System.out.println("명령"+id+"은 없습니다.");
        else{
            jsonlist.remove(del);
            System.out.println("명언"+id+"이 삭제되었습니다.");
        }

        // File 지우기
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            file.delete();
            System.out.println("해당 file 삭제됨");
        }
        else System.out.println("해당 file 없음");
    }

    static void modify(int id, String newSaying) {
        for(JSONObject json : jsonlist){
            if((int)json.get("id")==id){
                System.out.println("기존 명언 : "+json.get("saying"));
                json.replace("saying",newSaying);
                System.out.println("새 명언 : "+json.get("saying"));
            }
        }

    }

}