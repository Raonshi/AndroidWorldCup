package mobileworldcup;

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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {
   
   private JPanel contentPane;
   private JTextField Id_text;
   private JTextField Pwd_text;
   private JFrame frame;
   private Client client;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Login frame = new Login();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Login() {
      
	  
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 600, 800);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("\uB85C\uADF8\uC778");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setBackground(SystemColor.activeCaption);
      lblNewLabel_1.setForeground(SystemColor.desktop);
      lblNewLabel_1.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
      lblNewLabel_1.setBounds(92, 198, 400, 75);
      contentPane.add(lblNewLabel_1);
      
      Id_text = new JTextField();
      Id_text.setForeground(Color.LIGHT_GRAY);
      Id_text.setBackground(Color.WHITE);
      Id_text.setFont(new Font("µ¸¿ò", Font.PLAIN, 12));
      Id_text.setText("\uC544\uC774\uB514");
      Id_text.setBounds(199, 308, 185, 45);
      contentPane.add(Id_text);
      Id_text.setColumns(10);
      
      Pwd_text = new JTextField();
      Pwd_text.setForeground(Color.LIGHT_GRAY);
      Pwd_text.setFont(new Font("µ¸¿ò", Font.PLAIN, 12));
      Pwd_text.setText("\uD328\uC2A4\uC6CC\uB4DC");
      Pwd_text.setColumns(10);
      Pwd_text.setBounds(199, 384, 185, 45);
      contentPane.add(Pwd_text);
      
      JButton Login_but = new JButton("\uB85C\uADF8\uC778");
      Login_but.setFont(new Font("µ¸¿ò", Font.PLAIN, 12));
      Login_but.setForeground(new Color(255, 255, 255));
      Login_but.setBackground(new Color(60, 179, 113));
      Login_but.setBounds(199, 466, 185, 31);
      Login_but.setBorderPainted(false);
      contentPane.add(Login_but);
   
      Login_but.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
             // TODO Auto-generated method stub
             if(e.getSource()==Login_but) {
					ArrayList<userDTO> user;
					boolean idCheck=true;
						client= new Client();
						userDTO userDTO= new userDTO();
						userDTO.setUser_id(Id_text.getText());
						userDTO.setUser_pwd(Pwd_text.getText());
						
						boolean isSuccessful=client.userDTOCom(userDTO, "select");
						if(isSuccessful) {
						user = client.getUserList();

						for(int i = 0; i < user.size(); i++) {
							if(Id_text.getText().equals(user.get(i).getUser_id()) && Pwd_text.getText().equals(user.get(i).getUser_pwd())) {
								votemain mm = new votemain(i);
								mm.setVisible(true);
								idCheck=false;
								dispose();
									
								}
							}
						}if(idCheck) {
							JOptionPane.showMessageDialog(getContentPane(), "µî·ÏµÇÁö ¾ÊÀº »ç¿ëÀÚ ÀÔ´Ï´Ù.");
						}
             }
          }
       });
      
      JButton Register_but = new JButton("\uD68C\uC6D0\uAC00\uC785");
      Register_but.setForeground(Color.WHITE);
      Register_but.setFont(new Font("µ¸¿ò", Font.PLAIN, 12));
      Register_but.setBackground(new Color(255, 127, 80));
      Register_but.setBounds(199, 507, 185, 31);
      Register_but.setBorderPainted(false);
      contentPane.add(Register_but);
      //frame.getContentPane().add(Register_but);
      
      
      Register_but.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==Register_but) {
               Registration mm =new Registration();
               mm.setVisible(true);
               dispose();
            }
         }
      });
   }
}