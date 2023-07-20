from great_expectations.checkpoint import SimpleCheckpoint


class CheckPointConfig:

    @staticmethod
    def run_check_point_config(context,batch_request, suite_name):
        # checkpoints are used to run the tests on the data
        checkpoint_config=CheckPointConfig.get_check_point_config(batch_request, suite_name);
        checkpoint = SimpleCheckpoint(
            f"_tmp_checkpoint_test_suite",
            context,
            **checkpoint_config
        )
        checkpoint_result = checkpoint.run()

    @staticmethod
    def get_check_point_config(batch_request,suite_name):
        checkpoint_config = {
            "class_name": "SimpleCheckpoint",
            "validations": [
                {
                    "batch_request": batch_request,
                    "expectation_suite_name": f"{suite_name}"
                }
            ]
        }
        return checkpoint_config


# def run_check_point(batch_request,suite_name):
#     # checkpoints are used to run the tests on the data
#     checkpoint_config = {
#         "class_name": "SimpleCheckpoint",
#         "validations": [
#             {
#                 "batch_request": batch_request,
#                 "expectation_suite_name": f"{suite_name}"
#             }
#         ]
#     }
#     checkpoint = SimpleCheckpoint(
#         f"_tmp_checkpoint_test_suite",
#         context,
#         **checkpoint_config
#     )
#     checkpoint_result = checkpoint.run()