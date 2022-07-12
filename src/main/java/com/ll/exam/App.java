package com.ll.exam;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;


public class App {

    static int lastidx=0;
    static int max = 10;
    static ArrayList<WiseSaying> wslist = new ArrayList<>();

    public void run() throws IOException {

        int id;

        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");

        readFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        outer :
        while(true){

            System.out.printf("cmd)");
            Rq rq = new Rq(br.readLine().trim());

            switch (rq.getPath()){
                case "exit" :
                    System.out.println("Program exiting ... ");
                    break outer;
                case "regist" :
                    System.out.printf("Saying : ");
                    String saying= br.readLine();
                    System.out.printf("Writer : ");
                    String writer= br.readLine();
                    regist(++lastidx,saying,writer);

                    break;
                case "list" :
                    list();
                    break;
                case "delete" :
                    id = rq.getIntParam("id");
                    if(id!=-1)
                        delete(id);
                    else {
                        System.out.println("잘못된 입력입니다. delete?id=1 와 같은 형태로 입력해주세요");
                        continue;
                    }
                    break;
                case "modify" :
                    System.out.println("modify?id=1 와 같은 형태로 입력해주세요");
                    id = rq.getIntParam("id");
                    System.out.println("새 명언을 입력하세요.");
                    String newSaying = br.readLine();
                    modify(id,newSaying);
                    break;
            }
        }
        br.close();
        System.out.println("Program successfully exited");
    }


    public static ArrayList<WiseSaying> readFile() throws IOException {

        File[] filelist = new File(".\\json").listFiles();
        for(File file : filelist){
            if(file.isFile()&&file.canRead()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                wslist.add(new WiseSaying(br.readLine()));
            }
        }
        lastidx = wslist.size();
        return wslist;
    }


    public static void regist(int idx, String saying, String author) throws IOException {
        //String path = ".\\json\\WiseSaying"+lastidx+".json";

        WiseSaying ws = new WiseSaying(idx,saying,author);
        wslist.add(ws);

        addFile(idx, ws);
       // PrintWriter pw = new PrintWriter(new FileWriter(path));
       // pw.write(ws.toString());
       // pw.flush();
      //  pw.close();

        System.out.println(idx+"번 명언이 등록되었습니다.");
    }

    public static void addFile (int idx, WiseSaying ws) throws FileNotFoundException {
        File path = new File(".\\json\\WiseSaying"+idx+".json");
        PrintStream sysout = System.out;
        PrintStream pr = new PrintStream(new FileOutputStream(path));
        System.setOut(pr);
        System.out.println(ws);
        System.setOut(sysout);
        pr.close();
    }



    public static void list()  {
        System.out.println("Num / Author / Saying");
        System.out.println("----------------------");

        for(WiseSaying ws : wslist)
            System.out.println(ws.getId()+" / "+ ws.getAuthor()+" / "+ ws.getSaying());

    }



    public static void delete(int id)  {
        WiseSaying del = null;
        for(WiseSaying ws : wslist){
            if(ws.getId()==id){
                del=ws;
                break;
            }
        }
        if(del==null)
            System.out.println("명령"+id+"은 없습니다.");
        else{
            wslist.remove(del);
            deleteFile(id);
            System.out.println("명언"+id+"이 삭제되었습니다.");
        }
    }

    public static void deleteFile(int id){
        // File 지우기
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            if(file.delete())
            System.out.println("해당 file 삭제됨");
            else System.out.println("file 못지움");
        }
        else System.out.println("해당 file 없음");
    }

    public static void updateFile(int id,WiseSaying ws) throws IOException {
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

    public static void modify(int id, String newSaying) throws IOException {

        for(WiseSaying ws : wslist){
            if(ws.getId()==id){
                System.out.println("기존 명언 : "+ws.getSaying());
                //ws.replace("saying",newSaying); // key로 받는거 추후 추가
                ws.replaceSaying(newSaying);
                updateFile(id,ws);
                System.out.println("새 명언 : "+ws.getSaying());
            }
        }
    }

}