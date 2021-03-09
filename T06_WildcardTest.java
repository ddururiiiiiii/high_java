import java.util.ArrayList;
import java.util.List;

/**
	 * 와일드 카드 예제
	 * 
	 * <? extends T> => 와일드 카드의 상향 제한. T와 그 자손들만 가능.
	 * <? super T> => 와일드 카드의 하한 제한. T와 그조상들만 가능.
	 * <?>			=> 모든 타입이 가능 <? extends Object>와 동일. 
	 */
	



public class T06_WildcardTest {
	public static void main(String[] args) {
		List<String> aaa = new ArrayList<String>();
		
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 과일상자
		FruitBox<Apple> appleBox = new FruitBox<Apple>(); // 사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		

	}
}

	class Juicer{
		
		static void makeJuice(FruitBox<Fruit> box) {
			
			String fruitListStr = ""; // 과일목록
			
			int cnt = 0;
			for (Fruit f : box.getFruitList()) {
				if (cnt == 0) {
					fruitListStr += f;
				} else {
					fruitListStr += "," + f;
				}
				cnt++;
			}
			System.out.println(fruitListStr + "=> 쥬스완성!");
		}
	}





/**
 * 
 * 과일
 */

class Fruit {
	private String name; //과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "과일(" + name + ")"; 
	}
	
}
/**
 * 사과 클래스
 *
 */

	class Apple extends Fruit {
		public Apple() {
			super("사과");
		}
	}

	/**
	 * 포도 클래스
	 *
	 */

		class Grape extends Fruit {
			public Grape() {
				super("포도");
			}
		}	
	
	
		class FruitBox<T> {
			private List<T> fruitList;
			
			public FruitBox() {
				fruitList = new ArrayList<>();
			}
			
			public List<T> getFruitList(){
				return fruitList;
			}
			
			public void setFruitList(List<T> fruitList) {
				this.fruitList = fruitList;
			}
			
			public void add(T fruit) {
				fruitList.add(fruit);
			}
			
			
			
		}
		