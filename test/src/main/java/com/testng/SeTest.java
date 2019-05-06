package com.testng;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;

/**
 * 类说明
 *
 * @author iceb
 * @version $Id: SeTest.java, v0.1 2017/6/28.17:19 Exp $
 */

public class SeTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("测试开始");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("测试结束");
    }

    @Test
    public void logIn() {
        //设置驱动所在位置
        System.setProperty("webdriver.chrome.driver", "D:\\Doc--GitRepo\\Java\\test\\src\\main\\resources\\chromedriver.exe");
        //引用火狐浏览器驱动
        WebDriver driver = new ChromeDriver();
        //打开禅道界面
        driver.get("https://192.168.0.99:3999/login");
        //以下元素使用css格式 -cssSelector
        //输入账号
        ((ChromeDriver) driver).findElementByXPath("//*[@id=\"login-container\"]/div/div[2]/div[1]/form/div[1]/div/div/input").sendKeys("18952448323");
        //输入密码
        ((ChromeDriver) driver).findElementByXPath("//*[@id=\"login-container\"]/div/div[2]/div[1]/form/div[2]/div/div/input").sendKeys("123456ab");
        //点击登录
        driver.findElement(By.xpath("//*[@id=\"login-container\"]/div/div[2]/div[1]/form/div[4]/div/button")).click();
        //抓取成功登录后的用户名信息
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        String text = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div/div[2]/div[1]")).getText();

        //断言-校验是否登录成功
        Assert.assertEquals(text, "唐锐");


        //关闭浏览器进程及驱动
        driver.close();

    }

}