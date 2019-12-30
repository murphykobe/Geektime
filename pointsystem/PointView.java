import java.awt.event.ActionListener;
import javax.swing.*;

public class PointView extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField id = new JTextField(10);
    private JTextField credit = new JTextField(10);
    private JTextField channel_id = new JTextField(10);
    private JTextField event_id = new JTextField(10);
    private JTextField expired_time = new JTextField(10);
    private JLabel id_label = new JLabel("User ID");
    private JLabel id_credit = new JLabel("Point Amount");
    private JLabel id_channel = new JLabel("Channel");
    private JLabel id_event = new JLabel("Event");
    private JLabel id_expired_time = new JLabel("Expiration");
    private JButton addtrans = new JButton("Add Transaction");
    private JButton querypoints = new JButton("Query Points");
    private JTextField result = new JTextField("Result");

    PointController pointController;

    public PointView(){
        JPanel pointPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        pointPanel.add(id_label);
        pointPanel.add(id);
        pointPanel.add(id_credit);
        pointPanel.add(credit);
        pointPanel.add(id_channel);
        pointPanel.add(channel_id);
        pointPanel.add(id_event);
        pointPanel.add(event_id);
        pointPanel.add(id_expired_time);
        pointPanel.add(expired_time);
        pointPanel.add(addtrans);
        pointPanel.add(querypoints);
        pointPanel.add(result);
        this.add(pointPanel);
    }

    public long getId(){
        return Long.parseLong(id.getText());
    }

    public long getCredit(){
        return Long.parseLong(credit.getText());
    }

    public long getChannelId(){
        return Long.parseLong(channel_id.getText());
    }
    
    public long getEventId(){
        return Long.parseLong(event_id.getText());
    }

    public long getExpiredTime(){
        return Long.parseLong(expired_time.getText());
    }

    public long getResult(){
        return Long.parseLong(result.getText());
    }

    public void setResult(Long r){
        result.setText(Long.toString(r));
    }

    void addTransactionListener(ActionListener listenForAddButton){
        addtrans.addActionListener(listenForAddButton);
    }

    void addQueryListener(ActionListener listenForQueryButton){
        querypoints.addActionListener(listenForQueryButton);
    }

    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}