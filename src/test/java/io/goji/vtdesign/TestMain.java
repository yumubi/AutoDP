package io.goji.vtdesign;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.StringTemplate.STR;

public class TestMain {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        String platform = "Serverless";
        ArrayList<String> dict = new ArrayList<String>() {{
            add("有个叫什么magic的东西");
            add("哦哦…tcp层的“阻塞”");
            add("登陆，会自动合并账号");
            add(STR."这是不是一个\{platform}服务平台");
            add("我怎么就 python 变成 top 1 了");
            add("这两个一起摆，过于不协调了……");
            add("我也用了这个stats，不过我根本不会写样式…");
            add("这里我想吹下Kotlin，希望大家别讨厌我");
            add("我还么学会");
            add("String");

        }};
        TaskPool.getInstance().setDic(dict);

        JFrame f = new JFrame();
        final JTextField textField = new JTextField(20);
        final JList<String> list = new JList<>();
        final DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 400));

        JPanel p = new JPanel();
        p.add(textField);
        p.add(listScroller);
        f.add(p);

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String matchString = null;
                try {
                    matchString = TaskPool.getInstance().matchString(textField.getText());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (matchString != null) {
                    String[] candidates = matchString.split(" ");
                    listModel.clear();
                    // 假设TaskPool.getInstance().matchString返回一个候选词列表
                    // 可以这样添加候选词到列表模型
                     for(String candidate : candidates) {
                         listModel.addElement(candidate);
                     }
                }
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(20, 30, 550, 550);
        f.setVisible(true);
    }
}
