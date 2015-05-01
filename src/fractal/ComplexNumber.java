package fractal;

public class ComplexNumber
{
	private double x;
	private double y;
	
	public ComplexNumber(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public ComplexNumber add(ComplexNumber n)
	{
		double xn = n.getReal();
		double yn = n.getImaginary();
		xn += x;
		yn += y;
		return new ComplexNumber(xn,yn);
	}
	
	public ComplexNumber subtract(ComplexNumber n)
	{
		double xn = n.getReal();
		double yn = n.getImaginary();
		return this.add(new ComplexNumber(-xn,-yn));
	}
	
	public ComplexNumber mult(ComplexNumber n)
	{
		double a = n.getReal();
		double b = n.getImaginary();
		return new ComplexNumber(a*x-b*y,b*x+a*y);
	}
	
	public ComplexNumber divide(ComplexNumber n)
	{
		double a = n.getReal();
		double b = n.getImaginary();
		return new ComplexNumber((x*a+b*y)/(a*a+b*b),(y*a-x*b)/(a*a+b*b));
	}
	
	public double norm()
	{
		return Math.sqrt(x*x+y*y);
	}
	
	public double arg()
	{
		return Math.atan2(y, x);
	}
	
	public ComplexNumber scalarMult(int c)
	{
		return new ComplexNumber(x*c,y*c);
	}
	
	public ComplexNumber power(int exp)
	{
		return power(this,exp);
	}
	
	private ComplexNumber power(ComplexNumber base, int exp)
	{
		if (exp<=0) return new ComplexNumber(1,0);
		if (exp==1) return base;
		if (exp%2==0) return power(base.mult(base),exp/2);
		else return base.mult(power(base.mult(base),(exp-1)/2));
	}
	
	public double getReal()
	{
		return x;
	}
	
	public double getImaginary()
	{
		return y;
	}
}