package Dao;

import java.util.Iterator;
import java.util.Scanner;

import Baseball.BatterDto;
import Baseball.HumanDto;
import Baseball.PitcherDto;
import file.DataProc;

public class BaseballDaoIm1 implements BaseballDao{

	Scanner sc = new Scanner(System.in);
	
	private HumanDto human[];
//	private HumanDto human[] = {
//			new PitcherDto(1001, "홍길동", 24, 172.1, "투수", 10, 3, 0.234),
//			new BatterDto(1002, "성춘향", 16, 157.3, "타자", 15, 5, 0.312),
//			new BatterDto(1003, "일지매", 22, 181.8, "투수", 9, 4, 0.876),
//			new BatterDto(1004, "홍길동", 26, 191.6, "타자", 20, 6, 0.253),
//			new PitcherDto(1005, "홍두께", 25, 175.2, "투수", 9, 6, 0.356),
//			new PitcherDto(1006, "임꺽정", 27, 188.4, "투수", 12, 5, 0.412),
//	};
	
	private int counter;
	
	DataProc dataProc;
	
	public BaseballDaoIm1() {
		human = new HumanDto[10];
		counter = 0;
		
		dataProc = new DataProc("baseball");
		dataProc.createFile();
	}
	
	@Override
	public void insert() {
		System.out.println("선수등록입니다");
		System.out.println("등록할 포지션을 입력해 주세요");
		System.out.println("투수(1)/타자(2) = ");
		int position = sc.nextInt();
		
		System.out.println("선수번호 = ");
		int number = sc.nextInt();
		
		System.out.println("이름 = ");
		String name = sc.next();
		
		System.out.println("나이 = ");
		int age = sc.nextInt();
		
		System.out.println("신장 = ");
		double height = sc.nextDouble();
	
		HumanDto humanDto = null;
		if(position == 1) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			humanDto = new PitcherDto(number, name, age, height, "투수", win, lose, defence);
		}
		else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("히트수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			humanDto = new BatterDto(number, name, age, height, "타자", batCount, hit, hitAvg);
		}
		
		human[counter] = humanDto;
		counter++;
		
