package com.ll.exam;

import java.io.*;

public class App {
    /*
    * App declaration
    * 1. static mode = this is production
    * 2. private inputstream
    * 3. no constructor --> why? constructor에서 controller를 생성할경우? App()를 생성할때 controller를 잡는다면?
    *
    * App do what?
    * 1. get Controller class
    * 2. read client input, and get path.
    * 3. each case, call Controller's method
    * 4.
    * */

    static final String mode = "production";
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));





    public void run() throws IOException {

        System.out.println("== This is WISE SAYING Program ===");
        System.out.println("if you want to exit, enter 'exit' to cmd");

        WiseSayingController control = new WiseSayingController();
        control.load();

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