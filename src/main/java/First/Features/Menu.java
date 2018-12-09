package First.Features;

import First.BotLogic.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Menu implements Feature {
    private String help;
    private HashMap<String, Feature> commands = new HashMap<>();
    private String command;
    private String description;
    private HelpFeature helpFeature;

    public Menu(ArrayList<Feature> features, HelpFeature helpFeature){
        this.helpFeature = helpFeature;
        helpFeature.initHelp(features);
        initCommands(features);
    }

    public Menu(ArrayList<Feature> features, HelpFeature helpFeature,
                String command, String description)
    {
        this(features, helpFeature);
        this.command = command;
        this.description = description;
    }

    private void initCommands(ArrayList<Feature> features){
        for (var feature : features) {
            commands.put(feature.getCommand(), feature);
            if (feature instanceof Menu)
                commands.putAll(((Menu) feature).commands);
        }
        commands.put(helpFeature.getCommand(), helpFeature);
    }

    public HashSet<String> getCommands() { return new HashSet<String>(commands.keySet()); }

    public String useCommandFromMenu(String command, User user){
        return commands.get(command).use(user, command);
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String use(User user, String command) {
        return helpFeature.use(user, command);
    }
}
