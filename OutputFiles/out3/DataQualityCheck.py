import great_expectations as ge

from Entities.batches import Batch
from Entities.checkpoints import CheckPointConfig
from Entities.datasources import DataSource
from Entities.expectationUtiles import ExpectationUtil
from Entities.expectationsuites import ExpectationSuite
from Entities.databases import DataBaseType


# Get Context
context = ge.get_context()

if __name__ == '__main__':

	# Data Source Code
    dataSource=DataSource("root","root","127.0.0.1","3306","testdb",DataBaseType.MySQL)
    context.add_datasource(**dataSource.getMySQLDataCourceConfig())

    condition =""
    list_of_expectation_configuration=[]
    
    ##########################################################################################
	# Table Name
    tableName='radacct'
#---------------------------------        
    # List of expectations for each column
    
	#Expectations For Column [radacctid]    	
    expectation_configuration = ExpectationUtil.expect_column_values_to_be_null("radacctid")
    list_of_expectation_configuration.append(expectation_configuration)

    
    # add list of expectation configuration to test suite
    suite = ExpectationSuite.create_expectation_suite(tableName,context ,list_of_expectation_configuration)
    context.save_expectation_suite(suite)
    list_of_expectation_configuration.clear()

    # getting batch requests for the tables
    batch_request = Batch.get_batch(dataSource.dataSchema, tableName, condition)

    # running tests on tables
    print(tableName, " ", batch_request)
    CheckPointConfig.run_check_point_config(context,batch_request, tableName)
