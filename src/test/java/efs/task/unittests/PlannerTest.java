package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

public class PlannerTest {
    Planner planner;

    @BeforeEach
    void prepareEnv() {
        planner = new Planner();
    }


    @EnumSource(ActivityLevel.class)
    @ParameterizedTest(name="with {0}")
    void calculateDailyCaloriesDemand_PredefinedUserWithGivenActivityLevel_EqualToPredefinedCaloriesDemand(ActivityLevel activityLevel){
        //given
        User user = TestConstants.TEST_USER;
        Map<ActivityLevel, Integer> expectedCaloriesDemandPerActivityLevel = TestConstants.CALORIES_ON_ACTIVITY_LEVEL;

        //when
        int returnedCaloriesDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        Assertions.assertEquals(returnedCaloriesDemand, expectedCaloriesDemandPerActivityLevel.get(activityLevel));
    }

    @Test
    void calculateDailyIntake_PredefinedUser_EqualToPredefinedIntakeData(){
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake returnedDailyIntake = planner.calculateDailyIntake(user);

        //then
        Assertions.assertEquals(expectedDailyIntake.getCalories(), returnedDailyIntake.getCalories());
        Assertions.assertEquals(expectedDailyIntake.getProtein(), returnedDailyIntake.getProtein());
        Assertions.assertEquals(expectedDailyIntake.getFat(), returnedDailyIntake.getFat());
        Assertions.assertEquals(expectedDailyIntake.getCarbohydrate(), returnedDailyIntake.getCarbohydrate());
    }
}
