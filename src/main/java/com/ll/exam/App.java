package com.ll.exam;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;


public class App {

    static int lastidx=0;
    static int max = 10;
    //static  ArrayList<JSONObject> jsonlist = new ArrayList<>();
    static ArrayList<WiseSaying> wslist = new ArrayList<>();

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

            //String cmd = rq.getPath();

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
        //JSONObject obj = new JSONObject();
       // obj.put("id",ws.id);
       // obj.put("author",ws.author);
       // obj.put("saying",ws.saying);
        wslist.add(ws);
        PrintWriter pw = new PrintWriter(new FileWriter(path));
        pw.write(ws.toString());
        pw.flush();

        pw.close();

        System.out.println(lastidx+"번 명언이 등록되었습니다.");


    }
    static void list() throws IOException, ParseException {
        System.out.println("Num / Author / Saying");
        System.out.println("----------------------");
       // ArrayList<JSONObject> jsonlist = readFile();

        for(WiseSaying ws : wslist){

            System.out.println(ws.getId()+" / "+ ws.getAuthor()+" / "+ ws.getSaying());
        }


      //  for(JSONObject json : jsonlist)
      //      System.out.println(json.get("id")+" / "+ json.get("author")+" / "+ json.get("saying"));

    }


   // static ArrayList<JSONObject> readFile() throws IOException, ParseException {
    static ArrayList<WiseSaying> readFile() throws IOException {

        File[] filelist = new File(".\\json").listFiles();
        for(File file : filelist){

            if(file.isFile()&&file.canRead()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                wslist.add(new WiseSaying(br.readLine()));

               // JSONParser parser = new JSONParser();

              //  Object obj = parser.parse(new FileReader(file));
              //   jsonlist.add((JSONObject)obj);

                //JSONTokener tokener = new JSONTokener(new FileReader(file));
                // Object obj = tokener.nextValue();

            }

        }
        return wslist;
    }

    static void delete(int id)  {

       // ArrayList<JSONObject> jsonlist = readFile();

        WiseSaying del = null;
        for(WiseSaying ws : wslist){
            if(ws.getId()==id){
                del=ws;
                break;
            }
        }
        // WiseSaying del = wslist.get(id);
        if(del==null)
            System.out.println("명령"+id+"은 없습니다.");
        else{
            wslist.remove(del);
            deleteFile(id);
            System.out.println("명언"+id+"이 삭제되었습니다.");
        }


   /*
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
    */


    }

    static void deleteFile(int id){
        // File 지우기
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            if(file.delete())
            System.out.println("해당 file 삭제됨");
            else System.out.println("file 못지움");
        }
        else System.out.println("해당 file 없음");
    }

    static void updateFile(int id,WiseSaying ws) throws IOException {
        // File update
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.write(ws.toString());
            pw.flush();

            pw.close();
                System.out.println("해당 file 수정됨");
        }
        else System.out.println("해당 file 없음");
    }
    static void modify(int id, String newSaying) throws IOException {

        for(WiseSaying ws : wslist){
            if(ws.getId()==id){
                System.out.println("기존 명언 : "+ws.getSaying());
                //ws.replace("saying",newSaying); // key로 받는거 추후 추가
                ws.replaceSaying(newSaying);
                updateFile(id,ws);
                System.out.println("새 명언 : "+ws.getSaying());
            }
        }



        /*
        for(JSONObject json : jsonlist){
            if((int)json.get("id")==id){
                System.out.println("기존 명언 : "+json.get("saying"));
                json.replace("saying",newSaying);
                System.out.println("새 명언 : "+json.get("saying"));
            }
        }

         */

    }

}