import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        try{
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
                   
                    input = cons.readLine("Press any key to continue").toUpperCase();
                        break;
                    
                    case "1":
                        System.out.println("Display the list of unique Pokemon in stack (1-8) >");
                        int selection = Integer.parseInt(cons.readLine());
                        try{
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
                        }catch(Exception e){
                            System.out.println("Error, not a value from 1-8");
                            input = cons.readLine("Press any key to continue").toUpperCase();

                        }
                        break;

                    case "2":
                        System.out.println("Search for the next occurence of 5 stars Pokemon in all stacks based on entered Pokemon >");
                        input = cons.readLine();
                        printNext5StarsPokemon(input);
                        input = cons.readLine("Press any key to continue").toUpperCase();
                        break;

                    case "4":
                        printPokemonCardCount();
                        input = cons.readLine("Press any key to continue").toUpperCase();
                        break;

                    case "q":
                        System.out.println("Thank you for using the program... \nHope to see you soon...");
                        break;

                    default:
                    System.out.println("You did not enter a number between 1 and 4 or q");
                    input = cons.readLine("Press any key to continue").toUpperCase();
                        break;

                }

            }
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public static void printNext5StarsPokemon(String pokemon){
        int con = 0;
        int crit1 = 0;
        int crit2 = 0;
        List<String> temp = new ArrayList<>();
        temp = FileService.readCSV(filename);

        for (int i=0; i<temp.size(); i++){
            
            String[] temp1 = (temp.get(i)).split(",");
            System.out.printf("Set %d\n",i+1);

            for(String s:temp1){
        
                if (s.equals(pokemon)){
                    
                    String[] temp2 = Arrays.copyOfRange(temp1, Arrays.asList(temp1).indexOf(s) + 1, temp1.length);
                    
                    for (String s1:temp2){

                        if(s1.substring(0,2).equals("5*") && crit1!=1){
                            System.out.printf("%s>>>%d cards to go.\n", s1, Arrays.asList(temp2).indexOf(s1));
                            con = 1;
                            crit1 = 1;
                            break;
                        }
                        else if(Arrays.asList(temp2).indexOf(s1) == temp2.length-1 && crit2!=1) {
                            System.out.println("No 5 stars Pokemon found subsequently in the stack");
                            con = 1;
                            crit2 = 1;
                            break;
                        }
                    
                    }
                }
            }if(con == 0){
                System.out.printf("%s not found in this set\n", pokemon);
            }
        }
    }

    public static void printPokemonCardCount(){
        String line = "";
        Map<String, Integer> freq = new HashMap<>();
        List<String> temp = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            while((line = br.readLine())!=null){
                String[] all = line.split(",");
                for(String pokemon:all){
                    temp.add(pokemon);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        for (int i=0; i<temp.size(); i++){
            int counter = 0;
            for (int j=0; j<temp.size(); j++){
                if(temp.get(i).equals(temp.get(j))){
                    counter++;
                    
                }
            }
            freq.put(temp.get(i), counter);
        }
        Map<String,Integer>  sortedMap =  freq.entrySet().
                                                stream().
                                                sorted(Map.Entry.comparingByKey()).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<String,Integer>  sortedMap1 =  sortedMap.entrySet().
                                                stream().limit(10).
                                                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for(int k=0; k<=9; k++){
            System.out.printf("Pokemon %d : %s, Cards Count: %d\n", 
                             k+1, sortedMap1.keySet().toArray()[k], sortedMap1.values().toArray()[k]);
        }
        
    }

}
