package bTools;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class BNF 
{	
	private static DecimalFormat df = new DecimalFormat();
	private static DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	
	public static void setDecimalSeparator(char ds)
	{
		dfs.setDecimalSeparator(ds);
		df.setDecimalFormatSymbols(dfs);
	}
	public static void setExponentSeparator(String es)
	{
		dfs.setExponentSeparator(es);
		df.setDecimalFormatSymbols(dfs);
	}
	public static void setMaximumFractionDigits(int decimals)
	{
		System.out.println("Setting max fraction digits to"+decimals);
		df.setMaximumFractionDigits(decimals);
	}
	public static String sF(double number)
	{
		System.out.println("BNF from SPT Analyzer");
		df.setGroupingUsed(false);	
		return df.format(number);
	}
	public static String sF(float number)
	{
		System.out.println("BNF from SPT Analyzer");
		df.setGroupingUsed(false);	
		return df.format(number);
	}
	public static double truncate(double x)
	{
		System.out.println("BNF from SPT Analyzer");
		int ten = (int) Math.pow(10,df.getMaximumFractionDigits());
//		long y=(long)(x*ten);
		long y = Math.round(x*ten);
	    return (double)y/ten;
	}
	public static double getDecimals()
	{
		return df.getMaximumFractionDigits();
	}
}
