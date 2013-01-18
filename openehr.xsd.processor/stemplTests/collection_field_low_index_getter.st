get_$field_name$_lower_index:INTEGER
require
	$field_name$_not_null: $field_name$ /= void
do
	Result := $field_name$.lower
end