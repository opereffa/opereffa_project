get_$field_name$_upper_index:INTEGER
require
	$field_name$_not_null: $field_name$ /= void
do
	Result := $field_name$.upper
end	