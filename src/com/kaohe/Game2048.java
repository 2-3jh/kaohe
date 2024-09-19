/*

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game2048 extends JFrame {

    private int[][] arr = new int[4][4]; // 游戏数组
    private int[][] beifen = new int[4][4]; // 用于备份的数组
    private JPanel p3 = new JPanel(); // 游戏面板
    private JPanel losePanel = new JPanel(); // 游戏失败面板
    private int loseflat = 0; // 失败标志
    private int score = 0; // 当前分数
    private int bestscore = 0; // 最佳分数
    private File file = new File("BEST.txt"); // 存储最佳分数的文件
    public Game2048() {
        // 设置窗口属性
        setTitle("游戏");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 初始化游戏面板
        p3.setLayout(null);
        p3.setBounds(10, 10, 450, 450);
        add(p3);

        // 显示初始界面
        view();

        // 添加键盘监听器
        addKeyListener(new App());

        // 显示窗口
        setVisible(true);
        this.requestFocus();
    }
    //主入口
    public static void main(String[] args) {
        new Total();
    }
    // 显示游戏界面
    private void view() {
        p3.removeAll(); // 清空面板

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel img = new JLabel(new ImageIcon("" + arr[i][j] + ".png"));
                p3.add(img);
                img.setBounds(5 + j * 105, 5 + i * 105, 100, 100);
            }
        }

        p3.repaint();
    }
    // 判断是否可以向左移动
    public boolean checkLeft() {
        boolean flag = true;
        copy(arr, beifen); // 备份当前数组
        leftMove(); // 尝试左移
        flag = !equals(arr, beifen); // 比较备份和移动后的数组
        copy(beifen, arr); // 恢复原数组
        return flag;
    }
    // 复制数组
    private void copy(int[][] src, int[][] dest) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }
    // 判断两个数组是否相等
    private boolean equals(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    // 重新开始的Action处理
    class EndAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s) {
                case "重新开始":
                    loseflat = 0; // 更新失败标志
                    score = 0; // 重置分数
                    view(); // 重新绘制界面
                    p3.remove(losePanel); // 移除失败面板
                    p3.repaint(); // 重绘
                    Total.this.requestFocus(); // 让窗口获得焦点
                    break;
                default:
                    savePoint(); // 保存当前分数
                    System.exit(0); // 退出程序
                    break;
            }
        }
    }
    // 保存最高分
    void savePoint() {
        try {
            FileWriter fileWriter = new FileWriter(file.getName());
            fileWriter.write(bestscore + "");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    if (emptyCount == 0) {
        return; // 如果没有空白位置，返回
    }

    // 随机选择一个空白位置
    int index = random.nextInt(emptyCount);
    int count = 0;
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
    }
}
//键盘事件监听类
class App extends JFrame implements KeyListener {
    //得分
    private int score = 0;

    //记录界面的数组
    private int[][] map = new int[4][4];




    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下监听
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下键的KeyCode值
        int keyCode = e.getKeyCode();
        //哪一种写法更好
*/
/*
        //对所按的键进行判断
        //左,上，右，下
        if (keyCode == 37) {
            System.out.println("左");
            leftMove();
        } else if (keyCode == 38) {
            System.out.println("上");
            upMove();
        } else if (keyCode == 39) {
            System.out.println("右");
            rightMove();
        } else if (keyCode == 40) {
            System.out.println("下");
            downMove();
        }
        *//*

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                leftMove(); // 左移
                break;
            case KeyEvent.VK_UP:
                upMove(); // 上移
                break;
            case KeyEvent.VK_RIGHT:
                rightMove(); // 右移
                break;
            case KeyEvent.VK_DOWN:
                downMove(); // 下移
                break;
        }
    }

    //左移
    private void leftMove() {
        for (int row = 0; row < map.length; row++) {
            for (int col = map[row].length - 1; col > 0; col--) {
                if (map[row][col] == map[row][col - 1]) {
                    map[row][col] = 0;
                    map[row][col - 1] *=2;
                    score += map[row][col - 1];
                }
            }
        }
    }

    //上移
    private void upMove() {
        int colLength= map[0].length;
        for (int col = 0; col < colLength; col++) {
            for (int row = map.length-1; row > 0; row--) {
                if(map[row][col] == map[row - 1][col]) {
                    map[row][col] = 0;
                    map[row - 1][col] *=2;
                    score += map[row - 1][col];
                }
            }
        }
    }

    //右移
    private void rightMove() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col <map[row].length-1; col++) {
                if (map[row][col] == map[row][col + 1]) {
                    map[row][col] = 0;
                    map[row][col + 1] *=2;
                    score += map[row][col + 1];
                }
            }
        }
    }

    //下移
    private void downMove() {
        int colLength= map[0].length;
        for (int col = map[0].length-1; col > 0; col--) {
            for (int row = 0; row < map.length-1; row++) {
                if(map[row][col] == map[row + 1][col]) {
                    map[row][col] = 0;
                    map[row + 1][col] *=2;
                    score += map[row + 1][col];
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

//

*/
