package pac;

import java.util.Scanner;
import java.util.Stack;

public class Poly {
    
	//will store variable eg. x
	static private String variable;
		
	//storing coefficients into array
	private int polynomialArray[];
	
	//will store polynomial
	private  String polynomial;
	
	//for taking int value
	private static Scanner scannerObject=new Scanner(System.in);
	
	//for taking string value
	private static Scanner scannerObjectForString=new Scanner(System.in);
	
	//for controlling loop
	private static boolean isValid=true;
	
	public static void main(String[] args) {
		
		while(isValid) {			
			try {
				
				System.out.println("Select which operation you want to perform\n"
				+"1.Evaluate value of polynomial\n"
				+"2.Evaluate degree of polynomial\n"
				+"3.Add two polynomial\n"
				+"4.Multiply two polynomial\n"
				+"5.Exit");
				
				int input=scannerObject.nextInt();
				
				switch(input) {
				
				//for evaluating value of polynomial
				case 1:
					Poly polyObject=new Poly();
					
					polyObject.polynomial=inputPolynomialStringFromUser();
					
					polyObject.variable=getVariable(polyObject.polynomial);
					
					polyObject.polynomialArray=convertStringToArray(polyObject.polynomial);

					if(polyObject.polynomialArray.length==1) {
						System.out.println("Computed value is "+polyObject.polynomial);
					}
					else {
						System.out.println("Enter value of variable "+polyObject.variable);
						
						float valueOfVariable=scannerObject.nextFloat();
						
						double value=polyObject.evaluatePolynomial(polyObject.polynomialArray,valueOfVariable );
						System.out.println("Computed value is "+value);		
					}
								
					break;
				//for finding degree of polynomial
					
				case 2:
					Poly polyObjectForCase2=new Poly();
					polyObjectForCase2.polynomial=inputPolynomialStringFromUser();
					polyObjectForCase2.polynomialArray=convertStringToArray(polyObjectForCase2.polynomial);
					int degreeOfPolynomial=degreeOfPolynomial(polyObjectForCase2.polynomialArray);
					System.out.println("Degree of polynomial is "+degreeOfPolynomial);
					break;
					
				//for adding 2 polynomial
				case 3:
					Poly polyObjectStringFirst=new Poly();
					Poly polyObjectStringSecond=new Poly();
					
					//get polynomial and resp. variable for first polynomial
	                polyObjectStringFirst.polynomial=inputPolynomialStringFromUser();
					polyObjectStringFirst.variable=getVariable(polyObjectStringFirst.polynomial);
					
					//get polynomial and resp. variable for second polynomial
					polyObjectStringSecond.polynomial=inputPolynomialStringFromUser();
					polyObjectStringSecond.variable=getVariable(polyObjectStringSecond.polynomial);
                    
					String resultantString="0";
					
					//if variables are same, then only operation can be performed
					if(polyObjectStringFirst.variable.equals(polyObjectStringSecond.variable)) {
						polyObjectStringFirst.polynomialArray=polyObjectStringFirst.convertStringToArray(polyObjectStringFirst.polynomial);
						polyObjectStringSecond.polynomialArray=polyObjectStringSecond.convertStringToArray(polyObjectStringSecond.polynomial);

						resultantString=polyObjectStringFirst.additionTwoPolynomial(polyObjectStringFirst, polyObjectStringSecond);
						
						//in case when, sum is 0
						if(resultantString=="") System.out.println("Sum of polynomial is 0");
						
						else System.out.println("Sum of polynomial is "+resultantString);
					}
					
					//or one of them is constaint
					else if(polyObjectStringFirst.variable=="" || polyObjectStringSecond.variable=="") {
						polyObjectStringFirst.polynomialArray=polyObjectStringFirst.convertStringToArray(polyObjectStringFirst.polynomial);
						polyObjectStringSecond.polynomialArray=polyObjectStringSecond.convertStringToArray(polyObjectStringSecond.polynomial);

						
						resultantString=polyObjectStringFirst.additionTwoPolynomial(polyObjectStringFirst, polyObjectStringSecond);
						if(resultantString=="") System.out.println("Sum of polynomial is 0");
					     System.out.println("Sum of polynomial is "+resultantString);
					}
					else {
						System.out.println("Addition cannot be done");
					}
					break;
					
				//for multiplying 2 polynomial
				case 4:
					Poly polyObjectFirstString=new Poly();
					Poly polyObjectSecondString=new Poly();
					
	                polyObjectFirstString.polynomial=inputPolynomialStringFromUser();
					polyObjectFirstString.variable=getVariable(polyObjectFirstString.polynomial);
					
					polyObjectSecondString.polynomial=inputPolynomialStringFromUser();
					polyObjectSecondString.variable=getVariable(polyObjectSecondString.polynomial);
					String productString=0+"";

					//if variables are same, then only operation can be performed
					if(polyObjectFirstString.variable.equals(polyObjectSecondString.variable)) {
						polyObjectFirstString.polynomialArray=polyObjectFirstString.convertStringToArray(polyObjectFirstString.polynomial);
						polyObjectSecondString.polynomialArray=polyObjectSecondString.convertStringToArray(polyObjectSecondString.polynomial);

						productString=polyObjectFirstString.multiplyPolynomial(polyObjectFirstString, polyObjectSecondString);
						if(productString=="")  System.out.println("Product of polynomial is 0");
						else System.out.println("Product of  polynomial is "+productString);
					}
					
					//or 1 of them is constant
					else if(polyObjectFirstString.variable=="" || polyObjectSecondString.variable=="") {
						polyObjectFirstString.polynomialArray=polyObjectFirstString.convertStringToArray(polyObjectFirstString.polynomial);
						polyObjectSecondString.polynomialArray=polyObjectSecondString.convertStringToArray(polyObjectSecondString.polynomial);

						
						productString=polyObjectFirstString.multiplyPolynomial(polyObjectFirstString, polyObjectSecondString);
					     System.out.println("Product of polynomial is "+productString);
					}
					else {
						System.out.println("Multiplication cannot be done");
					}				
					break;
				//for terminating program
				case 5:
					isValid=false;
					System.out.println("Program terminated successfully");
					break;
				//for handling edge cases
				default:
					System.out.println("Select valid option");
					
				}			
			}
			catch(Exception e) {
				if(e.getMessage()==null) {
					System.out.println("Enter valid input");
				}
				else System.out.println(e.getMessage());
				scannerObject.nextLine();
			}
	
		}
			}
	
