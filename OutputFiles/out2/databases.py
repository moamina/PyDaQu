from enum import Enum

class DataBaseType(Enum):
    MySQL = "mysql+pymysql"
    Postgres = "postgresql+psycopg2"