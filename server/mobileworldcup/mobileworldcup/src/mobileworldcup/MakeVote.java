package mobileworldcup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.raon.androidworldcup.Communication.Client;

import user.userDAO;
import user.userDTO;
import vote.voteDAO;
import vote.voteDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MakeVote extends JFrame {

	private JPanel contentPane;
	private JTextField vote_title_txt;
	private JTextField vote_item1_txt;
	private JTextField vote_item2_txt;
	private JTextField vote_item3_txt;
	private Client client;

	public MakeVote(int i) {
		
		client= new Client();
        userDTO userDTO= new userDTO();
        
        boolean isSuccessful=client.userDTOCom(userDTO, "select");
        
        if(isSuccessful) {
        	
        	
        	ArrayList<userDTO> user;
			user = client.getUserList();
			
			String User_id = user.get(i).getUser_id();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 600, 800);
	    contentPane = new JPanel();
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("\uD22C\uD45C \uB9CC\uB4E4\uAE30");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setBounds(222, 67, 139, 15);
	    contentPane.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("\uD22C\uD45C\uC81C\uBAA9");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setBounds(105, 178, 123, 15);
	    contentPane.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("\uD22C\uD45C\uD56D\uBAA91");
	    lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1_1.setBounds(105, 230, 123, 15);
	    contentPane.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("\uD22C\uD45C\uD56D\uBAA92");
	    lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1_2.setBounds(105, 270, 123, 15);
	    contentPane.add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_1_3 = new JLabel("\uD22C\uD45C\uD56D\uBAA93");
	    lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1_3.setBounds(105, 315, 123, 15);
	    contentPane.add(lblNewLabel_1_3);
	    
	    vote_title_txt = new JTextField();
	    vote_title_txt.setBounds(305, 175, 116, 21);
	    contentPane.add(vote_title_txt);
	    vote_title_txt.setColumns(10);
	    
	    
	    
	    JButton Ok_btn = new JButton("\uD655\uC778");
	    Ok_btn.setBounds(155, 479, 97, 23);
	    contentPane.add(Ok_btn);
	    
	    Ok_btn.addActionListener(new ActionListener(){
	    	 public void actionPerformed(ActionEvent e) {
	             if(e.getSource()==Ok_btn) {
	            	client = new Client();
	            	String vote_title = vote_title_txt.getText().toString();
					int vote_item1 = 0;
					int vote_item2 = 0;
					int vote_item3=0;
					
					SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
					Date time = new Date();
					String vote_day = format1.format(time);
					
					voteDTO voteDTO = new voteDTO();
					userDTO userDTO = new userDTO();
					
					String user_id = User_id;
					
					voteDTO.setVote_title(vote_title);
					voteDTO.setUser_id(user_id);
					voteDTO.setVote_day(vote_day);
					
					System.out.println(vote_title);
					System.out.println(user_id);
					
					System.out.println(vote_day);
					
					boolean isSuccess=client.voteDTOCom(voteDTO, "insert");
	                 //JOptionPane.showMessageDialog(label, "가입이 정상적으로 되었습니다.");
	                 if(isSuccess) {
	                    JOptionPane.showMessageDialog(getContentPane(), "투표 만들기 성공");
	                 }else {
	                    JOptionPane.showMessageDialog(getContentPane(), "투표 만들기 실패");
	                 }
	                  dispose();
	                  votemain nn =new votemain(i);
	                  nn.setVisible(true);
	                  dispose();
	             
	             }
	    	 }
	    });
	    
	    JButton Back_btn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
	    Back_btn.setBounds(324, 479, 97, 23);
	    contentPane.add(Back_btn);
        }
	}

}
