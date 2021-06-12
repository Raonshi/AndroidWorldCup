package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import utill.DatabaseUtill;
import java.sql.Connection;
public class MasterServer {
	public static void main(String[] args) {
        // TODO Auto-generated method stub
		ArrayList<TransLator> serverList = new ArrayList<>();
		DatabaseUtill dbcon = new DatabaseUtill();
		Connection con = dbcon.getConnection();
		int count=0;
        System.out.println("마스터 서버가 실행중입니다.");
		try {
 
            ServerSocket server = new ServerSocket(8000);
            while (true) {
                Socket client = server.accept();
                TransLator translator = new TransLator(client,count,con);
                serverList.add(translator);
                serverList.get(count).start();
                count++;
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
