import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static List<String> stacks = new ArrayList<>();

    public static List<String> readCSV(String filepath){
        List<String> results = new ArrayList<>();
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            while((line = br.readLine()) != null){
                results.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("I am here in readCSV");
        }
        return results;
    }

    public static void WriteAsCSV(String filepath, String pokemons){
        String line = "";
        try(BufferedReader bw = new BufferedReader(new FileReader(filepath))){
            while((line = bw.readLine()) != null){
                stacks.add(line);
            }
        }catch(Exception e){
            System.out.println("File does not exist, creating a new file");
        }
        stacks.add(pokemons);
        try(BufferedWriter bw  = new BufferedWriter(new FileWriter(filepath))){
            for(String stack:stacks){
                bw.write(stack);
                bw.newLine();
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("I am here in writeCSV");
        }
    }
}
