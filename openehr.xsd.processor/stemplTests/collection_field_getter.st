get_$field_name$(p_index:INTEGER):$field_type$
require
	$field_name$_not_null: $field_name$ /= void
do
	Result := $field_name$.at (p_index)
end