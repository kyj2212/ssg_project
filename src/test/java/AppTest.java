
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void test_temp(){
        int rs = 10+20;
        assertEquals(30,rs);
    }

    @Test
    public void test_scan(){
        String input = """
                regist
                내 죽음을 적에게 알리지 말라
                이순신
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc= new Scanner(in);

        String cmd = sc.nextLine().trim();
        String saying = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals("regist", cmd);
        assertEquals("내 죽음을 적에게 알리지 말라", saying);
        assertEquals("이순신", author);

    }

}
