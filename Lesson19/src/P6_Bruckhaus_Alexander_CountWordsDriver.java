//CountWords Reflection

//Alex Bruckhaus, Period 6, 1/29/18
//This program took me 2 hours.
//This program was very interesting but quite challenging. It took me a while to brainstorm a technique to complete this assignment.
//I used some videos on the internet to help understand this assignment more as well as help from my father. In the end, I decided
//to use ArrayLists to store the list of sorted words by frequency. During this assignment I had some trouble getting rid of 
//punctuations, but I eventually used replaceAll() to solve this issue. Another issue I ran into was counting the hyphen as a word when it
//was actually not. To solve this, I used the matches() method to return out the block if the hyphen shows up without counting it as a word. 
//(This was in the dream.txt file). Overall, this lab was more challenging than others because it required me to do more research
//than usual and forced me to think very carefully while writing my program.


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_CountWordsDriver {
	private static final String DELIMITERS = "[ ,\r\n]";

	public static void main(String[] args) throws IOException {
		//FileInputStream f = new FileInputStream("simple.txt");
		//FileInputStream f = new FileInputStream("lincoln.txt");
		FileInputStream f = new FileInputStream("dream.txt");
		Scanner in = new Scanner(f);
		in.useDelimiter(DELIMITERS);
		WordList wordList = new WordList();
		while(in.hasNext()) {
			String next = in.next();
			wordList.observe(next);
		}
		in.close();
		f.close();
		wordList.sort();
		System.out.print(wordList);
	}
}

class Word implements Comparable<Word>{
	private static final String TRIM_CHARS = "[!,.\":?;]";
	private String word;
	private int count;
	
	public Word(String str) {
		str = prepare(str);
		this.word = str;
		this.count = 0;
	}

	public static String prepare(String str) {
		str = str.toLowerCase();
		str = str.replaceAll(TRIM_CHARS, "");
		return str;
	}
	
	public void count() {
		count++;
	}
	
	@Override
	public int compareTo(Word o) {
		return this.count - o.count;
	}
	
	@Override 
	public String toString() {
		return count + "\t" + word;
	}

	public String getWord() {
		return word;
	}
	
	
}

class WordList{
	private static final String IGNORED = "|-";
	private ArrayList<Word> list = new ArrayList<Word>();
	private int uniqueWords = 0;
	private int totalWords = 0;

	public void observe(String str) {
		if(str.matches(IGNORED)) {
			return;
		}
		int index = find(str);
		if(index == -1) {
			Word word = new Word(str);
			list.add(word);
			word.count();
			uniqueWords++;
			totalWords++;
		}else {
			Word word = list.get(index);
			word.count();
			totalWords++;
		}
	}

	public int find(String word) {
		word = Word.prepare(word);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getWord().equals(word)) {
				return i;
			}
		}
		return -1;
	}
	
	public void sort() {
		for(int outer = 1; outer < list.size(); outer++) {
			Word key = list.get(outer);
			int inner = outer - 1;
			while(inner >= 0 && list.get(inner).compareTo(key) < 0) {
				list.set(inner + 1, list.get(inner));
				inner--;
			}
			list.set(inner + 1, key);
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < list.size() && i < 30; i++) {
			Word word = list.get(i);
			result +=  (i + 1) + "\t" + word.toString() + "\r\n";
		}
		result += "\nNumber of unique words used = " + uniqueWords;
		result += "\nTotal # of words = " + totalWords;
		return result;
	}
}

