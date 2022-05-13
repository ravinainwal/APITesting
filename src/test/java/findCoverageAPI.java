import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;


public class findCoverageAPI {


    @ParameterizedTest(name = "Test {index} ")
    @CsvFileSource(resources = "/TestCases- ReAssure - Accident (1).csv", numLinesToSkip = 1)
    public void testing(String TestCaseId, String product_code, String benefit_category, String benefit_type, String benefit_code, String personal_accident_cover_sum_insured, String policy_enhancement_type, String claim_submission_type, String policy_base_sum_insured, String reason_for_hospitalization, String patient_days_of_hospitalization_during_policy_year, String benefit_covered, String benefit_exclusion_exceptions, String benefit_waiting_period, String benefit_default_waiting_period_for_PEDs, String limit_per_claim, String limit_per_day) throws ParseException {

        StringBuffer APIRequest = new StringBuffer();

        StringBuffer actualOutput = new StringBuffer();

        StringBuffer expectedOutput = new StringBuffer();

        StringBuffer differingFields = new StringBuffer();

        String benefitExclusionExceptionCode = "";

        RestAssured.baseURI = "https://development.vitrayatech.com/api/pml/v1";

        RequestSpecification request = given();

        JSONObject requestParams = new JSONObject();

        request.header("Content-Type", "application/json");

        if (product_code != null) {

            requestParams.put("product_code", product_code);
            APIRequest.append("product_code : " + product_code + "\n");

        }
        if (benefit_category != null) {

            requestParams.put("benefit_category", benefit_category);
            APIRequest.append("benefit_category : " + benefit_category + "\n");

        }
        if (benefit_type != null) {

            requestParams.put("benefit_type", benefit_type);
            APIRequest.append("benefit_type : " + benefit_type + "\n");

        }
        if (benefit_code != null) {

            requestParams.put("benefit_code", benefit_code);
            APIRequest.append("benefit_code : " + benefit_code + "\n");

        }
        if (personal_accident_cover_sum_insured != null) {

            requestParams.put("personal_accident_cover_sum_insured", Integer.parseInt(personal_accident_cover_sum_insured));
            APIRequest.append("personal_accident_cover_sum_insured : " + Integer.parseInt(personal_accident_cover_sum_insured) + "\n");

        }
        if (policy_enhancement_type != null) {

            requestParams.put("policy_enhancement_type", policy_enhancement_type);
            APIRequest.append("policy_enhancement_type : " + policy_enhancement_type + "\n");

        }
        if (claim_submission_type != null) {

            requestParams.put("claim_submission_type", claim_submission_type);
            APIRequest.append("claim_submission_type : " + claim_submission_type + "\n");

        }
        if (policy_base_sum_insured != null) {

            requestParams.put("policy_base_sum_insured", Integer.parseInt(policy_base_sum_insured));
            APIRequest.append("policy_base_sum_insured : " + Integer.parseInt(policy_base_sum_insured) + "\n");
        }
        if (reason_for_hospitalization != null) {

            requestParams.put("reason_for_hospitalization", reason_for_hospitalization);
            APIRequest.append("reason_for_hospitalization : " + reason_for_hospitalization + "\n");

        }
        if (patient_days_of_hospitalization_during_policy_year != null) {

            requestParams.put("patient_days_of_hospitalization_during_policy_year", Integer.parseInt(patient_days_of_hospitalization_during_policy_year));
            APIRequest.append("patient_days_of_hospitalization_during_policy_year : " + Integer.parseInt(patient_days_of_hospitalization_during_policy_year) + "\n");

        }


        request.body(requestParams.toJSONString());

        Response response = request.post("/findCoverage");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Boolean benefitCovered = jsonPathEvaluator.get("benefit_covered");

        String benefitWaitingPeriod = jsonPathEvaluator.get("benefit_waiting_period");

        String benefitDefaultWaitingPeriodForPeDs = jsonPathEvaluator.get("benefit_default_waiting_period_for_PEDs");

        Integer limitPerClaim = jsonPathEvaluator.get("limit_per_claim");

        Integer limitPerDay = jsonPathEvaluator.get("limit_per_day");

        List<Map<String, Object>> benefitExclusionExceptionsList = jsonPathEvaluator.getList("benefit_exclusion_exceptions");


        try {

            if (benefit_exclusion_exceptions != null) {

                benefitExclusionExceptionCode = benefitExclusionExceptionsList.get(0).get("code").toString();

                Assertions.assertEquals(benefit_exclusion_exceptions, benefitExclusionExceptionCode, "Benefit Exclusion Exception code is not matching");

            }

            if (limit_per_claim != null) {

                Assertions.assertEquals(Integer.parseInt(limit_per_claim), limitPerClaim, "Limit Per Claim is not matching ");

            }
            if (limit_per_day != null) {

                Assertions.assertEquals(Integer.parseInt(limit_per_day), limitPerDay, "Limit Per day is not matching");

            }
            if (benefitCovered == true) {

                Assertions.assertEquals(benefit_waiting_period, benefitWaitingPeriod, "Benefit waiting period is not matching");

                Assertions.assertEquals(benefit_default_waiting_period_for_PEDs, benefitDefaultWaitingPeriodForPeDs, "Benefit default waiting period for PEDs in not matching");

            }


            Assertions.assertEquals(benefit_covered.toLowerCase(Locale.ROOT), Boolean.toString(benefitCovered), "Benefit covered is not matching");

        } catch (AssertionFailedError e) {

            actualOutput.append("benefit_covered : " + benefitCovered + "\n");


            if (limitPerClaim != null) {

                actualOutput.append("limit_per_claim : " + limitPerClaim + "\n");

            }

            if (limitPerDay != null) {

                actualOutput.append("limit_per_day : " + limitPerDay + "\n");

            }

            if (benefitCovered == true) {

                actualOutput.append("benefit_waiting_period : " + benefitWaitingPeriod + "\n");

                actualOutput.append("benefit_default_waiting_period_for_PEDs : " + benefitDefaultWaitingPeriodForPeDs + "\n");

            }

            if (benefitExclusionExceptionsList != null) {

                benefitExclusionExceptionCode = benefitExclusionExceptionsList.get(0).get("code").toString();

                actualOutput.append("benefit_exclusion_exceptions : " + benefitExclusionExceptionCode);
            }

            expectedOutput.append("benefit_covered : " + benefit_covered + "\n");

            if (Boolean.parseBoolean(benefit_covered) == true) {

                expectedOutput.append("benefit_waiting_period : " + benefit_waiting_period + "\n");

                expectedOutput.append("benefit_default_waiting_period_for_PEDs : " + benefit_default_waiting_period_for_PEDs + "\n");

            }
            if (benefit_exclusion_exceptions != null) {

                expectedOutput.append("benefit_exclusion_exceptions : " + benefit_exclusion_exceptions + "\n");


            }

            if (limit_per_claim != null) {

                expectedOutput.append("limit_per_claim : " + Integer.parseInt(limit_per_claim) + "\n");

            }
            if (limit_per_day != null) {

                expectedOutput.append("limit_per_day : " + Integer.parseInt(limit_per_day) + "\n");

            }


            if (benefit_exclusion_exceptions != null) {

                benefitExclusionExceptionCode = benefitExclusionExceptionsList.get(0).get("code").toString();

                if (!benefit_exclusion_exceptions.equals(benefitExclusionExceptionCode)) {

                    differingFields.append("benefit_exclusion_exceptions is not matching ==> Expected :" + benefit_exclusion_exceptions + " Actual :" + benefitExclusionExceptionCode + "\n");

                }

            }

            if (!benefit_covered.toLowerCase(Locale.ROOT).equals(Boolean.toString(benefitCovered))) {

                differingFields.append("benefit_covered is not matching ==> Expected :" + benefit_covered + " Actual :" + benefitCovered + "\n");

            }

            if (limit_per_claim != null) {

                if (!limit_per_claim.equals(limitPerClaim)) {

                    differingFields.append("limit_per_claim is not matching ==> Expected :" + limit_per_claim + " Actual :" + limitPerClaim + "\n");

                }

            }
            if (limit_per_day != null) {

                if (!limit_per_day.equals(limitPerDay)) {

                    differingFields.append("limit_per_day is not matching ==> Expected :" + limit_per_day + " Actual :" + limitPerDay + "\n");

                }

            }
            if (benefitCovered == true) {

                if (benefit_waiting_period != null) {

                    if (!benefit_waiting_period.equals(benefit_waiting_period)) {

                        differingFields.append("benefit_waiting_period is not matching ==> Expected :" + benefit_waiting_period + " Actual :" + benefitWaitingPeriod + "\n");

                    }

                }

                if (benefit_default_waiting_period_for_PEDs != null) {

                    if (!benefit_default_waiting_period_for_PEDs.equals(benefitDefaultWaitingPeriodForPeDs)) {

                        differingFields.append("benefit_default_waiting_period_for_PEDs is not matching ==> Expected :" + benefit_default_waiting_period_for_PEDs + " Actual :" + benefitDefaultWaitingPeriodForPeDs + "\n");

                    }

                }


            }

            fail("Test failed for Test Case ID " + TestCaseId + "\n" + "\n" + "Input " + "\n" + "\n" + APIRequest + "\n" + "Actual Output " + "\n" + "\n" + actualOutput + "\n" + "\n" + "Expected Output " + "\n" + "\n" + expectedOutput + "\n" + "\n" + "Difference between Expected Output and Actual Output " + "\n" + "\n" + differingFields + "\n");

        }

    }
}
