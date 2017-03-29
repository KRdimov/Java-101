class Anagrams {
	public static void main(String[] args) {
		String a = args[0].toLowerCase();
		String b = args[1].toLowerCase();
		System.out.println(areAnagrams(a, b));
	}

	public static boolean areAnagrams(String a, String b) {
		String word1 = a.toLowerCase();
		String word2 = b.toLowerCase();

		if(word1.length() != word2.length()) {
			return false;
		}

		for(int i=0;i < word1.length();i++) {
			char letter = word1.charAt(i);
			int letterCount = letterOccurances(letter, word1);
			int searchedCount = letterOccurances(letter, word2);
			if(letterCount != searchedCount) {
				return false;
			}
		}
		return true;
	}

	public static int letterOccurances(char letter, String word) {
		int count = 0;
		for(int i=0; i<word.length(); i++) {
			if(word.charAt(i) == letter) {
				count++;
			}
		}
		return count;
	}
}