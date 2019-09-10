package main

import (
	"bufio"
	"database/sql"
	"os"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

// ConnectionInformation as type of required information Database connection
type ConnectionInformation struct {
	user     string
	pass     string
	host     string
	port     string
	database string
}

func (ele ConnectionInformation) toConnectionString() string {
	return ele.user + ":" + ele.pass + "@tcp(" + ele.host + ":" + ele.port + ")/" + ele.database
}

// Table as type of database table information
type Table struct {
	name string
	cols []string
}

const baseSrcFolder string = "src/main/kotlin/"
const packageName string = "freitech/se/ec/table/"

// データベースのカラム名をenumクラスにして吐き出すスクリプト
func main() {
	information := ConnectionInformation{"ec_user", "ec_user_01", "localhost", "3306", "ec_db"}
	db, err := sql.Open("mysql", information.toConnectionString())
	if err != nil {
		panic(err.Error())
	}
	defer db.Close()

	// テーブル名取得
	rows, err := db.Query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + information.database + "'")
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
		rows, err := db.Query(getColumnQuery(information.database, tableNames[i]))
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

	outPutClassFile(tables)
}

func getColumnQuery(schema string, table string) string {
	return "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + schema + "' AND TABLE_NAME = '" + table + "'"
}

func outPutConsole(tables []Table) {
	writer := bufio.NewWriter(os.Stdout)
	for i := 0; i < len(tables); i++ {
		table := tables[i]
		className := snakeToCamel(strings.ToUpper(table.name[:1]) + table.name[1:])
		writer.WriteString("enum class " + className + "Table (val name: String){\n")
		for j := 0; j < len(table.cols); j++ {
			col := table.cols[j]
			writer.WriteString("    " + snakeToCamel(col) + "(\"" + col + "\")\n")
		}
		writer.WriteString("}\n")
		writer.Flush()
	}
	/*
		const outPutPath string = ""
		for i := 0; i < len(tables); i++ {
			file, err := os.Open(outPutPath + "/" + tables[i].name + ".kt")
			if err != nil {
				panic(err.Error())
			}
			defer file.Close()
	*/

}

func outPutClassFile(tables []Table) {
	const outPutPath string = "../" + baseSrcFolder + packageName
	packageDef := strings.ReplaceAll(packageName[:len(packageName)-1], "/", ".")
	for t := 0; t < len(tables); t++ {
		table := tables[t]
		className := snakeToCamel(strings.ToUpper(table.name[:1]) + table.name[1:])
		file, err := os.Create(outPutPath + className + ".kt")
		if err != nil {
			panic(err.Error())
		}
		defer file.Close()

		writer := bufio.NewWriter(file)
		writer.WriteString("package " + packageDef + "\n")
		writer.WriteString("\n")
		writer.WriteString("enum class " + className + "Table (name: String){\n")
		for j := 0; j < len(table.cols); j++ {
			col := table.cols[j]
			if j == (len(table.cols) - 1) {
				writer.WriteString("    " + snakeToCamel(strings.ToUpper(col[0:1])+col[1:]) + "(\"" + strings.ToUpper(col) + "\")\n")
			} else {
				writer.WriteString("    " + snakeToCamel(strings.ToUpper(col[0:1])+col[1:]) + "(\"" + strings.ToUpper(col) + "\"),\n")
			}
		}
		writer.WriteString("}\n")
		writer.Flush()
	}
}

func snakeToCamel(str string) string {
	index := strings.Index(str, "_")
	if index < 0 {
		return str
	}
	prev := str[:index]
	post := strings.ToUpper(str[index+1:index+2]) + str[index+2:]
	return snakeToCamel(prev + post)
}
