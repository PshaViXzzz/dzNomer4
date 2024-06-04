import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class gitAssertionsCheckTest {
  @BeforeAll
  static void beforeAll() {
      Configuration.baseUrl = "https://github.com";
      Configuration.browserSize = "1920x1080";
      Configuration.pageLoadStrategy = "eager";
  }

  @Test
    void softAssertions() {
      open("/selenide/selenide");

      $("#wiki-tab").click();

      $("#wiki-pages-box").$("#wiki-pages-filter").setValue("SoftAssertions");

      $("#wiki-pages-box").$(byText("SoftAssertions")).click();

      $("#wiki-body").shouldHave(text("""
               @ExtendWith({SoftAssertsExtension.class})
                                      class Tests {
                                          @Test
                                          void test() {
                                              Configuration.assertionMode = SOFT;
                                              open("page.html");
                            
                                              $("#first").should(visible).click();
                                              $("#second").should(visible).click();
                                          }
                                      }
              """));
  }
}
