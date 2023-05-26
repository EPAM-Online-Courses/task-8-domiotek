package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FitCalculatorTest {

    @Test
    void isBMICorrect_DietIsRecommended_True() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void isBMICorrect_DietIsNotRecommended_False() {
        //given
        double weight = 69.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void isBMICorrect_HeightIsZero_IllegalArgumentException() {
        //given
        double height = 0.0;
        double weight = 86;

        //when
        Class exception = IllegalArgumentException.class;

        //then
        Assertions.assertThrows(exception, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ValueSource(doubles = {77.7, 74.5, 79.9})
    @ParameterizedTest(name = "with weight={0}")
    void isBMICorrect_DietIsRecommended_True(double weight) {
        //given
        double height = 1.68;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertTrue(recommended);
    }

    @CsvSource ({"115, 181", "67, 1.64", "41, 200"})
    @ParameterizedTest (name = "with weight={0} and height={1}")
    void isBMICorrect_DietIsNotRecommended_False(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(recommended);
    }

    @CsvFileSource (resources = "/data.csv", numLinesToSkip = 1)
    @ParameterizedTest (name = "with weight={0} and height={1}")
    void isBMICorrect_DietIsRecommendedWithDataFromFile_False(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(recommended);
    }

    @Test
    void findUserWithTheWorstBMI_PredefinedUserList_UserWithTheWorstBMI() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double expectedWeight = 97.3;
        double expectedHeight = 1.79;

        //when
        User returnedUser = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        Assertions.assertEquals(expectedWeight, returnedUser.getWeight());
        Assertions.assertEquals(expectedHeight, returnedUser.getHeight());
    }

    @Test
    void findUserWithTheWorstBMI_EmptyUserList_NULL() {
        //given
        List<User> users = new ArrayList<>();

        //when
        User returnedUser = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        Assertions.assertNull(returnedUser);
    }

    @Test
    void calculateBMIScore_PredefinedUserList_EqualToPredefinedBMIScore() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double [] expectedBMIScore = TestConstants.TEST_USERS_BMI_SCORE;

        //when
        double [] returnedBMIScore = FitCalculator.calculateBMIScore(users);

        //then
        Assertions.assertArrayEquals(expectedBMIScore, returnedBMIScore);
    }
}