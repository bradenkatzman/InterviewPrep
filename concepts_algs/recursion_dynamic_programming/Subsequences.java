public class Subsequences {


	public static String subsequences(String str) {
		return str + subsequencesHelper(str, str.length() - 1);
	}

	public static String subsequencesHelper(String str, int subsequenceLen) {
		// base case is when the subsequenceLen is 0
		if (subsequenceLen <= 0) return ",";

		int substringStart = 0;
		int substringEnd = subsequenceLen;

		String stringBuilder = "";

		while (substringEnd < str.length()) {
			stringBuilder = (stringBuilder + CS + str.substring(substringStart, substringEnd));

			substringStart = ++substringStart;
			substringEnd = ++substringEnd;
		}

		// once substring end exceeds the length of the string
		// there is still another subsequence to generate because
		// the end index in the substring method is not inclusive
		stringBuilder = stringBuilder + CS + str.substring(substringStart);

		return stringBuilder + subsequencesHelper(str, subsequenceLen-1);
	}

	public static String subsequences2(String str) {
		return subsequencesAfter("", str);
	}

	public static String subsequencesAfter(String partialSubsequence, String word) {
		System.out.println("Calling with args: " + partialSubsequence + "," + word);

		// base case
		if (word.isEmpty()) return partialSubsequence;

		return subsequencesAfter(partialSubsequence, word.substring(1))
			+ CS
			+ subsequencesAfter(partialSubsequence + word.charAt(0), word.substring(1));
	}


	public static void main(String[] args) {
		String str = subsequences2("abc");
		System.out.println(str);
	}

	public static final String CS = ",";

}