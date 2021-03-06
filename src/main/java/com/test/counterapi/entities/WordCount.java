package com.test.counterapi.entities;

public class WordCount {
	private String word;
	private int count;

	public WordCount(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return this.word + ": " + this.count ;
	}

}
