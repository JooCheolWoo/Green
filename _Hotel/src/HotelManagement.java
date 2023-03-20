import java.util.Scanner;

public class HotelManagement {
	public static int checkInt(int num, String check, String type) {
		Scanner scan = new Scanner(System.in);
		boolean go = true;
		if (check.equals("예약")) {
			while (go) {
				if (num < 31 && num >= 0) {
					go = false;
				} else {
					System.out.println("잘못된 입력입니다.");
					System.out.print("다시 입력해주세요: ");
					num = scan.nextInt();
				}
			}
		}
		if (check.equals("인원수")) {
			while (go) {
				if (type.equals("더블")) {
					if (num <= 2 && num > 0) {
						go = false;
					} else {
						System.out.println("최대 인원수는 2명 입니다.");
						System.out.print("입실 인원수: ");
						num = scan.nextInt();
					}
				}
				if (type.equals("디럭스")) {
					if (num <= 3 && num > 0) {
						go = false;
					} else {
						System.out.println("최대 인원수는 3명 입니다.");
						System.out.print("입실 인원수: ");
						num = scan.nextInt();						
					}
				}
				if (type.equals("스위트")) {	
					if (num <= 4 && num > 0) {
						go = false;
					} else {
						System.out.println("최대 인원수는 4명 입니다.");
						System.out.print("입실 인원수: ");
						num = scan.nextInt();							
					}
				}
				}
					
		}
		
		if (check.equals("영일")) {
			while(go) {
				if (num == 0 || num == 1) {
					go = false;
				} else {
					System.out.println("잘못된 입력입니다.");
					System.out.print("다시 입력해주세요: ");
					num = scan.nextInt();					
				}
			}
		}
		return num;
	}
	
	public static String checkString(String input, String check) {
		Scanner scan = new Scanner(System.in);
		boolean go = true;
		if (check.equals("타입")) {
			while (go) {
				if (input.equals("더블") || input.equals("디럭스") || input.equals("스위트")) {
					go = false;
				} else {
					System.out.println("잘못된 입력입니다.");
					System.out.print("다시 입력해주세요: ");
					input = scan.nextLine();					
				}
			}
		}
		if (check.equals("공백")) {
			while (go) {
				if(input.equals("")) {
					System.out.println("공백이 입력되었습니다.");
					System.out.print("다시 입력해주세요: ");
					input = scan.nextLine();
				} else {
					go = false;
				}
			}
		}
		return input;
	}
	
