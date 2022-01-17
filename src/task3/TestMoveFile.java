package task3;

import org.junit.Test;

import java.io.IOException;

public class TestMoveFile {
    @Test
    public void moveAllOptionWithNIO(){
        moveWithNIO("1 Кб", "C:\\ESD\\kb.txt");
        moveWithNIO("100 kb", "C:\\ESD\\book_manager.sql");
        moveWithNIO("10 Mb", "C:\\ESD\\qw.txt");
    }

    public void moveWithNIO(String sizeFile, String filePath){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                MoveFile.moveFileWitNIO(filePath, "C:\\ESD\\qwe\\aa.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");

    }
    @Test
    public void moveAllOptionWithChannel(){
        moveWithFileChannel("1 Кб", "C:\\ESD\\kb.txt");
        moveWithFileChannel("100 kb", "C:\\ESD\\book_manager.sql");
        moveWithFileChannel("10 Mb", "C:\\ESD\\qw.txt");
    }

    public void moveWithFileChannel(String sizeFile, String filePath){
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                MoveFile.moveFileUsingChannel(filePath, "C:\\ESD\\qwe\\aa.txt");
            }
            long finish = System.currentTimeMillis();
            long elapsed = finish - start;
            System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void moveAllOptionWithOUTBuffer(){
        moveFileWitOUTBuffer("1 Кб", "C:\\ESD\\kb.txt");
        moveFileWitOUTBuffer("100 kb", "C:\\ESD\\book_manager.sql");
        moveFileWitOUTBuffer("10 Mb", "C:\\ESD\\qw.txt");//Не дождался
    }


    public void moveFileWitOUTBuffer(String sizeFile, String filePath){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            MoveFile.moveWithOUTBuffer(filePath, "C:\\ESD\\qwe\\aa.txt");
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
    }

    @Test
    public void moveAllOptionWithBuffer(){
        moveFileWithBuffer("1 kb", "C:\\ESD\\kb.txt");
        moveFileWithBuffer("100 kb", "C:\\ESD\\book_manager.sql");
        moveFileWithBuffer("10 Mb", "C:\\ESD\\qw.txt");

    }

    public void moveFileWithBuffer(String sizeFile, String filePath){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            MoveFile.moveWithBuffer(filePath, "C:\\ESD\\qwe\\aa.txt");
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Тест с файлом размером в "+sizeFile+" занимает времени, мс: " + elapsed+"!\n");
    }
}
