package com.wedt.app;

import com.google.gson.Gson;
import com.wedt.analyzer.PostAnalyzer;
import com.wedt.metric.DaysIntervalProbability;
import com.wedt.model.FBPost;
import com.wedt.util.ReadPostsFromFile;
import com.wedt.util.SynonymUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {

//		SpringApplication.run(AppApplication.class, args);

		// Inicjalizacja słownika WordNet
		System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);

		try {
			ArrayList<FBPost> posts = ReadPostsFromFile.getPosts("fb_posts_test.json");
//			posts.forEach(element -> System.out.println(element.toString()));

		} catch (IOException e) {
		}

	}
}
