package notepad;


import util.BraceChecker;
import util.SearchFrame;
import util.UnderlineHighlighter;
import util.MistakeHighlighter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;


public class Notepad extends JFrame {

    private static final String DEFAULT_NAME = "Notepad";
    private int fontsize = 15;
    private JTextArea textArea;
    private JTextField resultMessageFile;
    private JFileChooser jFileChooser;
    private Font font;
    private BraceChecker braceChecker;
    private File file;
    private NotepadMenuBar notepadMenu;
    private Highlighter highlighter;
    private MistakeHighlighter mistakeHighlighter;
    private JTextArea lines;


    public Notepad() {
        super(DEFAULT_NAME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        notepadMenu = new NotepadMenuBar();
        jFileChooser = new JFileChooser();
        textArea = new JTextArea();
        highlighter = new UnderlineHighlighter(null);

        textArea.setHighlighter(highlighter);
        resultMessageFile = new JTextField();
        font = new Font("Font.PLAIN", Font.PLAIN, 15);
        braceChecker = BraceChecker.getInstance();
        textArea.setFont(font);
        JScrollPane scrollPain = new JScrollPane();
        JPanel jPanel = new JPanel();
        GridLayout layout = new GridLayout(1, 1);
        jPanel.setLayout(layout);
        jPanel.add(resultMessageFile);
        add(jPanel, BorderLayout.SOUTH);
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPain, BorderLayout.CENTER);
        toolBar.setAlignmentX(0);
        add(textArea, BorderLayout.CENTER);
        lines = new JTextArea("1");
        lines.setBackground(new Color(255, 255, 171, 255));
        lines.setEditable(false);
        lines.setColumns(2);
        lines.setFont(font);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(textArea);
        scrollPane.setRowHeaderView(lines);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel tFPanel = new JPanel();
        GridLayout layou = new GridLayout(1, 1);
        add(scrollPane, BorderLayout.CENTER);


        JButton newbutton = new JButton(createImageIcon("icons\\New.png"));
        newbutton.setToolTipText("New");
        toolBar.add(newbutton);


        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAction(e);
            }
        });

        JButton savebutton = new JButton(createImageIcon("icons\\Save.png"));
        savebutton.setToolTipText("Save");
        toolBar.add(savebutton);

        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction(e);
            }
        });


        JButton openbutton = new JButton(createImageIcon("icons\\Open.png"));
        openbutton.setToolTipText("Open");
        toolBar.add(openbutton);

        openbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAction(e);

            }
        });

        JButton zoomInbutton = new JButton(createImageIcon("icons\\ZoomIn.png"));
        zoomInbutton.setToolTipText("ZoomIn");
        toolBar.add(zoomInbutton);


        zoomInbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomInAction(e);

            }
        });

        JButton zoomOutbutton = new JButton(createImageIcon("icons\\ZoomOut.png"));
        zoomOutbutton.setToolTipText("ZoomOut");
        toolBar.add(zoomOutbutton);

        zoomOutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOutAction(e);

            }
        });


        JButton exitbutton = new JButton(createImageIcon("icons\\Exit.png"));
        exitbutton.setToolTipText("Exit");
        toolBar.add(exitbutton);

        exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });


        setJMenuBar(notepadMenu);
        // Add ActionListener
        notepadMenu.search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchAction(e);
            }
        });
        notepadMenu.mItemEn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notepadMenu.updateMenusLabels(LanguageType.EN);
            }
        });
        notepadMenu.mItemAm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notepadMenu.updateMenusLabels(LanguageType.AM);
            }
        });
        notepadMenu.mItemRu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notepadMenu.updateMenusLabels(LanguageType.RU);
            }
        });

        notepadMenu.newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAction(e);
            }
        });
        notepadMenu.openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAction(e);
            }
        });

        notepadMenu.saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction(e);
            }
        });

        notepadMenu.savaAsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savaAsFileAction(e);
            }
        });

        notepadMenu.exitFile.addActionListener(new ActionListener() {
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
        mistakeHighlighter = new MistakeHighlighter(textArea);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int count = 1;
                String content = textArea.getText();
                String text = count + System.getProperty("line.separator");
                for (int i = 0; i < content.length(); i++) {
                    if (content.charAt(i) == '\n') {
                        text += (++count) + System.getProperty("line.separator");
                    }
                }
                return text;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                lines.setText(getText());
                handleDocumentUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lines.setText(getText());
                handleDocumentUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }


    private void handleSearchAction(ActionEvent e) {
        SearchFrame searchFrame = new SearchFrame();
    }


    void handleDocumentUpdate(DocumentEvent e) {
        if (!braceChecker.parse(textArea.getText())) {
            highlighter = MistakeHighlighter.comp.getHighlighter();
            mistakeHighlighter.search(textArea.getText());

            resultMessageFile.setForeground(Color.red);
            resultMessageFile.setText(braceChecker.getMessage());
        } else {
            highlighter.removeAllHighlights();

            resultMessageFile.setForeground(Color.darkGray);
            resultMessageFile.setText("No Error");
        }
    }

    private void handleExitAction() {
        if (isChanged() && !handleSaveActionConfirm(ActionType.EXIT)) {
            return;
        }
        exit();
    }

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

    private void newAction(ActionEvent e) {
        if (isChanged() && !handleSaveActionConfirm(ActionType.NEW)) {
            return;
        }
        newFile();
    }

    private void zoomInAction(ActionEvent e) {
        if (fontsize < 50) {
            fontsize += 2;
            font = new Font("Font.PLAIN", Font.PLAIN, fontsize);
            textArea.setFont(font);

        }

    }

    private void zoomOutAction(ActionEvent e) {
        if (fontsize > 5) {
            fontsize -= 2;
            font = new Font("Font.PLAIN", Font.PLAIN, fontsize);
            textArea.setFont(font);

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
        int choice = jFileChooser.showSaveDialog(textArea);
        if (choice == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
            write(jFileChooser.getSelectedFile());
            setTitle(file.getName());
        }
    }

    public void newFile() {
        setTitle(DEFAULT_NAME);
        textArea.setText("");
        file = null;
    }

    public void open() {
        if (jFileChooser.showOpenDialog(jFileChooser) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
            textArea.setText(read(file));
            setTitle(file.getName());
        }
    }

    public void write(File file) {
        write(file, textArea.getText());
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


    public boolean isLoadedTextChenged(File file) {
        if (file == null) {
            return false;
        }
        return !textArea.getText().equals(read(file));
    }

    private int askSave() {
        int returnVal = JOptionPane.showConfirmDialog(null, "Do you want save file");
        return returnVal;
    }

    private boolean isChanged() {
        if (isNewMode() && textArea.getText().length() > 0) {
            return true;
        } else if (!isNewMode() && textArea.getText().length() > 0) {
            return true;
        }
        return false;
    }

    private boolean isNewMode() {
        return file == null;
    }


    public static void main(String[] args) {
        Notepad n = new Notepad();
    }

    public static ImageIcon createImageIcon(String path) {
        URL imageIconURL = NotepadMenuBar.class.getResource(path);
        if (imageIconURL != null) {
            return new ImageIcon(imageIconURL);

        } else {
            System.err.println("Could not find  File " + path);
            return null;
        }

    }

}


