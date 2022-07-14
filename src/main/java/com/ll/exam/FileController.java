package com.ll.exam;

import java.io.*;
import java.util.List;

public class FileController {

    public List<WiseSaying> readFile(List<WiseSaying> wslist) throws IOException {

        File[] filelist = new File(".\\json").listFiles();
        for(File file : filelist){
            if(file.isFile()&&file.canRead()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                wslist.add(new WiseSaying(br.readLine()));
                br.close();
            }
        }
        return wslist;
    }

    public void addFile (int idx, WiseSaying ws) throws FileNotFoundException {
        File path = new File(".\\json\\WiseSaying"+idx+".json");
        PrintStream sysout = System.out;
        PrintStream pr = new PrintStream(new FileOutputStream(path));
        System.setOut(pr);
        System.out.println(ws);
        System.setOut(sysout);
        pr.close();
    }

    public void updateFile(int id,WiseSaying ws) throws IOException {
        // File update
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.write(ws.toString());
            pw.flush();

            pw.close();
            System.out.println("해당 file 수정됨");
        }
        else System.out.println("해당 file 없음");
    }

    public void deleteFile(int id){
        // File 지우기
        File file = new File(".\\json\\WiseSaying"+id+".json");
        if(file.exists()){
            file.delete();
        }
        else System.out.println(file+"존재하지 않습니다.");
    }

}
