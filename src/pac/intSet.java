package pac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

final public class intSet {

	private int set[];
	
	//private constructor for initializing set
	private  intSet(List<Integer> listOfNumber, intSet intSetListObject) {
		intSetListObject.set=new int[listOfNumber.size()];
//		intSetLis.set=new int[listOfNumber.size()];=
		Arrays.fill(intSetListObject.set, Integer.MAX_VALUE);
		for(int listIndex=0;listIndex<listOfNumber.size();listIndex++) {
			intSetListObject.set[listIndex]=listOfNumber.get(listIndex);	
		}
	}
	
	private intSet() {
		
	}
    //for initializing set
	private static intSet  intSetObject=new intSet();
	
	//for taking input from user
	private static Scanner scannerObject=new Scanner(System.in);
	
	//for controlling loop
	private static  boolean isValid=true;
	
	
	public static void main(String[] args) {
		while(isValid) {
			
			try {
				
				addingItemsInSet(intSetObject);
				isValid=false;
			}
			catch(Exception e) {
				System.out.println("Enter valid numbers");
				scannerObject.nextLine();
			}
		}
		isValid=true;
		while(isValid) {
			try {
				
				System.out.println("Choose which operation you want to perform\n"
			     +"1.If x is a member of set\n"+"2.Compute size of set\n"
				 +"3.Check whether s is a subset of set\n"
			     +"4.Get complement of set\n"+"5.Find union of set\n"
				 +"6.Exit");
				int input=scannerObject.nextInt();
				switch(input) {
				
				//for checking if provided element is a member of set
				case 1:
					System.out.println("Enter value of X");
					int x=scannerObject.nextInt();
					boolean ifMember=isMember(x);
					if(ifMember)System.out.println("X is a member of set");
					else System.out.println("X is not a member of x");
					break;
					
				//for returning size of set
				case 2:
					int sizeOfSet=sizeOfSet();
					System.out.println("Size of set is "+sizeOfSet);
					break;
					
				//check if provided set is subset of the set
				case 3:
					//creating new set object for calculating subset
					intSet newSetObject=new intSet();
					addingItemsInSet(newSetObject);
					if(newSetObject.sizeOfSet()>intSetObject.sizeOfSet()) {
						System.out.println("Not a subset!!");
					}
					else {
						boolean isSubset=newSetObject.isSubSet(newSetObject);
						if(isSubset)System.out.println("Provided set is a subset of set");
						else System.out.println("Not a subset ");
					}
					
					break;
					
				//return complement of set	
				case 4:
					List<Integer> complementSet=getComplement();
					System.out.println("Complement of set is "+complementSet);					
					break;
					
				//finding union of set	
				case 5:
					//creating new set object for calculating union set
					intSet newSetObjectForUnion=new intSet();
					addingItemsInSet(newSetObjectForUnion);
					//get union of both sets
					List<Integer> unionSet=union(newSetObjectForUnion);
					System.out.println("Union of set is "+unionSet);	
					break;
					
				//for terminating program	
				case 6:
					System.out.println("Program terminated successfully");
					isValid=false;
					
				//for handling edge cases	
				default:
					System.out.println("Choose valid option");
				 
				}				
			}
			catch(Exception e) {
				System.out.println("Enter valid input");
				scannerObject.nextLine();				
			}
		}
	}
	
	/**
	 * will print the set
	 * @param {set} represents set provided by the user
	 */
	private static void  printSet(int set[]) {
		for(int setIndex=0;setIndex<set.length;setIndex++) {
			System.out.println(set[setIndex]+" ");
		}
	}
	
