package fractal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FractalComputer
{
	public class Polynomial
	{
		private List<Map.Entry<Integer,Integer>> poly;
		private List<Map.Entry<Integer,Integer>> dpoly;
		
		public Polynomial(List<Map.Entry<Integer,Integer>> p)
		{
			poly = p;
			dpoly = derivative();
		}
		
		public List<Map.Entry<Integer,Integer>> derivative()
		{
			List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>();
			int exp, coeff;
			for(Map.Entry<Integer,Integer> e: poly)
			{
				coeff = e.getValue();
				exp = e.getKey();
				coeff = coeff*exp;
				exp--;
				Map.Entry<Integer,Integer> map = new AbstractMap.SimpleEntry<Integer,Integer>(exp,coeff);
				list.add(map);
			}
			return list;
		}
		
		public ComplexNumber evaluate(List<Map.Entry<Integer,Integer>> poly,ComplexNumber x)
		{
			
		}
		
		public List<Map.Entry<Integer,Integer>> getPolynomial()
		{
			return poly;
		}
		
		public List<Map.Entry<Integer,Integer>> getDerivativePolynomial()
		{
			return dpoly;
		}
	}
	
	
	public class ComplexNumber
	{
		private int x;
		private int y;
		
		public ComplexNumber(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public ComplexNumber add(ComplexNumber n)
		{
			int xn = n.getReal();
			int yn = n.getImaginary();
			xn += x;
			yn += y;
			return new ComplexNumber(xn,yn);
		}
		
		public ComplexNumber subtract(ComplexNumber n)
		{
			int xn = n.getReal();
			int yn = n.getImaginary();
			return this.add(new ComplexNumber(-xn,-yn));
		}
		
		public ComplexNumber divide(ComplexNumber n)
		{
			int a = n.getReal();
			int b = n.getImaginary();
			return new ComplexNumber((x*a+b*y)/(a*a+b*b),(y*a-x*b)/(a*a+b*b));
		}
		
		public int getReal()
		{
			return x;
		}
		
		public int getImaginary()
		{
			return y;
		}
	}
	public static void main(String args[])
	{
		//z -> z - (p(z)/p'(z))
	}
}
