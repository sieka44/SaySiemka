package saysiemka.language;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.Polish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class PolishLanguageController implements LanguageController {
    private static final Language language = new Polish();
    private JLanguageTool languageTool;
    public PolishLanguageController(){
        try {
            languageTool = new JLanguageTool(language);
        } catch (IOException e) {
            System.out.println(language.getName() + " dictionary is missing.");
            e.printStackTrace();
        }
    }


    @Override
    public String getLanguage() {
        return language.getName();
    }

    @Override
    public List<RuleMatch> checkGrammar(String input) {
        List<RuleMatch> ruleMatches = null;
        try {
            ruleMatches = languageTool.check(input);
            return ruleMatches;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleMatches;
    }
}
