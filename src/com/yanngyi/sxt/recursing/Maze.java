package com.yanngyi.sxt.recursing;

/**
 * 迷宫问题
 * @author yangyi
 */
public class Maze {
    public static void main(String[] args) {
        //先创建一个二维数组
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i=0; i<7; i++) {
            //第一行与第七行为墙
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i=0; i<8; i++) {
            //第一列与第八行为墙
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[1][3] = 1;
        map[2][3] = 1;
        for (int i=0; i<7; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(map[j][i]+ " ");
            }
            System.out.println();
        }
    }

    /**
     * 说明
     *  1、如果小球能到[6][5]则说明小球到达终点
     *  2、当地图的i，j为[0][0]时，表示路还没走过，1表示墙，2表示路通可以走，3表示路已经走过，但是走不通
     *  3、在走迷宫时，需要确定一个策略（方法），下->右->上->左，如果该点走不通，再回溯。
     * @param map 表示地图
     * @param i 表示起始位置行
     * @param j 表示起始位置列
     * @return 是否找到路
     */
    public static boolean getWay(int[][] map, int i, int j){


        return false;
    }
}
