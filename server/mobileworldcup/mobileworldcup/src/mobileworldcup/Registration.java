package mobileworldcup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.raon.androidworldcup.Communication.Client;

import user.userDAO;
import user.userDTO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Button;


public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField Id_text;
	private JTextField Pwd_text;
	private Client client;
	private JButton Check_btn;
	private JButton Registration_btn;


	public Registration() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 600, 800);
	    contentPane = new JPanel();
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	      
	      
	      
	    JLabel lblNewLabel_1 = new JLabel("\uD68C\uC6D0\uAC00\uC785\r\n");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setForeground(Color.BLACK);
	    lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 30));
	    lblNewLabel_1.setBackground(SystemColor.activeCaption);
	    lblNewLabel_1.setBounds(90, 58, 400, 75);
	    contentPane.add(lblNewLabel_1);
	    
	    Id_text = new JTextField();
	    Id_text.setBounds(207, 210, 165, 38);
	    contentPane.add(Id_text);
	    Id_text.setColumns(10);
	    
	    Pwd_text = new JTextField();
	    Pwd_text.setColumns(10);
	    Pwd_text.setBounds(207, 305, 165, 38);
	    contentPane.add(Pwd_text);
	    
	    JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514 : ");
	    lblNewLabel.setBounds(90, 215, 67, 27);
	    contentPane.add(lblNewLabel);
	    
	    JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
	    lblNewLabel_2.setBounds(90, 310, 67, 27);
	    contentPane.add(lblNewLabel_2);
	    
	    
	    Check_btn = new JButton("\uC911\uBCF5\uD655\uC778");
	    Check_btn.setBounds(422, 217, 97, 23);
	    contentPane.add(Check_btn);
	    Check_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub
               if(e.getSource()==Check_btn) {
            	   ArrayList<userDTO> user;
                   boolean idCheck=true;
                   client= new Client();
                   userDTO userDTO= new userDTO();
                   userDTO.setUser_id(Id_text.getText());
                   
                   
                   
                   boolean isSuccessful=client.userDTOCom(userDTO, "select");
                   if(isSuccessful) {
                   user = client.getUserList();

                   for(int i = 0; i < user.size(); i++) {
                      if(Id_text.getText().equals(user.get(i).getUser_id())) {
                         JOptionPane.showMessageDialog(getContentPane(), "중복된 아이디 입니다.");
                         idCheck=false; 
    break; 
      }
                      }
    if(idCheck)
    JOptionPane.showMessageDialog(getContentPane(), "사용가능한 아이디 입니다.");
                   }
                }         
           }
        });
	    
	    Registration_btn = new JButton("\uD68C\uC6D0\uAC00\uC785");
	    Registration_btn.setBounds(246, 410, 97, 23);
	    contentPane.add(Registration_btn);
	    Registration_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               if(e.getSource()==Registration_btn) {
                 String Id = Id_text.getText().toString();
                 String Pwd = Pwd_text.getText().toString();
               
                 //String kind = txMembership.getText().toString();
                 //int age = Integer.parseInt(c_age); // 텍스트 필드는 무조건 String형으로 받기 떄문에 int형으로 형 변환 후 저장
                 client = new Client();
                 
                 userDTO userDTO = new userDTO();
                 userDTO.setUser_id(Id);
                 userDTO.setUser_pwd(Pwd);
                
                 boolean isSuccess=client.userDTOCom(userDTO, "insert");
                 //JOptionPane.showMessageDialog(label, "가입이 정상적으로 되었습니다.");
                 if(isSuccess) {
                    JOptionPane.showMessageDialog(getContentPane(), "회원가입이 완료되었습니다.");
                 }else {
                    JOptionPane.showMessageDialog(getContentPane(), "회원가입이 실패되었습니다.");
                 }
                  dispose();
                  Login nn =new Login();
                  nn.setVisible(true);
                  dispose();
               }
            }
         });
	}
}
