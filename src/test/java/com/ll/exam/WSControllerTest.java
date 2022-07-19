package com.ll.exam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.io.*;


/*
 * Controller declaration
 * 1. private Service
 * 2. controctor() {Service 객체 생성}
 *
 * Controller do what?
 * 1. load : if app launch, call this.load(). and this.load call service.load
 * 2. regist : parameter로 받은 input -> each string saying,author 에 저장, and push service.regist(saying,author)
 * 3. list : just call servie.list
 * 4. delete : parameter로 받은 url -> id parse, id에 저장, and push service.delete(id)
 * 5. modify : parmeter로 url,input -> id parse, id에 저장, input -> each string saying,author에 저장, and push service.modify(id,s,a)
 * */

// TDD 추가
// 해당 test용 파일은 /test 디렉 밑으로

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WSControllerTest {



    /*BeforeAll, BeforeEach 추가*/


    // 시작전에 한번 static으로 mode="test" 입력
    @BeforeAll
    public void beforeAll(){new App().setMode("test");}

    // 아하 각각의 테스트마다 따로 해야 가능하구나!! 알았음.
    @BeforeEach
    public void beforEach(){FileController.deleteAll(App.getDataDir());}
// BeforeEach 만 넣었더니 no tests were found  occurred
    // @TestInstance(TestInstance.Lifecycle.PER_CLASS) class 단위로 lifecyle을 줘야 한다.

    static String wsStr = new WiseSaying(1,"내 죽음을 적에게 알리지 말라","이순신").toString();

    static InputStream in = new ByteArrayInputStream(wsStr.getBytes());

    @Test
    public void test__isvalid(){
        assertEquals(1,1);
    }

    //1. regist 테스트
    @Test
    public void test_regist() throws IOException {
        WiseSayingController wc = new WiseSayingController();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        wc.regist(br);

        br = new BufferedReader(new FileReader(".\\test\\WiseSaying1.json"));
        assertEquals(wsStr,br.readLine());
        br.close();
    }


    // 3. delete 테스트
    @Test
    public void test__delete() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(".\\test\\WiseSaying1.json"));
        bw.write(wsStr);
        bw.flush();

        WiseSayingController wc = new WiseSayingController();
        wc.delete(new Rq("delete?id=1"));

        TestUtil.toFileRedirection("test__delete.txt");
        assertEquals("명언1이 삭제되었습니다.", TestUtil.readPrint("test__delete.txt"));
    }

    // file.delete() 테스트
    @Test
    public void test_delete_file(){

        File file = new File("test.txt");
        file.delete();
        assertEquals(false,file.exists());

    }

    // 4. replace 테스트
    @Test
    public void test_ws_replace(){
        WiseSaying ws = new WiseSaying(1,"명언1","작가1");
        String newSaying = "명언1-1";
        ws.replaceSaying(newSaying);
        assertEquals(newSaying,ws.getSaying());
    }


    @Test
    public void test__id__numbering(){
        // 파일을 remove 후,
        // remove 한 file 이 가지고 있던 id 는
        // 재사용이 가능한가?

        // 해당 id 를 재사용하게될때,
        // numbering의 순서는 더 앞에 있는 번호가 우선시 되야 하는 것인지

    }




}
