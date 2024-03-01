import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientApp {
    Map<Integer, List<String>> stack = new HashMap<>();
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2");
        System.out.println("(1) View unique list of Pokemon in the selected task");
        System.out.println("(2) Find next 5 stars Pokemon occurence");
        System.out.println("(3) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(4) Print distinct Pokemon and cards count");
        System.out.println("(q) to exit the program");

        String input = "";
        while(!input.equals("q")){
            Console cons = System.console();
            input = cons.readLine("Enter your selection >");
            switch (input) {
                case "3":
                System.out.println("Create a new Pokemon stack and save to a new file >");
                String pokeStack = cons.readLine();
                System.out.println("Enter filename to save (e.g path/filename.csv) >");
                input = cons.readLine();
                FileService.WriteAsCSV(input, pokeStack);
                    break;
                
                case "1":

                    break;
                default:
                System.out.println("You did not enter a number between 1 and 4 or q");
                    break;

            }

        }
    }


    public void uniqueList(){
       
    }
    public void next5Stars(){
        
    }
    public void newStack(){

    }
    public void distinctAndCount(){
       
    }
    public void exit(){
        
    }

}