	/**
	 * will compute sum of 2 polynomial
	 * @param polynomialFirstObject given by user
	 * @param polynomialSecondObject given by user
	 * @return sum of both polynomial
	 */
	public static String additionTwoPolynomial(Poly polynomialFirstObject,
			Poly polynomialSecondObject) {
		
		//initially setting size of sum array to be of length of second polynomial
		int sizeOfSumArray=polynomialSecondObject.polynomialArray.length;
		
		//if polynomial 1st .size is greater than second, update it with first polynomial size
		if(polynomialFirstObject.polynomialArray.length>
		polynomialSecondObject.polynomialArray.length) {
			sizeOfSumArray=polynomialFirstObject.polynomialArray.length;
		}
		
		int sumArray[]=new int[sizeOfSumArray];
		
		
		if(polynomialFirstObject.polynomialArray.length>
		polynomialSecondObject.polynomialArray.length) {
			
			//traversing through smaller polynomial
			for(int secondPolynomialIndex=0;secondPolynomialIndex<
			polynomialSecondObject.polynomialArray.length;
			secondPolynomialIndex++) {
				int sum=polynomialFirstObject.polynomialArray[secondPolynomialIndex]
				+polynomialSecondObject.polynomialArray[secondPolynomialIndex];
				sumArray[secondPolynomialIndex]+=sum;
			
			}
			
			// then traversing through larger polynomial to add remaining elements
			for(int firstPolynomialIndex=polynomialSecondObject.polynomialArray.length
					;firstPolynomialIndex<polynomialFirstObject.polynomialArray.length;firstPolynomialIndex++) {
				int sum=polynomialFirstObject.polynomialArray[firstPolynomialIndex];
				sumArray[firstPolynomialIndex]+=sum;
			
		}}
		else {
			//traversing through smaller polynomial
			for(int firstPolynomialIndex=0;firstPolynomialIndex<
			polynomialFirstObject.polynomialArray.length;
			firstPolynomialIndex++) {
				int sum=polynomialFirstObject.polynomialArray[firstPolynomialIndex]
				+polynomialSecondObject.polynomialArray[firstPolynomialIndex];
					sumArray[firstPolynomialIndex]+=sum;						
					}
			
			// then traversing through larger polynomial to add remaining elements
			for(int secondPolynomialIndex=polynomialFirstObject.polynomialArray.length;
					secondPolynomialIndex<polynomialSecondObject
				     .polynomialArray.length;secondPolynomialIndex++) {
						int sum=polynomialSecondObject.polynomialArray[secondPolynomialIndex];
			            sumArray[secondPolynomialIndex]+=sum;
			}
		}
		
		// conversion of polynomial to resultant sum polynomial
		String sumPolynomial="";
		for(int sumIndex=sumArray.length-1;sumIndex>=0;sumIndex--) {

			if(sumArray[sumIndex]!=0) {

				if(sumArray[sumIndex]>0) {
					sumPolynomial+="+";
					sumPolynomial+=sumArray[sumIndex];
				}
				else {
					
					sumPolynomial+=sumArray[sumIndex];
				}
				if(sumIndex>0) {
					sumPolynomial+=polynomialFirstObject.variable;				    
					if(sumIndex>1) {
						sumPolynomial+="^";
						sumPolynomial+=sumIndex;
					}
				}				
			}
		}		
		return sumPolynomial;		
	}
	
