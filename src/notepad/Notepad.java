package notepad;

import bracechecker.BraceChecker;
import searchFrame.SearchFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class Notepad extends JFrame {

    private static final String DEFAULT_NAME = "Notepad";
    private JTextArea textArea;
    private JTextField resultMessageFile;
    private JFileChooser jFileChooser;
    private Font font;
    private BraceChecker braceChecker;
    private File file;

    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem savaAsFile;
    private JMenuItem exitFile;


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
        JMenu mLang = new JMenu("Language");
        menuBar.add(mLang);
        menuBar.add(file);

        //Language menu items
        JMenuItem mItemEn = new JMenuItem(LanguageType.EN.getLabel());
        JMenuItem mItemAm = new JMenuItem(LanguageType.AM.getLabel());
        JMenuItem mItemRu = new JMenuItem(LanguageType.RU.getLabel());

        mLang.add(mItemEn);
        mLang.add(mItemAm);
        mLang.add(mItemRu);

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
        mItemEn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMenusLabels(LanguageType.EN);

            }
        });

        mItemAm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMenusLabels(LanguageType.AM);
            }
        });
        mItemRu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMenusLabels(LanguageType.RU);
            }
        });

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
//        textArea.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                handleDocumentUpdate();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                handleDocumentUpdate();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                handleDocumentUpdate();
//            }
//        });
//
//    }
    }

    private Properties getMenuLabels(LanguageType languageType) {
        String posix = languageType == LanguageType.AM
                ? "_" + LanguageType.AM.getLabel()
                : languageType == LanguageType.RU
                ? "_" + LanguageType.RU.getLabel()
                : "";

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("i18n/labels" + posix + ".properties");

        Properties labels = new Properties();
        try {
            labels.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return labels;
    }

    private void init(Properties labelKeys) {
        newFile = new JMenuItem(labelKeys.getProperty(LabelKey.NEW.getName()));
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem(labelKeys.getProperty(LabelKey.SAVE.getName()));
        savaAsFile = new JMenuItem("Save as");
        //  searchFile = new JMenuItem("Search");
        exitFile = new JMenuItem("Exit");
    }

    private void updateMenusLabels(LanguageType languageType) {
        Properties labels = getMenuLabels(languageType);

        newFile.setText(labels.getProperty(LabelKey.NEW.getName()));
        openFile.setText("Open");
        saveFile.setText(labels.getProperty(LabelKey.SAVE.getName()));
        savaAsFile.setText("Save as");
        //searchFile.setText("Search");
        exitFile.setText("Exit");
    }
//    void handleDocumentUpdate() {
//        if (!braceChecker.parse(textArea.getText())) {
//            resultMessageFile.setForeground(Color.red);
//            resultMessageFile.setText(braceChecker.getMessage());
//        } else {
//            resultMessageFile.setForeground(Color.darkGray);
//            resultMessageFile.setText("No Error");
//        }
//    }

    private void handleExitAction() {
        if (isChanged() && !handleSaveActionConfirm(ActionType.EXIT)) {
            return;
        }
        exit();
    }

    private void searchFileAction(ActionEvent e) {
//        SearchFrame searchFrame = new SearchFrame();
    }

    private void savaAsFileAction(ActionEvent e) {
        saveAs();
    }

    private void saveAction(ActionEvent e) {
        save();
    }


    private void openAction(ActionEvent e) {
        if (isChanged()) {
            if (!handleSaveActionConfirm(ActionType.OPEN)) {
                return;
            }
        }
        open();
    }

    /**
     * Opens a confirm dialog window to ask
     * save or no the content of TextArea or cancel the action.
     * if save is chosen then the content save,  otherwise if selected no nothing is saved.
     *
     * @return false if selected cancel option, otherwise true.
     */
    private boolean handleSaveActionConfirm(ActionType actionType) {
        switch (askSave()) {
            case JOptionPane.CANCEL_OPTION:
                return false;
            case JOptionPane.YES_OPTION:
                if ((ActionType.SAVE_AS == actionType) || isNewMode()) {
                    saveAs();
                } else {
                    save();
                }
        }

        return true;
    }

    private void newAction(ActionEvent e) {
        if (isChanged() && !handleSaveActionConfirm(ActionType.NEW)) {
            return;
        }
        newFile();
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

    private boolean isLoadedTextChenged(File file) {
        if (file == null) {
            return false;
        }
        return !textArea.getText().equals(read(file));
    }

    private int askSave() {
        int returnVal = JOptionPane.showConfirmDialog(null, "Do you want save file");
        return returnVal;
    }

    private boolean isNewMode() {
        return file == null;
    }

    private boolean isChanged() {
        if (isNewMode() && textArea.getText().length() > 0) {
            return true;
        } else if (!isNewMode() && isLoadedTextChenged(file)) {
            return true;
        }
        return false;
    }

    enum ActionType {NEW, SAVE, SAVE_AS, OPEN, EXIT}

    public static void main(String[] args) {
        Notepad n = new Notepad();
    }
}

enum LabelKey {
    NEW("new"),
    SAVE("save"),
    SAVE_AS("saveas"),
    OPEN("open"),
    EXIT("exit");

    LabelKey(String val) {
        this.name = val;
    }

    public String getName() {
        return name;
    }

    private final String name;
}


class LanguageType {
    static final LanguageType AM = new LanguageType(1, "am", "Armenian");
    static final LanguageType EN = new LanguageType(2, "en", "English");
    static final LanguageType RU = new LanguageType(3, "ru", "Russian");

    private LanguageType(int value, String label, String description) {
        this.value = value;
        this.label = label;
        this.description = description;
    }

    String getLabel() {
        return label;
    }

    private final int value;
    private final String label;
    private final String description;

}