package com.kaohe;

import javax.swing.*;
import javax.swing.*;

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
