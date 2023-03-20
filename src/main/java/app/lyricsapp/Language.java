package app.lyricsapp;

public class Language {
    private static String languageSelection = "fr";

    public static void setLanguageSelection(String languageSelection) {
        Language.languageSelection = languageSelection;
    }

    public static String getLanguageSelection() {
        return languageSelection;
    }

    public static void changeLanguage(){
        if (getLanguageSelection() == "fr") {
            setLanguageSelection("en");
        }
        else if (getLanguageSelection() == "en") {
            setLanguageSelection("fr");
        }
    }

}
