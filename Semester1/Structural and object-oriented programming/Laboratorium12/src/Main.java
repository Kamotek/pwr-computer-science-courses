import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Sniadanie implements ActionListener {
    String zakupy;
    String cena;


    JLabel menu = new JLabel("Menu");
    JCheckBox rogal = new JCheckBox("Rogalik - 5zł");
    JCheckBox jajecznica = new JCheckBox("Jajecznica - 15zł");
    JCheckBox herbata = new JCheckBox("Herbata - 4zł");
    JCheckBox kawa = new JCheckBox("Kawa - 6zł");

    JButton akceptuj = new JButton("Akceptuj");
    JPanel panel2 = new JPanel();
    JLabel mojeSniadanie = new JLabel("Moje śniadanie");
    JLabel kwotaDoZaplaty = new JLabel("Kwota do zapłaty");
    JTextField mojeSniadanieField = new JTextField(zakupy);
    JTextField kwotaDoZaplatyField = new JTextField(cena);
    public Sniadanie(){

        JFrame frame = new JFrame("Śniadanie");
        JPanel panel1 = new JPanel();

        zakupy = "";
        cena = "";

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel1.add(menu);
        panel1.add(rogal);
        panel1.add(jajecznica);
        panel1.add(herbata);
        panel1.add(kawa);
        panel1.add(akceptuj);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.add(mojeSniadanie);
        panel2.add(mojeSniadanieField);
        panel2.add(kwotaDoZaplaty);
        panel2.add(kwotaDoZaplatyField);

        frame.setLayout(new GridLayout(1,2));
        frame.add(panel1);
        frame.add(panel2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        akceptuj.addActionListener(this);

    }

    public static void main(String[] args) {

        new Sniadanie();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double cena = 0;
        this.cena = "";
        this.zakupy = "";

        if(rogal.isSelected()) {
            zakupy += "Rogalik, ";
            cena += 5;
        }
        if(jajecznica.isSelected()) {
            zakupy += "jajecznica, ";
            cena+=15;
        }
        if(herbata.isSelected()) {
            zakupy += "herbata, ";
            cena+=4;
        }
        if(kawa.isSelected()) {
            zakupy += "kawa, ";
            cena += 6;
        }

        this.cena = String.valueOf(cena);
        mojeSniadanieField.setText(zakupy);
        kwotaDoZaplatyField.setText(this.cena);
    }
}