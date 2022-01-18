import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestSerialization {
    @Test
    public void writeUser(){
        WriteObject.write();
    }


    @Test
    public void readUser(){
        ReadObject.read();
    }

    @Test
    public void createFile() {
        try {
            File dest = new File("C:\\ESD\\kobaf.txt");
            RandomAccessFile f = new RandomAccessFile(dest, "rw");
            f.setLength(1024*1024);
            f.setLength(1024*1024*1024);
            f.setLength(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
