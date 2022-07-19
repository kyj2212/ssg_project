package com.ll.exam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



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
 *
 * */

public class AppTest {

    static String input = """
                regist
                내 죽음을 적에게 알리지 말라
                이순신
                """.stripIndent();
    static InputStream in = new ByteArrayInputStream(input.getBytes());




    // 시작전에 한번 static으로 mode="test" 입력
    @BeforeAll
    public void beforeAll(){
        new App().setMode("test");
    }




    @Test
    public void test_temp(){
        int rs = 10+20;
        assertEquals(30,rs);
    }


    /*1. 입출력 테스트*/
    // 1-1) 입력테스트 (Reader)
    @Test
    public void test_reader() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String cmd = br.readLine().trim();
        String saying = br.readLine().trim();
        String author = br.readLine().trim();

        assertEquals("regist", cmd);
        assertEquals("내 죽음을 적에게 알리지 말라", saying);
        assertEquals("이순신", author);

    }

    //1-2) 출력 테스트 ( 표준 출력 -> 파일출력으로 redirect)
    @Test
    public void test_redirect_printstream() throws IOException {

        File file = new File("test_sysout.txt");
        PrintStream pr = new PrintStream(new FileOutputStream(file));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("test_filewriter.txt"))));

        System.out.println("It's console");
        System.setOut(pr);
        System.out.println("It's file");

        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("It's file",br.readLine());

    }



    /* 2. read client input, and get path.
     */

    // 2-1. 입력값에서 path 가져오기
    @Test
    public void Rq__getPath() {
        Rq rq = new Rq("delete?id=1");
        String path = rq.getPath();
        assertEquals("delete", path);

        // param 이 없을 때 getParam 잘되도록
        Rq rq2 = new Rq("list");
        assertEquals("list",rq2.getPath());
    }

    // 2.2 url에서 id 잘가져오는지 테스트
    @Test
    public void Rq__getIdParam(){
        Rq rq = new Rq("delete?id=1");
        int paramId = rq.getIntParamValue("id");
        assertEquals(1, paramId);
    }
    // 2.3 파마미터 열거개 있는 케이스 추가.
    @Test
    public void Rq__getParam(){
        // queryStr 에 여러 파라미터가 있을 때
        Rq rq = new Rq("search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=사과");

        String where = rq.getStringParamValue("where");
        String sm = rq.getStringParamValue("sm");
        int fbm = rq.getIntParamValue("fbm");
        String ie = rq.getStringParamValue("ie");
        String query = rq.getStringParamValue("query");
        assertEquals("nexearch, top_hty, 1, utf8, 사과",where+", "+sm+", "+fbm+", "+ie+", "+query);
    }


    // WiseSayong -> json make
    @Test
    public void test_object__tojson(){
        WiseSaying ws = new WiseSaying(1,"내 죽음을 적에게 알리지 말라","이순신");
        assertEquals("WiseSaying{id=1, saying=\'내 죽음을 적에게 알리지 말라\', author=\'이순신\'}",ws.toString());
    }

    // json -> WiseSaying parse
    @Test
    public void test_json__parse(){
        String json = "WiseSaying{id=1, saying=\'내 죽음을 적에게 알리지 말라\', author=\'이순신\'}";
        WiseSaying ws = new WiseSaying(json);
        assertEquals(1, ws.getId());
        assertEquals("내 죽음을 적에게 알리지 말라", ws.getSaying());
        assertEquals("이순신", ws.getAuthor());
    }





    // expected을 어떻게 구현해야할지?
    // 현재 파일을 읽어서 print하고, 그거와 메소드를 비교해야 하나?
    // AppTestRunner 을 통해서 입출력을 파일이 아닌 화면으로 리다이렉팅하여 실행하기

    @Test
    public void test_list() throws IOException {
        //WiseSayingController control = new WiseSayingController();

        File file = new File("test_list.txt");
        PrintStream pr = new PrintStream(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintStream sysout = System.out;
        System.setOut(pr);

      // control.list();
        System.setOut(sysout);

        //assertEquals(,br.readLine());
    }

    // expected을 어떻게 구현해야할지?

    @Test
    public void test_wsr__getwslist() throws IOException {

       // WiseSayingRepo wr = new WiseSayingRepo();
      //  wr.getWslist();
    }

    // expected을 어떻게 구현해야 할지
    @Test
    public void test_wsr__delete(){
     //   int id = 1;
     //  WiseSayingController wsc = new WiseSayingController();
     //   wsc.isDelete(rq);

    }
}
