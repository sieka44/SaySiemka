package saysiemka.language;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LanguageTest {
    @Test
    public void availabilityTest() throws IOException {
        List<Language> realLanguages = Language.getAllLanguages();
        System.out.println("This example will test a short string with all languages known to LanguageTool.");
        System.out.println("It's just a test to make sure there's at least no crash.");
        System.out.println("Using LanguageTool " + JLanguageTool.VERSION + " (" + JLanguageTool.BUILD_DATE + ")");
        System.out.println("Supported languages: " + realLanguages.size());
        for (Language language : realLanguages) {
            JLanguageTool langTool = new JLanguageTool(language);
            String input = "And the the";
            List<RuleMatch> result = langTool.check(input);
            System.out.println("Checking '" + input + "' with " + language + ":");
            for (RuleMatch ruleMatch : result) {
                System.out.println("    " + ruleMatch);
            }
        }
    }

    @Test
    public void gettingPolishSuggestionsTest() throws IOException {
        Language language = Language.getLanguageForName("Polish");
        System.out.println("Test for Polish language:");
        JLanguageTool languageTool = new JLanguageTool(language);
        String input = "konstfruktor";
        List<RuleMatch> result = languageTool.check(input);
        System.out.println("Sprawdzanie: " + input);
        for (RuleMatch ruleMatch : result) {
            System.out.println("-" + ruleMatch + "\n*" + ruleMatch.getSuggestedReplacements());
        }
    }

    @Test
    public void gettingEnglishSuggestionTest() throws IOException {
        Language language = new BritishEnglish();
        System.out.println("Test for English language:");
        JLanguageTool languageTool = new JLanguageTool(language);
        String input = "Testig it";
        List<RuleMatch> result = languageTool.check(input);
        System.out.println("Checking: " + input);
        for (RuleMatch ruleMatch : result) {
            System.out.println("-" + ruleMatch + "\n*" + ruleMatch.getSuggestedReplacements());
        }
    }

    @Test
    public void getShortMessage() throws IOException {
        Language language = new BritishEnglish();
        System.out.println("Test for English language:");
        JLanguageTool languageTool = new JLanguageTool(language);
        String input = "testig it";
        List<RuleMatch> result = languageTool.check(input);
        System.out.println("Checking: " + input);
        for (RuleMatch ruleMatch : result) {
            System.out.println("-" + ruleMatch.getShortMessage() + "\n*" + ruleMatch.getSuggestedReplacements());
        }
    }
}
