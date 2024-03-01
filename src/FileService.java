import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    
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

    public static void WriteAsCSV(String filepath, String[] pokemons){

        try(BufferedWriter br  = new BufferedWriter(new FileWriter(filepath))){
            for(String pokemon:pokemons){
                br.write(pokemon);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("I am here in writeCSV");
        }

    }
}
