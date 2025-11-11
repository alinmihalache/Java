 package def;
import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 public class JTextComponentDemo extends JFrame {
	 JTextField tField;
	 JPasswordField pwField;
	 JTextArea tArea;
	 JFormattedTextField formattedField;
	 /** Constructorul clasei pentru a initializa componentele GUI */
	 public JTextComponentDemo() {
		 JPanel tfPanel = new JPanel(new GridLayout(3, 2, 10, 2));
		 tfPanel.setBorder(BorderFactory.createTitledBorder("Text Fields: "));
		 tfPanel.add(new JLabel("  JTextField: "));
		 tField = new JTextField(10);
		 tfPanel.add(tField);
		 tField.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 tArea.append("\nAi introdus " + tField.getText());
				 
			 }
		 });
		 tfPanel.add(new JLabel("  JPasswordField: "));
		 pwField = new JPasswordField(10);
		 tfPanel.add(pwField);
		 pwField.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 tArea.append("\nParola este " + new String(pwField.getPassword()));
		 }
		 });
		 tfPanel.add(new JLabel("  JFormattedTextField"));
		 formattedField = new JFormattedTextField(java.util.Calendar.getInstance().getTime());
		 tfPanel.add(formattedField);
		 tArea = new JTextArea("O componenta JTextArea is editabila, "
				 + "dar textul are acelasi font.");
				 tArea.setFont(new Font("Serif", Font.ITALIC, 13));
				 tArea.setLineWrap(true);      
				tArea.setWrapStyleWord(true); 
				tArea.setBackground(new Color(204, 238, 241)); 
				JScrollPane tAreaScrollPane = new JScrollPane(tArea);
				 tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				 tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				 Container cp = this.getContentPane();
				 cp.setLayout(new BorderLayout(5, 5));
				 cp.add(tfPanel, BorderLayout.NORTH);
				 cp.add(tAreaScrollPane, BorderLayout.CENTER);
				 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 setTitle("JTextComponent Demo");
				 setSize(350, 350);
				 setVisible(true);
	 }
	 public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
		 @Override
		 public void run() {
		 new JTextComponentDemo();  
		}
		 });
		 } }
 