	/**
	 * add items in the set from the set
	 * @param {intSetObject} for declaring set
	 * @throws {Exception} in case user enters string or char or value greater then 1000
	 */
	static public void addingItemsInSet(intSet intSetObject) throws Exception {
		System.out.println("Enter number of items you want to add in set\n"
		+"Maximum items allowed is 1000");
		int sizeOfSet=scannerObject.nextInt();
		if(sizeOfSet>1001 || sizeOfSet<=0)throw new Exception();
		List<Integer> listItems=new ArrayList<>();
		System.out.println("Enter elements of set");
		
		//adding items into list
		for(int listIndex=0;listIndex<sizeOfSet;listIndex++) {
			int itemsOfSet=scannerObject.nextInt();
			
			//in case of duplicate elements, don't add
			if(listItems.contains(itemsOfSet)) {
				System.out.println("Item already present");
				listIndex--;
			}
			else {
				//if element not in the range, don't add
				if(itemsOfSet>0 && itemsOfSet<1001) {
					listItems.add(itemsOfSet);
				}
				else {
					System.out.println("Enter no. between 1-1000");
					listIndex--;
				}
			}
			
		}
		intSetObject=new intSet(listItems,intSetObject);
	}
	
	/**
	 * return boolean value whether x is an element of the set or not
	 * @param x element provided by the user
	 * @return whether element is a member of the set
	 */
	static public boolean isMember(int x) {
		for(int intSetObjectIndex=0;intSetObjectIndex<intSetObject.set.length;intSetObjectIndex++) {
			if(intSetObject.set[intSetObjectIndex]==x)return true;
		}
		return false;
		

	}
	
	/**
	 *  will find and return size of the set
	 * @return size of the set
	 */
	static public int sizeOfSet() {
		return intSetObject.set.length;

	}
	/**
	 * 
	 * @param s represents set provided by the user
	 * @return whether s is a subset of the set
	 */
	public boolean isSubSet(intSet s) {
		
		Set<Integer> hashSet=new HashSet<>();
		
		for(int elementOfSet : intSetObject.set) {
			hashSet.add(elementOfSet);
		}
		
		for(int elementOfSubSet : s.set) {
			if(!hashSet.contains(elementOfSubSet)) {
				return false;
			}
		}
		
		return true;
	}
	/**
	 * function finds complement of a set
	 * @return complement of set
	 */
	static public List<Integer> getComplement() {
		List<Integer> complementList=new ArrayList<>();
	
		Arrays.sort(intSetObject.set);
		int index=0;
		for(int indxOfSet=1;indxOfSet<=1000;indxOfSet++) {
			if(index<intSetObject.set.length)
			{
				if(indxOfSet!=intSetObject.set[index])
					complementList.add(indxOfSet);
				else {
					index++;
				}
			}
			else {
				complementList.add(indxOfSet);
			}			
		}
		return complementList;
		
	}
	
	/**
	 * will find union of both sets
	 * @param s intset object for calling its set
	 * @return union set
	 */
	static public List<Integer> union(intSet s) {
		List<Integer> unionList=new ArrayList<>();
		
		Arrays.sort(intSetObject.set);
		Arrays.sort(s.set);
		//traversing through smaller set first then second to avoid out of bound exception
		if(intSetObject.set.length>s.set.length) {
			
			for(int intSetIndex=0;intSetIndex<s.set.length;intSetIndex++) {

					if(unionList.contains(s.set[intSetIndex])) {}
					else unionList.add(s.set[intSetIndex]);
					if(unionList.contains(intSetObject.set[intSetIndex])) {}
					else unionList.add(intSetObject.set[intSetIndex]);

			}
			
			for(int intSetObjectIndex=s.set.length;intSetObjectIndex<intSetObject.set.length;intSetObjectIndex++) {

					if(unionList.contains(intSetObject.set[intSetObjectIndex])) {}
					else unionList.add(intSetObject.set[intSetObjectIndex]);

			}
		}
		
		else {
			
			//traversing through smaller set first then second to avoid out of bound exception
			for(int intSetObjectIndex=0;intSetObjectIndex<intSetObject.set.length;intSetObjectIndex++) {

					if(unionList.contains(s.set[intSetObjectIndex])) {}
					else unionList.add(s.set[intSetObjectIndex]);
					if(unionList.contains(intSetObject.set[intSetObjectIndex])) {}
					else unionList.add(intSetObject.set[intSetObjectIndex]);

			}
			for(int intSetIndex=intSetObject.set.length;intSetIndex<s.set.length;intSetIndex++) {
				

					if(unionList.contains(s.set[intSetIndex])) {}
					else unionList.add(s.set[intSetIndex]);

			}
		}
		
		return unionList;

	}
}
