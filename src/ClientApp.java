import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientApp {
    static Map<Integer, List<String>> stacks = new HashMap<>();
    static int stack = 1;
    static String filename = "";
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2");
        System.out.println("(1) View unique list of Pokemon in the selected task");
        System.out.println("(2) Find next 5 stars Pokemon occurence");
        System.out.println("(3) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(4) Print distinct Pokemon and cards count");
        System.out.println("(q) to exit the program");

        String input = "";
        filename = args[0];
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
                List<String> temp = new ArrayList<>();
                temp.add(pokeStack);
                stacks.put(stack, temp);
                stack++;
                System.out.println(stacks);
                    break;
                
                case "1":
                    System.out.println("Display the list of unique Pokemon in stack (1-8) >");
                    int selection = Integer.parseInt(cons.readLine());
                    String[] pokemons = stacks.get(selection).get(0).split(",");

                    Map<String, Integer> unique = new HashMap<>();
                    int counter = 1;
                    for (String pokemon:pokemons){
                        pokemon = pokemon.trim();
                        if(unique.get(pokemon) == null){
                        unique.put(pokemon, counter);
                        counter++;
                        }
                    }
                    Map<String,Integer>  sortedMap =  unique.entrySet().
                                                stream().
                                                sorted(Map.Entry.comparingByValue()).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                    sortedMap.forEach((k,v) -> System.out.printf("%d ==> %s\n", v,k));
                    break;
                case "2":
                    System.out.println("Search for the next occurence of 5 stars Pokemon in all stacks based on entered Pokemon >");
                    input = cons.readLine();
                    printNext5StarsPokemon(input);
                    break;
                case "q":
                    System.out.println("Thank you for using the program... \nHope to see you soon...");
                    break;
                default:
                System.out.println("You did not enter a number between 1 and 4 or q");
                    break;

            }

        }
    }


    public void uniqueList(){
       
    }
    public static void printNext5StarsPokemon(String pokemon){
        String line = "";

        List<String> temp = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            while((line = br.readLine())!=null){
                temp.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        pokemon.replace(" ", ",");
        
        for (int i=0; i<temp.size(); i++){
            String[] temp1 = temp.get(i).split(" ");
            System.out.printf("Set %d\n",i+1);
            for(String s:temp1){
                if (s.equals(pokemon)){
                    String[] temp2 = Arrays.copyOfRange(temp1, Arrays.asList(temp1).indexOf(s) + 1, temp1.length);
                    for (String s1:temp2){
                        if(s1.substring(0,2).equals("5*")){
                            System.out.printf("%s>>>%d cards to go\n", s1, Arrays.asList(temp2).indexOf(s1));
                        }
                        else{
                            System.out.println("No 5 stars Pokemon found subsequently in the stack");
                        }
                    }
                }
                else{
                    System.out.printf("%s not found in this set\n", pokemon);
                }
            }
        }
    }

    public void newStack(){

    }
    public void distinctAndCount(){
       
    }
    public void exit(){
        
    }

}
