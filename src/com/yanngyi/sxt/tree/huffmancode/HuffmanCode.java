package com.yanngyi.sxt.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码实现
 * @author yangyi
 */
public class HuffmanCode {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] bytes = huffmanZip(contentBytes);
        byte[] decode = decode(map, bytes);
        System.out.println(new String(decode));*/
        /*byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        Node huffmanTree = createHuffmanTree(nodes);
        preOrder(huffmanTree);


        getCodes(huffmanTree,"",sb);
        for (Map.Entry<Byte, String> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }

        byte[] zip = zip(contentBytes, map);
        System.out.println(Arrays.toString(zip));*/

        //String srcFile = "/Users/yangyi/Pictures/起风了壁纸/002.jpg";
        String destFile = "/Users/yangyi/Downloads/111.zip";
        String file = "/Users/yangyi/Downloads/222.jpg";
        //zipFile(srcFile, destFile);
        unZipFile(destFile, file);
    }

    /**
     * 解压文件的方法
     * @param zipFile 压缩文件
     * @param destFile 解压后的文件
     */
    public static void unZipFile(String zipFile,String destFile) {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        FileOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(zipFile);

            objectInputStream = new ObjectInputStream(fileInputStream);

            byte[] b = (byte[]) objectInputStream.readObject();
            Map<Byte, String> map1 = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] decode = decode(map1, b);

            outputStream = new FileOutputStream(destFile);

            outputStream.write(decode);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //1、先得到HuffmanBytes 对应的二进制的字符串 形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte转换为
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = true;
            if (i == huffmanBytes.length-1) {
                flag = false;
            }
            stringBuilder.append(byteToBitString(flag, huffmanBytes[i]));
        }
        //System.out.println(stringBuilder.toString());

        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询97->100
        Map<String, Byte> map1 = new HashMap<>();
        huffmanCodes.forEach((k,v) -> map1.put(v,k));

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            //小的计数器
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个 '1' '0'
                // i不动 让count移动 直到匹配到一个字符
                String key = stringBuilder.substring(i, count+i);
                b = map1.get(key);
                if (b == null) {
                    count++;
                } else {
                    //匹配到之后跳出循环 继续寻找下一个
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        //当for循环结束后，我们list中就存放了所有的字符"i like like like java do you like a java"
        byte[] b = new byte[list.size()];
        for (int i = 0; i<b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将文件进行压缩
     * @param srcFile
     * @param destFile
     */
    public static void zipFile(String srcFile, String destFile) {
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
             fileInputStream = new FileInputStream(srcFile);
             //创建一个与源文件大小的byte[]
             byte[] b = new byte[fileInputStream.available()];
             //读取文件
             fileInputStream.read(b);
             //直接对文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建一个文件的输出流，存放压缩文件
            outputStream = new FileOutputStream(destFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //把赫夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanBytes);

            //以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意 一定要把赫夫曼编码写入压缩文件
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                fileInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据的解压
    //思路
    //1、将HuffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重写先转成 赫夫曼编码对应的二进制的字符串"1010100010111..."
    //2、赫夫曼编码对应的二进制的字符串 => 对照赫夫曼编码 => "i like like like java do you like a java"

    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的byte
     * @param flag 标志是否需要补高位 如果是最后一个字符 无需补高位
     * @return 是该b对应的二进制的字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        //例b=77(1001101)  temp |=256 位或运算后 temp = 333(101001101)
        int temp = b;
        if (flag) {
            //按位或 256 1 0000 0000 => 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length()-8);
        } else {
            return s;
        }
    }
    /**
     * 使用一个方法，将前面的方法封装起来，便于我们的调用。
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据node创建的赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //对应的赫夫曼编码（根据赫夫曼树）
        getCodes(huffmanTree,"",sb);

        //根据生成的赫夫曼编码 压缩 得到压缩后的赫夫曼编码字节数组
        return zip(bytes, map);
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 思路：
     * 1、将赫夫曼编码表存放在Map<Byte,String> 形式
     *  32->01 97->100 100->11000等等[形式]
     */
    static StringBuilder sb = new StringBuilder();
    static Map<Byte, String> map = new HashMap<>(16);

    /**
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到HuffmanCodes集合
     * @param node 传入节点
     * @param code 路径 左子节点为0 右子节点为1
     * @param sb 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
               //递归处理
                //向左
                getCodes(node.left,"0",sb2);
                //向右
                getCodes(node.right,"1",sb2);
            } else {
                map.put(node.data, sb2.toString());
            }
        }
    }

    /**
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte数组
     * @param bytes 原始的字符串对应的byte数组
     * @param huffmanCodes 赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例：String content = "i like like like java do you like a java"；=> byte[] contentBytes = content.getBytes();
     * => 对应的byte[] huffmanCodes，即8位对应一个byte，放入到huffmanCodeBytes
     * huffmanCodeBytes[0]= 10101000(补码) => byte [推导 10101000 => 10101000 - 1 => 10100111 求反码=> 11011000]
     * huffmanCodeBytes[0]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1、先利用HuffmanCodes 将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println(stringBuilder.toString());
        //将"101010001011111110..."转成 byte[]
        //统计返回 byte[] huffmanCodeBytes
//        int len = (stringBuilder.length() + 7) /8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //c创建一个存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;
        //因为每8位对应一个byte，所以步长为8
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String strByte;
            if (i+8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i,i+8);

            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte,2);
        }
        return huffmanCodeBytes;
     }

    private static void preOrder(Node root) {
        root.preOrder();
    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        //遍历bytes，统计每个byte出现的次数->map[key, value]
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            //取出nodes中最小的二叉树根节点
            Node leftNode = nodes.get(0);
            //取出nodes中次小的二叉树根节点
            Node rightNode = nodes.get(1);

            //创建一棵新的二叉树只有权值 没有data
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的二叉树 从nodes中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新的二叉树加入到nodes
            nodes.add(parent);

        }
        return nodes.get(0);
    }


    static class Node implements Comparable<Node>{
        /**存放数据本身 'a'=>97*/
        Byte data;
        /**权值*/
        int weight;
        Node left;
        Node right;


        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        public void preOrder(){
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

    }
}
