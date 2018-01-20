package saysiemka.language;

import org.languagetool.rules.RuleMatch;

import java.util.List;

public interface LanguageController {
    String getLanguage();
    List<RuleMatch> checkGrammar(String input);
}
