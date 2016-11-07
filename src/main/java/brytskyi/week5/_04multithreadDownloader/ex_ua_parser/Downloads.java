package brytskyi.week5._04multithreadDownloader.ex_ua_parser;

import javax.swing.*;


public class Downloads extends JFrame {
    private JPanel main;
    private JScrollPane pane;
    private JPanel barsPanel;

    public Downloads() {
        super("Downloads");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 800);
        setResizable(false);
        add(main);
        setVisible(true);
    }


    public JProgressBar addNewProgressBar(String title) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        bar.setToolTipText(title);
        barsPanel.add(bar);
        barsPanel.updateUI();
        return bar;
    }

}
