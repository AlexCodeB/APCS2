
public class Phrase {
	private String currentPhrase;

	public Phrase(String p) {
		currentPhrase = p;
	}

	public int findNthOccurence(String str, int n) {
		int count = 0;
		int len = str.length();
		for(int i = 0; i < currentPhrase.length(); i++) {
			if (len >= currentPhrase.length()) {
				return -1;
			}
			System.out.println("i: " + i);
			System.out.println("len: " + len);
			if(currentPhrase.substring(i, len).equals(str)){
				count++;
				if(count == n) {
					return i;
				}
			}
		}
		return -1;
	}

	public void replaceNthOccurence(String str, int n, String repl) {
		int i = findNthOccurence(str, n);
		replace(i, i + str.length(), repl);
	}

	private String replace(int from, int to, String replacement) {
		return currentPhrase.substring(0, from) + replacement + currentPhrase.substring(from + to, currentPhrase.length());
	}

	public int findLastOccurence(String str) {
		int i = -1;
		int lastI = -1;
		int occurence = 1;
		while(true) {
			i = findNthOccurence(str, occurence);
			if(i == -1) {
				return lastI;
			}
			lastI = i;
			occurence++;
		}
	}

	public String toString() {
		return currentPhrase;
	}

	public static void main(String[] args) {
		Phrase p = new Phrase("A cat ate late.");
//		p.replaceNthOccurence("at", 1, "rane");
//		System.out.println(p);
//		System.out.println(p.findLastOccurence("at"));

		p = new Phrase("Alex said that Alex is awsesome when Alex woke up and saw Alex in the mirror.");
		int i = p.findLastOccurence("Alex");
		System.out.println("Last occurrence of Alex is at: " + i);
	}
}
