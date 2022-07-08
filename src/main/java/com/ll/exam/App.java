package com.ll.exam;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;


public class App {

    static int lastidx=0;
    static int max = 10;
    public void run() throws IOException, ParseException {
        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        outer :
        while(true){

            System.out.printf("cmd)");
            String cmd = br.readLine().trim();





            switch (cmd){
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

        PrintWriter pw = new PrintWriter(new FileWriter(path));
        pw.write(obj.toString());
        pw.flush();

        pw.close();

        System.out.println(lastidx+"번 명언이 등록되었습니다.");


    }
    static void list() throws IOException, ParseException {
        System.out.println("Num / Author / Saying");
        System.out.println("----------------------");

            File[] filelist = new File(".\\json").listFiles();

            for(File file : filelist){

                if(file.isFile()&&file.canRead()){
                    JSONParser parser = new JSONParser();

                    Object obj = parser.parse(new FileReader(file));
                    JSONObject json = (JSONObject)obj;

                    //JSONTokener tokener = new JSONTokener(new FileReader(file));
                    // Object obj = tokener.nextValue();

                    System.out.println(json.get("id")+" / "+ json.get("author")+" / "+ json.get("saying"));
                }

            }




    }

    static void delete(int id){}

}