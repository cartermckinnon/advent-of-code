import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class SolutionWithExtension {
    public static void main(String[] args) throws Exception {
        String message = Files.readString(new File(args[0]).toPath());
        int n = charactersUntilUniqueWindowObserved(message, 4);
        System.out.println(n);
        // extension
        n = charactersUntilUniqueWindowObserved(message, 14);
        System.out.println(n);
    }

    public static int charactersUntilUniqueWindowObserved(String s, int windowSize) {
        int n = 0;
        Set<Character> window = new LinkedHashSet<>(windowSize);
        for(; n < s.length() && window.size() < windowSize; n++) {
            char c = s.charAt(n);
            if(!window.add(c)) {
                // this character is already in the window
                // remove all characters in the window up to and including the prior occurrence of this character
                Iterator<Character> it = window.iterator();
                while(it.hasNext() && it.next() != c) {
                    it.remove();
                }
                it.remove();
                // add this character to the updated window
                window.add(c);
            }
        }
        return n;
    }
}
