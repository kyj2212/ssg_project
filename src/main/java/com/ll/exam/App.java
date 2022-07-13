package com.ll.exam;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;


public class App {

   // static int lastidx=0;
    //static ArrayList<WiseSaying> wslist = new ArrayList<>();

    public void run() throws IOException {

        int id;

        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");

        WiseSayingController control = new WiseSayingController();
       // WiseSayingRepo wsr = new WiseSayingRepo();
        control.load();
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
                    control.regist(br);
                    break;
                case "list" :
                    control.list();
                    break;
                case "delete" :
                    control.delete(rq);
                    break;
                case "modify" :
                    control.modify(rq,br);
                    break;
            }
        }
        br.close();
        System.out.println("Program successfully exited");
    }

}