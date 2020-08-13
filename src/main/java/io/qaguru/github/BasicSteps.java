package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;

public class BasicSteps {

    private static final String BASE_URL = "https://github.com";
    private static final String LOGIN_URL = "https://github.com/login";
    private static final String REPOSITORY = "/eroshenkoam/allure-example";
    private static final String EMAIL = "irinamarinko@gmail.com";
    private static final String PASSWORD ="password";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("LogIn")
    public void logIn() {
        open(LOGIN_URL);
        $("#login_field").setValue(EMAIL);
        $("#password").setValue(PASSWORD).pressEnter();}

    @Step("Open repository and Issue tab")
    public void openRepositoryAndIssueTab() {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
            $(By.linkText("eroshenkoam/allure-example")).click();
            $(byText("Issues"), 1).click();
    }

    @Step("Create Issue")
    public void createIssue() {
        $(".btn-primary > span").click();
        $("#issue_title").sendKeys("IK New Issue");
        $(withText("Submit new issue")).click();
    }

    @Step("Check that issue is created without lable and assignee")
    public void issueCreatedWithoutLableAndAssignee() {
        $(byText("IK New Issue")).shouldHave(text("IK New Issue"));
        $(".js-issue-assignees").shouldHave(text("No one assigned"));
        $(".labels").shouldHave(text("None yet"));
    }

    @Step("Ищем репозиторий")
    public void searchForRepository(final String name) {
        link("GitHub", String.format("%s/%s", BASE_URL, name));
        parameter("Репозиторий", name);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
    }

    @Step("Переходим по ссылке репозитория")
    public void openRepositoryByLink(final String name) {
        $(By.linkText(name)).click();
    }

    @Step("Открываем страницу с задачами")
    public void openIssuesPage() {
        $(withText("Issues")).click();
    }

    @Step("Проверяем наличие задачи с номером")
    public void shouldSeeIssueWithNumber(final int number) {
        parameter("Номер задачи", number);

        $(withText("#" + number)).should(Condition.exist);
    }

    @Step("Проверяем отсутствие задачи с номером")
    public void shouldNotSeeIssueWithNumber(final int number) {
        parameter("Номер задачи", number);

        $(withText("#" + number)).shouldNot(Condition.exist);
    }

}
