
import com.ll.exam.Rq;
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

    public void test_delete(){

    }

}
