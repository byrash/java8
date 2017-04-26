package com.shivaji.java8;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Demo6 {

	public static void main(String[] args) {
		try (Stream<String> lines = Files.lines(
				Paths.get("/Users/Shivaji/Workspace/Java8/src/com/shivaji/java8/Demo6.java"),
				Charset.defaultCharset());) {
			System.out.println(lines.flatMap((line) -> Arrays.stream(line.split(" ")))
					.distinct()
					.count());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
