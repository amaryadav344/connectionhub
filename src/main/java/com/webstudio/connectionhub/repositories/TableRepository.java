package com.webstudio.connectionhub.repositories;

import com.webstudio.connectionhub.models.IColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TableRepository {
    @Autowired
    private EntityManager entityManager;

    public List<String> getAllTables(String query) {
        List<String> tables;
        if (Objects.equals(query, " ")) {
            tables = entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES").getResultList();
        } else {
            tables = entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE lower(TABLE_NAME) LIKE lower(?1)")
                    .setParameter(1, "%" + query + "%")
                    .getResultList();

        }

        return tables;
    }

    public boolean getIsTablePresent(String tableName) {
        List<String> tables = entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME =?1")
                .setParameter(1, tableName)
                .getResultList();

        return tables.size() == 0 ? false : true;
    }

    public List<IColumn> getColumns(String tableName) {
        List<Object[]> tables = entityManager.createNativeQuery(
                "SELECT COLUMN_NAME as name,DATA_TYPE as dataType,IS_NULLABLE as canBeNull," +
                        " CAST(COALESCE(CHARACTER_MAXIMUM_LENGTH, NUMERIC_PRECISION,'') AS VARCHAR)" +
                        " as maxLength," +
                        "COLUMN_NAME as objectField" +
                        " FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?1")
                .setParameter(1, tableName)
                .getResultList();
        List<IColumn> columns = new ArrayList<>();
        for (int i = 0; i < tables.size(); i++) {
            Object[] row = tables.get(i);
            IColumn iColumn = new IColumn(row[0].toString(), row[4].toString(), getDataTypeFromDBDataType(row[1].toString()), row[3].toString(), row[2].toString());
            columns.add(iColumn);
        }
        return columns;

    }

    public String getDataTypeFromDBDataType(String DBDataType) {
        String DataType = "String";
        switch (DBDataType.toLowerCase()) {
            case "char":
            case "varchar":
            case "text":
            case "nchar":
            case "nvarchar":
            case "ntext":
                DataType = "String";
                break;
            case "bigint":
            case "int":
            case "smallint":
            case "tinyint":
            case "bit":
            case "numeric":
                DataType = "Integer";
                break;
            case "float":
                DataType = "Float";
                break;
            case "date":
            case "datetime":
            case "datetime2":
            case "smalldatetime":
            case "time":
                DataType = "DateTime";
                break;
        }
        return DataType;
    }

}