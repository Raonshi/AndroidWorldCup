package com.raon.androidworldcup.Communication;

import com.raon.androidworldcup.AppData;
import com.raon.androidworldcup.LoginActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class UserClient extends Thread{
    String serverIP = "112.186.171.198";  //서버 IP
    int serverPort = 8000;          //서버 port

    Socket soc;                     // 서버에 연결할 소켓

    ObjectOutputStream out;         //데이터 송신 객체
    ObjectInputStream in;           //데이터 수신 객체

    String response = null;
    userDTO userDTO;

    //사용자 정보 리스트
    ArrayList<userDTO> userDTOList = new ArrayList<>();

    //서버로 보내는 메시지객체
    Message outMessage = new Message();

    //서버가 보낸 메시지를 받는 객체
    Message inMessage = new Message();

    String str = null;

    public UserClient(String str){
        this.str = str;
        System.out.println("=============Client Has Created!!!===============");
    }

    @Override
    public void run() {
            try {
                soc = new Socket(serverIP,serverPort);
                out = new ObjectOutputStream(soc.getOutputStream()); //보내는 소켓
                in = new ObjectInputStream((soc.getInputStream())); //받는 소켓

                switch(str){
                    case "login":
                        userDTO = new userDTO(AppData.Singleton().id, AppData.Singleton().pw);
                        AppData.Singleton().isLogin = userDTOCom(userDTO, "select");
                        break;
                    case "idCheck":
                        userDTO = new userDTO(AppData.Singleton().registerId);
                        AppData.Singleton().isCheck = userDTOCom(userDTO, "select");

                        if(AppData.Singleton().isCheck == true){
                            for(int i = 0; i < userDTOList.size(); i++){
                                if(AppData.Singleton().registerId.equals(userDTOList.get(i).getUser_id())){
                                    AppData.Singleton().isCheck = false;
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("SQL조회 실패");
                        }

                        break;
                    case "register":
                        userDTO = new userDTO(AppData.Singleton().id, AppData.Singleton().pw);
                        AppData.Singleton().isRegister = userDTOCom(userDTO, "insert");
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public boolean userDTOCom(userDTO userDTO,String action){
        // TODO Auto-generated method stub

        try{
            outMessage.setMessage("start");
            System.out.println("now, you can use translator!!");



            //첫 번째 통신
            out.writeObject(outMessage); //outMessage를 out객체를 통해 서버로 전송한다.
            out.flush(); //비우기
            out.reset();
            boolean isCommuication=false;

            //서버와 통신하는 부분
            while(true){
                inMessage= (Message)in.readObject();
                System.out.println("통신:"+inMessage.getMessage());
                switch(inMessage.getMessage()) {
                    case "first": //어떤 객체인지 알려줌
                        outMessage.setMessage("userDTO");
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
                        out.writeObject(userDTO);
                        out.flush();
                        out.reset();
                        break;
                    case "fourth": //조회일 경우
                        this.userDTOList=(ArrayList<userDTO>)in.readObject();
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

    public ArrayList<userDTO> getUserList(){return this.userDTOList;}
}
