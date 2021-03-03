package kr.or.ddit.basic;

import java.util.ArrayList;

public class Practice_RemoveallClear {

	public static void main(String[] args) {
		
		// removeAll() : A배열에서 B배열을 빼는 제거하는 경우 사용		
		ArrayList<String>  marvel = new ArrayList<>();
		marvel.add("Iron man");
		marvel.add("Hulk");
		marvel.add("Captain america");
		System.out.println("marvel: " + marvel);

		ArrayList<String>  movies = new ArrayList<>();
		movies.add("Untouchable");
		movies.add("Spiderman");
		movies.add("Captain america");
		movies.add("Hulk");
		System.out.println("movies: " + movies);

		movies.removeAll(movies);
		System.out.println("movies - removeAll(marvel): " + movies);
		
		
		//clear() : 해당 ArrayList을 전체 삭제
		ArrayList<String>  marvel2 = new ArrayList<>();
		marvel.add("Iron man");
		marvel.add("Hulk");
		marvel.add("Captain america");
		System.out.println("marvel: " + marvel);
		
		movies.clear();
		System.out.println("marvel2.clear() : " + marvel2);
		

	}

}
