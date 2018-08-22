package com.test.counterapi.entities;

import java.util.Arrays;

public class SearchText {
	private String[] searchText;

	public String[] getSearchText() {
		return searchText;
	}

	public void setSearchText(String[] searchText) {
		this.searchText = searchText;
	}

	@Override
	public String toString() {
		return Arrays.toString(searchText);
	}
	
	
}
