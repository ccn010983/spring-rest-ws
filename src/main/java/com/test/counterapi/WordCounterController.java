package com.test.counterapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.counterapi.entities.Counts;
import com.test.counterapi.entities.SearchText;
import com.test.counterapi.entities.WordCount;
import com.test.counterapi.utils.Utils;

@RestController
public class WordCounterController {

	@RequestMapping(method = RequestMethod.POST, value = "/search")
	@ResponseBody
	public String searchWord(@RequestBody SearchText searchText) {

		String content = null;
		String jsonString = "";
		List<WordCount> list = new ArrayList<WordCount>();

		content = Utils.readFile();

		for (String word : searchText.getSearchText()) {
			int count = Utils.countOccurences(content, word);
			WordCount wordCountObj = new WordCount(word, count);
			list.add(wordCountObj);
		}

		Counts counts = new Counts(list);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonString = objectMapper.writeValueAsString(counts);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonString;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/top/{no}")
	@ResponseBody
	public String getTop(@PathVariable("no") long no) {
		String retString = "";
		String content = Utils.readFile();
		List<WordCount> sortedList = (List<WordCount>) Utils.sortWordCountList(content);
		List<WordCount> exportList = new ArrayList<WordCount>();;

		for (int i = 0; i < no; i++) {
			String temp = sortedList.get(i).getWord() + Utils.VERTICAL_DELIMITER + sortedList.get(i).getCount() + Utils.LINE_SEPARATOR;
			retString += temp;
			exportList.add(sortedList.get(i));
		}
		
		Utils.writeCSV(exportList);

		return retString;
	}
}
