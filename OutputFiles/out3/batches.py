from great_expectations.core.batch import RuntimeBatchRequest

class Batch:
    #
    # schema_name : database
    # table_name : table
    # condition: condition
    #
    @staticmethod
    def get_batch(schema_name, table_name, condition):
        # A Batch Request needs to be provided to a Datasource in order to create a Batch of the data that we want to test.
        batch_request = RuntimeBatchRequest(
            datasource_name="my_mysql_datasource",
            data_connector_name="default_runtime_data_connector_name",
            data_asset_name=f"{schema_name}.{table_name}",
            runtime_parameters={"query": f"SELECT * from {schema_name}.{table_name} {condition}"},
            batch_identifiers={"default_identifier_name": "default_identifier"},
            batch_spec_passthrough={"create_temp_table": False}
        )
        return batch_request

