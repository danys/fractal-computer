package fractal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FractalComputer
{
	
	
	public static void main(String args[])
	{
		//z -> z - (p(z)/p'(z)) Newton fractal formula
		//Example polynomial: z -> z^3-1
		List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>();
		Map.Entry<Integer,Integer> entry1 = new AbstractMap.SimpleEntry<Integer,Integer>(0,-1);
		Map.Entry<Integer,Integer> entry2 = new AbstractMap.SimpleEntry<Integer,Integer>(3,1);
		list.add(entry1);
		list.add(entry2);
		Polynomial poly = new Polynomial(list);
		Polynomial dpoly = poly.getDerivativePolynomial();
		ComplexNumber input;
		for(double x=-1;x<=1;x+=0.01)
		{
			for(double y=-1;y<=1;y+=0.01)
			{
				input = new ComplexNumber(x,y);
				ComplexNumber res = input.subtract(poly.evaluate(input).divide(dpoly.evaluate(input)));
				System.out.println("Complex value at point "+x+"+i*"+y+" = "+res.getReal()+"+i*"+res.getImaginary());
			}
		}
	}
}
