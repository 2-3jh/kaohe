package com.kaohe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game2 extends JFrame implements ActionListener, KeyListener {
    //菜单
    JMenu gameMenu = new JMenu("游戏");
    JMenuItem endItem = new JMenuItem("结束");
    JMenuItem pauseItem = new JMenuItem("暂停");
    JMenuItem beginItem = new JMenuItem("开始");

    //获得内容面板
    Container contentPane = getContentPane();

    //记录界面数据的数组
    private int[][] arr = new int[4][4];
    //总分
    private int score;

    //构造方法
    public Game2(){
        //初始化界面
        initFrame();

        //初始化按钮
        initMenu();

        //绘制游戏界面
        draw();
        //设置为可见
        this.setVisible(true);
    }

    //初始菜单
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("设置");
        gameMenu.add(beginItem);
        gameMenu.add(pauseItem);
        gameMenu.add(endItem);
        menuBar.add(gameMenu);
        beginItem.addActionListener(this);
        pauseItem.addActionListener(this);
        endItem.addActionListener(this);
        this.setJMenuBar(menuBar);
    }

    //初始化界面
    private void initFrame() {
        this.setSize(400, 500);
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        contentPane.setBackground(new Color(77, 57, 14));
    }

    // 游戏面板模块
    private void draw()
    {
        JLabel scoreLabel = new JLabel("得分：" + score);
        scoreLabel.setFont(new Font("宋体", Font.BOLD, 30));
        scoreLabel.setBounds(10,10,400,50);
        scoreLabel.setForeground(Color.WHITE);
        contentPane.add(scoreLabel);
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {

            }
        }
    }

    //游戏结束
    public void check() {
        if(leftMove(0)==0 && rightMove(0)==0 && upMove(0)==0 && downMove(0)==0 ) {
            System.exit(0);
        }
    }


    //按钮监听
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand=="开始"){
            
        }else if(actionCommand=="暂停"){

        }else if(actionCommand=="结束"){

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下监听
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下键的KeyCode值
        int keyCode = e.getKeyCode();

        //对所按的键进行判断
        //左,上，右，下
        if (keyCode == KeyEvent.VK_LEFT) {
            leftMove(1);
        } else if (keyCode == KeyEvent.VK_UP) {
            upMove(1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightMove(1);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            downMove(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    //左移和判断是否可移动
    private int leftMove(int choice) {
        int flag=0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = arr[row].length - 1; col > 0; col--) {
                if (arr[row][col] == arr[row][col - 1]||arr[row][col-1]==0) {
                    if (choice!=0) {
                        score += arr[row][col - 1];
                        arr[row][col - 1] += arr[row][col];
                        arr[row][col] = 0;
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //上移和判断是否可移动
    private int upMove(int choice) {
        int flag=0;
        int colLength = arr[0].length;
        for (int col = 0; col < colLength; col++) {
            for (int row = arr.length - 1; row > 0; row--) {
                if (arr[row][col] == arr[row - 1][col]||arr[row - 1][col]==0) {
                    if (choice!=0) {
                        score += arr[row - 1][col];
                        arr[row - 1][col] += arr[row][col];
                        arr[row][col] = 0;
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //右移和判断是否可移动
    private int rightMove(int choice) {
        int flag=0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length - 1; col++) {
                if (arr[row][col] == arr[row][col + 1]||arr[row][col + 1]==0) {
                    if (choice!=0) {
                        score += arr[row][col + 1];
                        arr[row][col + 1] +=arr[row][col];
                        arr[row][col] = 0;
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //下移和判断是否可移动
    private int downMove(int choice) {
        int flag=0;
        int colLength = arr[0].length;
        for (int col = arr[0].length - 1; col > 0; col--) {
            for (int row = 0; row < arr.length - 1; row++) {
                if (arr[row][col] == arr[row + 1][col]||arr[row+1][col]==0) {
                    if (choice!=0) {
                        score += arr[row + 1][col];
                        arr[row + 1][col] += arr[row][col];
                        arr[row][col] = 0;
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    /*//退出游戏
    void savePoint() {
        //将最高分储存在BEST.txt
        try {

            FileWriter fileWritter = new FileWriter(file.getName());
            fileWritter.write(bestscore + "");
            fileWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

