
import com.ll.exam.*;
import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void test_temp(){
        int rs = 10+20;
        assertEquals(30,rs);
    }


    /*입출력 테스트*/
    // 1. 입력테스트 (Reader)
    @Test
    public void test_reader() throws IOException {
        String input = """
                regist
                내 죽음을 적에게 알리지 말라
                이순신
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String cmd = br.readLine().trim();
        String saying = br.readLine().trim();
        String author = br.readLine().trim();

        assertEquals("regist", cmd);
        assertEquals("내 죽음을 적에게 알리지 말라", saying);
        assertEquals("이순신", author);

    }

    //2. 출력 테스트 ( 표준 출력 -> 파일출력으로 redirect)
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

    // regist 테스트
    @Test
    public void test_regist() throws IOException {
        WiseSayingController wc = new WiseSayingController();
        WiseSayingRepo wr = new WiseSayingRepo();
        //wc.regist("내 죽음을 적에게 알리지 말라","이순신");
        wr.regist("내 죽음을 적에게 알리지 말라","이순신");
        BufferedReader br = new BufferedReader(new FileReader(".\\json\\WiseSaying1.json"));

        assertEquals(new WiseSaying(1,"내 죽음을 적에게 알리지 말라","이순신").toString(),br.readLine());
        br.close();
    }



    @Test
    public void Rq__getPath() {
        Rq rq = new Rq("delete?id=1");
        String path = rq.getPath();
        assertEquals("delete", path);

        // param 이 없을 때 getParam 잘되도록
        Rq rq2 = new Rq("list");
        assertEquals("list",rq2.getPath());

    }
    @Test
    public void Rq__getIdParam(){
        Rq rq = new Rq("delete?id=1");
        int paramId = rq.getIntParam("id");
        assertEquals(1, paramId);
    }

    @Test
    public void Rq__getParam(){

        // queryStr 에 여러 파라미터가 있을 때
        Rq rq = new Rq("search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=사과");

        String where = rq.getStringParam("where");
        String sm = rq.getStringParam("sm");
        int fbm = rq.getIntParam("fbm");
        String ie = rq.getStringParam("ie");
        String query = rq.getStringParam("query");

        assertEquals("nexearch, top_hty, 1, utf8, 사과",where+", "+sm+", "+fbm+", "+ie+", "+query);


    }


    @Test
    public void test_object__tojson(){
        WiseSaying ws = new WiseSaying(1,"내 죽음을 적에게 알리지 말라","이순신");
        assertEquals("WiseSaying{id=1, saying=\'내 죽음을 적에게 알리지 말라\', author=\'이순신\'}",ws.toString());
    }
    @Test
    public void test_json__parse(){
        String json = "WiseSaying{id=1, saying=\'내 죽음을 적에게 알리지 말라\', author=\'이순신\'}";
        WiseSaying ws = new WiseSaying(json);
        assertEquals(1, ws.getId());
        assertEquals("내 죽음을 적에게 알리지 말라", ws.getSaying());
        assertEquals("이순신", ws.getAuthor());
    }


    @Test
    public void test_ws_replace(){
        WiseSaying ws = new WiseSaying(1,"명언1","작가1");
        String newSaying = "명언1-1";
        ws.replaceSaying(newSaying);
        assertEquals(newSaying,ws.getSaying());
    }

    @Test
    public void test_delete_file(){

        File file = new File("test.txt");
        file.delete();
        assertEquals(false,file.exists());

    }



    // expected을 어떻게 구현해야할지?
    // 현재 파일을 읽어서 print하고, 그거와 메소드를 비교해야 하나?

    @Test
    public void test_list() throws IOException {
        WiseSayingController control = new WiseSayingController();

        File file = new File("test_list.txt");
        PrintStream pr = new PrintStream(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintStream sysout = System.out;
        System.setOut(pr);

        control.list();
        System.setOut(sysout);

        //assertEquals(,br.readLine());
    }

    // expected을 어떻게 구현해야할지?

    @Test
    public void test_wsr__getwslist() throws IOException {

        WiseSayingRepo wr = new WiseSayingRepo();
        wr.getWslist();
    }

    // expected을 어떻게 구현해야 할지
    @Test
    public void test_wsr__delete(){
     //   int id = 1;
     //  WiseSayingController wsc = new WiseSayingController();
     //   wsc.isDelete(rq);

    }
}
