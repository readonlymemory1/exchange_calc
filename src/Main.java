
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.*;

class MainFrame extends JFrame{
    MainFrame(){
        setTitle("helloworld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(540, 360);
        JButton change = new JButton("change");
        JTextField input_money = new JTextField();
        JLabel d = new JLabel();
        JLabel u = new JLabel();
        change.setBounds(220, 180, 100, 40);
        input_money.setBounds(115, 180, 100, 35);
        d.setBounds(370, 200, 100, 50);
        u.setBounds(370, 200, 100, 50);
        add(change);
        add(input_money);
        add(d);
        add(u);

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input_text  = Integer.parseInt(input_money.getText());
                try {
                    Document doc = Jsoup.connect("https://kr.investing.com/currencies/usd-krw").post();
//                    System.out.println(doc.text());
                    Elements links = doc.select("span.text-2xl");
                    float dolar = 0;
                    for (Element link : links) {
                        String l = link.toString();
                        l = l.replace("<span class=\"text-2xl\" data-test=\"instrument-price-last\">", "");
                        l = l.replace("</span>", "");
                        l = l.replace(",", "");
                        dolar = Float.parseFloat(l);
                        break; // Stop the loop after the first iteration  https://kr.investing.com/currencies/eur-krw
                    }

                    float result = dolar * input_text;
                    d.setText("$: "+String.valueOf(result));

                } catch (IOException ex) {

                    throw new RuntimeException(ex);
                }

                try {
                    Document doc = Jsoup.connect("https://kr.investing.com/currencies/eur-krw").post();
//                    System.out.println(doc.text());
                    Elements links = doc.select("span.text-2xl");
                    float euro = 0;
                    for (Element link : links) {
                        String l = link.toString();
                        l = l.replace("<span class=\"text-2xl\" data-test=\"instrument-price-last\">", "");
                        l = l.replace("</span>", "");
                        l = l.replace(",", "");
                        euro = Float.parseFloat(l);
                        break; // Stop the loop after the first iteration  https://kr.investing.com/currencies/eur-krw
                    }

                    float result = euro * input_text;
                    u.setText("EUR : "+String.valueOf(result));

                } catch (IOException ex) {

                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
public class Main {
    public static void main(String[] args) {
        new MainFrame();
    }
}