		System.out.println("등록되었습니다");
	}	
	
	public void delete() {
		System.out.println("선수삭제입니다");
		System.out.print("삭제할 선수의 이름 = ");
		String name = sc.next();
		
		int index = search(name);
		if(index == -1) {
			System.out.println("선수명단에 없습니다");
			return;
		}
		
//		human[index] = null;
		
		human[index].setNumber(0);
		human[index].setName("");
		human[index].setAge(0);
		human[index].setHeight(0.0);
		
		if(human[index] instanceof PitcherDto) {
			PitcherDto p = (PitcherDto)human[index];
			p.setPosition("");
			p.setWin(0);
			p.setLose(0);
			p.setDefence(0.0);
		}else {
			BatterDto b = (BatterDto)human[index];
			b.setPosition("");
			b.setBatCount(0);
			b.setHit(0);
			b.setHitAvg(0.0);
		}
		System.out.println("삭제되었습니다");			
	}
	@Override
	public void select() {
		System.out.println("선수검색입니다");
		System.out.println("검색할 선수의 이름 = ");
		String name = sc.next();
		
		// 검색된 선수를 카운트
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if(h != null && !h.getName().equals("")) {
				if(name.equals(h.getName())) {
					count++;
				}
			}
		}
		
		if(count == 0) {
			System.out.println("선수 명단에 없습니다");
			return;
		}
		
		// 1명이상일 경우 배열을 확보
		HumanDto findHuman[] = new HumanDto[count];
		int c = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if(h != null && !h.getName().equals("")) {
				if(name.equals(h.getName())) {
					findHuman[c] = human[i];
					count++;
			}
		}
	}
		System.out.println("검색된 선수 명단입니다");
		for (int i = 0; i < findHuman.length; i++) {
			System.out.println(findHuman[i].info());
		}
	}
	public void update() {
		System.out.println("선수수정입니다");
		System.out.println("수정할 선수의 이름 = ");
		String name = sc.next();
		
		int index = this.search(name);
		
		if(index == -1) {
			System.out.println("선수명단에 없습니다");
			return;
		}
		
		if(human[index] instanceof PitcherDto) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			PitcherDto p = (PitcherDto)human[index];
			p.setWin(win);
			p.setLose(lose);
			p.setDefence(defence);
		}
		else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
		}
		
		System.out.println("수정되었습니다");
	}
	
	public void allPrint(){
		for (HumanDto h : human) {
			if(h != null && !h.getName().equals("")) {
				System.out.println(h.info());
			}
		}
	}
	@Override
	public void batSort() {


		HumanDto humanB[] = new HumanDto[10];
		
		// 타자만으로 (배열)구성
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if(h != null && !h.getName().equals("") == false) {
				if(h instanceof BatterDto) {
					humanB[count] = h;
					count++;
				}
			}
		}
		
		for (HumanDto h : humanB) {
			if(h != null) {
				System.out.println(h.info());
			}
		}
		
		// 순위(내림정렬)처리
		HumanDto temp;
		for (int i = 0; i < humanB.length; i++) {
			for (int j = 0; j < humanB.length; j++) {
				
				if(humanB[i] != null && !human[i].getName().equals("")
						&& humanB[j] != null && !humanB[j].getName().equals("")) {
					
					BatterDto b1 = (BatterDto)humanB[i];
					BatterDto b2 = (BatterDto)humanB[j];
					
					if(b1.getHitAvg() < b2.getHitAvg()) { // 비교는 타율로 한다
						temp = humanB[i];
						humanB[i] = humanB[j];
						humanB[j] = temp;
					}
				}
			}
		}
		for (int i = 0; i < humanB.length; i++) {
			if(humanB[i] != null) {
				System.out.println((i + 1) + "위"
						+ " 이름:" + humanB[i].getName()
						+ " 타율:" + ((BatterDto)humanB[i]).getHitAvg());
			}
		}
		
	}

	@Override
	public void pitchSort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		
		// 데이터 수 파악
		int count = 0;
		for (HumanDto h : human) { //foreach는 뭐임?
			if(h != null && !h.getName().equals("")) {
				count++;
			}
		}
		
		if(count == 0) {
			System.out.println("데이터가 없습니다");
			return; // 안전장치
		}
		// (String)배열설정
		String saveDatas[] = new String[count];
		
		// Human object -> String 

		int c = 0;
		for (HumanDto h : human) {// 데이터 취합
			if(h != null && h.getName().equals("")) {
				saveDatas[c] = h.toString();
				c++;
			}
		}
		
		// 저장
		dataProc.dataSave(saveDatas);
		
		System.out.println("저장되었습니다");
	}

	@Override
	public void load() {
		
		String datas[] = dataProc.dataLoad();
		
//		for (String s : datas) { // 확인
//			System.out.println(s);
//		}

		for (int i = 0; i < datas.length; i++) {
			String data[] = datas[i].split("-");
			
			if(data[4].equals("투수")) {
				human[i] = new PitcherDto( Integer.parseInt(data[0]),
										   data[1],
										   Integer.parseInt(data[2]),
										   Double.parseDouble(data[3]),
										   data[4],
										   Integer.parseInt(data[5]),
										   Integer.parseInt(data[6]),
										   Double.parseDouble(data[7]));
			}
			else if(data[4].equals("타자")) {
				human[i] = new BatterDto( Integer.parseInt(data[0]),
										   data[1],
										   Integer.parseInt(data[2]),
										   Double.parseDouble(data[3]),
										   data[4],
										   Integer.parseInt(data[5]),
										   Integer.parseInt(data[6]),
										   Double.parseDouble(data[7]));
			}
		}
		counter = datas.length;
		
		System.out.println("데이터를 로드하였습니다");
	}
	
	public int search(String name) {
		int index = -1;
		for (int i = 0; i < human.length; i++) {
			HumanDto dto = human[i];
			if(name.equals(dto.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}
}