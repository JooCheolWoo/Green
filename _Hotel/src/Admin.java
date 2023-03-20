import java.util.Scanner;

public class Admin {
	Scanner scan = new Scanner(System.in);
	private String id;
	private String pw;
	private String name;
	private String phone;
	
	public Admin(String id, String pw, String name, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}
	
	public Admin() {
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int login(Admin[] admin, int numOfAdmin) {
		boolean idPass = true;
		boolean pwPass = true;
		int idNum = -1;
		int count = 0;
		System.out.println("-----로그인-----");
		while (idPass) {
			if (count != 0) {
				System.out.println("등록되지 않은 ID 입니다.");
			}
			System.out.print("ID: ");
			String id = scan.nextLine();			
			for (int i = 0; i < numOfAdmin; i++) {
				if (admin[i].getId().equals(id)) {
					idNum = i;
					idPass = false;
					count = 0;
					break;
				}
				count++;
			}
		}
		while (pwPass) {
			if (count != 0) {
				System.out.println("잘못된 PW 입니다.");
			}
			System.out.print("PW: ");
			String pw = scan.nextLine();				
			if (admin[idNum].getPw().equals(pw)) {
				pwPass = false;
				System.out.println("관리자 " + admin[idNum].getName() + "님 반갑습니다.");
				break;
			}
			count++;
		}
		return idNum;
	}
}
