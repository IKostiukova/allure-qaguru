package io.qaguru.github;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("i.kostiukova")
@Feature("Issues")

public class IssueCreationStepsVersionTests {

    private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "/eroshenkoam/allure-example";
    private static final String EMAIL = "irinamarinko@gmail.com";
    private static final String PASSWORD ="password";

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Issue creation without Lable and Assignee")
    public void CreateIssueWithoutLabelAndAssignee() {
        parameter("Login page", LOGIN_URL);
        parameter("Email", EMAIL);
        parameter("Password", PASSWORD);
        parameter("Repository", REPOSITORY);
        
        step("LogIn", () -> {
            open(LOGIN_URL);
            $("#login_field").setValue(EMAIL);
            $("#password").setValue(PASSWORD).pressEnter();
        });

        step("Open repository and Issue tab", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
            $(By.linkText("eroshenkoam/allure-example")).click();
            $(byText("Issues"), 1).click();
        });

        step("Create Issue", () -> {
            $(".btn-primary > span").click();
            $("#issue_title").sendKeys("IK New Issue 1");
            $(withText("Submit new issue")).click();
        });

        step("Check that issue is created without lable and assignee", () -> {
            ;
            $(byText("IK New Issue 1")).shouldHave(text("IK New Issue 1"));
            $(".js-issue-assignees").shouldHave(text("No one assigned"));
            $(".labels").shouldHave(text("None yet"));
        });

    }
}