	public static int sales(Room[][] room, int i, int j) {
		int days = room[i][j].getGuest().getDays();
		int pay;
		pay = room[i][j].getPrice() * days + room[i][j].getGuest().getBreakfast() * 9000 * days * room[i][j].getGuest().getNumOfGuest();
		return pay;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean login = true; // 관리자 로그인 상황
		boolean onOff = true; // 프로그램 상황
		boolean menu; // switch 등의 작은 메뉴의 while 사용시 사용할 변수
		int daySales = 0; // 당일 매출 금액 저장
		int totalSalse = 0; // 전체 매출 금액 저장
		int roomNum = 0; // 객실번호 임시저장;
		
		// 관리자
		int numOfAdmin = 4; // 관리자수
		Admin[] admin = new Admin[numOfAdmin]; // 관리자 리스트 배열 선언
		Admin loginAdmin = new Admin(); // 현재 프로그램에 로그인한 관리자 저장
		
		// 초기 관리자 리스트
		admin[0] = new Admin("admin0", "abc123", "주철우", "01055059373");
		admin[1] = new Admin("admin1", "abc123", "김도연", "01011111111");
		admin[2] = new Admin("admin2", "abc123", "조성우", "01022222222");
		admin[3] = new Admin("admin3", "abc123", "한창우", "01033333333");
		
		// 객실
		// 추수 수정할 일이 있으면 쉽게하기 위해서 모두 변수로 설정 및 초기화
		int floor = 3; // 객실이 있는 층 갯수
		int roomsPerFloor = 5; // 층당 객실 수
		int startFloor = 2; // 객실이 있는 시작층
		Room[][] room = new Room[floor][roomsPerFloor];
		for (int i = 0; i < floor; i++) {
			for (int j = 0; j < roomsPerFloor; j++) {
				room[i][j] = new Room();
				roomNum = (i + startFloor) * 100 + (j + 1);
				room[i][j].setRoomNum(roomNum);
				if (i % 3 == 0) {
					room[i][j].setType("더블");
					room[i][j].setPrice(100000);
					room[i][j].setCapacity(2);
				}
				if (i % 3 == 1) {
					room[i][j].setType("디럭스");
					room[i][j].setPrice(180000);
					room[i][j].setCapacity(3);					
				}
				if (i % 3 == 2) {
					room[i][j].setType("스위트");
					room[i][j].setPrice(250000);
					room[i][j].setCapacity(4);						
				}
			}
		}
		
		while (onOff) {
			String name;
			String phone;
			int afterDay;
			int stayDay;
			String type;
			int numOfGuest;
			int Breakfast;
			int payment;
			// guest에 들어갈 변수들을 입력받아 올바른 입력인지 판별하기 위해서 임시적으로 저장할 변수들
			
			int count = 0;
			// while문에서 사용할 count 변수
			
			int tempI = -1;
			int tempJ = -1;
			int tempK = -1;
			// 배열의 번호를 일시적으로 저장하기 위한 변수
			// -1로 초기화 한것은 오류 발생시 알기 위해서(index에 음수 들어가면 프로그램 뻗음)
			
			if (login) {
				loginAdmin = admin[loginAdmin.login(admin, numOfAdmin)]; // 로그인한 admin을 loginAdmin에 저장
				login = false; // login을 false로 하여 로그인해있는동안 더 이상 실행하지 않음.
			}
			System.out.println("=====Green Hotel 관리자 프로그램=====");
			System.out.println("1.전체객실목록\n"
							+ "2.객실번호검색\n"
							+ "3.예약관리\n"
							+ "4.Check in\n"
							+ "5.Check out\n"
							+ "6.매출관리\n"
							+ "7.관리자 로그아웃\n"
							+ "8.다음날\n"
							+ "0.프로그램 종료");
			System.out.print("선택할 기능: ");
			switch (scan.nextInt()) {
			
			case 1: // 전체 객실 목록
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						System.out.println(room[i][j].toString());
					}
				}
				break;
				
			case 2: // 객실번호 검색
				menu = true; // 객실번호를 확인할 while에서 쓰일 booelan
				while (menu) {
					System.out.print("검색할 객실번호: ");
					roomNum = scan.nextInt(); //객실번호 입력받기
					tempI = roomNum / 100 - 2; //room[i][j] 에서 i로 변환 
					tempJ = roomNum % 100 - 1; //room[i][j] 에서 j로 변환
					if (tempI < floor && tempJ < roomsPerFloor) { // 존재하는 객실번호인지 확인
						menu = false; // 존재한다면 while문 탈출
					} else {
						System.out.println("존재하지 않는 객실번호입니다.");
					}
				}
				System.out.println(room[tempI][tempJ].toString()); // 해당하는 객실 배열의 내용 출력;
				break;
				
			case 3: // 예약관리
				System.out.print("1)예약하기\n"
							     + "2)예약취소\n"
							     + "3)예약목록\n"
							     + "0)뒤로가기\n");
				System.out.print("선택할 기능: ");
				switch (scan.nextInt()) {
				
				case 1: // 예약하기
					System.out.print("몇일 후 방문예정이신가요?(최대 30일): ");
					afterDay = checkInt(scan.nextInt(), "예약", "없음");
					System.out.print("몇일 숙박 예정이신가요?(최대 30일): ");
					stayDay = checkInt(scan.nextInt(), "예약", "없음");
					scan.nextLine();
					System.out.println("더블, 디럭스, 스위트 중 어떤 타입을 원하시나요?");
					System.out.println("더블: 100,000원\n"
									 + "디럭스: 180,000원\n"
									 + "스위트: 250,000원");
					System.out.print("타입: ");
					type = checkString(scan.nextLine(), "타입");
					count = 0;
					System.out.println("-----예약 가능한 객실목록-----");
					for (int i = 0; i < floor; i++) {
						for (int j = 0; j < roomsPerFloor; j++) {
							if (room[i][j].getType().equals(type) && room[i][j].getBookedDay().checkDay(afterDay, stayDay)) {
								// 입력한 타입과 같은 객실과 해당 객실이 입력한 날짜에 빈객실인지 확인
								System.out.println(room[i][j].toString());
								tempI = i;
								count++;
							}
						}
					}
					if (count == 0) {
						System.out.println("현재 예약 가능한 객실이 없습니다.");
						break;
					}
					menu = true;
					while (menu) {
						System.out.print("예약할 객실번호: ");
						roomNum = scan.nextInt();
						if (roomNum / 100 - 2 == tempI
								&& roomNum % 100 - 1 < roomsPerFloor 
								&& room[tempI][roomNum % 100 - 1].getBookedDay().checkDay(afterDay, stayDay)) {
							// 예약가능한 객실들의 층과 입력한 객실번호의 층이 같은지?
							// 입력한 객실번호가 존재하는 객실번호 범위 내에 있는지?
							// 입력한 객실번호가 해당 날짜에 예약이 존재하는지?
							menu = false;
						} else {
							System.out.println("예약가능한 객실번호 중 선택해주세요.");
						}
					}
					scan.nextLine();
					System.out.print("예약자 이름: ");
					name = checkString(scan.nextLine(), "공백");
					System.out.print("연락처(번호만입력): ");
					phone = checkString(scan.nextLine(), "공백");
					System.out.print("입실 인원수: ");
					numOfGuest = checkInt(scan.nextInt(), "인원수", type);
					System.out.print("조식여부(0.안먹음 1.먹음): ");
					Breakfast = checkInt(scan.nextInt(), "영일", "없음");
					System.out.print("결제여부(0.선결제 1.후결제): ");
					payment = checkInt(scan.nextInt(), "영일", "없음");
					Guest guest = new Guest(roomNum, name, phone, afterDay, stayDay, numOfGuest, Breakfast, payment, loginAdmin);
					tempI = roomNum / 100 - 2;
					tempJ = roomNum % 100 - 1;
					room[tempI][tempJ].getBooklist().setGuestList(guest); // 해당 Room → BookList → Guest에 정보 저장
					room[tempI][tempJ].getBookedDay().bookOn(afterDay, stayDay); // 해당 Room → BooedDay에 해당 날짜 저장
					System.out.println("예약이 완료되었습니다.");
					break;
					
				case 2: // 예약취소
					scan.nextLine();
					System.out.print("예약자 이름: ");
					name = checkString(scan.nextLine(), "공백");
					count = 0;
					for (int i = 0; i < floor; i++) {
						for (int j = 0; j < roomsPerFloor; j++) {
							for (int k = 0; k < 31; k++) {
								Guest tempG = room[i][j].getBooklist().getGuestList()[k]; // code를 줄이기 위해서 임시적으로 선언
								if (tempG != null && tempG.getName().equals(name)) {
									// null인 경우 오류를 피하기 위해서 제외
									// 해당 Room → BookList → Guest의 name변수가 입력한 값과 같다면 출력;
									System.out.println(tempG.toString());
									tempI = i;
									tempJ = j;
									tempK = k;
									// 해당하는 Room의 배열 index를 저장
									count++;
								}
							}
						}
					}
					if (count == 0) {
						System.out.println("해당 이름으로된 예약은 없습니다.");
						break;
					} else {
						System.out.println("예약을 취소하시겠습니까?");
						System.out.print("0.네 1.아니요 : ");
						int temp = checkInt(scan.nextInt(), "영일", "없음");
						switch (temp) {
						case 0:
							afterDay = room[tempI][tempJ].getGuest().getAfterDay();
							stayDay = room[tempI][tempJ].getGuest().getStayDay();
							room[tempI][tempJ].getBookedDay().bookOff(afterDay, stayDay);
							// 해당 Room의 BookedDay에 입력되었던 boolean을 다시 초기화
							room[tempI][tempJ].getBooklist().resetGuestList(tempK);
							// 해당 Room의 BookList를 초기화
							System.out.println("예약이 취소되었습니다.");
							break;
						case 1:
							System.out.println("예약취소를 취소했습니다.");
							break;
						default :
							System.out.println("잘못된 입력입니다.");
							break;
						}

					}
					break;
					
				case 3: // 예약목록
					count = 0;
					for (int i = 0; i < floor; i++) {
						for (int j = 0; j < roomsPerFloor; j++) {
							for (int k = 0; k < 31; k++) {
								if (room[i][j].getBooklist().getGuestList()[k] != null && room[i][j].getGuest().getName() == null) {
									// Room → BookList → Guest의 RoomNum이 0이 아니면 해당 변수에 이전에 예약을 했기때문임(초기값 0)
									System.out.println(room[i][j].getBooklist().getGuestList()[k].toString());
									System.out.println(i +" " + j + " " + k);
									count++;
								}
							}
						}
					}
					if (count == 0) {
						System.out.println("현재 예약자가 없습니다.");
					}
					break;
					
				case 0:
					break;
					
				default :
					System.out.println("잘못된 입력입니다.");
					break;
				}
				break;
				
			case 4: // 체크인
				scan.nextLine();
				System.out.print("예약자 이름: ");
				name = checkString(scan.nextLine(), "공백");
				count = 0;
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						for (int k = 0; k < 31; k++) {
							Guest tempG = room[i][j].getBooklist().getGuestList()[k];
							if (tempG != null  && tempG.getAfterDay() == 0 && tempG.getName().equals(name)) {
								// null인 경우를 제외
								// 예약예정 날짜가 0일 남았고
								// 입력한 이름과 Room → BookList → Guest의 name이 같다면 출력
								System.out.println(tempG.toString());
								tempI = i;
								tempJ = j;
								tempK = k;
								count++;
							}
						}
					}
				}
				if (count == 0) {
					System.out.println("해당 이름으로된 예약은 없습니다.");
					break;
				} else if (room[tempI][tempJ].getGuest().getName() != null) {
					// 해당 Room 배열에 고객이 존재한다면
					System.out.println("이전 고객이 체크아웃하지 않았습니다.");
					System.out.println("잠시만 기다려주세요.");
					break;
				}else {
					System.out.println("체크인 하시겠습니까?");
					System.out.print("0.네 1.아니요 : ");
					int temp = checkInt(scan.nextInt(), "영일", "없음");
					switch (temp) {
					case 0:
						room[tempI][tempJ].setGuest(room[tempI][tempJ].getBooklist().getGuestList()[tempK]);
						// BookList의 Guest를 Room으로 이동
						room[tempI][tempJ].setAdmin(loginAdmin);
						// 현재 로그인해 있는 admin의 정보를 Room admin에 대입
						System.out.println("체크인되었습니다.");
						if (room[tempI][tempJ].getGuest().getPayment() == 0) {
							// 예약할때 입력한 payment가 0(선결제)라면 체크인하면서 결제진행
							temp = sales(room, tempI, tempJ);
							daySales += temp;
							System.out.println(temp + "원 결제되었습니다.");
							totalSalse += temp;
						}
						break;
					case 1:
						System.out.println("체크인을 취소했습니다.");
						break;
					default :
						System.out.println("잘못된 입력입니다.");
						break;
					}

				}
				break;
				
			case 5: // 체크아웃
				scan.nextLine();
				System.out.print("고객 이름: ");
				name = checkString(scan.nextLine(), "공백");
				count = 0;
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						for (int k = 0; k < 31; k++) {
							Guest tempG = room[i][j].getGuest();
							if (tempG.getName() != null && tempG.getName().equals(name) && tempG.getStayDay() == 0 
									&& room[i][j].getBooklist().getGuestList()[k].getName().equals(name)) {
								// null 제외
								// 입력한 이름과 Room에 있는 Guest의 name이 같은지?
								// Room의 숙박일이 0인지?
								System.out.println(tempG.toString());
								tempI = i;
								tempJ = j;
								tempK = k;
								count++;
							}
						}
					}
				}
				if (count == 0) {
					System.out.println("금일 체크아웃 목록에 없습니다.");
					break;
				} else {
					System.out.println("체크아웃 하시겠습니까?");
					System.out.print("0.네 1.아니요 : ");
					int temp = checkInt(scan.nextInt(), "영일", "없음");
					switch (temp) {
					case 0:
						if (room[tempI][tempJ].getGuest().getPayment() == 1) {
							// 예약할때 입력한 payment가 1(후결제)라면 결제진행
							temp = sales(room, tempI, tempJ);
							daySales += temp;
							totalSalse += temp;
							System.out.println(temp + "원 결제되었습니다.");							
						}
						room[tempI][tempJ].setGuest(new Guest());
						room[tempI][tempJ].setAdmin(new Admin());
						room[tempI][tempJ].getBooklist().resetGuestList(tempK);
						// room의 guest, admin, booklist 초기화
						System.out.println("체크아웃 되었습니다.");
						break;
					case 1:
						System.out.println("체크아웃을 취소했습니다.");
						break;
					default :
						System.out.println("잘못된 입력입니다.");
						break;
					}

				}				
				break;
				
			case 6:
				System.out.println("금일 매출: " + daySales);
				System.out.println("총 매출: " + totalSalse);
				break;
				
			case 7:
				login = true;
				loginAdmin = new Admin();
				// 로그인 boolean값 변경, loginAdmin 초기화
				break;
				
			case 8: // 다음날
				System.out.println("-----체크아웃하지 않은 예약자-----");
				count = 0;
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						Guest tempG = room[i][j].getGuest();
						if (tempG.getName() != null && tempG.getStayDay() == 0 ) {
							// room의 guest의 name이 존재하는지? (존재한다면 객실에 고객이 checkin 되어있다는 것)
							// 숙박일이 0 인지?
							System.out.println(tempG.toString());
							tempI = i;
							tempJ = j;
							count++;
						}
					}
				}
				if (count == 0) {
					System.out.println("없음");
				} else {
					System.out.println("모두 체크아웃으로 변경합니다.");
					for (int i = 0; i < floor; i++) {
						for (int j = 0; j < roomsPerFloor; j++) {
							for (int k = 0; k < 31; k++) {
								name = room[i][j].getGuest().getName();
								if (room[i][j].getGuest().getName() != null && room[i][j].getGuest().getStayDay() == 0
										&& room[i][j].getBooklist().getGuestList()[k].getName().equals(name)) {
									// null 제외
									// room의 guest의 숙박일이 0 인지?
									System.out.println(room[i][j].getGuest().getName() + "님 체크아웃 되었습니다.");
									if (room[i][j].getGuest().getPayment() == 1) {
										int temp = sales(room, i, j);
										daySales += temp;
										totalSalse += temp;
										System.out.println(temp + "원 결제되었습니다.");							
									}
									room[i][j].setGuest(new Guest());
									room[i][j].setAdmin(new Admin());
									room[i][j].getBooklist().resetGuestList(k);
								}
							}
						}
					}
				}				
				
				System.out.println("-----체크인하지 않은 예약자-----");
				count = 0;
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						for (int k = 0; k < 31; k++) {
							Guest tempG = room[i][j].getBooklist().getGuestList()[k];
							if (tempG != null && tempG.getAfterDay() == 0 && room[i][j].getGuest().getName() == null) {
								// room → booklist → guest의 name이 존재?
								// 숙박예정일이 0 ?
								// 해당 room 의 guest가 비어있는지?
								System.out.println(tempG.toString());
								count++;
							}
						}
					}
				}
				if (count == 0) {
					System.out.println("없음");
				} else {
					System.out.println("모두 체크인으로 변경합니다.");
					for (int i = 0; i < floor; i++) {
						for (int j = 0; j < roomsPerFloor; j++) {
							for (int k = 0; k < 31; k++) {
								Guest tempG = room[i][j].getBooklist().getGuestList()[k];
								if (tempG != null && tempG.getAfterDay() == 0 && room[i][j].getGuest().getName() == null) {
									room[i][j].setGuest(room[i][j].getBooklist().getGuestList()[k]);
									room[i][j].setAdmin(loginAdmin);
									System.out.println(room[i][j].getGuest().getName() + "님 체크인되었습니다.");
									if (room[i][j].getGuest().getPayment() == 0) {
										int temp = sales(room, i, j);
										daySales += temp;
										System.out.println(temp + "원 결제되었습니다.");
										totalSalse += temp;
									}
								}
							}
						}
					}
				}
				// 날짜 지나가는 것을 표현
				for (int i = 0; i < floor; i++) {
					for (int j = 0; j < roomsPerFloor; j++) {
						for (int k = 0; k < 31; k++) {
							room[i][j].getBookedDay().dayPass(); // bookedDay에 저장되어있는 boolean 배열 한칸씩 당기기
							// 객실에 있는 숙박일 -1
							name = room[i][j].getGuest().getName();
							if (name != null && room[i][j].getBooklist().getGuestList()[k] != null && room[i][j].getGuest().getStayDay() != 0) {
								// room → guest의 name이 존재? 존재하면 객실에 고객이 있는것
								// 숙박일이 0일 아닌지? 0일이면 체크아웃 시킬 대상임.
								int temp = room[i][j].getGuest().getStayDay() - 1; // 숙박일에서 -1
								room[i][j].getGuest().setStayDay(temp);	 // 숙박일에 -1된 숙박일을 대입
							}
							// 예약자들 숙박예정일 -1
							if (room[i][j].getBooklist().getGuestList()[k] != null && room[i][j].getBooklist().getGuestList()[k].getAfterDay() != 0) {
								// room → booklist → guest의 숙박예정일이 0이 아닌지? (0이면 체크인 대상임)
								// room → booklist → guest의 name이 비어있지 않은지? (비어있으면 예약이 없는것)
								int temp = room[i][j].getBooklist().getGuestList()[k].getAfterDay() - 1;
								room[i][j].getBooklist().getGuestList()[k].setAfterDay(temp);
							}
						}
					}
				}
				daySales = 0;
				System.out.println("☆★☆★☆★하루가 지났습니다☆★☆★☆★");
				break;
				
			case 0:
				System.out.println("프로그램 종료");
				onOff = false;
				break;
				
			default :
				System.out.println("잘못된 입력입니다.");
				break;
			}
			
		}
	}
}