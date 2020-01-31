package es.codeurjc.daw;

import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

	@LocalServerPort
    int port;

	private WebDriver driver;

	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	@DisplayName("Crear un post y verificar que se crea correctamente")
	public void createPostTest() throws Exception{

		// CREAMOS UN NUEVO POST

		driver.get("http://localhost:"+this.port+"/");

		findElementWithText("Nuevo post").click();

		String postTitle = "Mi titulo";
		String postContent = "Mi contenido";
		
		driver.findElement(By.name("title")).sendKeys(postTitle);
		driver.findElement(By.name("content")).sendKeys(postContent);
		driver.findElement(By.tagName("form")).submit();

		// COMPROBAMOS QUE LA PÁGINA DE RESPUESTA ES CORRECTA
		assertNotNull(findElementWithText(postTitle));
		assertNotNull(findElementWithText(postContent));

		// COMPROBAMOS QUE EXISTE EN LA PÁGINA PRINCIPAL
		driver.get("http://localhost:"+this.port+"/");
		assertNotNull(findElementWithText(postTitle));
		
	}

	@Test
	@DisplayName("Añadir un comentario a un post y verificar que se añade el comentario")
	public void createCommentTest() throws Exception {

		// CREAMOS UN NUEVO POST

		driver.get("http://localhost:"+this.port+"/");

		findElementWithText("Nuevo post").click();

		String postTitle = "Mi titulo";
		String postContent = "Mi contenido";
		
		driver.findElement(By.name("title")).sendKeys(postTitle);
		driver.findElement(By.name("content")).sendKeys(postContent);
		driver.findElement(By.tagName("form")).submit();

		// CREAMOS UN NUEVO COMENTARIO

		String commentAuthor = "Juan";
		String commentMessage = "Buen comentario";

		driver.findElement(By.name("author")).sendKeys(commentAuthor);
		driver.findElement(By.name("message")).sendKeys(commentMessage);
		driver.findElement(By.tagName("form")).submit();

		// COMPROBAMOS QUE SE HA CREADO

		WebElement authorAndMessage = driver.findElement(By.tagName("ul"))
			.findElement(getConditionForText(commentAuthor+": "+commentMessage));
		assertNotNull(authorAndMessage);

	}

	@Test
	@DisplayName("Borrar un comentario de un post y verificar que no aparece el comentario")
	public void deleteCommentTest() throws Exception {

	}

	private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
	}
	
	protected By getConditionForText(String text) {
        return By.xpath(format("//*[text()='%s']", text));
    }

}