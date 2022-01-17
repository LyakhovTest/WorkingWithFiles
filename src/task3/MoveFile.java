package task3;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MoveFile {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            napolnitelFilov();
        }
    }

    public static void moveFileWitNIO(String sourceStr, String destStr) throws IOException {
        File source = new File(sourceStr);
        File dest = new File(destStr);

        Files.copy(source.toPath(), dest.toPath());
        dest.delete();
    }

    public static void moveFileUsingChannel(String source1, String dest1) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            File source = new File(source1);
            File dest = new File(dest1);
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
            dest.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveWithOUTBuffer(String file, String newDir){

        try {
            File newDirect = new File(newDir);
            FileInputStream fileInputStream = new FileInputStream(file);
            int i;
            FileOutputStream fileOutputStream = new FileOutputStream(newDirect);
            while((i=fileInputStream.read())!= -1){
                fileOutputStream.write((char)i);
            }
            fileInputStream.close();
            fileOutputStream.close();
            newDirect.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void moveWithBuffer(String file, String newDir){
            byte[] buffer = new byte[100000];
            try {
                File oldFile = new File(file);
                File newFile = new File(newDir);
                FileInputStream fis = new FileInputStream(oldFile);
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
                newFile.delete();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }

    public static void napolnitelFilov(){
        try {
            FileWriter f=new FileWriter("C:\\ESD\\book_manager.sql",true);
            f.write("static int i=0;\n" +
                    "    String ways;\n" +
                    "    static int co;\n" +
                    "    public static void main(String args[]) throws IOException{\n" +
                    "        while(i==0){\n" +
                    "        Random r=new Random();\n" +
                    "        co=r.nextInt(5);\n" +
                    "        try {\n" +
                    "            Thread.sleep(1000);\n" +
                    "        } catch (InterruptedException e) {\n" +
                    "            // TODO Auto-generated catch block\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "        if(co==0){\n" +
                    "            System.out.println(\"прямо\");\n" +
                    "        }\n" +
                    "        if(co==1){\n" +
                    "            System.out.println(\"налево\");\n" +
                    "        }\n" +
                    "        if(co==2){\n" +
                    "            System.out.println(\"направо\");\n" +
                    "        }\n" +
                    "    }\n" +
                    "        FileWriter f=new FileWriter(\"directions.txt\");\n" +
                    "        for(int i=0;i<5;i++){\n" +
                    "            f.write(co);\n" +
                    "        }\n" +
                    "        f.close();");
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
