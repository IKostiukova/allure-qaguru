package io.qaguru.github;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.$;

@Owner("i.kostiukova")
@Feature("Issues")

public class IssueCreationSelenideVersionTests {

    private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "/eroshenkoam/allure-example";
    private static final String EMAIL = "irinamarinko@gmail.com";
    private static final String PASSWORD ="RT65weds/";

    @Test
    public void CreateIssueWithoutLabelAndAssignee() {
        open(LOGIN_URL);
        $("#login_field").setValue(EMAIL);
        $("#password").setValue(PASSWORD).pressEnter();
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(By.linkText("eroshenkoam/allure-example")).click();
        $(byText("Issues"),1).click();

        $(".btn-primary > span").click();
        $("#issue_title").sendKeys("IK New Issue 1");
        $(withText("Submit new issue")).click();

        $(byText("IK New Issue 1")).shouldHave(text("IK New Issue 1"));
        $(".js-issue-assignees").shouldHave(text("No one assigned"));
        $(".labels").shouldHave(text("None yet"));
    }
}
