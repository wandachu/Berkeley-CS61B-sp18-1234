public class Palindrome {
    /**
     * Given a String, wordToDeque will return a Deque
     * where the characters appear in teh same order.
     * @param word - The word that the Character Deque will be generated upon.
     * @return the Deque of characters in word.
     */
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    /**
     * Returns true if a given word is a palindrome.
     * @param word - word to be tested if it is a palindrome.
     * @return true if the word is a palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> words = wordToDeque(word);
        String reversed = "";
        while (!words.isEmpty()) {
            reversed += words.removeLast();
        }
        return word.equals(reversed);
    }

    /**
     * Returns true if the given word is a palindrome
     * according to the character comparison test.
     * @param word - word to be tested if it is a palindrome.
     * @param cc - a CharacterComparator.
     * @return true if the given word is a palindrome
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> words = wordToDeque(word);
        while (words.size() >= 2) {
            char a = words.removeFirst();
            char b = words.removeLast();
            if (!cc.equalChars(a, b)) {
                return false;
            }
        }
        return true;
    }
}
