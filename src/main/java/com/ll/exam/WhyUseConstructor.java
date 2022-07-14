package com.ll.exam;

import java.io.IOException;

public class WhyUseConstructor {

    public void run () throws IOException {
        NoUse no = new NoUse();
        Use u = new Use();
    }

}
class NoUse{
    WiseSayingController c = new WiseSayingController();

    NoUse() throws IOException {
    }
}
class Use{
    private WiseSayingController c;
    Use() throws IOException {
        this.c=new WiseSayingController();
    }
}
