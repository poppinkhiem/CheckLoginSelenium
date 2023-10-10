package test;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class CheckLoginTestNG {

	WebDriver driver;
	CheckLoginPage checkloginPage;
	HomePage homePage;
	String driverPath = "C:\\Users\\Admin\\Desktop\\Automation Test\\Automation Test\\Edge\\msedgedriver.exe";

	@BeforeClass
	public void setUp() {
		// Khởi tạo WebDriver
		driver = new EdgeDriver();
		System.setProperty("webdriver.edge.driver", driverPath);

//		System.setProperty("webdriver.edge.verboseLogging", "true");ư
		homePage = new HomePage(driver);
		checkloginPage = new CheckLoginPage(driver);
		System.out.println("Setup driver và khởi tạo thành công");

	}

	@Test
	public void checkTitle() {
		// Mở trang web
		driver.get("https://staging.otakaraya-auction.jp");
		// Kiểm tra tiêu đề trang
		String pageTitle = driver.getTitle();
		AssertJUnit.assertEquals(pageTitle, "[STG] Otakaraya Auction");
		System.out.println("Check title thành công");
	}

	@Test
	public void checkMultiAccountLogin() {

		String[] usernames = { "faker.dgt@gmail.com", "faker.dgt+011@gmail.com", "faker.dgt+012@gmail.com" };
		String[] passwords = { "12345678", "12345678", "12345678" };

		for (int i = 0; i < usernames.length && i < passwords.length; i++) {

			driver.get("https://staging.otakaraya-auction.jp");
			System.out.println("Check Login");
			// Input username, password
			checkloginPage.setUsernameInput(usernames[i]);
			checkloginPage.setPasswordInput(passwords[i]);
			System.out.println(usernames[i] + passwords[i] );
			// Click log in
			checkloginPage.ClickLoginPage();
			// Handle error login
            
			try {
//
				if (checkloginPage.isErrorMessageDisplayed()) {
					String wrongText = checkloginPage.getErrorMessageText();
					System.out.println("Sai tai khoan hoac mat khau. Username:" + wrongText);
					continue;

				}
			} catch (Exception e) {

			}

			// Click toggle button
			homePage.clickToggleButton();
			homePage.setLogoutButton();
			System.out.println("Chạy đến hàm set Logout");
		}
	}

	@Test
	public void getLocalStorageValue() {
		int expectedValue = 12;
		// Truy cập trang web cần kiểm tra Local Storage
		driver.get("https://staging.otakaraya-auction.jp");

		// Lấy đối tượng Local Storage từ WebDriver
		WebStorage webStorage = (WebStorage) driver;
		LocalStorage localStorage = webStorage.getLocalStorage();

		// Lấy giá trị của một phần tử trong Local Storage
		String localStorageValue = localStorage.getItem("myKey");

		// Kiểm tra giá trị lấy được từ Local Storage
		Assert.assertNotEquals(localStorageValue, "expectedValue");
		System.out.println("Chạy đến hàm localStorage");
		System.out.println(localStorageValue);
	}

	@Test
	public void checkCookies() {
		// Truy cập trang web cần kiểm tra Cookie
		int expectedValue = 12;
		driver.get("https://staging.otakaraya-auction.jp");

		// Lấy danh sách các Cookie từ trình duyệt
		Set<Cookie> cookies = driver.manage().getCookies();
		SoftAssert softAssert = new SoftAssert();
	if (!cookies.isEmpty()) {
		
		System.out.println(cookies);
		
	}
	else
	System.out.println("Coockies rỗng");

		// Kiểm tra Cookie
		boolean cookieFound = false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("myCookie")) {
				Assert.assertNotEquals(cookie.getValue(), "expectedValue");
				cookieFound = true;
				break;

			}
		}

		// Kiểm tra xem có tồn tại Cookie cần tìm không
		Assert.assertTrue(cookieFound, "Cookie not found.");
	}

	@AfterClass
	public void tearDown() {
		// Đóng trình duyệt
		driver.quit();
	}
}