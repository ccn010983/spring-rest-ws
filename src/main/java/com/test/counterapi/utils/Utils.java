package com.test.counterapi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.test.counterapi.entities.WordCount;

public class Utils {

	public static final String VERTICAL_DELIMITER = "|";
	public static final String LINE_SEPARATOR = "\n";

	public static int countOccurences(String content, String word) {
		String arr[] = content.split("\\W+");
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (word.equals(arr[i]))
				count++;
		}
		return count;
	}

	public static String readFile() {
		File file;
		String content = null;
		try {
			file = ResourceUtils.getFile("classpath:document.txt");
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}

	public static List<WordCount> sortWordCountList(String content) {

		String[] fullWords = content.split("\\W+");
		List<WordCount> list = new ArrayList<WordCount>();
		LinkedHashSet<String> lhSetWords = new LinkedHashSet<String>(Arrays.asList(fullWords));
		StringBuilder sbTemp = new StringBuilder();
		int index = 0;

		for (String s : lhSetWords) {

			if (index > 0)
				sbTemp.append(" ");

			sbTemp.append(s);
			index++;
		}
		String changedStr = sbTemp.toString();
		String[] searchText = changedStr.split("\\W+");

		for (int i = 0; i < searchText.length; i++) {

			int count = countOccurences(content, searchText[i]);
			WordCount wordCountObj = new WordCount(searchText[i], count);
			list.add(wordCountObj);

		}

		list.sort(Comparator.comparingDouble(WordCount::getCount).reversed());
		return list;

	}

	public static void writeCSV(List<WordCount> wordCountList) {
		FileWriter fileWriter ;
		File file;
		try {
			file = ResourceUtils.getFile("classpath:result.csv");
			System.out.println(file.toPath().toString());
			fileWriter = new FileWriter(file.toPath().toString());
			for (WordCount wordCount : wordCountList) {
				fileWriter.append(wordCount.getWord());
				fileWriter.append(VERTICAL_DELIMITER);
				fileWriter.append(Integer.toString(wordCount.getCount()));
				fileWriter.append(LINE_SEPARATOR);
			}
			
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
