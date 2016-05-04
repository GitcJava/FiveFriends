package notepad;

import javax.swing.*;
import javax.swing.undo.UndoManager;


import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

class NotepadMenuBar extends JMenuBar {

    public JMenuItem newFile;
    public JMenuItem openFile;
    public JMenuItem saveFile;
    public JMenuItem savaAsFile;
    public JMenuItem exitFile;
    public JMenuItem search;
    public JMenuItem mItemEn;
    public JMenuItem mItemAm;
    public JMenuItem mItemRu;
    public JMenuItem undo;
    public JMenuItem redo;



    public NotepadMenuBar() {
        JMenu file = new JMenu("File");
        JMenu mLang = new JMenu("Language");
        JMenu tools = new JMenu("Tools");
        add(file);
        add(mLang);
        add(tools);


        Properties menuLabels = getMenuLabels(LanguageType.EN);
        init(menuLabels);

        mItemEn = new JMenuItem(LanguageType.EN.getLabel());
        mItemAm = new JMenuItem(LanguageType.AM.getLabel());
        mItemRu = new JMenuItem(LanguageType.RU.getLabel());
        search = new JMenuItem("Search");
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        mLang.add(mItemEn);
        mLang.add(mItemAm);
        mLang.add(mItemRu);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(savaAsFile);
        file.add(exitFile);
        tools.add(search);
        tools.add(undo);
        tools.add(redo);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        savaAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

    }


    public void init(Properties labelKeys) {
        newFile = new JMenuItem(labelKeys.getProperty(LabelKey.NEW.getName()));
        openFile = new JMenuItem(labelKeys.getProperty(LabelKey.OPEN.getName()));
        saveFile = new JMenuItem(labelKeys.getProperty(LabelKey.SAVE.getName()));
        savaAsFile = new JMenuItem(labelKeys.getProperty(LabelKey.SAVE_AS.getName()));
        exitFile = new JMenuItem(labelKeys.getProperty(LabelKey.EXIT.getName()));
    }

    public void updateMenusLabels(LanguageType languageType) {
        Properties labels = getMenuLabels(languageType);

        newFile.setText(labels.getProperty(LabelKey.NEW.getName()));
        openFile.setText(labels.getProperty(LabelKey.OPEN.getName()));
        saveFile.setText(labels.getProperty(LabelKey.SAVE.getName()));
        savaAsFile.setText(labels.getProperty(LabelKey.SAVE_AS.getName()));
        exitFile.setText(labels.getProperty(LabelKey.EXIT.getName()));
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


}

enum ActionType {NEW, SAVE, SAVE_AS, OPEN, EXIT}

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
    static final LanguageType AM = new LanguageType(1, "hy", "Armenian");
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
