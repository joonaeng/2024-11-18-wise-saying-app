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

        int lastId = 0; //마지막 기록 번호

        Scanner scanner = new Scanner(System.in); //스캐너 객체 생성
        List<WiseSaying> ws = new ArrayList<>(); //ArrayList 생성 WiseSaying 클래스의 배열을 생성

        while(true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine(); //다음 줄을 읽어오기

            String[] parts = cmd.split("\\?");

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
            }else if(parts[0].equals("수정")) { //명언 수정
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
            else if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) {

                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();

                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying(id, content, author); //WiseSaying 클래스의 객체에 id, content, author 저장

                ws.add(wiseSaying); //wiseSaying[n]에 id, content, author 주소 저장

                System.out.printf("%d번 명언이 등록되었습니다.\n",id);
            }
            else if(cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("---------------------------");
                if(lastId == 0) {
                    System.out.println("등록된 명언이 없습니다.");
                }
                else for(int i = 0; i <lastId ; i++) {
                    if(ws.get(i)!=null)
                        System.out.printf("%d / %s / %s\n", ws.get(i).id, ws.get(i).author, ws.get(i).content); //저장된 주소의 값에 접근해서 값 가져오기
                        //주소가 null 값이 아닐 때 ws(wiseSaying 배열).get(주소 번호).id(내용)
                }
            }
        }
        scanner.close();
    }
}

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}