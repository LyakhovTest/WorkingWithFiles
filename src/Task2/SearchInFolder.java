package Task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class SearchInFolder {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в DiskAnalyzer!");
        mainChoize();
        //"C:\\ESD"
    }

    private static void mainChoize() {
        System.out.println("Введите путь каталога с которым хотите работать:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        if(Files.exists(Paths.get(path))){
            choiseFunction(scanner, path);
        }else{
            System.out.println("!!!Вы ввели неверный путь! Попробуйте ещё раз!!!");
            mainChoize();
        }
    }

    private static void choiseFunction(Scanner scanner,String path) {
        System.out.println("Введите номер функции которую хотите применить:");
        System.out.println("   1 - Найти имя файла с максимальным количеством букв ‘s’ в имени.");
        System.out.println("   2 - Печать Топ-5 самых больших файлов (по размеру в байтах).");
        System.out.println("   3 - Средний размер файла в указанном каталоге или любом его подкаталоге.");
        System.out.println("   4 - Количество файлов и папок, разделенное по первым буквам алфавита.");
        int functionNumber = Integer.parseInt(scanner.nextLine());
        if(functionNumber>0&&functionNumber<5){
            String finishLetter = "Файл в который были занесены результаты : \n"+ "C:\\Users\\Igorj\\Desktop\\Стажер\\" +
                    "WorkingWithFiles\\src\\task2.txt";
            switch (functionNumber){
                case (1):
                    maximumNumberOfLettersS(path);
                    System.out.println(finishLetter);
                    break;
                case (2):
                    writeTopFiveBiggestFiles(path);
                    System.out.println(finishLetter);
                    break;
                case (3):
                    writeAverageSizeOfFiles(path);
                    System.out.println(finishLetter);
                    break;
                case (4):
                    writenNumberOfFileAndFoldersWithSameFirstLetter(path);
                    System.out.println(finishLetter);
                    break;
                default:
                    System.out.println();
            }
        }else {
            System.out.println("!!!Функции под таким номер не существует! Введите номер от 1 до 4!!!");
            choiseFunction(scanner, path);
        }
    }

    private static void writenNumberOfFileAndFoldersWithSameFirstLetter(String path) {
        try {
            Files.walk(Paths.get(path))
                    .collect(Collectors.groupingBy(e->e.toFile().getName().substring(0, 1).toLowerCase(Locale.ROOT), Collectors.counting()))
                    .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByKey())
                    .forEach(System.out::println);
            //Files.walk(Paths.get(path)).collect(Collectors.groupingBy((p) -> p.toFile().getName().substring(0, 1), )).
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeAverageSizeOfFiles(String path) {
        try(FileWriter writer = new FileWriter("C:\\Users\\Igorj\\Desktop\\Стажер\\ " +
                "WorkingWithFiles\\src\\task2.txt")) {
           OptionalDouble average = Files.walk(Paths.get(path)).filter(Files::isRegularFile)
                   .mapToLong(e->e.toFile().length()).average();
           if(average.isPresent()){
               writer.write("Средний размер файла в данном каталоге составляет: "+average.getAsDouble());
           }else
           {
               writer.write("В данном каталоге нет файлов!");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTopFiveBiggestFiles(String path) {
        try(FileWriter writer = new FileWriter("C:\\Users\\Igorj\\Desktop\\Стажер\\ " +
                "WorkingWithFiles\\src\\task2.txt"))
        {
            Files.walk(Paths.get(path)).filter(Files::isRegularFile)
                    .collect(Collectors.toMap(e->e.toFile().getAbsolutePath(), e->e.toFile().length()))
                    .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5)
                    .forEach(e-> {
                        try {
                            writer.write("Файл с названием "+e.getKey()+" имеет размер "+e.getValue()+"байт!\n");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void maximumNumberOfLettersS(String path){
        try(FileWriter writer = new FileWriter("C:\\Users\\Igorj\\Desktop\\Стажер\\ " +
                "WorkingWithFiles\\src\\task2.txt"))
        {
            // запись всей строки
            File fileWithMaxS = getMaxSFile(new File(path), 0);
            if(fileWithMaxS==null){
                System.out.println("Нет файлов с буквой S");
            }
            //System.out.println(fileWithMaxS.getAbsolutePath());
            String text = "Больше всего символов S в названиях" +
                    " файлов расположены в файле: "+fileWithMaxS.getAbsolutePath();
            writer.write(text);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static File getMaxSFile(File folder, int maxS)
    {
        File maxSFile = null;
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                maxSFile= getMaxSFile(entry, maxS);
                if(maxSFile==null){continue;}
                maxS = howManySInWord(maxSFile.getName());
                continue;
            }
            int countS=howManySInWord(entry.getName());
            if(countS>maxS){
                maxS=countS;
                maxSFile=entry;
            }
        }
        return maxSFile;
    }

    public static int howManySInWord(String fileName){
        int countS=0;
        for(char letter:fileName.toCharArray()){
            if(letter=='s'||letter=='S'){
                countS++;
            }
        }
        return countS;
    }
}
