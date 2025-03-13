package pac;

public class Poly {

	public static void main(String[] args) {
		
		String s="x^2+4x=9";
		String array[]=convertToArray(s);
		double b=evaluate(2, array);
	}
	/**
	 * 
	 * @param inputFromUser
	 * @return
	 */
	static String[]convertToArray(String inputFromUser){
		inputFromUser=inputFromUser.toLowerCase();
//		char polynomialArray[]=inputFromUser.toCharArray();
//         return polynomialArray;
				String polynomialArray[]=inputFromUser.split(" ");
		return polynomialArray;
	}
	
	/**
	 * 
	 * @param valueOfVariable
	 * @param polynomialArray
	 * @return
	 */
	static double evaluate(float valueOfVariable, String polynomialArray[]) 
	{
		for(int polynomialArrayIndex=0;polynomialArrayIndex<polynomialArray.length;polynomialArrayIndex++) 
		{
			String ch=polynomialArray[polynomialArrayIndex];
			
			polynomialArray[polynomialArrayIndex]=12+"";
			
//			char ch1=(char)"a";
			
//			if(polynomialArray[polynomialArrayIndex])
//			
			
//			if(polynomialArray[polynomialArrayIndex]>=97 && polynomialArray[polynomialArrayIndex]<=122) {
//				polynomialArray[polynomialArrayIndex]=Character.parseChar(   valueOfVariable);
//				System.out.println(polynomialArray[polynomialArrayIndex]);
//			}
		}
		return 0.7;
		
	}


}
