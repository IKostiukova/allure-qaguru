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

public class IssueCreationWithReusedBasicStepsVersionTests {

    /*private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "/eroshenkoam/allure-example";
    private static final String EMAIL = "irinamarinko@gmail.com";
    private static final String PASSWORD ="RT65weds/";*/

    private final BasicSteps steps = new BasicSteps();

    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Issue creation without Lable and Assignee")
    public void CreateIssueWithoutLabelAndAssignee() {
       /* parameter("Login page", LOGIN_URL);
        parameter("Email", EMAIL);
        parameter("Password", PASSWORD);
        parameter("Repository", REPOSITORY);*/

        steps.logIn();
        steps.openRepositoryAndIssueTab();
        steps.createIssue();
        steps.issueCreatedWithoutLableAndAssignee();
    }
}