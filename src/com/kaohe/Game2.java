package com.kaohe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

public class Game2 extends JFrame implements ActionListener, KeyListener {
    //菜单
    JMenu gameMenu = new JMenu("游戏");
    JMenuItem endItem = new JMenuItem("结束");
    JMenuItem pauseItem = new JMenuItem("暂停");
    JMenuItem beginItem = new JMenuItem("开始");
    JMenu settingsMenu = new JMenu("设置");
    JMenuItem keepFileItem = new JMenuItem("存档");
    JMenuItem loadItem = new JMenuItem("取档");
    JMenuItem restartItem =new JMenuItem("重新开始");

    //获得内容面板
    Container contentPane = getContentPane();

    //记录暂停
    boolean pause = false;

    //记录界面数据的数组
    private int[][] arr = new int[4][4];
    //总分
    private int score;

    //构造方法
    public Game2() {
        //初始化界面
        initFrame();

        //初始化按钮
        initMenu();

        //绘制游戏界面
        draw();

        //随机生成
        addRandomTile();

        //设置为可见
        this.setVisible(true);
    }

    //初始菜单
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        gameMenu.add(beginItem);
        gameMenu.add(pauseItem);
        gameMenu.add(endItem);
        gameMenu.add(restartItem);
        menuBar.add(gameMenu);
        settingsMenu.add(keepFileItem);
        settingsMenu.add(loadItem);
        restartItem.addActionListener(this);
        beginItem.addActionListener(this);
        pauseItem.addActionListener(this);
        endItem.addActionListener(this);
        keepFileItem.addActionListener(this);
        loadItem.addActionListener(this);
        menuBar.add(settingsMenu);
        this.setJMenuBar(menuBar);
    }

    //初始化界面
    private void initFrame() {
        this.setSize(425, 600);
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.addKeyListener(this);
        contentPane.setBackground(new Color(77, 57, 14));
    }

    // 游戏面板模块
    private void draw() {
        contentPane.removeAll();

        JLabel scoreLabel = new JLabel("得分：" + score);
        scoreLabel.setFont(new Font("宋体", Font.BOLD, 30));
        scoreLabel.setBounds(10, 10, 400, 50);
        scoreLabel.setForeground(Color.WHITE);
        contentPane.add(scoreLabel);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JLabel img = new JLabel(new ImageIcon("image/" + arr[row][col] + ".png"));
                img.setBounds(10 + col * 100, 90 + row * 100, 90, 90);
                contentPane.add(img);
            }
        }

        JLabel img = new JLabel(new ImageIcon("image/background.png"));
        img.setBounds(0, 80, 410, 410);
        contentPane.add(img);

        contentPane.repaint();
    }

    //游戏结束
    public void check() {
        if (leftMove(0) == 0 && rightMove(0) == 0 && upMove(0) == 0 && downMove(0) == 0) {



            contentPane.removeAll();

            JLabel scoreLabel = new JLabel("得分：" + score+" 游戏结束 ");
            scoreLabel.setFont(new Font("宋体", Font.BOLD, 30));
            scoreLabel.setBounds(10, 10, 400, 50);
            scoreLabel.setForeground(Color.WHITE);
            contentPane.add(scoreLabel);

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    JLabel img = new JLabel(new ImageIcon("image/" + arr[row][col] + ".png"));
                    img.setBounds(10 + col * 100, 90 + row * 100, 90, 90);
                    contentPane.add(img);
                }
            }

            JLabel img = new JLabel(new ImageIcon("image/background.png"));
            img.setBounds(0, 80, 410, 410);
            contentPane.add(img);

            contentPane.repaint();

        }
    }

    // 随机生成 2 或 4 的方块
    private void addRandomTile() {
        // 找到所有空白位置
        int emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    emptyCount++;
                }
            }
        }

        // 如果没有空白位置，返回
        if (emptyCount == 0) {
            return;
        }

        // 随机选择一个空白位置
        Random r = new Random();
        int index = r.nextInt(16);
        int count = r.nextInt();
        while (true) {
            if (arr[index / 4][index % 4] == 0) {
                if (count % 2 == 0) {
                    arr[index / 4][index % 4] = 2;
                } else {
                    arr[index / 4][index % 4] = 4;
                }
                draw();
                //判断是否能移动
                check();
                return;
            }
            index++;
            index %= 16;
        }
        /*int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    if (count == index) {
                        // 随机生成 2 或 4
                        arr[i][j] = (random.nextInt(2) + 1) * 2;
                        view(); // 更新界面
                        return;
                    }
                    count++;
                }
            }
        }*/
    }


    //按钮监听
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("开始")) {
            pause = false;
        } else if (actionCommand.equals("暂停")) {
            pause = true;
        } else if (actionCommand.equals("结束")) {
            System.exit(0);
        } else if (actionCommand.equals("存档")) {
            keepFile();
        } else if (actionCommand.equals("取档")) {
            loadFile();
        }else if(actionCommand.equals("重新开始")){
            for (int row = 0; row < arr.length; row++) {
                for (int col = 0; col < arr[row].length; col++) {
                    arr[row][col]=0;
                }
            }
            score=0;
            addRandomTile();
        }
    }

    private void loadFile() {
        File file = new File("src/com/kaohe/file.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int index=0;
            score=Integer.parseInt(bufferedReader.readLine());
            while(true){
                String str=bufferedReader.readLine();
                if(str==null){
                    break;
                }
                arr[index/4][index%4]=Integer.parseInt(str);
                index++;
            }
            bufferedReader.close();
            draw();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void keepFile()  {
        try {
            FileWriter fileWrite = new FileWriter( new File("src/com/kaohe/file.txt"));
            fileWrite.write(score+"\n");
            for (int row = 0; row < arr.length; row++) {
                for (int col = 0; col < arr[row].length; col++) {
                    fileWrite.write(arr[row][col]+"\n");
                }
            }
            fileWrite.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下监听
    @Override
    public void keyPressed(KeyEvent e) {
        if (pause) {
            return;
        }

        //获取按下键的KeyCode值
        int keyCode = e.getKeyCode();

        //对所按的键进行判断
        //左,上，右，下
        if (keyCode == KeyEvent.VK_LEFT) {
            leftMove(1);
            addRandomTile();
        } else if (keyCode == KeyEvent.VK_UP) {
            upMove(1);
            addRandomTile();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightMove(1);
            addRandomTile();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            downMove(1);
            addRandomTile();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    //左移和判断是否可移动
    private int leftMove(int choice) {
        int flag = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length - 1; col++) {
                if (arr[row][col] == arr[row][col + 1] || arr[row][col] == 0) {
                    if (choice != 0) {
                        int col1 = col;
                        while (col1 > -1 && (arr[row][col1] == arr[row][col1 + 1] || arr[row][col1] == 0)) {
                            score += arr[row][col1];
                            arr[row][col1] += arr[row][col1 + 1];
                            arr[row][col1 + 1] = 0;
                            col1--;
                        }
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //上移和判断是否可移动
    private int upMove(int choice) {
        int flag = 0;
        int colLength = arr[0].length;
        for (int col = 0; col < colLength; col++) {
            for (int row = 0; row < arr.length - 1; row++) {
                if (arr[row][col] == arr[row + 1][col] || arr[row][col] == 0) {
                    if (choice != 0) {
                        int row1 = row;
                        while (row1 > -1 && (arr[row1][col] == arr[row1 + 1][col] || arr[row1][col] == 0)) {
                            score += arr[row1][col];
                            arr[row1][col] += arr[row1 + 1][col];
                            arr[row1 + 1][col] = 0;
                            row1--;
                        }
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //右移和判断是否可移动
    private int rightMove(int choice) {
        int flag = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = arr[row].length - 1; col > 0; col--) {
                if (arr[row][col] == arr[row][col - 1] || arr[row][col] == 0) {
                    if (choice != 0) {
                        int col1 = col;
                        while (col1 <= 3 && (arr[row][col1] == arr[row][col1 - 1] || arr[row][col1] == 0)) {
                            score += arr[row][col1];
                            arr[row][col1] += arr[row][col1 - 1];
                            arr[row][col1 - 1] = 0;
                            col1++;
                        }
                    }
                    flag++;
                }
            }
        }
        return flag;
    }

    //下移和判断是否可移动
    private int downMove(int choice) {
        int flag = 0;
        for (int col = arr[0].length - 1; col > -1; col--) {
            for (int row = arr.length - 1; row > 0; row--) {
                if (arr[row][col] == arr[row - 1][col] || arr[row][col] == 0) {
                    if (choice != 0) {
                        int row1 = row;
                        while (row1 <= 3 && (arr[row1][col] == arr[row1 - 1][col] || arr[row1][col] == 0)) {
                            score += arr[row1][col];
                            arr[row1][col] += arr[row1 - 1][col];
                            arr[row1 - 1][col] = 0;
                            row1++;
                        }
                    }
                    flag++;
                }
            }
        }
        return flag;
    }
}

