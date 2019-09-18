package main

import (
	"./sub"
	"bufio"
	"os"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

const baseSrcFolder string = "src/main/kotlin/"

// ファイル出力に必要な値
type OutPutData struct {
	PackagePath string
	PackageName string
	Classes     []sub.ClassData
}

// ファイル出力に必要なデータを作成する
func NewOutputData(data []sub.Table) *OutPutData {
	instance := new(OutPutData)
	packagePath := "freitech/se/ec/gateway/db/table/"
	instance.PackagePath = packagePath
	instance.PackageName = strings.ReplaceAll(packagePath[:len(packagePath)-1], "/", ".")
	for i := 0; i < len(data); i++ {
		var value = sub.NewClassData(data[i])
		instance.Classes = append(instance.Classes, *value)
	}
	return instance
}

// データベースのカラム名をenumクラスにして吐き出すスクリプト
func main() {
	tableHolder := sub.ExtractTableData(sub.ConnectionInformation{User: "ec_user", Pass: "ec_user_01", Host: "localhost", Port: "3306", Database: "ec_db"})

	var outPutData = NewOutputData(tableHolder.Tables)

	outPutClassFile(*outPutData)
}

// Enumクラスの出力
func outPutClassFile(data OutPutData) {
	outPutPath := "../" + baseSrcFolder + data.PackagePath
	for t := 0; t < len(data.Classes); t++ {
		class := data.Classes[t]
		className := class.ClassName
		file, err := os.Create(outPutPath + className + ".kt")
		if err != nil {
			panic(err.Error())
		}

		writer := bufio.NewWriter(file)
		writer.WriteString("package " + data.PackageName + "\n")
		writer.WriteString("\n")
		writer.WriteString("enum class " + className + "Table (val colName: String){\n")

		for j := 0; j < len(class.Members); j++ {
			col := class.Members[j]
			if j == (len(class.Members) - 1) {
				writer.WriteString("    " + col.EnumName + "(\"" + col.ColumnName + "\")\n")
			} else {
				writer.WriteString("    " + col.EnumName + "(\"" + col.ColumnName + "\"),\n")
			}
		}
		writer.WriteString("}\n")
		writer.Flush()
		file.Close()
	}
}
