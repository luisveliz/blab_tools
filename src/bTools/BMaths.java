package bTools;
import Jama.Matrix;

/**
 * @author Luis
 *
 */
public class BMaths 
{	
	
	
	public static double sqr(double x)
	{
		return x*x;
	}
	public static double[] log10(double[] x)
	{
		double[] log = new double[x.length];
		for(int i=0;i<x.length;i++)
			log[i] = Math.log10(x[i]);
		return log;
	}
	public static double[] funcSin(double[] x, double[] p)
	{
		double[] m = new double[x.length];
		for(int i=0;i<x.length;i++)
			m[i]=p[0]*Math.sin(p[1]*x[i]+p[2])+p[3]*x[i]+p[4];
		return m;
		
	}
	
	public static double[] calculaDerivada(double[] y, int h)
	{
		double[] derivada = new double[y.length];
		
		for(int i=0;i<h;i++)
		{
			derivada[h]=0;
			derivada[derivada.length-1-h]=0;			
		}
				
		/**
		 * F�rmula de diferencia progresiva
		 */		
		for(int i=h;i<derivada.length-h;i++)
		{
			derivada[i]=(y[i+h]-y[i])/h;
		}		
		/**
		 * Aproximaci�n central de dos puntos
		 */
		/*for(int i=2;i<derivada.length-2;i++)
		{
			derivada[i]= (y[i+2]-y[i-2])/4;				
		}*/			
		return derivada;			
	}	
	public static double[] suavizar(double[] y, int amplitud)
	{
		double[] suavizado=new double[y.length];
		double suma=0;
		for(int i=0;i<amplitud;i++)
		{
			suavizado[i]=y[i];
			suavizado[y.length-i-1]=y[y.length-i-1];
		}		
		for(int i=amplitud;i<y.length-amplitud;i++)
		{
			suma=0;
//			System.out.println(" ");
			for(int j=0;j<2*amplitud;j++)
			{
				
//				System.out.println("y[]: "+y[i-amplitud+j]);
				suma+=y[i-amplitud+j];
//				System.out.println("suma "+j+":"+suma);
			}
			suavizado[i] = suma/(2*amplitud);
//			System.out.println("suavizado[i]: "+suavizado[i]);
		}
		return suavizado;
	}
	public static int[] suavizar(int[] y, int amplitud)
	{
		int[] suavizado=new int[y.length];
		double suma=0;
		for(int i=0;i<amplitud;i++)
		{
			suavizado[i]=y[i];
			suavizado[y.length-i-1]=y[y.length-i-1];
		}		
		for(int i=amplitud;i<y.length-amplitud;i++)
		{
			suma=0;
//			System.out.println(" ");
			for(int j=0;j<2*amplitud;j++)
			{
				
//				System.out.println("y[]: "+y[i-amplitud+j]);
				suma+=y[i-amplitud+j];
//				System.out.println("suma "+j+":"+suma);
			}
			suavizado[i] = (int)(suma/(2*amplitud));
//			System.out.println("suavizado[i]: "+suavizado[i]);
		}
		return suavizado;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public static double[] linealizar(double[] x,double[] y)
	{
		//System.out.println("length:"+x.length+" "+y.length);
		double[] lineal = new double[y.length];
		/**
		 * M�nimos cuadrados, Xa=Y
		 * 
		 */
		double[][] X = new double[2][2];
		double[][] Y = new double[2][1];
		double[] a = new double[2];
		
		for(int i=0;i<y.length;i++)
		{
			X[0][0]+=1;
			X[0][1]+=x[i];
			X[1][0]+=x[i];
			X[1][1]+=sqr(x[i]);
			Y[0][0]+=y[i];
			Y[1][0]+=x[i]*y[i];			
		}
		//JAMA
	    Matrix XM = new Matrix(X);
	    Matrix YM = new Matrix(Y);
	    Matrix aM = XM.solve(YM);
	    
	    for(int i=0;i<y.length;i++)
	    {
	    	lineal[i]=aM.get(0,0)+aM.get(1,0)*x[i];
	    }		
		return lineal;
	}
	
	/**
	 * @param y
	 * @return
	 */
	public static double pendiente(double[] y)
	{		
		/**
		 * M�nimos cuadrados, Xa=Y
		 * 
		 */
		double[][] X = new double[2][2];
		double[][] Y = new double[2][1];
		double[] a = new double[2];
		
		for(int i=0;i<y.length;i++)
		{
			X[0][0]+=1;
			X[0][1]+=i;
			X[1][0]+=i;
			X[1][1]+=sqr(i);
			Y[0][0]+=y[i];
			Y[1][0]+=i*y[i];			
		}
		//JAMA
	    Matrix XM = new Matrix(X);
	    Matrix YM = new Matrix(Y);
	    Matrix aM = XM.solve(YM);
	    
		return aM.get(1,0);		
	}
	
	/**
	 * @param y
	 * @param fit
	 * @return
	 */
	public static double getSRS(double[] y, double[] fit)
	{
		double error = 0.0f;
		for(int i=0;i<y.length;i++)
			error+= sqr(y[i]-fit[i]);
		return error;		
	}
	
	
	/** Returns R^2, where 1.0 is best.
    <pre>
     r^2 = 1 - SSE/SSD
     
     where:	 SSE = sum of the squares of the errors
                 SSD = sum of the squares of the deviations about the mean.
    </pre>
    */
    public static double getRSquared(double[] y, double[] fit)
    {
    	int numPoints = y.length;
        double mean = sum(y)/numPoints;

        double sumMeanDiffSqr = 0.0;
        for (int i=0; i<numPoints; i++)
            sumMeanDiffSqr += sqr(y[i]-mean);
        
        double rSquared = 0.0;
        if (sumMeanDiffSqr>0.0)
        {
//          rSquared = 1.0 - getSumResidualsSqr()/sumMeanDiffSqr;
        	rSquared = 1.0 - getSRS(y, fit)/sumMeanDiffSqr;
        }
        return rSquared;
    }

    /**  Get a measure of "goodness of fit" where 1.0 is best. */
    public static double getFitGoodness(double[] y, double[] fit, int numParams)
    {
    	int numPoints = y.length;
        double mean = sum(y)/numPoints;
        
        double sumMeanDiffSqr = 0.0;
        int degreesOfFreedom = numPoints - numParams;
        double fitGoodness = 0.0;
        for (int i = 0; i < numPoints; i++) 
            sumMeanDiffSqr += sqr(y[i] - mean);
        
        if (sumMeanDiffSqr > 0.0 && degreesOfFreedom != 0)
        {
//          fitGoodness = 1.0 - (getSumResidualsSqr() / degreesOfFreedom) * ((numPoints) / sumMeanDiffSqr);
        	fitGoodness = 1.0 - (getSRS(y, fit) / degreesOfFreedom) * ((numPoints) / sumMeanDiffSqr);
        }
        return fitGoodness;
    }
	
	/**
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static int coord (int a, int b, int c) 
	{
		return (((a) * (c)) + (b));
	}
	
	/**
	 * Distance between two points
	 * 
	 */
	public static double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(sqr(x2-x1)+sqr(y2-y1));		
	}
	
