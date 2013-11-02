package bTools;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.resources.JFreeChartResources;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BPlots 
{	
	public static ChartPanel voidChartPanel()
	{			
		XYPlot plot = new XYPlot();				
		JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);			
		ChartPanel panel = new ChartPanel(chart);
		panel.setBackground(Color.BLACK);
		return panel;		
	}

	public static JFreeChart buildTrajMSDPlot_Chart(int[] frames, double[] msd)
	{
		if(frames.length==msd.length)
		{
			System.out.println("construyendo gráfico MSD_Chart");			
			XYSeries series = new XYSeries("Name");				
			for(int i=0;i < msd.length;i++)
			{
				series.add(frames[i],msd[i]);		
			}			
			XYSeriesCollection xySeries = new XYSeriesCollection(series);			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);
					
			return chart;	
		}
		else
		{
			System.out.println("Error!!!! frame.length!=msd.length");
			return null;
		}
	}
	
	public static ChartPanel buildTrajMSDPlot(int[] frames, double[] msd)
	{
		if(frames.length==msd.length)
		{
			System.out.println("construyendo gráfico MSD");			
			XYSeries series = new XYSeries("Name");				
			for(int i=0;i < msd.length;i++)
			{
				series.add(frames[i],msd[i]);		
			}			
			XYSeriesCollection xySeries = new XYSeriesCollection(series);			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);		
			
			
			ChartPanel panel = new ChartPanel(chart);
			return panel;	
		}
		else
		{
			System.out.println("Error!!!! frame.length!=msd.length");
			return null;
		}
	}
	public static ChartPanel buildTrajMSDCurveFittedPlot(int[] frames, double[] msd, double[] curveFitted)
	{
		if(frames.length==msd.length)
		{
			System.out.println("construyendo gráfico MSD");			
			
			XYSeries series = new XYSeries("MSD");			
			for(int i=0;i < msd.length;i++)
			{
				series.add(frames[i],msd[i]);		
			}			
			XYSeriesCollection xySeries = new XYSeriesCollection(series);			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);

			XYSeries series2 = new XYSeries("MSD Curve Fitted");
			int initialFrame = frames[0];
			for(int i=0;i < curveFitted.length;i++)
			{
				//System.out.println("i: "+i+" CurveFittedValue: "+curveFitted[i]);
				series2.add(initialFrame+i,curveFitted[i]);		
			}			
			XYSeriesCollection xySeries2 = new XYSeriesCollection(series2);
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			renderer2.setSeriesLinesVisible(1, true);
	        renderer2.setSeriesShapesVisible(0, false);
	        renderer2.setSeriesPaint(0, Color.BLUE);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setDataset(1,xySeries2);
			plot.setRenderer(1,renderer2);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			//plot.setDomainGridlinesVisible(true);
					
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);			
			ChartPanel panel = new ChartPanel(chart);
			return panel;
		}
		else
		{
			System.out.println("Error!!!! frame.length!=msd.length");
			return null;
		}
	}
	public static JFreeChart buildTrajMSDCurveFittedPlot_Chart(int[] frames, double[] msd, double[] curveFitted)
	{
		if(frames.length==msd.length)
		{
			System.out.println("construyendo gráfico MSD");			
			
			XYSeries series = new XYSeries("MSD");			
			for(int i=0;i < msd.length;i++)
			{
				series.add(frames[i],msd[i]);		
			}			
			XYSeriesCollection xySeries = new XYSeriesCollection(series);			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);

			XYSeries series2 = new XYSeries("MSD Curve Fitted");
			int initialFrame = frames[0];
			for(int i=0;i < curveFitted.length;i++)
			{
				//System.out.println("i: "+i+" CurveFittedValue: "+curveFitted[i]);
				series2.add(initialFrame+i,curveFitted[i]);		
			}			
			XYSeriesCollection xySeries2 = new XYSeriesCollection(series2);
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			renderer2.setSeriesLinesVisible(1, true);
	        renderer2.setSeriesShapesVisible(0, false);
	        renderer2.setSeriesPaint(0, Color.BLUE);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setDataset(1,xySeries2);
			plot.setRenderer(1,renderer2);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			//plot.setDomainGridlinesVisible(true);
					
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);			
			return chart;
		}
		else
		{
			System.out.println("Error!!!! frame.length!=msd.length");
			return null;
		}
	}
	
	/**
	 * @param time
	 * @param msd
	 * @param curveFitted
	 * @param flags
	 * @return
	 */
	public static JFreeChart buildMultipleMSDFittedPlot(int[] time, double[] msd, double[][] curveFitted, boolean[] flags)
	{
		System.out.println("construyendo gráfico MSD Múltiple");			
		
		XYSeries msdSeries = new XYSeries("MSD");			
		for(int i=0;i < time.length;i++)
		{
			msdSeries.add(time[i],msd[i]);		
		}			
		XYSeriesCollection msdSeriesCollection = new XYSeriesCollection(msdSeries);			
		XYDotRenderer msdDotRenderer = new XYDotRenderer();
		msdDotRenderer.setSeriesPaint(0, Color.RED);
		msdDotRenderer.setDotHeight(2);
		msdDotRenderer.setDotWidth(2);
		

		XYSeries msdAutomaticSeries = new XYSeries("Automatic");
		XYSeries msdCorralledSeries = new XYSeries("Corralled");
		XYSeries msdAnomalousSeries = new XYSeries("Anomalous");
		XYSeries msdDirectedSeries = new XYSeries("Directed");
		
		for(int i=0;i < time.length;i++)
		{
			msdAutomaticSeries.add(time[i],curveFitted[0][i]);
			msdCorralledSeries.add(time[i],curveFitted[1][i]);
			msdAnomalousSeries.add(time[i],curveFitted[2][i]);
			msdDirectedSeries.add(time[i],curveFitted[3][i]);
		}
		
		XYSeriesCollection fittedMSDSeriesCollection = new XYSeriesCollection();
		if(flags[0])
			fittedMSDSeriesCollection.addSeries(msdAutomaticSeries);
		if(flags[1])
			fittedMSDSeriesCollection.addSeries(msdCorralledSeries);
		if(flags[2])
			fittedMSDSeriesCollection.addSeries(msdAnomalousSeries);
		if(flags[3])
			fittedMSDSeriesCollection.addSeries(msdDirectedSeries);
		
		XYLineAndShapeRenderer fitMSDLineRenderer = new XYLineAndShapeRenderer();
		fitMSDLineRenderer.setSeriesLinesVisible(0, true);
		fitMSDLineRenderer.setSeriesLinesVisible(1, true);
		fitMSDLineRenderer.setSeriesLinesVisible(2, true);
		fitMSDLineRenderer.setSeriesLinesVisible(3, true);
        fitMSDLineRenderer.setSeriesShapesVisible(0, false);
        fitMSDLineRenderer.setSeriesShapesVisible(1, false);
        fitMSDLineRenderer.setSeriesShapesVisible(2, false);
        fitMSDLineRenderer.setSeriesShapesVisible(3, false);
        fitMSDLineRenderer.setSeriesPaint(0, Color.BLUE);
        fitMSDLineRenderer.setSeriesPaint(1, Color.GREEN);
        fitMSDLineRenderer.setSeriesPaint(2, Color.YELLOW);
        fitMSDLineRenderer.setSeriesPaint(3, Color.MAGENTA);
        
		final ValueAxis domainAxis = new NumberAxis("Time");
		final ValueAxis rangeAxis = new NumberAxis("MSD");			
		XYPlot plot = new XYPlot();
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		plot.setDataset(0,msdSeriesCollection);
		plot.setRenderer(0,msdDotRenderer);
		plot.setDataset(1,fittedMSDSeriesCollection);
		plot.setRenderer(1,fitMSDLineRenderer);
		plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		//plot.setDomainGridlinesVisible(true);
				
		JFreeChart chart = new JFreeChart("MSD vs Time",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
		chart.setAntiAlias(true);			
		return chart;		
	}
	
	public static JFreeChart buildTypeOfMotionResume(int corralled, int anomalous, int normal, int directed)
	{
		System.out.println("construyendo resume plot");
		//Gráfico con JFreeChart :)
		
		 // row keys...
        final String series1 = "Corralled";
        final String series2 = "Anomalous";
        final String series3 = "Normal";
        final String series4 = "Directed";

        // column keys...
        final String category1 = "";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(corralled, series1, category1);
        dataset.addValue(anomalous, series2, category1);
        dataset.addValue(normal, series3, category1);
        dataset.addValue(directed, series4, category1);
		
		
		
		// create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Resume",         // chart title
            "Type of motion",               // domain axis label
            "Trajectories",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
		
	}
	
	
	public static JFreeChart buildTrajIntensityPlot(int[] frames, double[] intensities)
	{
		if(frames.length==intensities.length)
		{
			System.out.println("construyendo Intensity Plot");
			//Gráfico con JFreeChart :)
			XYSeries series = new XYSeries("NameSerie");	
			
			for(int i=0;i < frames.length;i++){
				series.add(frames[i],intensities[i]);		
			}
			
			//XYDataset xyDataset = new XYSeriesCollection(series);
			XYSeriesCollection xySeries = new XYSeriesCollection(series);				
			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
					
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);
			//ChartPanel panel = new ChartPanel(chart);
			
			return chart;	
		}
		else
		{
			System.out.println("Error!!!! frame.length!=intensities.length");
			return null;
		}
	}
	public static JFreeChart buildTrajIntensityXYPlot(double[] intensitiesX, double[] intensitiesY)
	{
		if(intensitiesX.length==intensitiesY.length)
		{
			//System.out.println("construyendo IntensityXY Plot");
			//Gráfico con JFreeChart :)
			XYSeries series = new XYSeries("Intensities X");	
			for(int i=0;i < intensitiesX.length;i++){
				series.add(i+1,intensitiesX[i]);		
			}
			XYSeries intensitiesYseries = new XYSeries("Intensities Y");
			for(int i=0;i < intensitiesY.length;i++){
				intensitiesYseries.add(i+1,intensitiesY[i]);		
			}
			
			XYSeriesCollection xySeries = new XYSeriesCollection(series);
			xySeries.addSeries(intensitiesYseries);
			
			
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.BLUE);
			renderer.setSeriesPaint(1, Color.RED);
			renderer.setBaseShapesVisible(false);
			renderer.setBaseLinesVisible(true);
			
			
			final ValueAxis domainAxis = new NumberAxis("XY");
			final ValueAxis rangeAxis = new NumberAxis("Intensity");
			rangeAxis.setRange(0, 260);
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(xySeries);
			plot.setRenderer(renderer);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
					
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);
			//XYPlot plot2 = chart.getXYPlot();
			
			
			//ChartPanel panel = new ChartPanel(chart);
			
			return chart;	
		}
		else
		{
			System.out.println("Error!!!! intensitiesX.length!=intensitiesY.length");
			return null;
		}
	}
	
	
	public static JFreeChart buildTrajVelocityPlot(int[] frames, double[] velocity)
	{
		if(frames.length==velocity.length)
		{
			System.out.println("construyendo Velocity Plot");
			//Gráfico con JFreeChart :)
			XYSeries series = new XYSeries("NameSerie");	
			
			for(int i=0;i < frames.length;i++){
				series.add(frames[i],velocity[i]);		
			}
			XYSeriesCollection xySeries = new XYSeriesCollection(series);				
			
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setDotHeight(2);
			renderer.setDotWidth(2);
			
			final ValueAxis domainAxis = new NumberAxis("Frame");
			final ValueAxis rangeAxis = new NumberAxis("MSD");			
			XYPlot plot = new XYPlot();
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(0,xySeries);
			plot.setRenderer(0,renderer);
			plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

					
			JFreeChart chart = new JFreeChart("Titulo",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
			chart.setAntiAlias(true);			
			//ChartPanel panel = new ChartPanel(chart);
			return chart;	
		}
		else
		{
			System.out.println("Error!!!! frame.length!=velocity.length");
			return null;
		}
	}
	public static JFreeChart buildTrajDoublePlot(int[] frames, double[] intensity, double[] velocity)
	{		
		System.out.println("construyendo Intensity Plot");
		XYSeries series = new XYSeries("Intensity");		
		for(int i=0;i < frames.length;i++)
		{
			series.add(frames[i],intensity[i]);		
		}
		XYSeriesCollection xySeries = new XYSeriesCollection(series);		
		
		XYItemRenderer renderer1 = new StandardXYItemRenderer();
		NumberAxis rangeAxis1 = new NumberAxis("Intensity");
		XYPlot subplot1 = new XYPlot(xySeries, null, rangeAxis1, renderer1);		
		subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		
		XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
		annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
		annotation.setRotationAngle(Math.PI / 4.0);
		subplot1.addAnnotation(annotation);
		
		// create subplot 2...
		System.out.println("construyendo Velocity Plot");
		//Gráfico con JFreeChart :)
		XYSeries series2 = new XYSeries("Velocity");	
		
		for(int i=0;i < frames.length;i++){
			series2.add(frames[i],velocity[i]);		
		}		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries2 = new XYSeriesCollection(series2);
				
		XYItemRenderer renderer2 = new StandardXYItemRenderer();
		NumberAxis rangeAxis2 = new NumberAxis("Velocity");
		rangeAxis2.setAutoRangeIncludesZero(false);
		XYPlot subplot2 = new XYPlot(xySeries2, null, rangeAxis2, renderer2);
		subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);		
		
		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Frame"));		
		plot.setGap(10.0);
		
		plot.add(subplot1, 1);
		plot.add(subplot2, 1);
		plot.setOrientation(PlotOrientation.VERTICAL);
		JFreeChart chart= new JFreeChart("Intensity & Velocity", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		
//	 	Creación del panel con el gráfico
		//ChartPanel panel = new ChartPanel(chart);
		return chart;
		
	}
	public static void plotJFreeChart(double[] y, String nameSerie)
	{
		//Gráfico con JFreeChart :)		
		XYSeries series = new XYSeries(nameSerie);	
		
		for(int i=0;i < y.length;i++){
			series.add(i,y[i]);		
		}
		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries = new XYSeriesCollection(series);				
		
		JFreeChart chart = ChartFactory.createXYLineChart(nameSerie,  // Title
		                     "x",           // X-Axis label
		                     "y",           // Y-Axis label
		                     xySeries,          // Dataset
		                     PlotOrientation.VERTICAL,
		                     true,               // Show legend
		                     true,				  // Show tooltips
		                     true                // Show urls
		                    );
		chart.setAntiAlias(true);		
				
		// 	Creación del panel con el gráfico
		ChartPanel panel = new ChartPanel(chart);
		
		JFrame ventana = new JFrame(nameSerie);
		ventana.getContentPane().add(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
	public static void plotJFreeChart(int[] x, double[] y, String nameSerie)
	{
		//Gráfico con JFreeChart :)		
		XYSeries series = new XYSeries(nameSerie);	
		
		for(int i=0;i < y.length;i++){
			series.add(x[i],y[i]);		
		}
		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries = new XYSeriesCollection(series);				
		
		JFreeChart chart = ChartFactory.createXYLineChart(nameSerie,  // Title
		                     "x",           // X-Axis label
		                     "y",           // Y-Axis label
		                     xySeries,          // Dataset
		                     PlotOrientation.VERTICAL,
		                     true,               // Show legend
		                     true,				  // Show tooltips
		                     true                // Show urls
		                    );
		chart.setAntiAlias(true);		
				
		// 	Creación del panel con el gráfico
		ChartPanel panel = new ChartPanel(chart);
		
		JFrame ventana = new JFrame(nameSerie);
		ventana.getContentPane().add(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
	public static void plotJFreeChart(double[] y, double[] y2, String nameSerie1,String nameSerie2)
	{
		//Gráfico con JFreeChart :)		
		XYSeries series = new XYSeries(nameSerie1);
		XYSeries series2 = new XYSeries(nameSerie2);	
		
		
		for(int i=0;i < y.length;i++){
			series.add(i,y[i]);		
		}
		for(int i=0;i < y2.length;i++){
			series2.add(i,y2[i]);				
		}
		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries = new XYSeriesCollection(series);
		xySeries.addSeries(series2);	
		
		JFreeChart chart = ChartFactory.createXYLineChart("MSD vs tiempo",  // Title
		                     "tiempo",           // X-Axis label
		                     "MSD",           // Y-Axis label
		                     xySeries,          // Dataset
		                     PlotOrientation.VERTICAL,
		                     true,               // Show legend
		                     true,				  // Show tooltips
		                     true                // Show urls
		                    );
		chart.setAntiAlias(true);		
				
		// 	Creación del panel con el gráfico
		ChartPanel panel = new ChartPanel(chart);
		
		JFrame ventana = new JFrame("MSD vs Time");
		ventana.getContentPane().add(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
	public static void plotJFreeChart(double[] y, double[] y2, double[] y3, 
			String nameSerie1, String nameSerie2, String nameSerie3)
	{
		//Gráfico con JFreeChart :)		
		XYSeries series = new XYSeries(nameSerie1);
		XYSeries series2 = new XYSeries(nameSerie2);
		XYSeries series3 = new XYSeries(nameSerie3);
		
		
		for(int i=0;i < y.length;i++){
			series.add(i,y[i]);		
		}
		for(int i=0;i < y2.length;i++){
			series2.add(i,y2[i]);				
		}
		for(int i=0;i < y3.length;i++){
			series3.add(i,y3[i]);				
		}
		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries = new XYSeriesCollection(series);
		xySeries.addSeries(series2);	
		xySeries.addSeries(series3);
		
		JFreeChart chart = ChartFactory.createXYLineChart("MSD vs tiempo",  // Title
		                     "tiempo",           // X-Axis label
		                     "MSD",           // Y-Axis label
		                     xySeries,          // Dataset
		                     PlotOrientation.VERTICAL,
		                     true,               // Show legend
		                     true,				  // Show tooltips
		                     true                // Show urls
		                    );
		chart.setAntiAlias(true);		
				
		// 	Creación del panel con el gráfico
		ChartPanel panel = new ChartPanel(chart);
		
		JFrame ventana = new JFrame("MSD vs Time");
		ventana.getContentPane().add(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
	public static void plotJFreeChart(double[] x, double[] y, double[] x2, double[] y2, 
			String title, String xLabel, String yLabel, String nameSerie1, String nameSerie2, String tipo)							    
	{
		//Gráfico con JFreeChart :)		
		XYSeries series = new XYSeries(nameSerie1);
		XYSeries series2 = new XYSeries(nameSerie2);	
		
		
		for(int i=0;i < y.length;i++){
			series.add(x[i],y[i]);		
		}
		for(int i=0;i < y2.length;i++){
			series2.add(x2[i],y2[i]);				
		}
		
		//XYDataset xyDataset = new XYSeriesCollection(series);
		XYSeriesCollection xySeries = new XYSeriesCollection(series);
		xySeries.addSeries(series2);		
		
		if(tipo=="Line")
		{
			JFreeChart chart = ChartFactory.createXYLineChart("MSD vs tiempo",  // Title
		                      "tiempo",           // X-Axis label
		                      "MSD",           // Y-Axis label
		                      xySeries,          // Dataset
		                      PlotOrientation.VERTICAL,
		                      true,               // Show legend
		                      true,				  // Show tooltips
		                      true                // Show urls
		                     );
			chart.setAntiAlias(true);		
		
		
			// 	Creación del panel con el gráfico
			ChartPanel panel = new ChartPanel(chart);
			
			JFrame ventana = new JFrame("MSD vs Time");
			ventana.getContentPane().add(panel);
			ventana.pack();
			ventana.setVisible(true);
		}
		else
		{
			JFreeChart chart2 = ChartFactory.createScatterPlot(title,  // Title
		        xLabel,           // X-Axis label
                yLabel,           // Y-Axis label
                xySeries,          // Dataset
                PlotOrientation.VERTICAL,
                true,               // Show legend
                true,				  // Show tooltips
                true                // Show urls
               );
			chart2.setAntiAlias(true);
			ChartPanel panel2 = new ChartPanel(chart2);
			JFrame ventana2 = new JFrame("MSD vs Time");
			ventana2.getContentPane().add(panel2);
			ventana2.pack();
			ventana2.setVisible(true);
		}
		
		/*NumberAxis domainAxis = new NumberAxis("X");
        domainAxis.setAutoRangeIncludesZero(false);
        NumberAxis rangeAxis = new NumberAxis("Y");
        rangeAxis.setAutoRangeIncludesZero(false);
        
        double[][] data = new double[2][msd[1].length];
        data[0]=msd[0];
        data[1]=msd[1];        
        
        FastScatterPlot scatterPlot = new FastScatterPlot(data, domainAxis, rangeAxis);
        
        JFreeChart chart3 = new JFreeChart("Fast Scatter Plot", scatterPlot);
        ChartPanel panel3 = new ChartPanel(chart3, true);
        JFrame ventana3 = new JFrame("MSD vs Time");
		ventana3.getContentPane().add(panel3);
		ventana3.pack();
		ventana3.setVisible(true);*/      
		
	}

}
