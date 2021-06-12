package mobileworldcup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class votemain extends JFrame {

	private JPanel contentPane;

	public votemain(int i) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 600, 800);
	    contentPane = new JPanel();
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("\uBA54\uC778\uD654\uBA74");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 16));
	    lblNewLabel.setBounds(214, 60, 155, 43);
	    contentPane.add(lblNewLabel);
	    
	    JButton MakeVote_btn = new JButton("\uD22C\uD45C \uB9CC\uB4E4\uAE30");
	    MakeVote_btn.setBounds(243, 189, 97, 23);
	    contentPane.add(MakeVote_btn);
	    MakeVote_btn.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==MakeVote_btn) {
            dispose();
            MakeVote nn = new MakeVote(i);
            nn.setVisible(true);
            dispose();
            
            }
	    }
	});
	    
	    
	    JButton attendVote_btn = new JButton("\uD22C\uD45C \uCC38\uC5EC");
	    attendVote_btn.setBounds(243, 325, 97, 23);
	    contentPane.add(attendVote_btn);
	    attendVote_btn.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            if(e.getSource()==attendVote_btn) {
	            dispose();
	            MakeVote nn = new MakeVote(i);
	            nn.setVisible(true);
	            dispose();
	            
	            }
		    }
		});
	}
}
