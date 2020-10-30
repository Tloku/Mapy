package maps;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Zawartość: Klasa MapWindowApp
 * Autor: Dominik Tłokiński
 * Nr indeksu: 252689
 * Data: październik 2020 r.
 */


public class MapWindowApp extends JFrame {
    private Map currentMap;

    JButton newButton = new JButton("Nowa mapa");
    JButton modifyButton = new JButton("Zmodyfikuj mape");
    JButton saveToFileButton = new JButton("Zapisz mape");
    JButton printFromFileButton = new JButton("Wczytaj mape");
    JButton deleteButton = new JButton("Usuń mape");
    JButton authorInfoButton = new JButton("O autorze");
    JButton exitButton = new JButton("Wyjdź");

    JLabel mapNameLabel = new JLabel("Nazwa mapy");
    JLabel mapWidthLabel = new JLabel("Szerokość");
    JLabel mapHeightLabel = new JLabel("Wysokość");
    JLabel mapScaleLabel = new JLabel("Skala");
    JLabel mapPublisherLabel = new JLabel("Wydawca");
    JLabel mapPrizeLabel = new JLabel("Cena");

    JTextField mapNameTextField = new JTextField(10);
    JTextField mapWidthTextField = new JTextField(10);
    JTextField mapHeightTextField = new JTextField(10);
    JTextField mapScaleTextField = new JTextField(10);
    JTextField mapPublisherTextField = new JTextField(10);
    JTextField mapPrizeTextField = new JTextField(10);

    public MapWindowApp() {
        initComponents();
    }

    public static void main(String[] args) {
        new MapWindowApp();
    }

    private void initComponents() {
        this.setTitle("Okienkowe Mapy");


        addButtonsLabelsAndTextFields();
        showCurrentMap();
        this.setLocationRelativeTo(null);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }

    public void addButtonsLabelsAndTextFields() {

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMap = MapWindowDialog.createNewMap(this);
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMap == null) JOptionPane.showMessageDialog(rootPane, "Nie ma obiektu do zmodyfikowania");
                MapWindowDialog.changeMapData(this, currentMap);
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                while (fileName == null || fileName.equals(""))
                    fileName = JOptionPane.showInputDialog("Podaj nazwe pliku, do którego chcesz zapisać obiekt");

                try {
                    if(currentMap == null)
                        JOptionPane.showMessageDialog(rootPane,"Nie ma mapy do zapisania!");

                    Map.printToFile(currentMap, fileName);
                } catch (MapException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Nie udało się zapisać do pliku");
                }
            }
        });

        printFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                while (fileName == null || fileName.equals(""))
                    fileName = JOptionPane.showInputDialog("Podaj nazwe pliku, z którego chcesz wpisać obiekt");

                try {
                    currentMap = Map.readFromFile(fileName);
                } catch (MapException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Nie udało się odczytać pliku");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMap = null;
            }
        });

        authorInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, MapConsoleApp.AUTHOR);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        showCurrentMap();

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup()
                                .addComponent(mapNameLabel).addComponent(mapWidthLabel).addComponent(mapHeightLabel).addComponent(mapScaleLabel)
                                .addComponent(mapPublisherLabel).addComponent(mapPrizeLabel)
                                .addComponent(newButton).addComponent(saveToFileButton).addComponent(deleteButton).addComponent(exitButton)
                )
                .addGroup(
                        layout.createParallelGroup()
                                .addComponent(mapNameTextField).addComponent(mapWidthTextField).addComponent(mapHeightTextField).addComponent(mapScaleTextField)
                                .addComponent(mapPublisherTextField).addComponent(mapPrizeTextField)
                                .addComponent(modifyButton).addComponent(printFromFileButton).addComponent(authorInfoButton)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup().addComponent(mapNameLabel).addComponent(mapNameTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(mapWidthLabel).addComponent(mapWidthTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(mapHeightLabel).addComponent(mapHeightTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(mapScaleLabel).addComponent(mapScaleTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(mapPublisherLabel).addComponent(mapPublisherTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(mapPrizeLabel).addComponent(mapPrizeTextField)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(newButton).addComponent(modifyButton)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(saveToFileButton).addComponent(printFromFileButton)
                )
                .addGroup(
                        layout.createParallelGroup().addComponent(deleteButton).addComponent(authorInfoButton)
                )
                .addComponent(exitButton)
        );

        mapNameTextField.setEditable(false);
        mapWidthTextField.setEditable(false);
        mapHeightTextField.setEditable(false);
        mapScaleTextField.setEditable(false);
        mapPublisherTextField.setEditable(false);
        mapPrizeTextField.setEditable(false);
    }


    public void showCurrentMap()
    {
        if(currentMap == null)
        {
            mapNameTextField.setText("");
            mapWidthTextField.setText("");
            mapHeightTextField.setText("");
            mapScaleTextField.setText("");
            mapPublisherTextField.setText("");
            mapPrizeTextField.setText("");
        }
        else{
            mapNameTextField.setText(currentMap.getName());
            mapWidthTextField.setText(String.valueOf(currentMap.getWidth()));
            mapHeightTextField.setText(String.valueOf(currentMap.getHeight()));
            mapHeightTextField.setText(String.valueOf(currentMap.getHeight()));
            mapScaleTextField.setText(currentMap.getScale().toString());
            mapPublisherTextField.setText(currentMap.getPublisher().toString());
            mapPrizeTextField.setText(String.valueOf(currentMap.getPrize()));
        }

    }
}


