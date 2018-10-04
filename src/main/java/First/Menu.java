package First;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private String help;
    private HashMap<String, Feature> commands = new HashMap<>();


    public Menu(ArrayList<Feature> features){
        initHelp(features);
        initCommands(features);
    }

    private void initCommands(ArrayList<Feature> features){
        for (var feature : features) {
            commands.put(feature.getCommand(),feature);
        }
    }

    private void initHelp(ArrayList<Feature> features){
        var helpBuilder = new StringBuilder();
        for (var feature : features) {
            helpBuilder.append(feature.getNameFeature());
            helpBuilder.append(" ---> ");
            helpBuilder.append(feature.getCommand());
            helpBuilder.append("\n");
        }
        helpBuilder.append("Получить помощь по боту --> помощь\n");
        help = helpBuilder.toString();
    }

    public String getHelp(){
        return help;
    }

    public boolean commandAvailable(String command){
        return commands.containsKey(command) || command.equalsIgnoreCase("помощь");
    }

    public String useCommand(String command, User user){
        if (command.equalsIgnoreCase("помощь")){
            return getHelp();
        }
        return commands.get(command).use(user);
    }
}
