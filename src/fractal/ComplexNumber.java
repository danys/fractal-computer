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
	
	private double power(double base, int exp)
	{
		if (exp<=0) return 1.0;
		if (exp==1) return base;
		if (exp/2==0) return power(base*base,exp/2);
		else return base*power(base*base,(exp-1)/2);
	}
	
	public ComplexNumber power(int exp)
	{
		if (exp<=0) return new ComplexNumber(1,0);
		if (exp==1) return this;
		double norm = norm();
		double args = arg();
		norm = power(norm,exp);
		args = (args*exp)%(2*Math.PI);
		return new ComplexNumber(norm*Math.cos(args),norm*Math.sin(args));
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