from great_expectations.core import ExpectationConfiguration


class ExpectationUtil:

# Generating First Group
    @staticmethod
    def expect_column_values_to_not_be_null(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_not_be_null",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_null(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_null",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_of_type(column, dataType):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_of_type",
            kwargs={
                "column": f"{column}",
                "type_": f"{dataType}",
            },
            # Note optional comments omitted
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_in_type_list(column, type_list):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_in_type_list",
            kwargs={
                "column": f"{column}",
                "type_list": f"{type_list}",
            },
            # Note optional comments omitted
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_in_set(column, valueSet):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_in_set",
            kwargs={
                "column": f"{column}",
                "value_set": f"{valueSet}",
            },
            # Note optional comments omitted
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_not_be_in_set(column, valueSet):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_not_be_in_set",
            kwargs={
                "column": f"{column}",
                "value_set": f"{valueSet}",
            },
            # Note optional comments omitted
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_between(column, maxValue, minValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_increasing(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_increasing",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_decreasing(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_decreasing",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_distinct_values_to_equal_set(column, value_set):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_distinct_values_to_equal_set",
            kwargs={
                "column": f"{column}",
                "value_set":f"{value_set}"
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_distinct_values_to_contain_set(column, value_set):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_distinct_values_to_contain_set",
            kwargs={
                "column": f"{column}",
                "value_set": f"{value_set}"
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_mean_to_be_between(column, maxValue, minValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_mean_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_median_to_be_between(column, maxValue, minValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_median_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_unique_value_count_to_be_between(column, maxValue, minValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_unique_value_count_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_most_common_value_to_be_in_set(column, value_set):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_most_common_value_to_be_in_set",
            kwargs={
                "column": f"{column}",
                "value_set": f"{value_set}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_sum_to_be_between(column, result_format,minValue,maxValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_sum_to_be_between",
            kwargs={
                "column": f"{column}",
                "result_format": f"{result_format}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_min_to_be_between(column, result_format, minValue, maxValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_min_to_be_between",
            kwargs={
                "column": f"{column}",
                "result_format": f"{result_format}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_max_to_be_between(column, result_format, minValue, maxValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_max_to_be_between",
            kwargs={
                "column": f"{column}",
                "result_format": f"{result_format}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_proportion_of_unique_values_to_be_between(column, minValue, maxValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_proportion_of_unique_values_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_be_unique(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_unique",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration
#second Group

    @staticmethod
    def expect_column_value_lengths_to_be_between(column, minValue, maxValue):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_value_lengths_to_be_between",
            kwargs={
                "column": f"{column}",
                "min_value": f"{minValue}",
                "max_value": f"{maxValue}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_value_lengths_to_equal(column, value):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_value_lengths_to_equal",
            kwargs={
                "column": f"{column}",
                "value": f"{value}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_match_regex(column, regex):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_regex",
            kwargs={
                "column": f"{column}",
                "regex ": f"{regex}",
            },
        )
        return expectation_configuration


    @staticmethod
    def expect_column_values_to_match_regex_list(column, regex_list):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_regex_list",
            kwargs={
                "column": f"{column}",
                "regex_list ": f"{regex_list}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_match_like_pattern(column, like_pattern):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_like_pattern",
            kwargs={
                "column": f"{column}",
                "like_pattern ": f"{like_pattern}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_not_match_like_pattern(column, like_pattern):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_not_match_like_pattern",
            kwargs={
                "column": f"{column}",
                "like_pattern ": f"{like_pattern}",
            },
        )
        return expectation_configuration


    @staticmethod
    def expect_column_values_to_match_like_pattern_list(column, like_pattern_list):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_like_pattern_list",
            kwargs={
                "column": f"{column}",
                "like_pattern_list ": f"{like_pattern_list}",
            },
        )
        return expectation_configuration

    @staticmethod
    def expect_column_values_to_not_match_like_pattern_list(column, like_pattern_list):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_not_match_like_pattern_list",
            kwargs={
                "column": f"{column}",
                "like_pattern_list ": f"{like_pattern_list}",
            },
        )
        return expectation_configuration


    @staticmethod
    def expect_column_values_to_match_strftime_format(column, strftime_format):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_strftime_format",
            kwargs={
                "column": f"{column}",
                "strftime_format ": f"{strftime_format}",
            },
        )
        return expectation_configuration


    @staticmethod
    def expect_column_values_to_be_dateutil_parseable(column):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_be_dateutil_parseable",
            kwargs={
                "column": f"{column}",
            },
        )
        return expectation_configuration


    @staticmethod
    def expect_column_values_to_match_json_schema(column,json_schema):
        expectation_configuration = ExpectationConfiguration(
            expectation_type="expect_column_values_to_match_json_schema",
            kwargs={
                "column": f"{column}",
                "json_schema":f"{json_schema}"
            },
        )
        return expectation_configuration