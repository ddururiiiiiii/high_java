package ex1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LottoStore {
	
	Scanner scanner = new Scanner(System.in);
	int input;
	int money;
	
	void main() {
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴입력 : ");
		input = scanner.nextInt();
	}
	
	void buyLotto() {
		while(true) {
			System.out.println("Lotte 구입을 진행합니다.");
			System.out.println("로또 1장 : 1,000원");
			System.out.print("구매 금액 입력 : ");
			money = scanner.nextInt();
			if (money <= 0) {
				System.out.println("입력 금액이 1000원 이하 입니다.");
				System.out.println("로또를 구매하실 수 없습니다.");
				return;
			} else {
				System.out.println("로또를 구입하셨습니다.");
				for (int i = 0; i <money / 1000; i++) {
					System.out.print("로또번호 " + (i + 1) + " : ");
					randomLotto();
			}
				System.out.println("받은 금액은 " + money + "원 이고,");
				System.out.println("거스름돈은 " + (money%1000) + "원 입니다.");
				return;
			}
		}
	}
		
	void randomLotto() {
		Set<Integer> lotte = new HashSet<>();
		while(lotte.size() < 6) {
			int random = (int)(Math.random() * 45 + 1);
			lotte.add(random);
		}
		System.out.println(lotte);
	}


	public static void main(String[] args) {	
	
		LottoStore ls = new LottoStore();
		
		while(true) {
			ls.main();
			switch (ls.input) {
			case 1 :
				ls.buyLotto();
				break;
			case 2 : 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default : 
				System.out.println("번호를 잘못 입력하셨습니다.");
				return;
			}
		}

}



		

		
		
		
		
		
		


}
