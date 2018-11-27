package First.Features;

import First.BotLogic.User;

import java.util.ArrayList;

public class HelpFeature implements Feature {
    private String help;
    @Override
    public String getCommand() {
        return "помощь";
    }

    @Override
    public String getDescription() {
        return "Получить помощь по боту";
    }

    @Override
    public String use(User user, String command) {
        return help;
    }

    public void initHelp(ArrayList<Feature> features) {
        var helpBuilder = new StringBuilder();
        features.add(this);
        for (var feature : features) {
            helpBuilder.append(feature.getDescription());
            helpBuilder.append(" ---> ");
            helpBuilder.append(feature.getCommand());
            helpBuilder.append("\n");
        }
        help = helpBuilder.toString();
    }
}
