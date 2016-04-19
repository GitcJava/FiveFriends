package notepad;

import bracechecker.BraceChecker;
import searchFrame.SearchFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Notepad extends JFrame {

    private static final String DEFAULT_NAME = "Notepad";
    private JTextArea textArea;
    private JTextField resultMessageFile;
    private JFileChooser jFileChooser;
    private Font font;
    private BraceChecker braceChecker;
    private File file;

    public Notepad() {
        super(DEFAULT_NAME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jFileChooser = new JFileChooser();
        textArea = new JTextArea();
        resultMessageFile = new JTextField();
        font = new Font("Font.PLAIN", Font.PLAIN, 22);
        braceChecker = BraceChecker.getInstance();
        textArea.setFont(font);
        JPanel jPanel = new JPanel();
        GridLayout layout = new GridLayout(1, 1);
        jPanel.setLayout(layout);
        jPanel.add(resultMessageFile);
        add(textArea, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);

        // Add MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Add Menu
        JMenu file = new JMenu("File");
        menuBar.add(file);

        //Add MenuItem
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem savaAsFile = new JMenuItem("Save as");
        JMenuItem searchFile = new JMenuItem("Search");
        JMenuItem exitFile = new JMenuItem("Exit");
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(savaAsFile);
        file.add(searchFile);
        file.add(exitFile);

        // Add ActionListener

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAction(e);
            }
        });
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAction(e);
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction(e);
            }
        });

        savaAsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savaAsFileAction(e);
            }
        });

        searchFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFileAction(e);
            }
        });

        exitFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocation(100, 100);
        setVisible(true);
        resultMessageFile.setFont(font);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }
        });

    }

    void handleDocumentUpdate() {
        if (!braceChecker.parse(textArea.getText())) {
            resultMessageFile.setForeground(Color.red);
            resultMessageFile.setText(braceChecker.getMessage());
        } else {
            resultMessageFile.setForeground(Color.darkGray);
            resultMessageFile.setText("No Error");
        }
    }

    private void handleExitAction() {
        if (textArea.getText().equals("") || isTextEquals(file)) {
            exit();
        } else {
            switch (askSave()) {
                case 0:
                    saveAs();
                    exit();
                    break;
                case 1:
                    exit();
            }
        }
    }

    private void searchFileAction(ActionEvent e) {
        SearchFrame searchFrame = new SearchFrame();
    }

    private void savaAsFileAction(ActionEvent e) {
        saveAs();
    }

    private void saveAction(ActionEvent e) {
        save();
    }


    private void openAction(ActionEvent e) {
        if (textArea.getText().equals("") || isTextEquals(file)) {
            open();
        } else {
            switch (askSave()) {
                case 0:
                    saveAs();
                    break;
                case 1:
                    open();
                    break;
            }
        }
    }

    private void newAction(ActionEvent e) {

        switch (askSave()) {
            case 0:
                saveAs();
                newFile();
                break;
            case 1:
                newFile();
                break;
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void save() {
        if (file != null) {
            write(file, textArea.getText());
        } else {
            saveAs();
        }
    }

    public void saveAs() {
        if (jFileChooser.showSaveDialog(jFileChooser) == JFileChooser.APPROVE_OPTION) ;
        file = jFileChooser.getSelectedFile();
        write(file, textArea.getText());
        setTitle(file.getName());
    }

    public void newFile() {
        setTitle(DEFAULT_NAME);
        textArea.setText("");
    }

    public void open() {
        if (jFileChooser.showOpenDialog(jFileChooser) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
            textArea.setText(read(file));
            setTitle(file.getName());
        }
    }


    public void write(File file, String text) {
        String path;
        if (!file.getName().contains(".txt")) {
            path = file.getAbsolutePath() + ".txt";
        } else {
            path = file.getAbsolutePath();
        }
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(File file) {
        byte[] b = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(b);
    }

    public boolean isTextEquals(File file) {
        if (file == null) {
            return false;
        }
        String text;
        String notepadTaex = textArea.getText();
        byte[] b = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text = new String(b);
        return notepadTaex.equals(text);
    }

    private int askSave() {
        int returnVal = JOptionPane.showConfirmDialog(null, "Do you want save file");
        return returnVal;
    }

    private boolean isChanged() {
        if (file == null && textArea.getText().trim().length() > 0) {
            return true;
        } else if (file != null && file.exists() && textArea.getText().equals("")) {

            save();
            exit();
        }
        return false;
    }

    public static void main(String[] args) {
        Notepad n = new Notepad();
    }
}
