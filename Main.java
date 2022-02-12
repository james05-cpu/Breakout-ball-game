package com.Annonymous;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[]args){
        JFrame obj= new JFrame();
        Gameplay gameplay=new Gameplay();

/*JMenuBar menuBar=new JMenuBar();
JMenu level=new JMenu("Level");
JMenuItem normal=new JMenuItem("Normal");
level.add(normal);
        JMenuItem hard=new JMenuItem("Expert");
        level.add(hard);
        menuBar.add(level);
obj.setJMenuBar(menuBar);
hard.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        gameplay.playerlen=25;
        gameplay.leftWall=675;
        gameplay.rightWall=6;
        gameplay.ballydir=-4;
        gameplay.ballxdir=-5;
    }
});
       normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameplay.playerlen=100;
                gameplay.leftWall=600;
                gameplay.rightWall=10;
                gameplay.ballydir=-1;
                gameplay.ballxdir=-2;
            }
        });


 */        obj.add(gameplay);

        obj.setBounds(10,10,700,633);
        //obj.setLocationRelativeTo(null);
        obj.setFocusable(false);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);
    }
}
