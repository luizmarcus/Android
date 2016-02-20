package br.com.luizmarcus.exemplotesteinterface;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by luizm on 18/02/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void executarTesteLoginContaCorreta() {

        //Insere o usuário no campo user
        onView(withId(R.id.user)).perform(typeText("Testeapp"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.pass)).perform(typeText("Password"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.submit)).perform(click());

        //Verifica se o texto é exibido no textView da SecondActivity
        onView(withId(R.id.success)).check(matches(withText("Logado!")));

    }

    @Test
    public void executarTesteLoginContaErrada() {

        //Insere o usuário no campo user
        onView(withId(R.id.user)).perform(typeText("Usuario"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.pass)).perform(typeText("Senha"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.submit)).perform(click());

    }

}
