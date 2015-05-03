package fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class FractalComputer
{
	public static double scale(double x,double xmin, double xmax, double cxmin, double cxmax)
	{
		double startdimensionwidth, enddimensionwidth;
		startdimensionwidth=Math.abs(xmin-xmax);
		enddimensionwidth=Math.abs(cxmin-cxmax);
		return ((x/(startdimensionwidth))*enddimensionwidth)-xmin+cxmin;
	}
	
	public static Color computeColor(int i,double s,double nu)
	{
		int r[] = {101,101,101,120,101,52,40,177,139,154,203,215,196,215,215,215};
		int g[] = {215,196,139,101,215,203,154,101,215,40,52,101,215,177,120,101};
		int b[] = {177,215,215,215,120,153,116,215,101,78,102,196,101,101,101,139};
		double it = (double)(i+1)-nu;
		int i1 = (int) Math.floor(it);
		int i2 = i1+1;
		double cr = (r[i2%r.length]<nu*(r[i2%r.length]-r[i1%r.length])) ? r[i2%r.length] : r[i2%r.length]-nu*(r[i2%r.length]-r[i1%r.length]);
		double cg = (g[i2%r.length]<nu*(g[i2%r.length]-g[i1%r.length])) ? g[i2%r.length] : g[i2%r.length]-nu*(g[i2%r.length]-g[i1%r.length]);
		double cb = (b[i2%r.length]<nu*(b[i2%r.length]-b[i1%r.length])) ? b[i2%r.length] : b[i2%r.length]-nu*(b[i2%r.length]-b[i1%r.length]);
		if (cr>255) cr = 255.0;
		if (cg>255) cg = 255.0;
		if (cb>255) cb = 255.0;
		return new Color((int)cr,(int)cg,(int)cb);
		//return new Color((int)((double)r[i%r.length]*s),(int)((double)g[i%g.length]*s),(int)((double)b[i%b.length]*s));
	}
	
	public static void main(String args[])
	{
		//z -> z - (p(z)/p'(z)) Newton fractal formula
		//Example polynomial: z -> z^3-1
		List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>();
		//Map.Entry<Integer,Integer> entry1 = new AbstractMap.SimpleEntry<Integer,Integer>(0,-1);
		//Map.Entry<Integer,Integer> entry2 = new AbstractMap.SimpleEntry<Integer,Integer>(3,1);
		Map.Entry<Integer,Integer> entry1 = new AbstractMap.SimpleEntry<Integer,Integer>(2,1);
		list.add(entry1);
		//list.add(entry2);
		//Polynomial poly = new Polynomial(list);
		//Polynomial dpoly = poly.getDerivativePolynomial();
		int dimensionx,dimensiony,maxiterations;
		double cminx,cminy,cmaxx,cmaxy,minx,maxx,miny,maxy;
		dimensionx=3000;
		dimensiony=2000;
		minx=0;
		miny=0;
		maxx=dimensionx-1;
		maxy=dimensiony-1;
		cminx=-2;
		cminy=-1;
		cmaxx=1;
		cmaxy=1;
		maxiterations=1000;
		ComplexNumber c,temp;
		BufferedImage im = new BufferedImage(dimensionx,dimensiony,BufferedImage.TYPE_INT_ARGB);
		//int maxval=255;
		Color color=null;
		int k;
		double pcount[] = new double[maxiterations+1];
		int bucketindex[][] = new int[dimensionx][dimensiony];
		double nu[][] = new double[dimensionx][dimensiony];
		int ptotal=0;
		for(int i=0;i<=maxiterations;i++) pcount[i] = 0;
		for(int i=0;i<dimensionx;i++)
		{
			for(int j=0;j<dimensiony;j++)
			{
				ptotal = 0;
				c = new ComplexNumber(scale(i,minx,maxx,cminx,cmaxx),scale(j,miny,maxy,cminy,cmaxy));
				temp = new ComplexNumber(0,0);
				k=0;
				while(k<maxiterations && temp.norm()<2)
				{
					//temp = temp.subtract(poly.evaluate(temp).divide(dpoly.evaluate(input)));
					//temp = temp.mult(temp).add(c);
					temp = temp.power(2).add(c);
					k++;
				}
				pcount[k]++;
				bucketindex[i][j]=k;
				nu[i][j]=Math.log(Math.log(temp.norm())/Math.log(2))/Math.log(2);
			}
		}
		for(int i=0;i<=maxiterations;i++) ptotal += pcount[i];
		double sum=0.0;
		for(int i=0;i<=maxiterations;i++)
		{
			sum += pcount[i];
			pcount[i]=sum/(double)ptotal;
		}
		for(int i=0;i<dimensionx;i++)
		{
			for(int j=0;j<dimensiony;j++)
			{
				color = computeColor(bucketindex[i][j],pcount[bucketindex[i][j]],nu[i][j]);
				im.setRGB(i,j, color.getRGB());
			}
		}
		System.out.println("Done");
		try
		{
			ImageIO.write(im, "PNG", new File("C:\\Users\\Dany\\Downloads\\fractal.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
