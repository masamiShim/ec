package sub

import "strings"

// enumのメンバ変数定義に必要な値
type MemberValue struct {
	EnumName   string
	ColumnName string
}

//
func NewMemberValue(col string) *MemberValue {
	instance := new(MemberValue)
	instance.EnumName = snakeToCamel(strings.ToUpper(col[0:1]) + col[1:])
	instance.ColumnName = strings.ToUpper(col)
	return instance
}

// 1クラス表示用に必要な値
type ClassData struct {
	ClassName string
	Members   []MemberValue
}

// テーブルの内容をクラス出力に必要なデータへ変換する
func NewClassData(table Table) *ClassData {
	instance := new(ClassData)
	instance.ClassName = snakeToCamel(strings.ToUpper(table.Name[:1]) + table.Name[1:])

	for i := 0; i < len(table.Cols); i++ {
		var value = NewMemberValue(table.Cols[i])
		instance.Members = append(instance.Members, *value)
	}
	return instance
}

// スネークケースをキャメルケースへ変換
func snakeToCamel(str string) string {
	index := strings.Index(str, "_")
	if index < 0 {
		return str
	}
	prev := str[:index]
	post := strings.ToUpper(str[index+1:index+2]) + str[index+2:]
	return snakeToCamel(prev + post)
}
