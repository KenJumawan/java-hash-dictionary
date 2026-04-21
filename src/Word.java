package edu.commonwealthu.mrodriguez.cmsc230.hashlab;


public class Word {

    private final String word;
    private final String definition;

    // Build a Word with a word string and one definition.
    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    // Get the word text
    public String getWord() {
        return word;
    }

    // Get the definition text
    public String getDefinition() {
        return definition;
    }

    @Override
    public int hashCode() {
        if (word == null) return 0;
        return word.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Word)) return false;

        Word w = (Word) other;

        if (this.word == null && w.word == null) {
            return true;
        } else if (this.word == null || w.word == null) {
            return false;
        } else {
            return this.word.equals(w.word);
        }
    }

    @Override
    public String toString() {
        return "[Word=\"" + word + "\" def=\"" + definition + "\"]";
    }
}
