package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.raon.androidworldcup.Communication.userDTO;
import com.raon.androidworldcup.Communication.Message;
import com.raon.androidworldcup.Communication.userDAO;


import java.sql.Connection;
import java.util.ArrayList;

import com.raon.androidworldcup.Communication.voteDTO;
import com.raon.androidworldcup.Communication.voteDAO;


public class TransLator extends Thread {
	Socket client;
    
    int count=0;
    String action;
    String obj;
    ObjectOutputStream out; //����ȭ ��ü
    ObjectInputStream in;  //������ȭ ��ü
    Connection con;
    
    Message outMessage = new Message();
    Message inMessage = new Message();
    
    ArrayList<userDTO> userDTOList = new ArrayList<com.raon.androidworldcup.Communication.userDTO>();
    ArrayList<voteDTO> voteDTOList = new ArrayList<com.raon.androidworldcup.Communication.voteDTO>();
    
    userDTO userDTO = new userDTO();
    userDAO userDAO = new userDAO();
    
    voteDTO voteDTO = new voteDTO();
    voteDAO voteDAO = new voteDAO();
    
    int isSuccess=0;

    
    TransLator(Socket client, int count, Connection con) {
        this.client = client;
        this.count=count;
        this.con=con;
    }
    public void run() {
        try {
        	out = new ObjectOutputStream(client.getOutputStream()); //������ ����
      		in = new ObjectInputStream((client.getInputStream())); //�޴� ����
            System.out.println("����ȭ �����̺� ����"+(count+1)+"�� ������");
            
            inMessage = (Message)in.readObject(); //������ȭ
            
            System.out.println(inMessage.getMessage());
           
            boolean isCommuication=false;
            while (true){
            	System.out.println(inMessage.getMessage());
                switch(inMessage.getMessage()) {
                	case "start":
                		outMessage.setMessage("first");
                		out.writeObject(outMessage);
                		out.flush();
                		out.reset();
                		inMessage = (Message)in.readObject(); //������ȭ
                		break;
                	case "userDTO":
                		obj=inMessage.getMessage(); //���� ��ü����
                		outMessage.setMessage("second");
                		out.writeObject(outMessage);
                		out.flush();
                		out.reset();
                		inMessage = (Message)in.readObject(); //������ȭ
                		action=inMessage.getMessage(); //���� �ൿ����
                		outMessage.setMessage("third");
                		out.writeObject(outMessage);
                		out.flush();
                		out.reset();
                		userDTO=(userDTO)in.readObject(); //��ü ����
                		switch(action) {
                			case "insert":
                				isSuccess=userDAO.join(userDTO.getUser_id(), userDTO.getUser_pwd());
                				outMessage.setMessage("sixth");
                        		out.writeObject(outMessage);
                				out.flush();
                				out.reset();
                				
                				inMessage = (Message)in.readObject(); //������ȭ
                				if(isSuccess==-1) {
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				else{
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				break;	
                			case "select":
                				outMessage.setMessage("fourth");
                				out.writeObject(outMessage);
                				out.flush();
                				out.reset();
                				
                				userDTOList=userDAO.list();
                				out.writeObject(userDTOList);
                				out.flush();
                				out.reset();
                				inMessage = (Message)in.readObject(); //������ȭ
                				System.out.println(inMessage);
                				
                				if(userDTOList.size()>0) {
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}else{
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				break;
                				
                			/*case "update":
                				isSuccess=userDAO.modify(userDTO.getUser_pwd(), userDTO.getUser_id());
                				outMessage.setMessage("sixth");
                        		out.writeObject(outMessage);
                				out.flush();
                				out.reset();
                				
                				inMessage = (Message)in.readObject(); //������ȭ
                				if(isSuccess==-1) {
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				else{
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				break;
                				
                			case "delete":
                				isSuccess=userDAO.deleteinfor(userDTO.getUser_id());
                				outMessage.setMessage("sixth");
                        		out.writeObject(outMessage);
                				out.flush();
                				out.reset();
                				
                				inMessage = (Message)in.readObject(); //������ȭ
                				if(isSuccess==-1) {
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				else{
                					outMessage.setMessage("����");
                					out.writeObject(outMessage);
                					out.flush();
                					out.reset();
                				}
                				break;*/
                		}
                		break;
                	case "voteDTO":
                		action=inMessage.getMessage();
                		System.out.println(action);
                		
                		obj=inMessage.getMessage(); //���� ��ü����
                		
                		outMessage.setMessage("second");
                		out.writeObject(outMessage);
                		out.flush();
                		out.reset();
                		inMessage = (Message)in.readObject(); //������ȭ
                		action=inMessage.getMessage(); //���� �ൿ����
                		outMessage.setMessage("third");
                		out.writeObject(outMessage);
                		out.flush();
                		out.reset();
                		voteDTO=(voteDTO)in.readObject(); //��ü ����
                		
                		switch(action) {
            				case "insert":
            					isSuccess=voteDAO.join(voteDTO.getVote_title(), voteDTO.getUser_id(), voteDTO.getVote_day());
            					System.out.println(isSuccess);
            					outMessage.setMessage("sixth");
            					out.writeObject(outMessage);
            					out.flush();
            					out.reset();
            				
            					inMessage = (Message)in.readObject(); //������ȭ
            					if(isSuccess==-1) {
            						outMessage.setMessage("����");
            						out.writeObject(outMessage);
            						out.flush();
            						out.reset();
            					}
            					else{
            						outMessage.setMessage("����");
            						out.writeObject(outMessage);
            						out.flush();
            						out.reset();
            					}
            				break;
            			
                		}
                		break;
                
        		case "ok":
        			isCommuication=true;
        			break;
                }
                if(isCommuication) {
                	break;
                }
            }

        } catch (IOException e) {
        	e.printStackTrace();
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
        	try {
        		client.close();
        		System.out.println("�����̺� ����"+(count+1)+"�� ����");
        	}catch(IOException e) {
        		System.out.println("���� ����");
        	}
        }
    }
}