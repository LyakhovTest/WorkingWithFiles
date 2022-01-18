package task3;

import org.junit.Test;

import java.io.*;

public class TestMoveFile {
    File kb1 = getFile1kb();
    File kb100 = getFile100kb();
    File mb10 = getFile10mb();

    @Test
    public void moveAllOptionWithNIO(){
        universalTestForMoveFile("1 Кб", kb1,1);
        universalTestForMoveFile("100 kb", kb100,1);
        universalTestForMoveFile("10 Mb", mb10,1);
    }

    @Test
    public void moveAllOptionWithChannel(){
        universalTestForMoveFile("1 Кб", kb1, 2);
        universalTestForMoveFile("100 kb", kb100,2);
        universalTestForMoveFile("10 Mb", mb10, 2);
    }

    @Test
    public void moveAllOptionWithOUTBuffer(){
        universalTestForMoveFile("1 Кб", kb1, 3);
        universalTestForMoveFile("100 kb", kb100,3);
       // universalTestForMoveFile("10 Mb", mb10, 3);//Не дождался
    }

    @Test
    public void moveAllOptionWithBuffer(){
        universalTestForMoveFile("1 kb", kb1, 4);
        universalTestForMoveFile("100 kb", kb100, 4);
        universalTestForMoveFile("10 Mb", mb10, 4);
    }

    public void universalTestForMoveFile(String sizeFile, File sourceFile, int numberOfFunction){
        File dest = new File("C:\\ESD\\qwe\\aa.txt");
        long generalTime = 0;
        String functionName = "";
        for (int i = 0; i < 10; i++) {
            try {
                long start = System.currentTimeMillis();
                switch (numberOfFunction) {
                    case 1:
                        MoveFile.moveFileWitNIO(sourceFile, dest);
                        functionName = "Копирование методом API NIO 2 ";
                        break;
                    case 2:
                        MoveFile.moveFileUsingChannel(sourceFile, dest);
                        functionName = "Копирование методом FileChannel ";
                        break;
                    case 3:
                        MoveFile.moveWithFileStreamNoBuffer(sourceFile, dest);
                        functionName = "Копирование методом FileStream без буфера ";
                        break;
                    case 4:
                        MoveFile.moveWithBuffer(sourceFile, dest);
                        functionName = "Копирование методом FileStream с буфером ";
                        break;
                }
                long finish = System.currentTimeMillis();
                long elapsed = finish - start;
                generalTime+=elapsed;
                dest.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileWriter writer = new FileWriter("Report.txt", true)) {
            writer.write(functionName+"с файлом размером в "+sizeFile+" занимает времени, мс: " + generalTime/10+"!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(generalTime);
        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + generalTime/1000+"!\n");
    }

    private File getFile10mb() {
        File dest = new File("C:\\ESD\\mb10.txt");
        RandomAccessFile f;
        try {
            f = new RandomAccessFile(dest, "rw");
            f.setLength(1024*1024*10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    private File getFile100kb() {
        File dest = new File("C:\\ESD\\kb100.txt");
        RandomAccessFile f;
        try {
            f = new RandomAccessFile(dest, "rw");
            f.setLength(1024*100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    private File getFile1kb() {
        File dest = new File("C:\\ESD\\kb1.txt");
        RandomAccessFile f;
        try {
            f = new RandomAccessFile(dest, "rw");
            f.setLength(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }



//    public void moveWithFileChannel(String sizeFile, String filePath){
//        try {
//            long start = System.currentTimeMillis();
//            for (int i = 0; i < 1000; i++) {
//                MoveFile.moveFileUsingChannel(filePath, "C:\\ESD\\qwe\\aa.txt");
//            }
//            long finish = System.currentTimeMillis();
//            long elapsed = finish - start;
//            System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




//    public void moveFileWitOUTBuffer(String sizeFile, String filePath){
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            MoveFile.moveWithFileStreamNoBuffer(filePath, "C:\\ESD\\qwe\\aa.txt");
//        }
//        long finish = System.currentTimeMillis();
//        long elapsed = finish - start;
//        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
//    }



//    public void moveFileWithBuffer(String sizeFile, String filePath){
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            MoveFile.moveWithBuffer(filePath, "C:\\ESD\\qwe\\aa.txt");
//        }
//        long finish = System.currentTimeMillis();
//        long elapsed = finish - start;
//        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
//    }
}
