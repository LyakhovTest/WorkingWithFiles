package task3;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MoveFile {


    public static void moveFileWitNIO(File source, File destStr) throws IOException {
        //File source = new File(sourceStr);
        //File dest = new File(destStr);

        Files.copy(source.toPath(), destStr.toPath());
        //destStr.delete();
    }

    public static void moveFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            //File source = new File(source);
            //File dest = new File(dest1);
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
           // dest.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveWithFileStreamNoBuffer(File source, File newDirect){
        //File newDirect = new File(newDir);
        try {

            FileInputStream fileInputStream = new FileInputStream(source);
            int i;
            FileOutputStream fileOutputStream = new FileOutputStream(newDirect);
            while((i=fileInputStream.read())!= -1){
                fileOutputStream.write((char)i);
            }
            fileInputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void moveWithBuffer(File source, File newFile){
            byte[] buffer = new byte[100000];
            try {//Доделать try with res, обработать удаление
               // File source = new File(source);
                //File newFile = new File(newDir);
                FileInputStream fis = new FileInputStream(source);
                BufferedInputStream bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream(newFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                int numBytes;
                while ((numBytes = bis.read(buffer))!= -1)
                {
                    bos.write(buffer,0,numBytes);
                }
                //bos.flush();
                //bos.write("\u001a");

                //System.out.println(" ---- "+oldFile+ " is successfully copied to "+newFile);

                bis.close();
                bos.close();
               // newFile.delete();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