	/**
	 * @param y1
	 * @param y2
	 * @return
	 */
	public static double diff(double[] y1, double[] y2)
	{
		double diff = 0.0f;
		assert y1.length == y2.length;
		for(int i=0;i<y1.length;i++)
		{
			diff+= Math.abs(y1[i]-y2[i]);
		}
		return diff;
	}
	/**
	 * @param x
	 * @return
	 */
	public static Object[] minmax(double[] x)
	{
		double max = x[0];
		double min = x[0];
		int maxIndex = 0;
		int minIndex = 0;
		for(int i=0;i<x.length;i++)
		{
			if(x[i]>max)
			{
				max = x[i];
				maxIndex = i;
			}
			if(x[i]<min)
			{
				min = x[i];
				minIndex = i;
			}
		}
		return new Object[]{minIndex,min,maxIndex,max};
	}
	public static int[] max(int[] x)
	{
		int max = x[0];
		int maxIndex = 0;
		for(int i=0;i<x.length;i++)
		{
			if(x[i]>max)
			{
				max = x[i];
				maxIndex = i;
			}
		}
		return new int[]{maxIndex,max};
	}
	public static Object[] maxO(double[] x)
	{
		double max = x[0];
		int maxIndex = 0;
		for(int i=0;i<x.length;i++)
		{
			if(x[i]>max)
			{
				max = x[i];
				maxIndex = i;
			}
		}
		return new Object[]{maxIndex,max};
	}
	
	/**
	 * @param x
	 * @return
	 */
	public static double max(double[] x)
	{
		double max = x[0];
		for(int i=0;i<x.length;i++)
		{
			if(x[i]>max)
				max=x[i];
		}
		return max;
	}
	public static float max(float[] x)
	{
		float max = x[0];
		for(int i=0;i<x.length;i++)
		{
			if(x[i]>max)
				max=x[i];
		}
		return max;
	}
	/**
	 * @param x
	 * @return
	 */
	public static double min(double[] x)
	{
		double min = x[0];
		for(int i=0;i<x.length;i++)
		{
			if(x[i]<min)
				min=x[i];
		}
		return min;
	}	
	public static float min(float[] x)
	{
		float min = x[0];
		for(int i=0;i<x.length;i++)
		{
			if(x[i]<min)
				min=x[i];
		}
		return min;
	}
	
	public static double sum(double[] x)
	{
		double sum = 0;
		for (int i = 0; i < x.length; i++) 
        	sum += x[i];
		return sum;
	}
	
	/**
	 * @param x
	 * @return
	 */
	public static double avg(double[] x)
	{
		double avg=0;
		for(int i=0;i<x.length;i++)
			avg+=x[i];
		return avg/=x.length;
	}
	public static float avg(float[] x)
	{
		float avg=0;
		for(int i=0;i<x.length;i++)
		{
			avg+=x[i];
		}
		return avg/=x.length;
	}
	public static double avg(int[] x)
	{
		double avg=0;
		for(int i=0;i<x.length;i++)
		{
			avg+=x[i];
		}
		return avg/=x.length;
	}
	public static double[] avgstd(double[] x)
	{
		double mean = avg(x);
		double std = 0;
		for(int i=0;i<x.length;i++)
			std+=sqr(x[i]-mean);
		return new double[]{mean,Math.sqrt(std/x.length)};
	}
	
	public static double[] getSubArray(double[] array, int startIndex, int finalIndex)
	{
		if(finalIndex>startIndex && finalIndex<array.length)
		{
			double[] subarray = new double[finalIndex-startIndex+1];
			for(int i=startIndex;i<=finalIndex;i++)
				subarray[i-startIndex]=array[i];
			return subarray;
		}
		else
			return null;	
		
	}
	
	public static double[] toDouble(int[] x)
	{
		double[] y = new double[x.length];
		for(int i=0;i<x.length;i++)
		{
			y[i] = Integer.valueOf(x[i]).doubleValue();
		}
		return y;
	}
	
}



















