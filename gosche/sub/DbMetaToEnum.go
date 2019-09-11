package sub

import "database/sql"

// ConnectionInformation as type of required information Database connection
type ConnectionInformation struct {
	User     string
	Pass     string
	Host     string
	Port     string
	Database string
}

// Table as type of database table information
type Table struct {
	Name string
	Cols []string
}

func (ele ConnectionInformation) ToConnectionString() string {
	return ele.User + ":" + ele.Pass + "@tcp(" + ele.Host + ":" + ele.Port + ")/" + ele.Database
}

type TableDataHolder struct {
	Tables []Table
}

func ExtractTableData(setting ConnectionInformation) *TableDataHolder {
	db, err := sql.Open("mysql", setting.ToConnectionString())
	if err != nil {
		panic(err.Error())
	}
	defer db.Close()

	// テーブル名取得
	rows, err := db.Query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + setting.Database + "'")
	if err != nil {
		panic(err.Error())
	}

	var tableNames []string
	for rows.Next() {
		var tableName string
		err := rows.Scan(&tableName)
		if err != nil {
			panic(err.Error())
		}
		tableNames = append(tableNames, tableName)
	}

	if len(tableNames) < 1 {
		panic("処理するデータがありません")
	}

	var tables []Table

	for i := 0; i < len(tableNames); i++ {
		var columns []string
		rows, err := db.Query(getColumnQuery(setting.Database, tableNames[i]))
		if err != nil {
			panic(err.Error())
		}
		for rows.Next() {
			var col string
			err := rows.Scan(&col)
			if err != nil {
				panic(err.Error())
			}
			columns = append(columns, col)
		}
		tables = append(tables, Table{tableNames[i], columns})
	}
	instance := new(TableDataHolder)
	instance.Tables = tables
	return instance
}

func getColumnQuery(schema string, table string) string {
	return "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + schema + "' AND TABLE_NAME = '" + table + "'"
}
