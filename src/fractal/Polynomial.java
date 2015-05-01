package fractal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Polynomial
{
	private List<Map.Entry<Integer,Integer>> poly;
	
	public Polynomial(List<Map.Entry<Integer,Integer>> p)
	{
		poly = p;
	}
	
	private List<Map.Entry<Integer,Integer>> derivative()
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
	
	public ComplexNumber evaluate(ComplexNumber x)
	{
		int exp,coeff;
		ComplexNumber res,temp;
		res = new ComplexNumber(0,0);
		for(Map.Entry<Integer,Integer> e: poly)
		{
			exp = e.getKey();
			coeff = e.getValue();
			temp = x.power(exp);
			temp = temp.scalarMult(coeff);
			res = res.add(temp);
		}
		return res;
	}
	
	public List<Map.Entry<Integer,Integer>> getPolynomial()
	{
		return poly;
	}
	
	public Polynomial getDerivativePolynomial()
	{
		return new Polynomial(derivative());
	}
}