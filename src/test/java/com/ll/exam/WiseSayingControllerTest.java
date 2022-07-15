package com.ll.exam;
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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class WiseSayingControllerTest {

    // 시작전에 한번 static으로 mode="test" 입력
    @BeforeAll
    public void beforeAll(){
        new App().setMode("test");
    }

    // 왜 시작할때마다 dir 하위의 모든 파일 다 지울까? 왜지우지 그냥 해도되는거아닌가
    @BeforeEach
    public void beforEach(){
        Util.file.deleteAll(App.getBaseDir());
    }



}
