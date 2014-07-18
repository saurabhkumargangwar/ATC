import java.util.*;
class CODEIT03{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		//String arr[]={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December" };
		String  days[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		int t=sc.nextInt();
		while(t-->=1){ 
			int date =sc.nextInt();
			int month =sc.nextInt();
			int year =sc.nextInt();
			int day=solve(date,month,year);
			System.out.println(days[day]);  
		}
		
	}
	private static int solve(int d,int m,int y){
		int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
		//if(m<3)
		y -= (m < 3 ? 1 : 0);
		//y -= m < 3;
		return (y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
	}
}