	/**
	 * will compute product of 2 polynomial
	 * @param polynomialFirstObject given by user
	 * @param polynomialSecondObject given by user
	 * @return product of both polynomial
	 */
	public static String multiplyPolynomial(Poly polynomialFirstObject,
			Poly polynomialSecondObject) {
		
		/*setting size of product array to be of length= maximum exponent
		of first polynomial + maximum exponent of first polynomial*/
		int sizeOfProductArray=polynomialFirstObject.polynomialArray
		.length+polynomialSecondObject.polynomialArray.length;
		
		int productArray[]=new int[sizeOfProductArray];
		
		//traversing first element of first polynomial with every elements of second polynomial
		for(int polynomialFirstIndex=0;polynomialFirstIndex<
		polynomialFirstObject.polynomialArray.length;
		polynomialFirstIndex++) {
			
			for(int polynomialSecondIndex=0;polynomialSecondIndex<
					polynomialSecondObject.polynomialArray.length;
					polynomialSecondIndex++) {
				
						double product=polynomialFirstObject.polynomialArray[polynomialFirstIndex]
						*polynomialSecondObject.polynomialArray[polynomialSecondIndex];
//						System.out.println(polynomialFirstObject.polynomialArray.length+" "+" p"+product+" "+polynomialSecondObject.polynomialArray.length);
						int indexOfProduct=polynomialFirstIndex+polynomialSecondIndex;
					
						productArray[indexOfProduct]+=product;						
					}				
		}
	
		//converting product array to polynomial
		String productPolynomial="";
		for(int productIndex=productArray.length-1;productIndex>=0;productIndex--) {
			
			if(productArray[productIndex]!=0) {
				
				if(productArray[productIndex]>0) {
					productPolynomial+="+";
					productPolynomial+=productArray[productIndex];
					
				}
				else {					
					productPolynomial+=productArray[productIndex];
				}
				if(productIndex>0) {
					productPolynomial+=polynomialFirstObject.variable;	
					
					if(productIndex>1) {
						productPolynomial+="^";
						productPolynomial+=productIndex;
					}
				}				
			}
		}		
		return productPolynomial;		
	}
	
