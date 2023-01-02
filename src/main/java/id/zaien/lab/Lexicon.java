package id.zaien.lab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lexicon {
	
	private final HashSet<String> words;
	
	public Lexicon(String dictionaryFile) {
		
		Path currentRelativePath = Paths.get("");
		String lexiconFile = currentRelativePath.toAbsolutePath().toString() + File.separator + dictionaryFile;
		
		List<String> lexicon;
		try (Stream<String> lines = Files.lines(Paths.get(lexiconFile))) {
			lexicon = lines.collect(Collectors.toList());
		} catch (IOException e) {
			lexicon = new ArrayList<>();
		}
		
		words = new HashSet<>(lexicon);
	}
	
	public boolean isContainWord(String word) {
		return words.contains(word);
	}

}
