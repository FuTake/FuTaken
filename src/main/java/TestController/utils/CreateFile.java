package TestController.utils;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author zhg
 * @date 2023/9/6
 */
public class CreateFile {

    public static void main(String[] args) {
        String filePath = "C:/Users/a1557/Desktop/test.txt";
        long fileSizeInBytes = 310L * 1024L * 1024L; // 要创建的文件大小，300M转换为字节

        try {
            File file = new File(filePath);

            // 使用RandomAccessFile类来创建文件，并设置文件大小
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.setLength(fileSizeInBytes);
            raf.close();

            System.out.println("文件创建成功!");
        } catch (Exception e) {
            System.out.println("文件创建失败: " + e.getMessage());
        }
    }
}
