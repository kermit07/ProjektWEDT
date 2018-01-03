package com.wedt.app;

import com.google.gson.Gson;
import com.wedt.model.FBPost;
import com.wedt.util.SynonymUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {

//		SpringApplication.run(AppApplication.class, args);

		// Wczytywanie postów z jsona
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get("fb_posts_test.json")));
		} catch (IOException e) {
		}
		Gson g = new Gson();
		FBPost[] posts = g.fromJson(content, FBPost[].class);

		// Przykład użycia WordNet
		System.setProperty("wordnet.database.dir", Config.WORDNET_DIST_DIR);
		SynonymUtils su = new SynonymUtils();
		Set<String> compSyn = su.getSynonymsSet("komputer");
		compSyn.forEach(
				element -> System.out.println(element));
	}
}