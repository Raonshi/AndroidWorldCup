package com.raon.androidworldcup.Communication;

import com.raon.androidworldcup.AppData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class VoteClient extends Thread {
/*
    String serverIP = "112.186.171.198";  //서버 IP
    int serverPort = 8000;          //서버 port
*/
    Socket soc;                     // 서버에 연결할 소켓

    ObjectOutputStream out;         //데이터 송신 객체
    ObjectInputStream in;           //데이터 수신 객체

    String response = null;

    voteDTO voteDTO;

    //투표 정보 리스트
    ArrayList<voteDTO> voteDTOList = new ArrayList<>();

    //서버로 보내는 메시지객체
    Message outMessage = new Message();

    //서버가 보낸 메시지를 받는 객체
    Message inMessage = new Message();

    String str = null;

    public VoteClient(String str){
        this.str = str;
        System.out.println("=============Client Has Created!!!===============");
    }


    @Override
    public void run() {
        super.run();

        // 연결된 서버로부터 데이터를 받아올 준비를 한다
        // 연결된 서버로 데이터를 보낼 준비를 함
        try {
            soc = new Socket(AppData.Singleton().serverIP,AppData.Singleton().serverPort);
            out = new ObjectOutputStream(soc.getOutputStream()); //보내는 소켓
            in = new ObjectInputStream((soc.getInputStream())); //받는 소켓

            boolean isSuccess = false;
            
            switch(str){
                //메인 화면, 나의 투표 그리드뷰
                case "main":
                case "my":
                    voteDTO = new voteDTO();
                    System.out.println("MAINMAINMAIN");
                    AppData.Singleton().isMain = voteDTOCom(voteDTO, "select");
                    break;
                //투표 생성
                case "create":
                    //투표 생성 쿼리 실행
                    AppData.Singleton().isCreate = voteDTOCom(AppData.Singleton().createVoteDTO, "insert");
                    break;
                case "join":
                    //투표 참가 쿼리 실행
                    AppData.Singleton().isJoin = voteDTOCom(AppData.Singleton().selectedVoteDTO, "update");
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<voteDTO> getVoteList(){return this.voteDTOList;}


    public boolean voteDTOCom(voteDTO voteDTO,String action){
        try{
            outMessage.setMessage("start");

            System.out.println("now, you can use translator!!");

            //첫 번째 통신
            out.writeObject(outMessage); //객체의 직렬화
            out.flush(); //비우기
            out.reset();
            boolean isCommuication=false;

            while(true){
                inMessage= (Message)in.readObject();
                System.out.println("통신:"+inMessage.getMessage());

                switch(inMessage.getMessage()) {
                    case "first": //어떤 객체인지 알려줌
                        outMessage.setMessage("voteDTO");
                        out.writeObject(outMessage); //객체의 직렬화
                        out.flush();
                        out.reset();
                        break;
                    case "second": //어떤 행동인지 알려줌
                        outMessage.setMessage(action);
                        out.writeObject(outMessage); //객체의 직렬화
                        out.flush();
                        out.reset();
                        break;
                    case "third": //객체를 보냄
                        out.writeObject(voteDTO);
                        out.flush();
                        out.reset();
                        break;
                    case "fourth": //조회일 경우
                        this.voteDTOList=(ArrayList<voteDTO>)in.readObject();
                        out.flush();
                        out.reset();
                        outMessage.setMessage("ok");
                        out.writeObject(outMessage); //객체의 직렬화
                        out.flush();
                        out.reset();
                        inMessage= (Message)in.readObject();
                        isCommuication=true;
                        break;
                    case "fifth": //출력일 경우
                        outMessage.setMessage("ready");
                        out.writeObject(outMessage); //객체의 직렬화
                        out.flush();
                        out.reset();
                        isCommuication=true;
                        break;
                    case "sixth": //삭제, 추가, 수정일 경우
                        isCommuication=true;
                        outMessage.setMessage("ok");
                        out.writeObject(outMessage); //객체의 직렬화
                        out.flush();
                        out.reset();
                        inMessage= (Message)in.readObject();
                        break;
                }
                if(isCommuication) {
                    break;
                }
            }
            System.out.println("통신 끝"+inMessage.getMessage());
            if(inMessage.getMessage().equals("성공"))
                return true;

        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
