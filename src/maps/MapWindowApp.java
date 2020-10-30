package maps;

import javax.swing.*;
import java.awt.*;

public class MapWindowApp extends JFrame
{
    public MapWindowApp()
    {
       initComponents();
    }

    public static void main(String[] args)
    {
        new MapWindowApp();
    }

    public void initComponents()
    {
        this.setTitle("Okienkowe Mapy");

        addButtons();



        this.setLocationRelativeTo(null);
        this.setSize(500,400);
        //this.setBounds(200,200,300,300);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }

    public void addButtons()
    {
        JPanel panel = new JPanel();
        JButton newButton = new JButton("Nowa mapa");
        JButton modifyButton = new JButton("Zmodyfikuj mape");
        JButton saveToFileButton = new JButton("Zapisz mape");
        JButton printFromFileButton = new JButton("Wczytaj mape");
        JButton deleteButton = new JButton("Usuń mape");
        JButton authorInfoButton = new JButton("O autorze");
        JButton exitButton = new JButton("Wyjdź");
/*
        panel.add(newButton);
        panel.add(modifyButton);
        panel.add(saveToFileButton);
        panel.add(printFromFileButton);
        panel.add(deleteButton);
        panel.add(authorInfoButton);
        panel.add(exitButton);

 */
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(exitButton)
        );
    }

}