	/**
	 * will find character variable present in the polynomial
	 * @param polynomial provided by the user
	 * @return variable present in polynomial
	 * @throws Exception in case more than one variable are present
	 */
        private static String getVariable(String polynomial) throws Exception {
		int asciiValueOfCharacter=0;
		String variable="";
		for(int polynomialIndex=0;polynomialIndex<polynomial.length();
		polynomialIndex++) {
			
			//if character at particular index of polynomial is a lowercase letter
			if(polynomial.charAt(polynomialIndex)>=97 && polynomial.charAt(polynomialIndex)<=122) {
				if(asciiValueOfCharacter==0) {
					asciiValueOfCharacter=polynomial.charAt(polynomialIndex);
			
				}
				else if(asciiValueOfCharacter==polynomial.charAt(polynomialIndex)) {
			
				}
				else if(polynomial.charAt(polynomialIndex)==32) {
					throw new Exception("Enter string without spaces");
				}
				else throw new Exception();
			}
			
		}
		
		//in case no variable is present in a polynomial, it will return empty string
		if(asciiValueOfCharacter>=97 && asciiValueOfCharacter<=122)
		variable=(char)asciiValueOfCharacter+"";
		return variable;
	}
	
    /**
     * return the value of the polynomial for the given value of the variable
     * @param polynomialArray which stores only the coefficients
     * @param valueOfVariable provided by the user
     * @return the value of the polynomial for the given value of the variable
     */
	public static double evaluatePolynomial(int polynomialArray[],float
	valueOfVariable) {
		double result=0;
		for(int polynomialIndex=0;polynomialIndex<polynomialArray.length;
		polynomialIndex++) {
			//calculating exponent value
			double exponentResult=Math.pow(valueOfVariable, polynomialIndex);
			//multiplying with coefficient
			double valueOfTerm=polynomialArray[polynomialIndex]*(exponentResult);
			result+=valueOfTerm;
		}
		return result;
	}
	
	/**
	 * compute the degree of the polynomial
	 * @param polynomialArray which stores only the coefficients
	 * @return degree of the polynomial
	 */
	static int degreeOfPolynomial(int polynomialArray[]) {
		//since elements are stored in sorted order of the exponent value
		return polynomialArray.length-1;
	}
	
	
	/**
	 * 
	 * @return polynomial string provided by user
	 * @throws Exception 
	 */
	static String inputPolynomialStringFromUser() throws Exception{
		System.out.println("Enter single variable polynomial without spaces");
		String inputPolynomial=scannerObjectForString.nextLine();
//		scannerObjectForString.nextLine();
		if(inputPolynomial.contains(" "))throw new Exception("Spaces not allowed");
		
		
		return inputPolynomial;
	}
	
