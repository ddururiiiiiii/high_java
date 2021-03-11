package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Board {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
		
	/**
	 * 게시판을 출력하는 메소드
	 */	
	private void displayAll() {
		System.out.println("---------------------------------------");
		System.out.println("글번호\t제 목\t작성자\t작성일");
		System.out.println("---------------------------------------");
		
		try {
			// 1. 드라이버 로딩
			conn = JDBCUtil.getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board");
			
			while (rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				System.out.println(board_no + "\t" + board_title + "\t"
									+ "\t" + board_writer + "\t" + board_date);
			}
			System.out.println("---------------------------------------");	
		} catch(SQLException ex) {
			ex.printStackTrace();			
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}	
	
	/** 
	 * 메뉴 메서드
	 */		
	public void displayMenu(){
		int choice;
		do{
			displayAll();	
			System.out.println("1. 게시글조회\t2.게시글작성\t3.게시글수정");
			System.out.println("4. 게시글삭제\t0.프로그램종료");
			System.out.println("---------------------------------------");	
			System.out.print("원하는 작업 선택 >> ");
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  
					reading();
					break;
				case 2 :  
					write();
					break;
				case 3 :  
					update();
					break;
				case 4 :  
					delete();
					break;
				case 0 :  
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice != 5);
	}
	
	
	/**
	 * 특정게시글을 조회하는 메서드 (입력받은 글번호를 이용하여 조회한다.)
	 */	
	private void reading() {
		System.out.println("조회할 글번호를 입력하세요. ");
		System.out.print("글번호 >>");
		int board_no = scan.nextInt();
		
		try {
			// 1. 드라이버 로딩
			conn = JDBCUtil.getConnection();
			
			String sql="select * from jdbc_board where board_no = ?";

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			String board_date = rs.getString("board_date");
			String board_writer = rs.getString("board_writer");
			String board_title = rs.getString("board_title");
			String board_content = rs.getString("board_content");
			
			System.out.println("---------------게시글 조회----------------");	
			System.out.println("글번호 : " + board_no);
			System.out.println("작성일 : " + board_date);
			System.out.println("작성자 : " + board_writer);
			System.out.println("글제목 : " + board_title);
			System.out.println("글내용 : " + board_content);
			System.out.println("---------------------------------------");	
			System.out.println("1. 게시판으로 돌아가기");
			System.out.println("---------------------------------------");
			}
			System.out.print("입력>");
			int input = scan.nextInt();
			switch (input) {
			case 1 :
				displayMenu();
				break;
			default :
				System.out.println("번호를 잘못 입력하셨습니다.");
				reading();
				break;
			}
		} catch(SQLException ex) {
			ex.printStackTrace();			
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}	

	/**
	 * 게시글을 삭제하는 메서드 (입력받은 글번호를 이용하여 삭제한다.)
	 */	
	private void delete() {
		
		System.out.println("삭제할 글번호를 입력하세요. ");
		System.out.print("글번호 >>");
		int board_no = scan.nextInt();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(board_no + "번 게시글 삭제 작업 성공!");
			} else {
				System.out.println(board_no + "번 게시글 삭제 작업 실패!!!");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();	
			System.out.println(board_no + "번 게시글 삭제에 실패하였습니다. ");
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

	}	
	
	/**
	 * 게시글을 수정하는 메서드
	 */
	private void update() {
		boolean cbn = false;
		int board_no;
		do {
			System.out.println("수정할 게시글번호를 정보를 입력하세요.");
			System.out.print("게시글 번호 >>");
			board_no = scan.nextInt();
			
			cbn = check_boardNo(board_no);
			
			if (cbn == false) {
				System.out.println("게시글번호가 " + board_no + "인 게시글이 없습니다.");
				System.out.println("다시 입력해주세요");
			}
		} while(cbn == false);
		
		System.out.print("게시글 제목 수정>> ");
		String board_title = scan.next();
		
		System.out.print("게시글 작성자 수정>> ");
		String board_writer = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("게시글 내용 수정>> ");
		String board_content  = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update jdbc_board set board_writer = ?, "
					+ "board_title = ?, board_content = ?, "
					+ "board_date  =? where board_no  = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board_writer);
			pstmt.setString(2, board_title);
			pstmt.setString(3, board_content);
			pstmt.setString(4, sdf.format(now));
			pstmt.setInt(5, board_no);
			
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("게시글 수정 작업 성공!");
			} else {
				System.out.println("게시걸 수정 작업 실패!!!");
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();	
			System.out.println("게시글 수정에 실패하였습니다. ");
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}	

	private void write() {
		
		System.out.print("글제목 >> ");
		String board_title = scan.next();

		System.out.print("글내용 >> ");
		String board_content = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("작성자 >> ");
		String board_writer = scan.next();

		try {
			// 1. 드라이버 로딩
			conn = JDBCUtil.getConnection();

			String sql = "insert into jdbc_board "
					+ "(board_no, board_title, board_content, board_writer, board_date)" 
			+ "values (board_seq.nextVal, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setString(3, board_writer);
			pstmt.setString(4, sdf.format(now));
			
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 작성 성공!");
			} else {
				System.out.println("게시글 작성 실패!");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

	}
	
	/**
	 * 글번호를 이용하여 해당글번호의 게시글이 알려주는 메서드 
	 * @param board_no
	 * @return 존재하면 true, 없으면 false
	 */
		
	private boolean check_boardNo(int board_no) {

		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}		
		return chk;
	}
	

	
	public static void main(String[] args) {
		Board board = new Board();
		board.displayMenu();
	}
}
