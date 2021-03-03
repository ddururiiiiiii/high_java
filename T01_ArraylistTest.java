package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01_ArraylistTest {
	
	public static void main(String[] args) {
		// ArrayList는 기본적인 사용법이 Vector와 같다.
		// DEFAULT_CAPACITY = 10
		
		//ArrayList 생성
		List list1 = new ArrayList<>();

		//vector로 생성한다면?
		// List list1 = new Vector<>();
		//기능은 거의 흡사한데 요즘은 ArrayList 를 더 많이 씀. 
		
		//데이터 추가 : add(data)
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		
		//데이터의 갯수 : size()
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		//데이터 꺼내오기 : get(index)
		System.out.println("1번째 자료 : " + list1.get(1));
		
		//데이터 끼워넣기 : add(index, data)
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기 : set(index, data)
		String temp = (String)list1.set(0, "YYY");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		// 데이터 삭제 : remove(index) / remove(data)
		// 삭제하면 그 index의 값이 null 값이 아니라 땡겨서 채워진다 잊지말기!
		list1.remove(0);
		System.out.println("삭제 후 : " + list1);
		list1.remove("bbb");
		System.out.println("bbb 삭제후 : " + list1);
		System.out.println("========================");
		
		//제너릭 : list 안에 string (특정타입) 외의 다른 타입은 넣을 수 없게!
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		//list2.add(123); -> 컴파일 오류 : String 타입으로 제너릭을 지정했기 때문에.
				
		//ArrayList 전체 출력하기 (1) - for문
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("---------------------------");
		
		//ArrayList 전체 출력하기 (2) - for-each문 / 향상된 for문
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("---------------------------");		
		//전체 출력을 할 때는, for-each문이 편하지만 일부를 출력할 때는 for문으로만 출력 가능.
			
		// contains(비교객체)
		// 리스트에 '비교객체'가 있으면 true, 없으면 false 리턴함.		
		System.out.println(list2.contains("DDD")); // true
		System.out.println(list2.contains("ZZZ")); // false
		System.out.println("---------------------------");
		
		// 리스트의 제너릭타입에 맞는 자료형의 배열로 변환하는 방법
		// 1) toArray() : 리스트 안의 데이터들을 배열로 변환하여 반환한다. (기본적으로 Object형 배열로 변환)		
		Object[] strArr = list2.toArray();
		System.out.println("배열의 갯수 : " + strArr.length);
		// 2) 제너릭타입의 0개 짜리 배열을 생성해서 매개변수로 넣어준다.
		// => 배열의 크기가 리스트 크키보다 작으면 리스트의 크기에 맞는 배열을 넣어준다.
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 갯수 : " + strArr2.length);
		//String[] strArr2 = (String)list2.toArray(new String[0]);
		//이건 컴파일 오류남.

		//ArrayList 전체 삭제하기
		for (int i = list2.size() - 1; i >= 0; i--) {
			list2.remove(i);
		}
		System.out.println("list2 전체 삭제 후 길이는? : " + list2.size());
	

	}
}
