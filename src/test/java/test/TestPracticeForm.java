package test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObject.RegistrationForm;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class TestPracticeForm {

    final String firstName = "Adel";
    final String lastName = "Zakiev";
    final String email = "zakievadel1994@yandex.ru";
    final String gender = "Male";
    final String mobile = "9082787177";
    final String birthDateDay = "18";
    final String birthDateMouth = "March";
    final String birthDateYear = "1994";
    final String birthDate = this.birthDateDay + " " + this.birthDateMouth + "," + this.birthDateYear;
    final String subjects = "Computer Science";
    final String[] hobbies = {"Sports", "Reading"};
    final File picture = new File("src/test/resources/test.jpg");
    final String address = "Kazan";
    final String state = "NCR";
    final String city = "Delhi";

    final private RegistrationForm registrationForm = new RegistrationForm();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void entryPoint() {
        Step1OpenUrl();
        Step2FillFormRegistrations();
        Step3DataCheck();
    }

    @Step("Открыть страницу")
    void Step1OpenUrl() {
        //Открытие сервера
        open("/automation-practice-form");
    }

    @Step("Заполнение формы регистрации")
    void Step2FillFormRegistrations() {
        registrationForm
                .firstNameInput(firstName)
                .lastName(lastName)
                .userEmail(email)
                .gender(gender)
                .mobile(mobile)
                .birthDate(birthDateYear, birthDateMouth, birthDateDay)
                .subjects(subjects)
                .hobbies(hobbies)
                .picture(picture)
                .address(address)
                .state(state)
                .city(city)
                .submitForm();
    }

    @Step("Проверка данных")
    void Step3DataCheck() {
        registrationForm
                .checkResultInModal("Student Name", firstName + " " + lastName)
                .checkResultInModal("Student Email", email)
                .checkResultInModal("Gender", gender)
                .checkResultInModal("Mobile", mobile)
                .checkResultInModal("Date of Birth", birthDate)
                .checkResultInModal("Mobile", mobile)
                .checkResultInModal("Subjects", subjects)
                .checkResultInModal("Hobbies", String.join(", ", hobbies))
                .checkResultInModal("Picture", picture.getName())
                .checkResultInModal("Address", address)
                .checkResultInModal("State and City", state + " " + city);
    }
}
