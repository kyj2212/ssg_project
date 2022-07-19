package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class FileController {



    // dataDir 하위 파일 모두 삭제
    public static void deleteAll(String dataDir) {
        if(existsFile(dataDir)) {
            try {

                Files.walk((new File(dataDir)).toPath())
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean existsFile(String dataDir) {

        while(true){
            File file = new File(dataDir);
            if(file.exists())
                return true;
            else return false;
        }


    }

    public List<WiseSaying> readFile(List<WiseSaying> wslist) throws IOException {
        if(!checkDir())
            new File(App.getDataDir()).mkdirs();
        File[] filelist = new File(App.getDataDir()).listFiles();
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
        checkDir();
        File path = new File(".\\"+App.getDataDir()+"\\WiseSaying"+idx+".json");
        PrintStream sysout = System.out;
        PrintStream pr = new PrintStream(new FileOutputStream(path));
        System.setOut(pr);
        System.out.println(ws);
        System.setOut(sysout);
        pr.close();
    }

    public boolean checkDir() {
        File dir = new File(App.getDataDir());
        if(dir.exists())
            return true;
        else return false;
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
