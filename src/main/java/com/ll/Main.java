package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {
    public void run() {
        System.out.println("== 명언 앱 ==");
        int lastId = 0;

        Scanner scanner = new Scanner(System.in);
        List<WiseSaying> ws = new ArrayList<>();

        while(true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();
            if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) {

                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();

                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying();


                wiseSaying.id = id;
                wiseSaying.content = content;
                wiseSaying.author = author;

                ws.add(wiseSaying);

                System.out.printf("%d번 명언이 등록되었습니다.\n",id);
            }
            else if(cmd.equals("목록")) {
                for(int i = lastId-1; i >= 0 ; i--) {
                    System.out.printf("%d / %s / %s\n",ws.get(i).id,ws.get(i).author,ws.get(i).content);
                }
            }
//            else if()
        }
        scanner.close();
    }
}

class WiseSaying {
    int id;
    String content;
    String author;
}