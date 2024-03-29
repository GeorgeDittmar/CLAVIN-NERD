package com.berico.clavin.nerd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.berico.clavin.extractor.LocationExtractor;
import com.berico.clavin.extractor.LocationOccurrence;

public class NerdLocationExtractorTest {

	@Test
	public void extractor_correctly_extracts_an_easily_found_location() throws Exception {
		
		String text = "I went to Bolivia last week.";
		
		int expectedNumberOfExtractedLocations = 1;
		
		String expectedLocation = "Bolivia";
		
		int expectedLocationOffset = text.indexOf(expectedLocation);
		
		SequenceClassifierProvider sequenceClassifierProvider = 
			new ExternalSequenceClassifierProvider(
				"src/main/resources/all.3class.distsim.crf.ser.gz");
		
		LocationExtractor locationExtractor = 
			new NerdLocationExtractor(sequenceClassifierProvider);
		
		List<LocationOccurrence> results = 
			locationExtractor.extractLocationNames(text);
		
		assertEquals(expectedNumberOfExtractedLocations, results.size());
		
		LocationOccurrence actualLocation = results.get(0);
		
		assertEquals(expectedLocation, actualLocation.text);
		
		assertEquals(expectedLocationOffset, actualLocation.position);
	}
	
	@Test
	public void extractEntityAtOffsets_correctly_extracts_text_at_the_specified_offsets(){
		
		String text = "Get TARGET word.";
		
		String expected = "TARGET";
		
		String actual = NerdLocationExtractor.extractEntityAtOffsets(text, 4, 10);
		
		assertEquals(expected, actual);
	}
}
