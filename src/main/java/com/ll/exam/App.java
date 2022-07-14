package com.ll.exam;

import java.io.*;

public class App {


    public void run() throws IOException {


        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");

        WiseSayingController control = new WiseSayingController();
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