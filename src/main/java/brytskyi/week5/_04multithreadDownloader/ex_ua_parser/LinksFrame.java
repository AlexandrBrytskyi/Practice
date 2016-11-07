package brytskyi.week5._04multithreadDownloader.ex_ua_parser;

import brytskyi.week3.downloaders.exUaDownloader.UrlTittleWrapper;
import brytskyi.week5._04multithreadDownloader.ex_ua_parser.controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;


public class LinksFrame extends JFrame {
    private Controller controller;
    private JPanel mainPanel;
    private JScrollPane pane;
    private JList list1;
    private DefaultListModel<UrlTittleWrapper> model;
    private Downloads downloadsFrame;

    public LinksFrame(Controller controller) throws HeadlessException {
        super("Links");
        this.controller = controller;
        downloadsFrame = new Downloads();
        model = new DefaultListModel<UrlTittleWrapper>();
        list1.setModel(model);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                UrlTittleWrapper wrapper = model.getElementAt(list1.getSelectedIndex());
                e.getFirstIndex();
                if (JOptionPane.showConfirmDialog(list1, "Download?", wrapper.getTittle(), JOptionPane.YES_NO_OPTION) == 0) {
                    controller.download(wrapper, downloadsFrame.addNewProgressBar(wrapper.getTittle()));
                }
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 800);
        setResizable(true);
        add(mainPanel);
        setVisible(true);
    }


    public void addLinks(java.util.List<UrlTittleWrapper> links) {
        for (UrlTittleWrapper link : links) {
            model.addElement(link);
        }
    }

}
