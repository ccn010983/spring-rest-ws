package com.test.counterapi.entities;

import java.util.List;

public class Counts {
	private List<WordCount> counts;

	public List<WordCount> getCounts() {
		return counts;
	}

	public void setCounts(List<WordCount> counts) {
		this.counts = counts;
	}

	public Counts(List<WordCount> counts) {
		super();
		this.counts = counts;
	}

}
