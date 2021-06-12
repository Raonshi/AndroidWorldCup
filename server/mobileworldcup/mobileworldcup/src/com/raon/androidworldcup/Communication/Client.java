package com.raon.androidworldcup.Communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


import user.userDTO;
import user.Message;

import vote.voteDTO;

public class Client {
Socket soc;     // 서버에 연결할 소켓
    
    ObjectOutputStream out; //직렬화 객체
    ObjectInputStream in;  //역 직렬화 객체
    Scanner scan;   // 사용자가 입력한 데이터를 읽을 스캐너
    String response = null;
    
    userDTO userDTO;
    //voteDTO voteDTO;
    
    ArrayList<userDTO> userDTOList = new ArrayList<>();
    ArrayList<voteDTO> voteDTOList = new ArrayList<>();
  
    Message outMessage = new Message();
    Message inMessage = new Message();
    
    
    public Client() {
         // 연결된 서버로부터 데이터를 받아올 준비를 한다
         // 연결된 서버로 데이터를 보낼 준비를 함
         try {
        	 soc = new Socket("127.0.0.1",8000);
        	 out = new ObjectOutputStream(soc.getOutputStream()); //보내는 소켓
     		 in = new ObjectInputStream((soc.getInputStream())); //받는 소켓
     		 
         } catch (IOException e) {
			e.printStackTrace();
		}
	}
    public ArrayList<userDTO> getUserList(){return this.userDTOList;}
    public ArrayList<voteDTO> getVoteList(){return this.voteDTOList;}

    
    public boolean userDTOCom(userDTO userDTO,String action){
        // TODO Auto-generated method stub

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