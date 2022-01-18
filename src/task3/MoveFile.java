package task3;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MoveFile {


    public static void moveFileWitNIO(File source, File destStr) throws IOException {
        Files.copy(source.toPath(), destStr.toPath());
    }

    public static void moveFileUsingChannel(File source, File dest) throws IOException {
        try(FileChannel sourceChannel = new FileInputStream(source).getChannel(); FileChannel destChannel
                = new FileOutputStream(dest).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveWithFileStreamNoBuffer(File source, File newDirect){
        try (FileInputStream fileInputStream = new FileInputStream(source);FileOutputStream fileOutputStream
                = new FileOutputStream(newDirect);){
            int i;
            while((i=fileInputStream.read())!= -1){
                fileOutputStream.write((char)i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveWithBuffer(File source, File newFile){
            byte[] buffer = new byte[100000];
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
                 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));){
                int numBytes;
                while ((numBytes = bis.read(buffer))!= -1)
                {
                    bos.write(buffer,0,numBytes);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
