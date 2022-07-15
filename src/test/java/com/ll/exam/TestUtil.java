package com.ll.exam;

import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtil {

    private final static PrintStream sysout = System.out;
    public static void toConsoleRedirection(){
        System.setOut(sysout);
    }

    // setout printstream -> file
    public static void toFileRedirection(String filename) throws FileNotFoundException {
        File file = new File(filename);
        PrintStream pr = new PrintStream(new FileOutputStream(file));
        PrintStream sysout = System.out;
        System.setOut(pr);
    }

    public static String readPrint(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String printed="";
        while(true){
            String str= br.readLine();
            if(str==null)
                break;
            printed+=str+"\n";
        }
        br.close();
        toConsoleRedirection();
        return printed;

    }



    // setout printstream -> bytearray
    public String readPrintByteArray(ByteArrayOutputStream output) throws IOException {
        String anwser = output.toString().trim();
        output.close();
        toConsoleRedirection();
        return anwser;
    }

    public ByteArrayOutputStream toByteArrayRedirection(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    @Test
    public void test__ByteArrayRedirection() throws IOException {
        ByteArrayOutputStream output = toByteArrayRedirection();
        System.out.println(String.valueOf("test"));
        System.out.println("test2".toString());  // Windows 의 경우 개행 문자를 \r\n으로 사용
        assertEquals("test\r\ntest2",readPrintByteArray(output) );
    }


    //setout printstream -> bytearray with buffered -> buf.flush를 해줘야 하는구나!!!
    public String readPrintBuffered(BufferedOutputStream buf,ByteArrayOutputStream output) throws IOException {
        buf.flush();
        String anwser=output.toString();
        toConsoleRedirection();
        buf.close();
        return anwser;
    }
    public BufferedOutputStream toBufferedRedirection(ByteArrayOutputStream output) throws IOException {
        BufferedOutputStream buf = new BufferedOutputStream(output);
        System.setOut(new PrintStream(buf));
        return buf;
    }


    @Test
    public void test__BufferedRedirection() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        BufferedOutputStream buf = toBufferedRedirection(output);
        System.out.println("test");
        System.out.println("test2");  // Windows 의 경우 개행 문자를 \r\n으로 사용
        assertEquals("test\r\ntest2\r\n",readPrintBuffered(buf,output));
    }


    @Test
    public void test_redirect_printstream() throws IOException {

        File file = new File(".\\test\\test_sysout.txt");
        PrintStream pr = new PrintStream(new FileOutputStream(file));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("test_filewriter.txt"))));
        PrintStream sysout = System.out;
        System.out.println("It's console");
        System.setOut(pr);
        System.out.println("It's file");
        System.setOut(sysout);
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("It's file",br.readLine());

    }


}
