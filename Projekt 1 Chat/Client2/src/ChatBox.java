import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatBox{
    private JTextField inputMessage;
    private JPanel MessageBox;
    private JButton sendButton;
    private JPanel MainWindow;
    private ClientSocket clientSocketHandler;
    private JFrame mainWindow;
    Box box;


    public ChatBox(ClientSocket clientSocket) {
        this.clientSocketHandler = clientSocket;
        box =  Box.createVerticalBox();
        MessageBox.add(box);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageToSend = inputMessage.getText();
                clientSocketHandler.sendBroadCast(messageToSend);
                newMessage("You: "+messageToSend);
                inputMessage.setText("");
            }

        });
        MainWindow.setVisible(true);
        inputMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyChar() == '\n')
                    sendButton.doClick();
            }
        });
    }
    public void start(String login){

        mainWindow = new JFrame("AK's Chat -"+login);
        mainWindow.setContentPane(this.MainWindow);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800,600);
       // mainWindow.pack();
        mainWindow.setVisible(true);
        newMessage("Witaj "+login+" dołącz do grupowej konwersacji");
    }
    public static String getNick (){
        return JOptionPane.showInputDialog("Podaj nick");
    }
    public void newMessage (String message){
        box.add(new JLabel(message));
        mainWindow.validate();
        mainWindow.repaint();
    }
}
