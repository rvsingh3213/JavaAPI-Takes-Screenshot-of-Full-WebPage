package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

import javax.imageio.ImageIO;
// sometimes application doesn't run on tomcat server so by including these two
// application definately rung on tomcat server
// without it application may run by this ensure 100% that application will run
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

//for selenium
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;	


 
@Path("/hello")
public class Hello {

	
	static WebDriver driver;
	static int Count=0;
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getResult(@QueryParam("link") String link)
	{	
		
		try {
			initDriver();
			System.out.print("Reached till here");
		capture(link);
		}
		catch(Exception e) 
		{System.out.println("Exception occ:"+e);}
		
		destroy();
		return "";
		
	}
	
	public static void initDriver()
	{
		
		System.setProperty("webdriver.chrome.driver",
				"C://Users/RAVISHER SINGH/eclipse-workspace/JavaAPI/src/driver/chromedriver.exe");
				
		
		//WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//driver.manage().window().setPosition(new Point(100,100));
	}
	
	public static void capture(String site) throws IOException
	{
		Count++;
		
		/*driver.get(site);
		driver.manage().window().maximize();
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("site"+screenshotNum+".png"));
		System.out.println("Took Screenshot for "+site+" and saved as "+"site"+screenshotNum+".png");
		System.out.println("Took Screen Shot");
		
		*/
		
		driver.get(site);
		driver.manage().window().maximize();
		
		 try {
		        Thread.sleep(2000);
		        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		        ImageIO.write(fpScreenshot.getImage(), "PNG", new File("site"+Count+".png"));
		        Thread.sleep(2000);
		   } catch (Exception ex) {
		        ex.printStackTrace();
		   }
		
		
	}
	
	public static void destroy()
	{
		driver.quit();
	}
	
	
	
	
}
