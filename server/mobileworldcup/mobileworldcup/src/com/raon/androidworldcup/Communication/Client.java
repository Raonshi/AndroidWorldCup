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
Socket soc;     // ������ ������ ����
    
    ObjectOutputStream out; //����ȭ ��ü
    ObjectInputStream in;  //�� ����ȭ ��ü
    Scanner scan;   // ����ڰ� �Է��� �����͸� ���� ��ĳ��
    String response = null;
    
    userDTO userDTO;
    //voteDTO voteDTO;
    
    ArrayList<userDTO> userDTOList = new ArrayList<>();
    ArrayList<voteDTO> voteDTOList = new ArrayList<>();
  
    Message outMessage = new Message();
    Message inMessage = new Message();
    
    
    public Client() {
         // ����� �����κ��� �����͸� �޾ƿ� �غ� �Ѵ�
         // ����� ������ �����͸� ���� �غ� ��
         try {
        	 soc = new Socket("127.0.0.1",8000);
        	 out = new ObjectOutputStream(soc.getOutputStream()); //������ ����
     		 in = new ObjectInputStream((soc.getInputStream())); //�޴� ����
     		 
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
            
            //ù ��° ���
            out.writeObject(outMessage); //��ü�� ����ȭ
            out.flush(); //����
            out.reset();
            boolean isCommuication=false;
            
            while(true){
                inMessage= (Message)in.readObject();
                System.out.println("���:"+inMessage.getMessage());
                switch(inMessage.getMessage()) {
                	case "first": //� ��ü���� �˷���
                		outMessage.setMessage("userDTO");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		break;
                	case "second": //� �ൿ���� �˷���
                		outMessage.setMessage(action);
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		break;
                	case "third": //��ü�� ����
                		out.writeObject(userDTO);
                		out.flush();
                		out.reset();
                		break;
                	case "fourth": //��ȸ�� ���
                		this.userDTOList=(ArrayList<userDTO>)in.readObject();
                		out.flush();
                		out.reset();
                		outMessage.setMessage("ok");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		
                		out.flush();
                		
                		out.reset();
                		
                		inMessage= (Message)in.readObject();
                
                		isCommuication=true;
                	
                		break;
                	case "fifth": //����� ���
                		outMessage.setMessage("ready");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		isCommuication=true;
                		break;
                	case "sixth": //����, �߰�, ������ ���
                		isCommuication=true;
                		outMessage.setMessage("ok");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		inMessage= (Message)in.readObject();
                		break;
                }
                if(isCommuication) {
                	break;
                }
            }
            System.out.println("��� ��"+inMessage.getMessage());
            if(inMessage.getMessage().equals("����"))
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
            
            //ù ��° ���
            out.writeObject(outMessage); //��ü�� ����ȭ
            out.flush(); //����
            out.reset();
            boolean isCommuication=false;
            
            while(true){
                inMessage= (Message)in.readObject();
                System.out.println("���:"+inMessage.getMessage());
                switch(inMessage.getMessage()) {
                	case "first": //� ��ü���� �˷���
                		outMessage.setMessage("voteDTO");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		break;
                	case "second": //� �ൿ���� �˷���
                		outMessage.setMessage(action);
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		break;
                	case "third": //��ü�� ����
                		out.writeObject(voteDTO);
                		out.flush();
                		out.reset();
                		break;
                	case "fourth": //��ȸ�� ���
                		this.voteDTOList=(ArrayList<voteDTO>)in.readObject();
                		out.flush();
                		out.reset();
                		outMessage.setMessage("ok");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		inMessage= (Message)in.readObject();
                		isCommuication=true;
                		break;
                	case "fifth": //����� ���
                		outMessage.setMessage("ready");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		isCommuication=true;
                		break;
                	case "sixth": //����, �߰�, ������ ���
                		isCommuication=true;
                		outMessage.setMessage("ok");
                		out.writeObject(outMessage); //��ü�� ����ȭ
                		out.flush();
                		out.reset();
                		inMessage= (Message)in.readObject();
                		break;
                }
                if(isCommuication) {
                	break;
                }
            }
            System.out.println("��� ��"+inMessage.getMessage());
            if(inMessage.getMessage().equals("����"))
            	return true;
 
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();            
        }
        return false;
    }
}