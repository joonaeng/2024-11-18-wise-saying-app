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
    private final Scanner scanner;
    private int lastId;
    private final List<WiseSaying> ws;

    public App() {
        scanner = new Scanner(System.in); //스캐너 객체 생성
        lastId = 0;
        ws = new ArrayList<>(); //ArrayList 생성 WiseSaying 클래스의 배열을 생성
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine(); //cmd 값 받아오기
            String[] parts = cmd.split("\\?"); //cmd의 값을 '?'를 기준으로 분류

            if(parts[0].equals("삭제")) { //명언 삭제
                String param = parts[1];
                String[] KeyValue = param.split("=");
                if(KeyValue[0].equals("id")) {
                    int Value = Integer.parseInt(KeyValue[1]);
                    if(lastId < Value) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",Value);
                    }else{
                        if(ws.get(Value-1)!=null) {
                            ws.set(Value-1, null);
                            System.out.printf("%d번 명언이 삭제되었습니다.\n",Value);
                        }else System.out.printf("%d번 명언은 존재하지 않습니다.\n",Value);
                    }
                }
            }
            else if(parts[0].equals("수정")) { //명언 수정
                String param = parts[1];

                String[] KeyValue = param.split("=");
                if(KeyValue[0].equals("id")) {
                    int Value = Integer.parseInt(KeyValue[1]);
                    if(lastId < Value) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",Value);
                    }else{
                        if(ws.get(Value-1)!=null) {
                            System.out.printf("명언(기존) : %s\n",ws.get(Value-1).content);
                            System.out.print("명언 : ");
                            ws.get(Value-1).content = scanner.nextLine();
                            System.out.printf("작가(기존) : %s\n",ws.get(Value-1).author);
                            System.out.print("작가 : ");
                            ws.get(Value-1).author = scanner.nextLine();
                        }else System.out.printf("%d번 명언은 존재하지 않습니다.\n",Value);
                    }
                }
            }
            else if(cmd.equals("등록")) {
                upload();
            }
            else if(cmd.equals("목록")) {
                showList();
            }
            else if(cmd.equals("종료")) break;

        }
        scanner.close();
    }

    public void upload() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        int id = ++lastId;
        // id, content, author의 값을 받아옴

        WiseSaying wiseSaying = new WiseSaying(id, content, author); //WiseSaying 클래스의 객체에 id, content, author 저장
        ws.add(wiseSaying); //wiseSaying[n]에 id, content, author 주소 저장

        System.out.printf("%d번 명언이 등록되었습니다.\n",id);
    }

    public void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------------");

        if(lastId == 0) { //명언을 등록하지 않고 확인하려 했을 때
            System.out.println("등록된 명언이 없습니다.");
        }
        else for(WiseSaying wiseSaying : ws) {//질문하기 향상된 for문 사용 전에는 int i 인자를 사용해서 ws.get(i)값으로 가져옴
            if(wiseSaying!=null) {
                System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content); //저장된 주소의 값에 접근해서 값 가져오기
            }//주소가 null 값이 아닐 때 ws(wiseSaying 배열).get(주소 번호).id(내용)
        }
    }
}

class WiseSaying {
    public int id;
    public String content;
    public String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}