	/**
	 * 
	 * @param polynomial provided by the user
	 * @return int representation of the polynomial
	 * @throws Exception in case of invalid polynomial
	 */
	static int[] convertStringToArray(String polynomial) throws Exception {
		/*
		 * initially we will be calculating max. exponent value present
		 */
		
		//convert all characters to lowercase
		polynomial.toLowerCase();
		
		int asciiValueOfCharacter=0;
		int maximumDegree=0;
		int exponentOfPolynomial=0;
		
		//traversing throughout the polynomial
		for(int polynomialIndex=0;polynomialIndex<polynomial.length();
		polynomialIndex++) {
			 if(polynomial.charAt(polynomialIndex)=='^') {
					//if polynomial is valid
				    if(polynomialIndex<polynomial.length()-1 && 
				    polynomial.charAt(polynomialIndex+1) !='+' &&
				     polynomial.charAt(polynomialIndex+1) !='-'){
				    
				    
				    int innerLoopIndex=polynomialIndex+1;
				     String exponentValue="";
				     
				     //traverse till we get an operator (+ or -1)
				    while(innerLoopIndex<polynomial.length()
				    && polynomial.charAt(innerLoopIndex)!='+' 
				    && polynomial.charAt(innerLoopIndex)!='-'){

				        exponentValue+=polynomial.charAt(innerLoopIndex);
				        innerLoopIndex++;
				    }
				    
				    exponentOfPolynomial=Integer.parseInt(exponentValue);
					
					maximumDegree=Math.max(exponentOfPolynomial, maximumDegree);
				}
				    else throw new Exception();
				
			}
			
			//eg. x exponent will be 1
			else if((polynomial.charAt(polynomialIndex)>=97 && polynomial.charAt(polynomialIndex)<=122 && 
			polynomialIndex+1>=polynomial.length())||(polynomial.charAt(polynomialIndex)>=97 && 
			polynomial.charAt(polynomialIndex)<=122 && 	polynomial.charAt(polynomialIndex+1)!='^')) {
				if(asciiValueOfCharacter==0) {
					asciiValueOfCharacter=polynomial.charAt(polynomialIndex);
					exponentOfPolynomial=1;
					maximumDegree=Math.max(exponentOfPolynomial, maximumDegree);	
				}
				else if(asciiValueOfCharacter==polynomial.charAt(polynomialIndex)) {
					exponentOfPolynomial=1;
					maximumDegree=Math.max(exponentOfPolynomial, maximumDegree);	
				
				}
				else throw new Exception();
	
			}
			else {}
		}

//create polynomial array of size max. degree+1
int polynomialArray[]=new int[maximumDegree+1];

//stack for converting polynomial to array
Stack<String> addCoefficientsToArray=new Stack<>();

int exponent=0;
int coefficient=0;

for(int polynomialIndex=polynomial.length()-1;polynomialIndex>=0;polynomialIndex--) {
	
     //if an operator is encountered, remove elements from stack
	if((polynomial.charAt(polynomialIndex)=='+')|| polynomial.charAt(polynomialIndex)=='-') {
		if(addCoefficientsToArray.size()==0) {
			coefficient=1;
		}
		else {
			String coefficientString="";
			while(addCoefficientsToArray.size()>0) {
				coefficientString+=addCoefficientsToArray.pop();
			}

			coefficient=Integer.parseInt(coefficientString);
		}
		
		//in case operator is negative, convert coefficient into negative of it
		if(polynomial.charAt(polynomialIndex)=='-') {
			coefficient*=-1;
		}
		//add into respective exponent index
		polynomialArray[exponent]+=coefficient;
		exponent=0;
		
	}
	
	//in case of a variable
	else if(polynomial.charAt(polynomialIndex)>=97 && 	polynomial.charAt(polynomialIndex)<=122) {
		if(polynomialIndex+1==polynomial.length() || polynomial.charAt(polynomialIndex+1)=='+'
	|| 	polynomial.charAt(polynomialIndex+1)=='-' || polynomial.charAt(polynomialIndex+2)=='1') {
			exponent=1;
		}
	}
		//in case of power operator, we need to recalculate exponent
		else if(polynomial.charAt(polynomialIndex)=='^') {
			String exponentString="";
			while(addCoefficientsToArray.size()>0) {
				exponentString+=addCoefficientsToArray.pop();
			}
			exponent=Integer.parseInt(exponentString);
		}
	    
	    //in case of digits
		else {
			
			addCoefficientsToArray.push(String.valueOf(polynomial.charAt(polynomialIndex)));
		}	
}

//in case there is coefficient at index 0
if(addCoefficientsToArray.size()>0) {
	String coefficientString="";
	while(addCoefficientsToArray.size()>0) {
		coefficientString+=addCoefficientsToArray.pop();
	}
	coefficient=Integer.parseInt(coefficientString);
	polynomialArray[exponent]+=coefficient;
}

//if not, coefficient would be 1 (eg. x)
else 
	{
	if(polynomial.charAt(0)=='+' || polynomial.charAt(0)=='-' ) {}
	else polynomialArray[exponent]+=1;
	}

return polynomialArray;

	}

}
