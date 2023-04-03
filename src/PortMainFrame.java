import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PortMainFrame {
    private JTextField idTextField;
    private JTextField weightTextField;
    private JPanel mainPanel;
    private JLabel idNumLabel;
    private JLabel weightLabel;
    private JLabel descLabel;
    private JTextArea descTextArea;
    private JLabel remCompLabel;
    private JTextField remCompTextField;
    private JLabel recCompLabel;
    private JTextField recCompTextField;
    private JButton pileButton;
    private JButton unpileButton;
    private JLabel operationsLabel;
    private JButton showDescButton;
    private JTextArea contDescTextArea;
    private JButton numContButton;
    private JComboBox<String> fromCountryComboBox;
    private JLabel countryLabel;
    private JLabel priorityLabel;
    private JLabel stateLabel;
    private JLabel customInspLabel;
    private JComboBox<String> countryComboBox;
    private JLabel companylogoLabel;
    private JRadioButton a1RadioButton;
    private JTextArea stateTextArea;
    private JCheckBox customInspCheckBox;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JTextField unpileFromTextField;
    private JTextField fromCountryCountText;
    private JTextArea textArea1;
    private JRadioButton hub1RadioButton;
    private JRadioButton hub2RadioButton;
    private JRadioButton hub3RadioButton;
    private JLabel imageLabel;
    Port port = new Port();
    int iwishnt = 1;

    public PortMainFrame() {
        ImageIcon imageIcon = new ImageIcon("To be filled with the logo of the company");
        imageLabel = new JLabel(imageIcon);
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container cont = new Container();
                int which = 1;
                cont.setID(Integer.parseInt(idTextField.getText()));
                cont.setWeight(Integer.parseInt(weightTextField.getText()));
                cont.setSender(remCompTextField.getText());
                cont.setReceiver(recCompTextField.getText());
                if (a1RadioButton.isSelected()){
                    cont.setPriority(1);
                }
                if (a2RadioButton.isSelected()){
                    cont.setPriority(2);
                }
                if (a3RadioButton.isSelected()){
                    cont.setPriority(3);
                }
                cont.setDescription(descTextArea.getText());
                cont.setCountry((String) countryComboBox.getSelectedItem());
                if (customInspCheckBox.isSelected()){
                    cont.setInspected(true);
                }
                else {
                    cont.setInspected(false);
                }
                iwishnt = 1;
                if (port.hub1.stackCont(cont) == -1){
                    which+=1;
                    iwishnt = 2;
                    if (port.hub2.stackCont(cont)==-1){
                        which+=1;
                        iwishnt = 3;
                        if (port.hub3.stackCont(cont)==-1){
                            which+=1;
                            descTextArea.setText("The columns are full.");
                        }
                    }
                }
                if (which == 1){
                    stateTextArea.setText("HUB 1\n"+port.hub1.hubToString());
                }
                if (which == 2){
                    stateTextArea.setText("HUB 2\n"+port.hub2.hubToString());
                }
                if (which == 3){
                    stateTextArea.setText("HUB 3\n"+port.hub3.hubToString());
                }
                idTextField.setText(""+(cont.ID+1));
            }
        });
        showDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!port.hub1.dataContID(Integer.parseInt(textArea1.getText())).equals("Not found")){
                    contDescTextArea.setText(port.hub1.dataContID(Integer.parseInt(textArea1.getText())));
                }
                if (!port.hub2.dataContID(Integer.parseInt(textArea1.getText())).equals("Not found")){
                    contDescTextArea.setText(port.hub2.dataContID(Integer.parseInt(textArea1.getText())));
                }
                if (!port.hub3.dataContID(Integer.parseInt(textArea1.getText())).equals("Not found")){
                    contDescTextArea.setText(port.hub3.dataContID(Integer.parseInt(textArea1.getText())));
                }
            }
        });
        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iwishnt == 1){
                    port.hub1.removeContFrom(Integer.parseInt(unpileFromTextField.getText()));
                    stateTextArea.setText("HUB 1\n"+port.hub1.hubToString());
                }
                if (iwishnt == 2){
                    port.hub2.removeContFrom(Integer.parseInt(unpileFromTextField.getText()));
                    stateTextArea.setText("HUB 2\n"+port.hub2.hubToString());
                }
                if (iwishnt == 3){
                    port.hub3.removeContFrom(Integer.parseInt(unpileFromTextField.getText()));
                    stateTextArea.setText("HUB 3\n"+port.hub3.hubToString());
                }
            }
        });
        numContButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sum = 0;
                sum += port.hub1.howManyFrom((String) fromCountryComboBox.getSelectedItem());
                sum += port.hub2.howManyFrom((String) fromCountryComboBox.getSelectedItem());
                sum += port.hub3.howManyFrom((String) fromCountryComboBox.getSelectedItem());
                String stup = ""+sum;
                fromCountryCountText.setText(stup);
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hub1RadioButton.isSelected()){
                    iwishnt = 1;
                    stateTextArea.setText("HUB 1\n"+port.hub1.hubToString());
                }
                if (hub2RadioButton.isSelected()){
                    iwishnt = 2;
                    stateTextArea.setText("HUB 2\n"+port.hub2.hubToString());
                }
                if (hub3RadioButton.isSelected()){
                    iwishnt = 3;
                    stateTextArea.setText("HUB 3\n"+port.hub3.hubToString());
                }
            }
        };
        hub1RadioButton.addActionListener(listener);
        hub2RadioButton.addActionListener(listener);
        hub3RadioButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PortMainFrame");
        frame.setContentPane(new PortMainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
