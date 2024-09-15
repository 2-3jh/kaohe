package com.kaohe;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game2048 {


    public static void main(String[] args) {



        // 游戏面板模块
        int [][]arr=new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //图片注意对应，图片指
                JLabel img=new JLabel(new ImageIcon("D:\\code\\keshe\\IMG\\icon\\icon-"+arr[i][j]+".png"));
                p3.add(img);
                img.setBounds(5+j*105, 5+i*105, 100, 100);
            }
        }
        //游戏失败
        public boolean checkLeft() {//判断是否可以向左移动
            boolean flag=true;
            copy(arr,beifen);

            //左移动
            MoveLeft(0);
            //判断是否相同
            flag=notEquals(arr,beifen);//若不相同则返回true
            //System.out.println(flag);
            copy(beifen,arr);//恢复原来的数组
            return flag;
        }
//重新开始
        class endAction implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String s=e.getActionCommand();//获取命令
                switch (s) {
                    case "重新开始":{
                        loseflat=0;//更新失败标志
                        score=0;
                        view();
                        diamonds();
                        p3.remove(losePanel);
                        //losePanel.removeAll();
                        p3.repaint();
                        frame.this.requestFocus();

                    }
                    break;

                    default:
                        savePoint();
                        System.exit(0);
                        break;
                }

            }

        }
//退出游戏
        void savePoint() {
            //将最高分储存在BEST.txt
            try{

                FileWriter fileWritter = new FileWriter(file.getName());
                fileWritter.write(bestscore+"");
                fileWritter.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}



//


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

