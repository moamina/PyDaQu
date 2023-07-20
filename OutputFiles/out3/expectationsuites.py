
class ExpectationSuite:
    def create_expectation_suite(suite_name,context ,suite_expectations_list):
        # creating a suite that contains all test cases of data quality
        suite = context.create_expectation_suite(
            expectation_suite_name=f"{suite_name}", overwrite_existing=True
        )
        for expectation in suite_expectations_list:
            suite.add_expectation(expectation_configuration=expectation)
        return suite

