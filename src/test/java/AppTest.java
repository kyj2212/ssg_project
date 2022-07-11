
import com.ll.exam.Rq;
import com.ll.exam.WiseSaying;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void test_temp(){
        int rs = 10+20;
        assertEquals(30,rs);
    }

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

    @Test
    public void Rq__getPath(){
        Rq rq = new Rq("delete?id=1");
        String path = rq.getPath();
        assertEquals("delete", path);
    }
    @Test
    public void Rq__getIdParam(){
        Rq rq = new Rq("delete?id=1");
        int paramId = rq.getIdParam();
        assertEquals(1, paramId);
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
}
