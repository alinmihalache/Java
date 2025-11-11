import java.awt.*;
import java.awt.event.*;

public class GUI1 extends Frame {
    public GUI1(String s) {
        super(s);
        setBackground(Color.green);
        setLayout(new FlowLayout());
        Button b1 = new Button("Apasa-ma");
        add(b1);

        // Închide fereastra la click pe X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}