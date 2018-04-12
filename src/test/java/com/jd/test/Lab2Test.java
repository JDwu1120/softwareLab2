package com.example.tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Lab2Test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static final HashMap<String,String> map = new HashMap<String, String>();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/Users/wujindong/IdeaProjects/softwareTestLab2/src/main/resources/chromedriver");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLab2() throws Exception {
    String username = null;
    String password = null;
    XSSFCell cf = null;
    XSSFCell c = null;
    try {
      InputStream in = new FileInputStream("/Users/wujindong/Downloads/input.xlsx");
      XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
      XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

      int length = sheetAt.getLastRowNum();
      for (int rowNum = 1; rowNum <= length; rowNum++) {
        XSSFRow r = sheetAt.getRow(rowNum);
        cf = r.getCell(0);
        c = r.getCell(1);
        username = cf.getStringCellValue().trim();
        password = username.substring(4);
        driver.get("https://psych.liebes.top/st");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submitButton")).click();
        assertEquals(c.getStringCellValue().trim(), driver.findElement(By.tagName("P")).getText());
      }
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
